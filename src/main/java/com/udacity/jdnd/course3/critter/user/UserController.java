package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.*;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer(customerDTO.getName(), customerDTO.getPhoneNumber(), customerDTO.getNotes());
        Long id = userService.save(customer);
        customerDTO.setId(id);

        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        Iterable<Customer> customers = userService.getAllCustomers();

        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer: customers) {
            CustomerDTO customerDTO = getCustomerDTO(customer);

            customerDTOS.add(customerDTO);
        }

        return customerDTOS;
    }

    private CustomerDTO getCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setNotes(customer.getNotes());
        List<Pet> pets = customer.getPets();
        if (pets != null) {
            List<Long> petIds = new ArrayList<>(pets.size());
            for (Pet pet : pets) {
                petIds.add(pet.getId());
            }
            customerDTO.setPetIds(petIds);
        } else {
            customerDTO.setPetIds(new ArrayList<>());
        }
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        return customerDTO;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Set<DayOfWeek> daysAvailable = employeeDTO.getDaysAvailable();
        String name = employeeDTO.getName();
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        long id = employeeDTO.getId();
        Optional<Employee> employeeOptional = userService.getEmployee(id);
        Employee employee = employeeOptional.orElseGet(() -> {
            ArrayList<EmployeeSkill> skillsList;
            if (skills != null) {
                skillsList = new ArrayList<>(skills);
            } else {
                skillsList = new ArrayList<>();
            }
            ArrayList<DayOfWeek> daysAvailableList;
            if (daysAvailable != null) {
                daysAvailableList = new ArrayList<>(daysAvailable);
            } else {
                daysAvailableList = new ArrayList<>();
            }

            return new Employee(name, skillsList, daysAvailableList);
        });
        id = employeeService.save(employee);
        employeeDTO.setId(id);

        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Optional<Employee> employeeOptional = userService.getEmployee(employeeId);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employeeDTO.setDaysAvailable(new HashSet<>(employee.getDaysAvailable()));
            employeeDTO.setName(employee.getName());
            employeeDTO.setSkills(new HashSet<>(employee.getSkills()));
        }
        employeeDTO.setId(employeeId);

        return employeeDTO;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }
}
