package main.java;

import java.io.Serializable;

public abstract class AllAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String name;
    protected int accountBalance;

    public AllAccount(String name, int accountBalance) {
        this.name = name;
        this.accountBalance = accountBalance;
    }

    public String getName() {
        return name;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public abstract void displayAccountType();

    @Override
    public String toString() {
        return "Account Information: {name = '" + name + "', accountBalance = " + accountBalance + "}";
    }
}

interface AccountOperations {
    void deposit(int amount);
    void withdraw(int amount);
}
