package ca.mcgill.ecse321.parkinglotbackend.data;

import ca.mcgill.ecse321.parkinglotbackend.model.Person;

public class PersonMockBuilder {
    static Person build() {
        return Person.builder()
                .name("Jon")
                .phoneNumber("5554443333")
                .build();
    }
}
