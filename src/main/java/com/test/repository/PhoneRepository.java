package com.test.repository;

import com.test.model.Phone;
import com.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PhoneRepository extends JpaRepository<Phone,Integer> {
    Phone getById(int id);
    Phone getByModel(String model);
    List<Phone> getAllByModel(String model);

    @Modifying
    @Query("update Phone p set p.model = ?2 where p.id = ?1")
    void setPhoneInfoById(int id, String model);
}
