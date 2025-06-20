package com.springboot.point_of_sale.controller;

import com.springboot.point_of_sale.dto.CustomerDTO;
import com.springboot.point_of_sale.dto.request.ItemSaveRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemGetResponseDTO;
import com.springboot.point_of_sale.service.CustomerService;
import com.springboot.point_of_sale.service.ItemService;
import com.springboot.point_of_sale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/item-save")
    public ResponseEntity<StandardResponse> itemSave(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        itemService.itemSave(itemSaveRequestDTO);
        String message = "Item Saved " + itemSaveRequestDTO.getItemName();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Updated", message),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/get-item-name")
    public ResponseEntity<StandardResponse> getItemByNameAndStatus(@RequestParam String itemName) {
        List<ItemGetResponseDTO> itemGetResponseDTO = itemService.getItemByNameAndStatus(itemName);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Updated", itemGetResponseDTO),
                HttpStatus.OK
        );
    }
    
    @GetMapping(path = "/get-all-items")
    public ResponseEntity<StandardResponse> getAllItems() {
        List<ItemGetResponseDTO> items = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", items),
                HttpStatus.OK
        );
    }
    
    @GetMapping(path = "/get-by-id/{id}")
    public ResponseEntity<StandardResponse> getItemById(@PathVariable int id) {
        ItemGetResponseDTO item = itemService.getItemById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", item),
                HttpStatus.OK
        );
    }
    
    @PutMapping(path = "/update-item/{id}")
    public ResponseEntity<StandardResponse> updateItem(
            @PathVariable int id,
            @RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String message = itemService.updateItem(id, itemSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", message),
                HttpStatus.OK
        );
    }
    
    @DeleteMapping(path = "/delete-item/{id}")
    public ResponseEntity<StandardResponse> deleteItem(@PathVariable int id) {
        String message = itemService.deleteItem(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", message),
                HttpStatus.OK
        );
    }
    
    @GetMapping(path = "/get-by-status/{status}")
    public ResponseEntity<StandardResponse> getItemsByActiveStatus(@PathVariable boolean status) {
        List<ItemGetResponseDTO> items = itemService.getItemsByActiveStatus(status);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", items),
                HttpStatus.OK
        );
    }
    
    @PutMapping(path = "/update-qty/{id}")
    public ResponseEntity<StandardResponse> updateItemQuantity(
            @PathVariable int id,
            @RequestParam double quantity) {
        String message = itemService.updateItemQuantity(id, quantity);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", message),
                HttpStatus.OK
        );
    }
}
