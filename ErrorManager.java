package SoftwareEngineeringPro1;

import javax.swing.*;

public class ErrorManager {

    public void handleError(Exception e) {
        System.out.println("Error detected: " + e.getMessage());
    }
    //Potential errors to detect
    //Detect if there is a missing {} or () pairs

}
