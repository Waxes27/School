package com.waxes27.School.Repositories;

import com.waxes27.School.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

    Optional<Teacher> findByName(String name);

    Optional<Teacher> findByStudents_Username(String username);


}