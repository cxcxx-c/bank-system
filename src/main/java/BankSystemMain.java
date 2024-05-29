package main.java;

import java.util.Scanner;

public class BankSystemMain {
    public static void main(String[] args){

        System.out.println("계좌 개설은 1, 입출금은 2, 시스템 종료는 0을 눌러 주세요.");
        Scanner sc = new Scanner(System.in);
        String input_num = sc.nextLine();


        if (input_num.equals("1")){
            System.out.println("개설하실 통장의 이름을 입력해 주세요.");
            String accountName = sc.nextLine();
            String file = "C:\\Users\\copyc\\Desktop\\project\\java\\BankSystem\\"+accountName+".txt";
            OpenBankAccount.createBankAccount(file);
        }

        if (input_num.equals("2")){
            System.out.println("입금하실 통장의 이름을 입력해 주세요.");
            String depositName = sc.nextLine();
            String file = "C:\\Users\\copyc\\Desktop\\project\\java\\BankSystem\\"+depositName+".txt";
            System.out.println("입금하실 금액을 입력해 주세요.");
            String input = sc.nextLine();
            DepositAccount.inputToBank(file, input);
        }

        if (input_num.equals("3")){
            System.out.println("출금하실 통장의 이름을 입력해 주세요.");
            String withdrawalName = sc.nextLine();
            String file = "C:\\Users\\copyc\\Desktop\\project\\java\\BankSystem\\"+withdrawalName+".txt";
            System.out.println("출금하실 금액을 입력해 주세요.");
            String input = sc.nextLine();
            WithdrawalAccount.outputToBank(file, input);
        }
    }
}