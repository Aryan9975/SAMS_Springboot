
package com.mycompany.springboot;


import java.sql.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class APIController {
    
    @Value("${spring.datasource.url}")
private String url;
    @Value("${spring.datasource.username}")
private String user;

@Value("${spring.datasource.password}")
private String password;

    @Autowired
    private PersonMethods personService;
    
    @Autowired
    private LocationMethods locationService;
   
    
    @GetMapping("/persons-passengers")
    public List<PersonPassengerDTO> getAllPersonsWithPassengers() {
        return personService.getAllPersonsWithPassengers();
    }
    
    @GetMapping("/passengers-id")
    public PersonPassengerbyIDDTO getsomePersonsWithPassengers(@RequestParam String pID) {
        System.out.println(personService.getsomePersonsWithPassengers(pID));
        return personService.getsomePersonsWithPassengers(pID);
    }
    @GetMapping("/allpersonids")
    public String getAllPersonIDs() {
        return personService.getpersonID().toString();}
    
    @GetMapping("/alllocationids")
    public String getAllLocationIDs() {
        return locationService.getlocationID().toString();
    }

    @GetMapping("/hello")
    public StringBuilder getID() {
        String query = "SELECT * from person"; 
        StringBuilder sb=new StringBuilder();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while(rs.next()) {
               sb.append(rs.getString("personID")).append("\n");
            } 
            return sb;
        } catch (SQLException e) {
            return sb.append("Database connection error: ").append(e.getMessage());
        }
    }
    
    @PostMapping("/set-location")
    public String addLocation(@RequestBody String locID) {
    locationService.addlocationID(locID);
    return "Location added successfully!";
}
    @PostMapping("/update-location/{id}")
    public String updateLocation(@PathVariable("id") String oldID ,@RequestBody String locID) {
    locationService.updateLocationID(oldID,locID);
    return "Location updated successfully!";
}
    
    @DeleteMapping("/deleteLocation/{id}")
public ResponseEntity<String> deleteLocation(@PathVariable("id") String locID) {
    try {
        boolean isDeleted = locationService.deleteLocation(locID);
        if (isDeleted) {
            return ResponseEntity.ok("Location deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Location ID " + locID + " not found.");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }
}

    @PostMapping("/location")
public ResponseEntity<String> insertLocation(@RequestBody Map<String, String> payload) {
    String locationid = payload.get("locationID");  

    String query = "INSERT INTO location (locationID) VALUES (?)";
    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, locationid);
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Location added successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert location.");
        }
    } catch (SQLException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
    }
}
}

