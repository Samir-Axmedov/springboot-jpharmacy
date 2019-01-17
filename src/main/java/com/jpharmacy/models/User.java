package com.jpharmacy.models;

import com.jpharmacy.validators.annotations.UniqueEmail;
import com.jpharmacy.validators.annotations.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 4, max = 36)
    @UniqueUsername
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;
    @Size(min=2, max=25)
    private String userFirstName;
    @Size(min=3, max=40)
    private String userLastName;
    //@InvalidPhone
    private String userPhone;
    @UniqueEmail
    private String userEmail;
    private boolean enabled = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private UserAddress userAddress;

    @AssertTrue(message = "Podane hasła nie są takie same.")
    private boolean isPasswordsEquals(){
        return password == null || passwordConfirm == null || password.equals(passwordConfirm);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public User(String username){
        this(username, false);
    }

    public User(String username, boolean enabled){
        this.username = username;
        this.enabled = enabled;
    }

}