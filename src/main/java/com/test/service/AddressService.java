package com.test.service;

import com.test.exceptions.NotFoundException;
import com.test.model.Address;
import com.test.model.Phone;

import javax.transaction.Transactional;
import java.util.List;

public interface AddressService {
    List<Address> getAll();
    void save(Address address) throws NotFoundException;
    void deleteById(int id);
    void updateCity(int id, String city);
    Address getById(int id) throws NotFoundException;
    Address getByCity(String city);
    List<Address> getAllByCity(String city);
}
