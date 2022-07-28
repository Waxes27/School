package com.waxes27.School.Models;


import com.waxes27.School.Enums.UserRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Teacher extends User implements UserDetails {
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
    private final UserRoles userRole = UserRoles.TEACHER;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Student> students = new LinkedHashSet<>();

    public Teacher(String name, String surname, String username, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void addStudent(Student student){
        this.students.add(student);
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
        jsonObject.put("name",name+" "+surname);
        jsonObject.put("userRole",userRole);
        return jsonObject.toString();
    }
}
