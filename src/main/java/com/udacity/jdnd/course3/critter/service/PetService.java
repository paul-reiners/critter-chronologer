package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Long save(Pet pet) {
        petRepository.save(pet);
        long customerId = pet.getOwner().getId();
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
        petDTO.setOwnerId(pet.getOwner().getId());
        petDTO.setType(pet.getType());

        return petDTO;
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        Optional<Customer> customerOptional = customerRepository.findById(ownerId);
        List<Pet> pets;
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            pets = customer.getPets();
        } else {
            pets = new ArrayList<>();
        }

        List<PetDTO> petDTOs = new ArrayList<>(pets.size());
        for (Pet pet: pets) {
            petDTOs.add(getPetDTO(pet));
        }

        return petDTOs;
    }

    public Optional<Pet> findById(Long petId) {
        return petRepository.findById(petId);
    }
}
