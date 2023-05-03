package com.izumi.data;

import lombok.Data;

import java.util.List;

@Data
public class ResultData {
    private Integer pageNum;
    private Integer pageSize;
    private List list;
    private Integer total;

    public ResultData(Integer pageNum, Integer pageSize, List list, Integer total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.list = list;
        this.total = total;
    }
}
