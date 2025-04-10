

package com.mycompany.springboot.Service;

import com.mycompany.springboot.Model.Location;
import com.mycompany.springboot.Model.Person;
import com.mycompany.springboot.Model.PersonPassengerDTO;
import com.mycompany.springboot.Model.PersonPassengerbyIDDTO;
import com.mycompany.springboot.Repository.LocationRepository;
import com.mycompany.springboot.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonMethods {
    
     @Autowired
    private PersonRepository personRepository;
    private LocationRepository locationRepository;
    
  public List<PersonPassengerDTO> getAllPersonsWithPassengers() {
        return personRepository.getAllPersonsWithPassengers();
    }
  public PersonPassengerbyIDDTO getsomePersonsWithPassengers(String pID) {
        return personRepository.getsomePersonsWithPassengers(pID);
    }

    public StringBuilder getpersonID() {
        StringBuilder sb = new StringBuilder();
        try {
            // Fetch all Person records
            List<Person> persons = personRepository.findAll();
            sb.append(persons.stream()
                    .map(person -> person.getPersonID()+","+person.getName())
                    .collect(Collectors.joining("\n")));
        } catch (Exception e) {
            sb.append("Database error: ").append(e.getMessage());
        }
        return sb;
    }
    public StringBuilder getlocationID() {
        StringBuilder sb = new StringBuilder();
        try {
            // Fetch all Person records
            List<Location> locations = locationRepository.findAll();
            sb.append(locations.stream()
                    .map(location -> location.getlocationID().toString())
                    .collect(Collectors.joining("\n")));
        } catch (Exception e) {
            sb.append("Database error: ").append(e.getMessage());
        }
        return sb;
    }
}
