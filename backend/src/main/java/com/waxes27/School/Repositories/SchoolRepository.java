package com.waxes27.School.Repositories;

import com.waxes27.School.Models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long>{

    Optional<School> findByName(String name);



}