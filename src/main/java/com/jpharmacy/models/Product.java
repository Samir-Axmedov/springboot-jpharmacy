package com.jpharmacy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name = "products")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
    @Length(min = 3, message = "Nazwa produktu musi mieć minimum 3 znaki!")
    // @InvalidValues(ignoreCase = true, values = {"XXX", "YYY"})
    private String productName;

    private String productDescription;

    private String productManufacturer;

    @Positive
    @Min(0)
    @Max(1000000)
    private Float productPrice;

    @Temporal(TemporalType.DATE)
    @Column(name="production_date", nullable = false)
    private Date productionDate;

    @Temporal(TemporalType.DATE)
    @Column(name="expiration_date", nullable = false)
    private Date expirationDate;

    @Min(value = 0, message = "Ilosc nie moze być mniejsza niż zero")
    private Integer unitInStock;

    @Transient
    private MultipartFile productImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;


}
