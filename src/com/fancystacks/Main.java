package com.fancystacks;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    final static byte MONTHS_OF_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {

// variables declared for use outside of functions

        // Principal amount if user enters valid number
        int principal = (int) readNumber("Principal: ", 1000, 1_000_000);

        // Payment period calculation upon value entered
        float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);

        // Years input for payment period
        byte years = (byte) readNumber("Period (In Years): ", 1, 30);

        double mortgage = calcMortgage(principal, annualInterest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("Mortgage");
        System.out.println("--------");
        System.out.println("Monthly Mortgage: " + mortgageFormatted);

        System.out.println();
        System.out.println("Schedule of Payments");
        System.out.println("--------------------");
        for (short month = 1; month <= years * MONTHS_OF_YEAR; month++) {
            double balance = calcBalance(principal, annualInterest, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, double min, double max)  {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Please enter a value between" + min + " and" + max);
        }
        return value;
    }

    public static double calcBalance(
            int principal,
            float annualInterest,
            byte years,
            short numberOfPaymentsMade) {

         float monthlyInterest = annualInterest / PERCENT / MONTHS_OF_YEAR;
         short numberOfPayments = (short)(years * MONTHS_OF_YEAR);

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;
    }

    public static double calcMortgage(
            int principal,
            float annualInterest,
            byte years) {

        float monthlyInterest = annualInterest / PERCENT / MONTHS_OF_YEAR;
        short numberOfPayments = (short)(years * MONTHS_OF_YEAR);
        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }

}
