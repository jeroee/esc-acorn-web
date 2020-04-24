package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Generators {
    public static String LongMesage(){
        String msg="";
        Random rand = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int j = 0; j < 500; j++) {
            System.out.println(j);
            msg = msg + alphabet.charAt(rand.nextInt(alphabet.length()));
        }
        return msg;
    }
    public static ArrayList<String> getNames() {
        Random rand = new Random();

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String wrongFirstName = "";
        String wrongLastName = "";
          ArrayList<String> str = new ArrayList<String>(4);

        for (int i = 0; i < 25; i++) {
            wrongFirstName = wrongFirstName + alphabet.charAt(rand.nextInt(alphabet.length()));
        }
        for (int j = 0; j < 25; j++) {
            wrongLastName = wrongLastName + alphabet.charAt(rand.nextInt(alphabet.length()));
        }
        String firstName = "John";
        String lastName = "Doe";
        str.add(firstName);
        str.add(lastName);
        str.add(wrongFirstName);
        str.add(wrongLastName);

        return str;
    }


}
