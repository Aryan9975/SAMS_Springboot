
package com.mycompany.springboot.Repository;


import com.mycompany.springboot.Model.Person;
import com.mycompany.springboot.Model.PersonPassengerDTO;
import com.mycompany.springboot.Model.PersonPassengerbyIDDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.repository.query.Param;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
@Query("SELECT new com.mycompany.springboot.Model.PersonPassengerDTO(p.personID, p.name, p.lastName,p.locID, pa.miles, pa.funds) " +
           "FROM Person p INNER JOIN Passenger pa ON p.personID = pa.pID")
    List<PersonPassengerDTO> getAllPersonsWithPassengers();

@Query("SELECT new com.mycompany.springboot.Model.PersonPassengerbyIDDTO(p.personID,p.name,p.lastName, p.locID, pa.miles, pa.funds) " +
       "FROM Person p INNER JOIN Passenger pa ON p.personID = pa.pID " +
       "WHERE p.personID LIKE :pID")
PersonPassengerbyIDDTO getsomePersonsWithPassengers(@Param("pID") String pID);



}
