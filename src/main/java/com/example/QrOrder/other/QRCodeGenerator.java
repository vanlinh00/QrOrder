package com.example.QrOrder.other;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;

import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class QRCodeGenerator {

    public static String generateQRCodeImage(String text, int width, int height)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        // Tạo ByteArrayOutputStream để lưu trữ hình ảnh mã QR dưới dạng byte
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

            // Chuyển đổi byte array thành Base64 string
            byte[] qrCodeBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(qrCodeBytes);
        }
    }
}
