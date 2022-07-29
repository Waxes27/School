package com.waxes27.School.Services;

import com.waxes27.School.Controllers.auth.ConfirmationToken.ConfirmationToken;
import com.waxes27.School.Models.*;
import com.waxes27.School.Repositories.PrincipalRepository;
import com.waxes27.School.Repositories.SchoolRepository;
import com.waxes27.School.Repositories.TeacherRepository;
import com.waxes27.School.Repositories.UserRepository;
import com.waxes27.School.Repositories.auth.ConfirmationTokenRepository;
import com.waxes27.School.Security.PasswordEncoder;
import com.waxes27.School.Services.auth.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PrincipalRepository principalRepository;
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ConfirmationTokenService confirmationTokenService;
    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(userRepository.findByUsername(username));
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token);

        if (confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("Email already confirm");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token Expired");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        this.enableUser(confirmationToken.getUser().getEmail());
        return "Confirmed";
    }

    public String registerStudent(Student user, String teacherName){
        ConfirmationToken confirmationToken = createConfirmationToken(user);
        System.out.println("Looking for teacher...");
        Optional<Teacher> teacherOptional = teacherRepository.findByName(teacherName);
        if (teacherOptional.isPresent()){
            user.setTeacherName(teacherName);
            user.setTeacher(teacherOptional.get());
        }
        else {
            throw new UsernameNotFoundException("No teacher by that name here");
        }
        System.out.println("Done");


        System.out.println(userRepository.save(user));
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return user.toString();
    }

    public void enableUser(String email){
        Optional<Student> user = userRepository.findByEmail(email);

        if (user.isEmpty()){
            throw new IllegalStateException("User not found!");
        }else {
            user.get().setEnabled(true);
        }
    }


    public ConfirmationToken createConfirmationToken(Student user){
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()));


        return new ConfirmationToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
    }

    public String registerTeacher(Teacher user) {
        user.setSchool(
                schoolRepository.findByName(user.getSchoolName()).get()
        );

        System.out.println(teacherRepository.save(user));
        return user.toString();
    }

    public String registerSchool(School school){
        Principal principal = principalRepository.save(
                new Principal(
                        "p",
                        "p",
                        "p",
                        "p",
                        "p"
                ));
        school.setPrincipal(principal);
        ;

        return schoolRepository.save(school).toString();
    }
}
