package com.test.repository;

import com.test.model.Address;
import com.test.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    Address getById(int id);
    Address getByCity(String city);
    List<Address> getAllByCity(String city);

    @Modifying
    @Query("update Address a set a.city = ?2 where a.id = ?1")
    void setAddressInfoById(int id, String city);
}
