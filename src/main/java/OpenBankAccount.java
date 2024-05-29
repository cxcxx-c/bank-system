package main.java;

import java.io.IOException;
import java.io.FileOutputStream;

public class OpenBankAccount {
    public static void createBankAccount(String file) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            String data = "잔액: 0";
            byte[] bytes = data.getBytes();
            outputStream.write(bytes);
            outputStream.close();
            System.out.println("새 통장이 성공적으로 생성되었습니다.");
        } catch (IOException e) {
            System.err.println("통장을 생성하는 동안 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}