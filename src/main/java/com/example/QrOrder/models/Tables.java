package com.example.QrOrder.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tables{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qr_code", nullable = false)
    private String qrCode;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime();
        updatedAt = Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime();
    }

//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//    }


    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}