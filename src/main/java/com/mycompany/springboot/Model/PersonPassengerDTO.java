package com.mycompany.springboot.Model;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class PersonPassengerDTO {
    private String personID;
    private String name;
    private String lastName;
    private String locID;
    private Integer miles; 
    private Integer funds;  

    
    
}
