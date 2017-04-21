package com.agunga.util;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Created by agunga on 1/17/17. This Class contains 2 copy methods
 */
public class MyUtility {

    public static void myPrintln(Object object) {
        System.out.println(object);
    }

    public static Scanner myScanner() {
        return new Scanner(System.in);
    }

    /*ByteStream method that takes filenames as argument*/
    public static void byteStream(String input_filename, String output_filename) throws IOException {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(input_filename);
            fileOutputStream = new FileOutputStream(output_filename);

            int c;
            while ((c = fileInputStream.read()) != -1) {
                fileOutputStream.write(c);
            }
            System.out.println("ByteStream file coping success.");

        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    /*CharacterStream method that takes filename as an argument*/
    public static void characterStream(String input_filename, String output_filename) throws IOException {
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(input_filename);
            fileWriter = new FileWriter(output_filename);

            int c;
            while ((c = fileReader.read()) != -1) {
                fileWriter.write(c);
            }
            System.out.println("CharacterStream file coping success.");

        } finally {
            if (fileReader != null) {
                fileReader.close();
            }

            if (fileWriter != null) {
                fileWriter.close();
            }
        }

    }

    public static void writeStringToFile(String text, String filename) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true))) {
            char[] chars = text.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                bufferedWriter.write(c);
            }

            MyUtility.myPrintln("Writing to file successful");

        } catch (IOException e) {
            MyUtility.myPrintln("PatientDetail detail saving failed.");
        }
    }

    public static String readFile(String filename) {

        String output = "";
        if (filename.equals("0")) {
        } else {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
                int i;
                while ((i = bufferedReader.read()) != -1) {
                    output += (char) i;
                }
                MyUtility.myPrintln("File read successful");

            } catch (IOException e) {
                System.err.println("The requested record could not be found.");
            }
        }
        return output;
    }

    public static int myScanInt() {
        int my_number = 0;
        try {
            my_number = Integer.parseInt(MyUtility.myScanner().nextLine());
        } catch (NumberFormatException e) {
            MyUtility.myPrintln("Invalid Input,only numeric figures allowed. Try again");
            myScanInt();
        }
        return my_number;
    }

    public static long myParseLong(String s) {
        long my_number = 0;
        try {
            my_number = Long.parseLong(s);
        } catch (NumberFormatException e) {
        }
        return my_number;
    }

    public static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {

        } catch (UnsupportedEncodingException e) {

        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        String result;
        try (Formatter formatter = new Formatter()) {
            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            result = formatter.toString();
        }
        return result;
    }
}
