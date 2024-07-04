package com.armagetdon.server.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PagingResponse<T> {
    private T data;
    private int page;
    private int size;
    private int totalPage;
    private long totalElements;
}
