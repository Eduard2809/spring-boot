package com.test.service;

import com.test.exceptions.NotFoundException;
import com.test.model.Phone;
import com.test.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService{

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public List<Phone> getAll() {
        return phoneRepository.findAll();
    }

    @Override
    public void save(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    public void deleteById(int id) {
        phoneRepository.deleteById(id);
    }

    @Override
    public void updateModel(int id, String model) {
        phoneRepository.setPhoneInfoById(id, model);
    }

    @Override
    public Phone getById(int id) throws NotFoundException {
        return phoneRepository.getById(id);
    }

    @Override
    public Phone getByModel(String model) {
        return phoneRepository.getByModel(model);
    }

    @Override
    public List<Phone> getAllByModel(String model) {
        return phoneRepository.getAllByModel(model);
    }
}
