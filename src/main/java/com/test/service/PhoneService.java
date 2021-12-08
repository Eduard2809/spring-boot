package com.test.service;

import com.test.exceptions.NotFoundException;
import com.test.model.Phone;
import com.test.model.User;

import java.util.List;

public interface PhoneService {
    List<Phone> getAll();
    void save(Phone phone);
    void deleteById(int id);
    void updateModel(int id, String model);
    Phone getById(int id) throws NotFoundException;
    Phone getByModel(String model);
    List<Phone> getAllByModel(String model);
}
