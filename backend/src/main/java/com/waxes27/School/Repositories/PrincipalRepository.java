package com.waxes27.School.Repositories;

import com.waxes27.School.Models.Principal;
import com.waxes27.School.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrincipalRepository extends JpaRepository<Principal, Long>{

    Optional<Principal> findByName(String name);


}