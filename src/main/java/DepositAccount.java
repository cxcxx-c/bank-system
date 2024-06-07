package main.java;

import java.io.*;
import java.util.Scanner;

public class DepositAccount {
    public void inputToBank() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("등록하신 통장 이름을 입력해 주세요: ");
        String filename = scanner.nextLine();

        OpenBankAccount account = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename + ".txt"))) {
            account = (OpenBankAccount) ois.readObject();
            System.out.println("통장 잔액: " + account);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("통장 내역이 존재하지 않습니다: " + e.getMessage());
            return;
        }

        System.out.print("입금은 1, 출금은 2를 눌러 주세요: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("입금하실 금액을 입력해 주세요: ");
            int amount = scanner.nextInt();

            int newBalance = account.getAccountBalance() + amount;
            account.setAccountBalance(newBalance);

            System.out.println("거래 후 잔액: " + account);
        }
        else if (choice == 2) {
            System.out.print("출금하실 금액을 입력해 주세요: ");
            int amount = scanner.nextInt();

            if (amount > account.getAccountBalance()) {
                System.out.println("잔액이 부족합니다.");
                return;
            }

            int newBalance = account.getAccountBalance() - amount;
            account.setAccountBalance(newBalance);

            System.out.println("거래 후 잔액: " + account);
        }
        else {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename + ".txt"))) {
            oos.writeObject(account);
        }
        catch (IOException e) {
            System.out.println("통장 잔액 저장에 오류가 발생했습니다.: " + e.getMessage());
        }

        scanner.close();
    }
}