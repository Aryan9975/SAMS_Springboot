
package com.mycompany.springboot;
import jakarta.persistence.*;

@Entity
@Table(name="location")
public class Location {
   
@Id
@Column(name="locationID")
private String locationID;


public String getlocationID(){
   return locationID;} 

public void setlocationID(String locationID){
this.locationID=locationID;
}
}