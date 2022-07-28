package com.waxes27.School.Controllers.auth;

import com.waxes27.School.Enums.UserRoles;
import com.waxes27.School.Models.Student;
import com.waxes27.School.Models.Teacher;
import com.waxes27.School.Models.User;
import com.waxes27.School.Services.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleInfoNotFoundException;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;
    String response;


    @GetMapping
    public String getUser(){
        return "";
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return userService.confirmToken(token);
    }

    @PostMapping
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") String role,
            @RequestParam("name") String name,
            @RequestParam("teacherName") String teacherName,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email
    ) throws RoleInfoNotFoundException {




        try{
            switch (UserRoles.valueOf(role.toUpperCase(Locale.ROOT))){
                case STUDENT:
                    Student user = new Student(
                            name,
                            surname,
                            username,
                            email,
                            password
                    );
                    response = userService.registerStudent(user, teacherName);
                    break;
                case TEACHER:
                    Teacher teacher = new Teacher(
                            name,
                            surname,
                            username,
                            email,
                            password
                    );
                    response = userService.registerTeacher(teacher);
                    break;
                default:
                    response = "";
                    throw new RoleInfoNotFoundException();
            }
        }catch (IllegalArgumentException e){
            response = e.getLocalizedMessage();
        }

        return response;
    }
}
