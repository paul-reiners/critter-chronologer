package com.udacity.jdnd.course3.critter.user;

import org.hibernate.annotations.CollectionType;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;

@Entity
public class Employee extends Person {

    @ElementCollection @Enumerated(EnumType.STRING)
    private List<EmployeeSkill> skills;

    @ElementCollection @Enumerated(EnumType.STRING)
    private List<DayOfWeek> daysAvailable;

    public List<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public List<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(List<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
