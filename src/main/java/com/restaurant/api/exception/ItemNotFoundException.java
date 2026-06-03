package com.restaurant.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends  RuntimeException {
    private final Object id;

    public ItemNotFoundException(Class<?> entityClass, Object id) {
        super(String.format("%s with ID: %s not found", entityClass.getSimpleName(), id));
        this.id = id;
    }
}
