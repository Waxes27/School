package com.waxes27.School.Models;


import com.waxes27.School.Enums.UserRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Principal extends User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String surname;
    private String idNumber;
    private String username;
    private String email;
    private String password;
    private final UserRoles userRole = UserRoles.PRINCIPAL;

    @OneToOne(mappedBy = "principal", cascade = CascadeType.ALL, orphanRemoval = true)
    private School school;


    public Principal(String name, String surname, String username, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.userRole.name());
        return Collections.singleton(authority);
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",username);
        jsonObject.put("email",email);
        jsonObject.put("name",name);
        jsonObject.put("userRole",userRole);
        return jsonObject.toString();
    }

}
