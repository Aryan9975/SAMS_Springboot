

package com.mycompany.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationMethods {
    
     @Autowired
    private LocationRepository locationRepository;

    public StringBuilder getlocationID() {
        StringBuilder sb = new StringBuilder();
        try {
           
            List<Location> locations = locationRepository.findAll();
            sb.append(locations.stream()
                    .map(location -> location.getlocationID())
                    .collect(Collectors.joining("\n")));
        } catch (Exception e) {
            sb.append("Database error: ").append(e.getMessage());
        }
        return sb;
    }
    @Autowired
    private LocationRepository locationRepository2;
    public void addlocationID(String locID) {
    try {
      
        Location locations = new Location();
        locations.setlocationID((locID)); 
        locationRepository2.save(locations);
    } catch (NumberFormatException e) {
        System.out.println("Invalid Location ID format: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error saving location: " + e.getMessage());
    }
}
    @Autowired
private LocationRepository locationRepository3;

public boolean deleteLocation(String locationID) {
    if (locationRepository3.existsById(locationID)) {
        locationRepository3.deleteById(locationID);
        return true;
    }
    return false; 
}

    
    @Autowired
    private LocationRepository locationRepository4;
    public void updateLocationID(String locationID,String newlocationID){
    locationRepository3.findById(locationID);
    Location locations = new Location();
    locations.setlocationID((newlocationID)); 
    locationRepository4.save(locations);
    locationRepository4.deleteById(locationID);
    }

}
