
package com.mycompany.springboot;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.repository.query.Param;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
@Query("SELECT new com.mycompany.springboot.PersonPassengerDTO(p.personID, p.name, p.lastName,p.locID, pa.miles, pa.funds) " +
           "FROM Person p INNER JOIN Passenger pa ON p.personID = pa.pID")
    List<PersonPassengerDTO> getAllPersonsWithPassengers();

@Query("SELECT new com.mycompany.springboot.PersonPassengerbyIDDTO(p.personID,p.name,p.lastName, p.locID, pa.miles, pa.funds) " +
       "FROM Person p INNER JOIN Passenger pa ON p.personID = pa.pID " +
       "WHERE p.personID LIKE :pID")
PersonPassengerbyIDDTO getsomePersonsWithPassengers(@Param("pID") String pID);
    

    
}
