package com.example.QrOrder.service;
import com.example.QrOrder.models.MenuItem;
import com.example.QrOrder.repository.MenuItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuItemService implements IMenuItemService {
    private MenuItemRepository menuItemRepository;
    @Override
    public MenuItem addMenuItem(MenuItem menuItem) throws Exception {
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem getMenuItemById(Long id) throws Exception {
        Optional<MenuItem> menuItemOp = menuItemRepository.findById(id);
        if (menuItemOp.isEmpty()) {
            throw new DataIntegrityViolationException("don't exists this user");
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
