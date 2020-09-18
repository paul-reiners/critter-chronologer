package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PetRepository {
    @PersistenceContext
    EntityManager entityManager;


    public List<Pet> getPetsByOwner(long ownerId) {
        TypedQuery<Pet> query = entityManager.createNamedQuery("Pet.findByOwner", Pet.class);
        query.setParameter("ownerId", ownerId);

        return query.getResultList();
    }

    public void save(Pet pet) {
        entityManager.persist(pet);
    }

    public Optional<Pet> findById(long petId) {
        TypedQuery<Pet> query = entityManager.createNamedQuery("Pet.find", Pet.class);
        query.setParameter("petId", petId);

        return Optional.of(query.getSingleResult());
    }
}
