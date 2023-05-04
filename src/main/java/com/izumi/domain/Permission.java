package com.izumi.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class Permission {
    private Long id;
    private String name;
    private String expression;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}
