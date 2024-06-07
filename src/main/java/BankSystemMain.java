package main.java;

import java.util.Scanner;
import java.io.*;

public class BankSystemMain {
    public static void main(String[] args){

        System.out.println("계좌 개설은 1, 입출금은 2, 시스템 종료는 0을 눌러 주세요.");
        Scanner sc = new Scanner(System.in);
        String input_num = sc.nextLine();

        if (input_num.equals("1")) {
            System.out.println("이름을 입력해 주세요.");
            String name = sc.nextLine();
            int accountBalance = 10000;
            OpenBankAccount openBankAccount = new OpenBankAccount(name, accountBalance);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name+".txt"))) {
                oos.writeObject(openBankAccount);
                System.out.println("개설한 통장이 성공적으로 저장되었습니다.");
            }
            catch (IOException e) {
                e.printStackTrace(); // exception 세부화시켜서 오류 정보를 정확히 확인할 수 있게 해야 함 (상위 exception 맨 아래로)
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name+".txt"))) {
                OpenBankAccount savedAccount = (OpenBankAccount) ois.readObject();
                System.out.println(savedAccount);
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        else if (input_num.equals("2")) {
            DepositAccount depositAccount = new DepositAccount();
            depositAccount.inputToBank();
        }
    }
}