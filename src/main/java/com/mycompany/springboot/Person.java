
package com.mycompany.springboot;
import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "person")
@Data
public class Person {
 
    
    @Id
    @Column(name="personID",unique=true,nullable=false)
    private String personID;

   @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "locationID")
    private String locID;
    
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Passenger passenger;   
  
}



