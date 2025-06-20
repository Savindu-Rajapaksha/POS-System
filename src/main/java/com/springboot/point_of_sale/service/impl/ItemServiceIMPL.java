package com.springboot.point_of_sale.service.impl;

import com.springboot.point_of_sale.dto.request.ItemSaveRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemGetResponseDTO;
import com.springboot.point_of_sale.entity.Item;
import com.springboot.point_of_sale.exceptions.NotFoundException;
import com.springboot.point_of_sale.repo.ItemRepo;
import com.springboot.point_of_sale.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private ItemRepo itemRepo;

    @Override
    public String itemSave(ItemSaveRequestDTO itemSaveRequestDTO) {
        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
        item.setActiveState(true);

        itemRepo.save(item);
        return "Item Saved";
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName, true);

        if(items.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(items, new TypeToken<List<ItemGetResponseDTO>>(){}.getType());
            return itemGetResponseDTOS;
        }else {
            throw new NoSuchElementException("No item found with name " + itemName);
        }
    }
    
    @Override
    public List<ItemGetResponseDTO> getAllItems() {
        List<Item> items = itemRepo.findAll();
        if (items.isEmpty()) {
            throw new NotFoundException("No items found");
        }
        return modelMapper.map(items, new TypeToken<List<ItemGetResponseDTO>>(){}.getType());
    }
    
    @Override
    public ItemGetResponseDTO getItemById(int id) {
        Optional<Item> item = itemRepo.findById(id);
        if (item.isPresent()) {
            return modelMapper.map(item.get(), ItemGetResponseDTO.class);
        } else {
            throw new NotFoundException("Item not found with ID: " + id);
        }
    }
    
    @Override
    public String updateItem(int id, ItemSaveRequestDTO itemSaveRequestDTO) {
        if (itemRepo.existsById(id)) {
            Item existingItem = itemRepo.findById(id).get();
            
            // Update properties
            existingItem.setItemName(itemSaveRequestDTO.getItemName());
            existingItem.setMeasuringUnitType(itemSaveRequestDTO.getMeasuringUnitType());
            existingItem.setBalanceQty(itemSaveRequestDTO.getBalanceQty());
            existingItem.setSupplierPrice(itemSaveRequestDTO.getSupplierPrice());
            existingItem.setSellingPrice(itemSaveRequestDTO.getSellingPrice());
            
            itemRepo.save(existingItem);
            return "Item updated successfully";
        } else {
            throw new NotFoundException("Item not found with ID: " + id);
        }
    }
    
    @Override
    public String deleteItem(int id) {
        if (itemRepo.existsById(id)) {
            // Soft delete by setting active state to false
            Item item = itemRepo.findById(id).get();
            item.setActiveState(false);
            itemRepo.save(item);
            return "Item deleted successfully";
        } else {
            throw new NotFoundException("Item not found with ID: " + id);
        }
    }
    
    @Override
    public List<ItemGetResponseDTO> getItemsByActiveStatus(boolean status) {
        List<Item> items = itemRepo.findAllByActiveStateEquals(status);
        if (items.isEmpty()) {
            throw new NotFoundException("No items found with active status: " + status);
        }
        return modelMapper.map(items, new TypeToken<List<ItemGetResponseDTO>>(){}.getType());
    }
    
    @Override
    public String updateItemQuantity(int id, double quantity) {
        if (itemRepo.existsById(id)) {
            Item item = itemRepo.findById(id).get();
            item.setBalanceQty(quantity);
            itemRepo.save(item);
            return "Item quantity updated successfully";
        } else {
            throw new NotFoundException("Item not found with ID: " + id);
        }
    }
}
