package com.springboot.point_of_sale.service.impl;

import com.springboot.point_of_sale.dto.request.ItemSaveRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemGetResponseDTO;
import com.springboot.point_of_sale.entity.Item;
import com.springboot.point_of_sale.repo.ItemRepo;
import com.springboot.point_of_sale.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(items, List.class);
            return itemGetResponseDTOS;
        }else {
            throw new NoSuchElementException("No item found with name " + itemName);
        }
    }
}
