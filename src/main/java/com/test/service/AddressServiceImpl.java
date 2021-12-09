package com.test.service;

import com.test.exceptions.NotFoundException;
import com.test.model.Address;
import com.test.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Transactional
    @Override
    public void save(Address address) throws NotFoundException {
        addressRepository.save(address);
//        if(true){
//            throw new NotFoundException("Not Found");
//        }
    }

    @Override
    public void deleteById(int id) {
        addressRepository.deleteById(id);
    }

    @Override
    public void updateCity(int id, String city) {
        addressRepository.setAddressInfoById(id, city);
    }

    @Override
    public Address getById(int id) throws NotFoundException {
        return addressRepository.getById(id);
    }

    @Override
    public Address getByCity(String city) {
        return addressRepository.getByCity(city);
    }

    @Override
    public List<Address> getAllByCity(String city) {
        return addressRepository.getAllByCity(city);
    }
}
