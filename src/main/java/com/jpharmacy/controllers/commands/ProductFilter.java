package com.jpharmacy.controllers.commands;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.PositiveOrZero;

@Getter @Setter
public class ProductFilter {

    private String phrase;
    private Long category;

    @PositiveOrZero
    private Float minPrice;

    @PositiveOrZero
    private Float maxPrice;


    public boolean isEmpty(){
        return StringUtils.isEmpty(phrase) && minPrice == null && category == null && minPrice == null;
    }

    public void clear(){
        this.phrase = "";
        this.category = null;
        this.minPrice = null;
        this.maxPrice = null;
    }

    public String getPhraseLIKE(){
        if(StringUtils.isEmpty(phrase)) {
            return null;
        }else{
            return "%"+phrase+"%";
        }
    }


}
