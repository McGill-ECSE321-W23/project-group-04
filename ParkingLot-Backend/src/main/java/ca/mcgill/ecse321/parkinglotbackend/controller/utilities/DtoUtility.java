package ca.mcgill.ecse321.parkinglotbackend.controller.utilities;

import ca.mcgill.ecse321.parkinglotbackend.dto.AccountDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.MonthlyReservationDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.PersonDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.StaffAccountDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;

/**
 * Helper methods for converting Person and Account
 * between DTOs and Entities
 * @author Lin Wei Li
 */
public class DtoUtility {
    
    // Person
    public static PersonDto convertToDto(Person person) {
        if (person == null) {
            return null;
        }
        PersonDto personDto = new PersonDto(person.getPersonID(), person.getPhoneNumber(), person.getName());
        return personDto;
    }

    public static Person convertToEntity(PersonDto personDto) {
        if (personDto == null) {
            return null;
        }
        Person person = new Person(personDto.getPersonID(), personDto.getPhoneNumber(), personDto.getName());
        return person;
    }

    // Account
    public static AccountDto convertToDto(Account account) {
        if (account == null) {
            return null;
        }
        AccountDto accountDto = new AccountDto(account.getAccountID(), account.getEmail(), account.getPassword(), convertToDto(account.getPerson()));
        return accountDto;
    }

    public static Account convertToEntity(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        Account account = new Account(accountDto.getAccountID(), accountDto.getEmail(), accountDto.getPassword(), convertToEntity(accountDto.getPerson()));
        return account;
    }

    public static StaffAccountDto convertToDto(StaffAccount staffAccount) {
        return new StaffAccountDto(
                staffAccount.getAccountID(),
                staffAccount.getEmail(),
                staffAccount.getPassword(),
                DtoUtility.convertToDto(staffAccount.getPerson()),
                staffAccount.getSalary()
        );
    }

    public static MonthlyReservationDto convertToDto(MonthlyReservation monthlyReservation) {
        return new MonthlyReservationDto(monthlyReservation.getStartDate(), monthlyReservation.getEndDate(), monthlyReservation.getPerson().getPersonID());
    }

    public static MonthlyReservation convertToEntity(MonthlyReservationDto monthlyReservation, Person person) {
        return new MonthlyReservation(monthlyReservation.getStartDate(), monthlyReservation.getEndDate(), person);
    }
}
