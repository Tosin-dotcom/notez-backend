package com.tosin.notez.model;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedResponse<T> {

    private List<T> records;
    private long total;
    private int size;
    private int page;

}
