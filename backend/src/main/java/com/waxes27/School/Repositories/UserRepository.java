package com.waxes27.School.Repositories;


import com.waxes27.School.Models.Student;
import com.waxes27.School.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByEmail(String email);

    Optional<Student> findByUsername(String username);
}
