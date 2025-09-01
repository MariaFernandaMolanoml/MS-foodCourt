package com.example.foodcourt.application.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PagedOrderResponse {
    private List<OrderResponse> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
