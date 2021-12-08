package com.test.controller;

import com.test.exceptions.NotFoundException;
import com.test.model.Phone;
import com.test.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/phone")
@RestController
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping
    public List<Phone> getAll(){
        return phoneService.getAll();
    }

    @GetMapping("{id}")
    public Phone getById(@PathVariable int id) throws NotFoundException {
        return phoneService.getById(id);
    }

    @RequestMapping("/get-by-model")
    @GetMapping
    public Phone getByModel(@RequestParam("model") String model){
        return phoneService.getByModel(model);
    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id) {
        phoneService.deleteById(id);
    }

    @PutMapping
    public void updateModel(@RequestParam("id") int id,@RequestParam String model){
        phoneService.updateModel(id, model);
    }
    @PostMapping
    public void save(@RequestBody Phone phone){
        phoneService.save(phone);
    }
}
