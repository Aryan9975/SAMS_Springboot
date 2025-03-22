
package com.mycompany.springboot;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="passenger")
@Data
public class Passenger {

@Id
@Column(name="personID")
private String pID;

 @Column(name = "miles")
    private Integer miles;

    @Column(name = "funds")
    private Integer funds;
    
@OneToOne
@JoinColumn(name = "personID", referencedColumnName = "personID", unique = true)
private Person person;

}
