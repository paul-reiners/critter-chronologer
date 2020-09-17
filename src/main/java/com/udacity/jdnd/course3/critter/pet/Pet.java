package com.udacity.jdnd.course3.critter.pet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
}
