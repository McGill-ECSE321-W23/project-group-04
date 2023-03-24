package ca.mcgill.ecse321.parkinglotbackend.controller.utilities;

import ca.mcgill.ecse321.parkinglotbackend.dto.AccountDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.PersonDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

public class DtoUtility {
    
    // Person
    public static PersonDto convertToDto(Person person) throws Exception {
        if (person == null) {
            throw new Exception("There is no such Person!");
        }
        PersonDto personDto = new PersonDto(person.getPersonID(), person.getPhoneNumber(), person.getName());
        return personDto;
    }

    public static Person convertToEntity(PersonDto personDto) throws Exception {
        if (personDto == null) {
            throw new Exception("There is no such PersonDto!");
        }
        Person person = new Person(personDto.getPersonID(), personDto.getPhoneNumber(), personDto.getName());
        return person;
    }

    // Account
    public static AccountDto convertToDto(Account account) throws Exception {
        if (account == null) {
            throw new Exception("There is no such Account!");
        }
        AccountDto accountDto = new AccountDto(account.getAccountID(), account.getEmail(), account.getPassword(), convertToDto(account.getPerson()));
        return accountDto;
    }

    public static Account convertToEntity(AccountDto accountDto) throws Exception {
        if (accountDto == null) {
            throw new Exception("There is no such AccountDto!");
        }
        Account account = new Account(accountDto.getAccountID(), accountDto.getEmail(), accountDto.getPassword(), convertToEntity(accountDto.getPerson()));
        return account;
    }

}
