package com.example.QrOrder.service;

import com.example.QrOrder.exceptions.ResourceNotFoundException;
import com.example.QrOrder.models.MenuItem;
import com.example.QrOrder.other.QRCodeGenerator;
import com.example.QrOrder.exceptions.DataAlreadyExistsException;
import com.example.QrOrder.models.Tables;
import com.example.QrOrder.repository.TableRepository;

import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TableService implements ITableService {
    private final TableRepository tableRepository;

    @Override
    public List<Tables> getAllTables() {
        return tableRepository.findAll();
    }

    @Override
    public Optional<Tables> getTableById(Long id) throws Exception  {
        Optional<Tables> tablesOptional = tableRepository.findById(id);
        if (tablesOptional.isEmpty()) {
            throw new ResourceNotFoundException("Don't exits tables");
        }
       // Tables tables = tablesOptional.get();
        return tablesOptional;
    }

    @Override
    public Tables updateTable(Long id, Tables tableDetails) {
        Tables table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found with id " + id));
        table.setQrCode(tableDetails.getQrCode());
        return tableRepository.save(table);
    }

    @Override
    public void deleteTable(Long id) {
        tableRepository.deleteById(id);
    }

    @Override
    public String generateQrCodeForTable(Long tableId) {
        Optional<Tables> tableOptional = tableRepository.findById(tableId);
        if (tableOptional.isPresent()) {
            throw new DataAlreadyExistsException("Table is exits");
        }
        String qrCodeBase64 = "";
        String qrCodeUrl = "https://yourdomain.com/table/" + tableId; //
        try {
            qrCodeBase64 = QRCodeGenerator.generateQRCodeImage(qrCodeUrl, 200, 200);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Failed to generate QR code", e);
        }
        Tables table = Tables.builder()
                .qrCode(qrCodeBase64)
                .build();
        tableRepository.save(table);
        return qrCodeUrl;
    }

}
