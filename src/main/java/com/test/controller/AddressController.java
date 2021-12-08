package com.test.controller;

import com.test.exceptions.NotFoundException;
import com.test.model.Address;
import com.test.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/address")
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAll(){
        return addressService.getAll();
    }

    @GetMapping("{id}")
    public Address getById(@PathVariable int id) throws NotFoundException {
        return addressService.getById(id);
    }

    @RequestMapping("/get-by-city")
    @GetMapping
    public Address getByCity(@RequestParam("city") String city){
        return addressService.getByCity(city);
    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id) {
        addressService.deleteById(id);
    }


    @PutMapping
    public void updateCity(@RequestParam("id") int id,@RequestParam String city){
        addressService.updateCity(id, city);
    }
    @PostMapping
    public void save(@RequestBody Address address) throws NotFoundException {
        addressService.save(address);
    }
}