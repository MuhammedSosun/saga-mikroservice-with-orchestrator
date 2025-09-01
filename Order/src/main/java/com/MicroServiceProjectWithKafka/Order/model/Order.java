package com.MicroServiceProjectWithKafka.Order.model;

import com.MicroServiceProjectWithKafka.Order.dto.ItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal price;

    private LocalDateTime orderDate;

    @ElementCollection
    @CollectionTable(name = "items", joinColumns = @JoinColumn(name = "order_id"))
    private List<ItemDto> items;

    @PrePersist
    private void prePersist() {
        this.orderDate = LocalDateTime.now();
    }
}
