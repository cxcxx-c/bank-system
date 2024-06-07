package main.java;

import java.io.Serializable;

public class OpenBankAccount implements Serializable {

    private final long serialVersionUID = 1L;
    private String name;
    private int accountBalance;

    public OpenBankAccount(String name, int accountBalance) {
        this.name = name;
        this.accountBalance = accountBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Account Information: {name = '" + name + "', accountbalance = " + accountBalance + "}";
    }
}