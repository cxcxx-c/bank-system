package main.java;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class OpenBankAccount {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("개설하실 통장의 이름을 입력해 주세요.");

        String accountName = sc.nextLine();
        String file = "C:\\Users\\copyc\\Desktop\\project\\java\\BankSystem\\"+accountName+".txt";

        try {
            FileOutputStream accountfile = new FileOutputStream(file, true);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
