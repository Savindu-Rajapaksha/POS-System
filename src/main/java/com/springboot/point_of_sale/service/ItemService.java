package com.springboot.point_of_sale.service;

import com.springboot.point_of_sale.dto.request.ItemSaveRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemGetResponseDTO;

import java.util.List;


public interface ItemService {
    String itemSave(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);
}
