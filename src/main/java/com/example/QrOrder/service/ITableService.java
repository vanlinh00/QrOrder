package com.example.QrOrder.service;

import com.example.QrOrder.models.Tables;

import java.util.List;
import java.util.Optional;

public interface ITableService {
    List<Tables> getAllTables();

    Optional<Tables> getTableById(Long id) throws Exception;

    Tables updateTable(Long id, Tables tableDetails);

    void deleteTable(Long id);

    String generateQrCodeForTable(Long tableId);
}
