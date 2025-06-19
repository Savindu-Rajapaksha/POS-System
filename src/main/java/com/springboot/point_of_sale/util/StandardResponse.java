package com.springboot.point_of_sale.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StandardResponse {
    private int code;
    private String message;
    private Object data;

    public StandardResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
