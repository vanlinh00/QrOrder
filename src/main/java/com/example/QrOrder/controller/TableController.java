package com.example.QrOrder.controller;

import com.example.QrOrder.models.Tables;
import com.example.QrOrder.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/api/qr-code")
@AllArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping("/tables")
    public ResponseEntity<List<Tables>> getAllTables() {
        return ResponseEntity.ok(tableService.getAllTables());
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<Tables> getTableById(@PathVariable Long id) throws Exception {
        return tableService.getTableById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/generate/{tableId}")
    public ResponseEntity<String> generateQrCodeForTable(@PathVariable Long tableId) {
        try {
            String qrCode = tableService.generateQrCodeForTable(tableId);
            return ResponseEntity.ok(qrCode);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/tables/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        try {
            tableService.deleteTable(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
