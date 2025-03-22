package com.mycompany.springboot;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class PersonPassengerbyIDDTO {
    private String personID;
    private String name;
    private String lastName;
    private String locID;
    private Integer miles; 
    private Integer funds;  
    
}
