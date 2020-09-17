package com.udacity.jdnd.course3.critter.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.critter.views.Views;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

// Uses InheritanceType.JOINED to store shared fields in 'critter' and unique fields
// in subclass tables
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @JsonView(Views.Public.class)
    @Nationalized
    private String name;

    public Person() { }

    public Person(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
