package com.MicroServiceProjectWithKafka.Order.model;

import lombok.Getter;
import lombok.Setter;

@Getter

public enum OrderStatus {
    CREATED,
    COMPLETED,
    CANCELLED,

}
