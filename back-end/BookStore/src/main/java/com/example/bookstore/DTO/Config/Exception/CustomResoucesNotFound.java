package com.example.bookstore.DTO.Config.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomResoucesNotFound extends RuntimeException {
    public CustomResoucesNotFound(String err) {
        super(err);
    }
}
