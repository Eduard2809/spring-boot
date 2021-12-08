package com.test.repository;

import com.test.model.Authorities;
import com.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities,Integer> {
    Authorities getByName(String name);

}
