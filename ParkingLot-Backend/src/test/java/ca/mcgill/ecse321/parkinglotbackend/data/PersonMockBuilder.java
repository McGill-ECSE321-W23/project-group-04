package ca.mcgill.ecse321.parkinglotbackend.data;

import ca.mcgill.ecse321.parkinglotbackend.model.Person;

public class PersonMockBuilder {
    Person person;

    public static PersonMockBuilder builder() {
        return new PersonMockBuilder();
    }

    public Person build() {
        person = new Person("5554443333", "Jon");
        return person;
    }
}
