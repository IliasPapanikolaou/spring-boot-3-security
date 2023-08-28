package com.ipap.springboot3security.dto;

import lombok.Builder;

@Builder
public record Product(int id, String name, int qty, double price) {
}
