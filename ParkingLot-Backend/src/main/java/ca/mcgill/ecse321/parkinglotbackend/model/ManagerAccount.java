/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

// line 43 "../../../../../../ParkingLot.ump"
@Entity
@NoArgsConstructor
public class ManagerAccount extends StaffAccount
{
    public ManagerAccount(Person person, String email, String password, float salary) {
        super(person, email, password, salary);
    }
}