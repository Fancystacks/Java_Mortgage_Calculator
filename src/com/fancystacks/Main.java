package com.fancystacks;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

// variables declared for use outside of functions
        int principal = 0;
        float annualInterest = 0;
        byte years = 0;

        // Principal amount if user enters valid number
        principal = (int) readNumber("Principal: ", 1000, 1_000_000);

        // Payment period calculation upon value entered
        annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);

        // Years input for payment period
        years = (byte) readNumber("Period (In Years): ", 1, 30);

        double mortgage = calcMortgage(principal, annualInterest, years);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);

        System.out.println("Monthly Mortgage: " + mortgageFormatted);
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

    public static double calcMortgage(
            int principal,
            float annualInterest,
            byte years) {

        final byte MONTHS_OF_YEAR = 12;
        final byte PERCENT = 100;

        float monthlyInterest = annualInterest / PERCENT / MONTHS_OF_YEAR;
        short numberOfPayments = (short)(years * MONTHS_OF_YEAR);
        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }

}
