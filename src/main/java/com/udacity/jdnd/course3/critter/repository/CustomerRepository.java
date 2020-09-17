package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerRepository {
    @PersistenceContext
    EntityManager entityManager;

    public void persist(Customer customer) {
        entityManager.persist(customer);
    }

    public List<Customer> getAllCustomers() {
        TypedQuery<Customer> query = entityManager.createNamedQuery("Customer.findAll", Customer.class);

        return query.getResultList();
    }
}
