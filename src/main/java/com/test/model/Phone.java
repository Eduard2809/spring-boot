package com.test.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Phone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;

    @JsonBackReference
    @OneToOne(mappedBy = "phone")
    private User user;

    public Phone(){}

    public Phone(String model) {
        this.model = model;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return id == phone.id && Objects.equals(model, phone.model) && Objects.equals(user, phone.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, user);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", user=" + user +
                '}';
    }
}
