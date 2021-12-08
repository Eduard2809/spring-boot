package com.test.service;

import com.test.model.Authorities;

import java.util.List;

public interface AuthoritiesService {
    List<Authorities> getAll();
    Authorities getByName(String name);
}
