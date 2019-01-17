package com.jpharmacy.models;

import com.jpharmacy.validators.annotations.UniqueCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull
    @NotBlank(message = "Pole nie może być puste! ")
    @UniqueCategory
    private String categoryName;

    public Category( String categoryName) {
        this.categoryName = categoryName;
    }
}
