package com.izumi.util;


import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private int currentPage;
    private int pageSize;
    private int totalCount;
    private List<T> data;

    private int totalPage;
    private int prevPage;
    private int nextPage;

    public PageResult(int currentPage, int pageSize, int totalCount, List<T> data) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;

        this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount /this.pageSize + 1;

        this.prevPage = this.currentPage -1 >= 1 ? this.currentPage -1 : 1;

        this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1 : this.totalPage;
    }
}
