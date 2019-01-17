package com.jpharmacy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Lek nie istnieje")
public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(){
        super(String.format("Lek nie istnieje"));
    }

    public ProductNotFoundException(Long id){
        super(String.format("Lek o id %d nie istnieje", id));
    }
}
