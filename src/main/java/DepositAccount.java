package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DepositAccount {
    public static void inputToBank(String file, String input) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String tempFile = "temp.txt";

        try {
            reader = new BufferedReader(new FileReader(file));
            writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("잔액:")) {
                    int balanceIndex = line.indexOf("잔액:") + 4;
                    int currentBalance = Integer.parseInt(line.substring(balanceIndex).trim());
                    int inputAmount = Integer.parseInt(input);
                    int newBalance = currentBalance + inputAmount;
                    line = line.substring(0, balanceIndex) + newBalance;
                }
                writer.write(line + "\n");
            }
        }
        catch (IOException e) {
            System.err.println("입금 과정에서 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.err.println("통장의 데이터를 읽는 과정에서 오류가 발생했습니다: " + e.getMessage());
                e.printStackTrace();
            }
        }

        try {
            copyFile(tempFile, file);
            java.io.File temp = new java.io.File(tempFile);
            if (temp.exists()) {
                temp.delete();
            }
            System.out.println("입력하신 금액이 통장에 성공적으로 입금 되었습니다.");
        }
        catch (IOException e) {
            System.err.println("파일 복사 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void copyFile(String source, String destination) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(source));
            writer = new BufferedWriter(new FileWriter(destination));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
            }
        }
        finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
}