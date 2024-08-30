
package com.example.QrOrder.models;

import com.example.QrOrder.dtos.StatusDTO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_id", nullable = false)
    private Long tableId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public enum Status {
        RECEIVED("Đã nhận"),
        PREPARING("Đang chuẩn bị"),
        COMPLETED("Hoàn thành");

        @Getter
        private final String displayName;

        Status(String displayName) {
            this.displayName = displayName;
        }
    }

    // Phương thức để chuyển đổi từ StatusDTO sang Status
    public static Status fromDTO(StatusDTO statusDTO) {
        if (statusDTO == null || statusDTO.getStatus() == null) {
            throw new IllegalArgumentException("StatusDTO or its status cannot be null");
        }
        for (Status status : Status.values()) {
            if (status.name().equalsIgnoreCase(statusDTO.getStatus())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + statusDTO.getStatus());
    }
}
