package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;

import javax.persistence.*;
import java.util.List;

@NamedQuery(name = "Customer.findAll",
        query = "select c from Customer c")
@Entity
public class Customer extends Person {
    private String phoneNumber;

    @Column(name = "notes", length = 500)
    private String notes;

    // changed CascadeType to ALL
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pet> pets;

    public Customer() {}

    public Customer(String name, String phoneNumber, String notes) {
        super(name);

        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
