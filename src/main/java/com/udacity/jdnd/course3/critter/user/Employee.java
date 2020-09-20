package com.udacity.jdnd.course3.critter.user;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.DayOfWeek;
import java.util.List;

@Entity
public class Employee extends Person {

    @ElementCollection @Enumerated(EnumType.STRING)
    private List<EmployeeSkill> skills;

    @ElementCollection @Enumerated(EnumType.STRING)
    private List<DayOfWeek> daysAvailable;

    public Employee(String name, List<EmployeeSkill> skills, List<DayOfWeek> daysAvailable) {
        super(name);
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

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
