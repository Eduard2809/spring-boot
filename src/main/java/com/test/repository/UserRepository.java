package com.test.repository;

import com.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query("update User u set u.name = ?2 where u.id = ?1")
    void setUserInfoById(int id, String name);

    @Query(value = "select u FROM User u where u.email =:email")
    User getByEmail(String email);

    List<User> getAllByName(String name);

    User getByEmailAndPassword(String email, String password);

    User getByResetPasswordToken(String resetPasswordToken);

}
