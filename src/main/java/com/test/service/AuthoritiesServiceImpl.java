package com.test.service;

import com.test.model.Authorities;
import com.test.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public List<Authorities> getAll() {
        return authoritiesRepository.findAll();
    }

    @Override
    public Authorities getByName(String name) {
        return authoritiesRepository.getByName(name);
    }
}
