package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Long save(Pet pet) {
        petRepository.save(pet);
        long customerId = pet.getOwnerId();
        Customer customer = customerRepository.getCustomer(customerId);
        customer.addPet(pet);
        customerRepository.persist(customer);

        return pet.getId();
    }

    public PetDTO getPet(long petId) {
        Optional<Pet> petOptional = petRepository.findById(petId);
        if (petOptional.isPresent()) {
            Pet pet = petOptional.get();
            return getPetDTO(pet);
        } else {
            return new PetDTO();
        }
    }

    private PetDTO getPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setName(pet.getName());
        petDTO.setNotes(pet.getNotes());
        petDTO.setOwnerId(pet.getOwnerId());
        petDTO.setType(pet.getType());

        return petDTO;
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        List<Pet> pets = petRepository.getPetsByOwner(ownerId);

        List<PetDTO> petDTOS = new ArrayList<>(pets.size());
        for (Pet pet: pets) {
            petDTOS.add(getPetDTO(pet));
        }

        return petDTOS;
    }
}
