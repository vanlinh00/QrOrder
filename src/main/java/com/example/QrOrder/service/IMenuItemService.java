package com.example.QrOrder.service;

import com.example.QrOrder.dtos.MenuItemDTO;
import com.example.QrOrder.models.MenuItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMenuItemService {
    MenuItem addMenuItem(MenuItemDTO menuItemDTO) throws Exception;

    MenuItem getMenuItemById(Long id) throws Exception;

    List<MenuItem> getAllMenuItems() throws Exception;

    MenuItem updateMenuItem(Long id, MenuItem menuItem) throws Exception;

    void deleteMenuItem(Long id) throws Exception;
}
