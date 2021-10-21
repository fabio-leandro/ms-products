package com.fabio.productcontrolapi.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception {


    private String message;

    public ProductNotFoundException(String message){
        this.message = message;
    }


}
