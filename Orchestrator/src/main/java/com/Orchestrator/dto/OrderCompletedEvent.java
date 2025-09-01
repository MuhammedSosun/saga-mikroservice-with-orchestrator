package com.Orchestrator.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCompletedEvent {

    private Long orderId;
}
