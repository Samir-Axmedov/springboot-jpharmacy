package com.jpharmacy.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@NamedQuery(name = "TheOrder.findAllOrdersUsingNamedQuery",
        query = "SELECT v FROM TheOrder v WHERE upper(v.item_name) LIKE upper(:phrase) or upper(v.category.categoryName) LIKE upper(:phrase)")

@Getter
@Setter
public class TheOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    private String user_name;

    private String item_name;
    private float item_price;
    private String item_description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    @Column(name="created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;


    public TheOrder() {
        this.createdDate = new Date();
        this.category = new Category();
    }

    public TheOrder(User user, String item_name, float item_price, String item_description, Category category, Date createdDate) {
        this.user = user;
        this.user_name = user.getUsername();
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_description = item_description;
        this.category = category;
        this.createdDate = createdDate;
    }
}