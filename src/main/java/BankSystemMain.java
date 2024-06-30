package main.java;

import java.util.Scanner;
import java.io.*;

public class BankSystemMain {
    public static void main(String[] args){

        System.out.println("계좌 개설은 1, 입출금은 2, 시스템 종료는 0을 눌러 주세요.");
        Scanner sc = new Scanner(System.in);
        String input_num = sc.nextLine();

        if (input_num.equals("1")) {
            System.out.println("계좌 종류를 선택해 주세요: 1. 마이너스 통장 2. 적금 통장 3. 일반 예금 통장");
            String accountType = sc.nextLine();

            System.out.println("이름을 입력해 주세요.");
            String name = sc.nextLine();

            AllAccount newAccount = null;
            switch (accountType) {
                case "1":
                    System.out.println("마이너스 한도를 입력해 주세요.");
                    int overdraftLimit = sc.nextInt();
                    newAccount = new MinusAccount(name, 10000, overdraftLimit);
                    break;
                case "2":
                    System.out.println("이자율을 입력해 주세요.");
                    int interestRate = sc.nextInt();
                    newAccount = new SavingAccount(name, 10000, interestRate);
                    break;
                case "3":
                    newAccount = new RegularAccount(name, 10000);
                    break;
                default:
                    System.out.println("잘못된 계좌 종류입니다.");
                    System.exit(0);
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name + ".txt"))) {
                oos.writeObject(newAccount);
                System.out.println("개설한 통장이 성공적으로 저장되었습니다.");
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name + ".txt"))) {
                AllAccount savedAccount = (AllAccount) ois.readObject();
                System.out.println(savedAccount);
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        else if (input_num.equals("2")) {
            System.out.println("이름을 입력해 주세요.");
            String name = sc.nextLine();

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name + ".txt"))) {
                AllAccount account = (AllAccount) ois.readObject();

                System.out.println("입금은 1, 출금은 2를 선택해 주세요.");
                String transactionType = sc.nextLine();

                System.out.println("금액을 입력해 주세요.");
                int amount = sc.nextInt();

                if (transactionType.equals("1")) {
                    ((AccountOperations) account).deposit(amount);
                } else if (transactionType.equals("2")) {
                    ((AccountOperations) account).withdraw(amount);
                } else {
                    System.out.println("잘못된 입력입니다.");
                }

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name + ".txt"))) {
                    oos.writeObject(account);
                }
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}