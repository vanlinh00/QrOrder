package com.example.QrOrder.controller;

import com.example.QrOrder.dtos.MenuItemDTO;
import com.example.QrOrder.models.MenuItem;
import com.example.QrOrder.service.MenuItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/api/menu")
@AllArgsConstructor
public class MenuController {

    private MenuItemService menuService;

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() throws Exception {
        return ResponseEntity.ok(menuService.getAllMenuItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(menuService.getMenuItemById(id));
    }

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(
            @RequestBody MenuItemDTO menuItemDTO) throws Exception {
        return ResponseEntity.ok(menuService.addMenuItem(menuItemDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id,
                                                   @RequestBody MenuItem menuItem) throws Exception {
        return ResponseEntity.ok(menuService.updateMenuItem(id, menuItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Long id) throws Exception {
        menuService.deleteMenuItem(id);
        return ResponseEntity.ok("ok");
    }

}
