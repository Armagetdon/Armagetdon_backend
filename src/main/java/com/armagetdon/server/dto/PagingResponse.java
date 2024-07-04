package com.armagetdon.server.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PagingResponse<T> {
    private List<T> data;
    private int page;
    private int totalPages;
    private int totalElements;
    private Boolean isFirst;
    private Boolean isLast;
}