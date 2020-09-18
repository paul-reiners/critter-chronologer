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
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.addPet(pet);
            customerRepository.save(customer);
        }

        return pet.getId();
    }

    public Pet getPet(long petId) {
        Optional<Pet> petOptional = petRepository.findById(petId);

        return petOptional.orElseGet(Pet::new);
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
        List<Pet> pets = petRepository.getPetsByOwnerId(ownerId);

        List<PetDTO> petDTOS = new ArrayList<>(pets.size());
        for (Pet pet: pets) {
            petDTOS.add(getPetDTO(pet));
        }

        return petDTOS;
    }
}
