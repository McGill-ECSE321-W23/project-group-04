package ca.mcgill.ecse321.parkinglotbackend.data;

import ca.mcgill.ecse321.parkinglotbackend.model.Account;

public class AccountMockBuilder {
    Account account;

    public static AccountMockBuilder builder() {
        return new AccountMockBuilder();
    }

    public Account build() {
        account = new Account();
        account.setEmail("dsa");
        account.setPassword("aa");
        return account;
    }

}
