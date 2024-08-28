package com.example.QrOrder.service;

import com.example.QrOrder.dtos.MenuItemDTO;
import com.example.QrOrder.exceptions.ResourceNotFoundException;
import com.example.QrOrder.models.MenuItem;
import com.example.QrOrder.repository.MenuItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuItemService implements IMenuItemService {
    private MenuItemRepository menuItemRepository;

    @Override
    public MenuItem addMenuItem(MenuItemDTO menuItemDTO) throws Exception {
        MenuItem menuItem = MenuItem.builder()
                .name(menuItemDTO.getName())
                .price(menuItemDTO.getPrice())
                .description(menuItemDTO.getDescription())
                .imageUrl(menuItemDTO.getImageUrl())
                .build();
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem getMenuItemById(Long id) throws Exception {
        Optional<MenuItem> menuItemOp = menuItemRepository.findById(id);
        if (menuItemOp.isEmpty()) {
            throw new ResourceNotFoundException("don't exists this MenuItem");
        }
        MenuItem menuItem = menuItemOp.get();
        return menuItem;
    }

    @Override
    public List<MenuItem> getAllMenuItems() throws Exception {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem menuItem) throws Exception {
        menuItem.setId(id);
        return menuItemRepository.save(menuItem);
    }

    @Override
    public void deleteMenuItem(Long id) throws Exception {
        menuItemRepository.deleteById(id);
    }
}
