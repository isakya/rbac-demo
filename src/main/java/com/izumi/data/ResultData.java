package com.izumi.data;

import com.izumi.domain.Department;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentData {
    private Integer pageNum;
    private Integer pageSize;
    private List<Department> list;
    private Integer total;

    public DepartmentData(Integer pageNum, Integer pageSize, List<Department> list, Integer total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.list = list;
        this.total = total;
    }
}
