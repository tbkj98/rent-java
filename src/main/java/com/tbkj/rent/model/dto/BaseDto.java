package com.tbkj.rent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseDto<T> {
    private T data;
    private String message;
}
