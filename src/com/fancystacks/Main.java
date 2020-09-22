package com.fancystacks;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

// variables declared for use outside of functions
        int principal = 0;
        float annualInterest = 0;
        byte years = 0;

        Scanner scanner = new Scanner(System.in);

        // Principal amount if user enters valid number
        while (true) {
            System.out.print("Principal Amount (1k to 1M): ");
            principal = scanner.nextInt();
            if (principal > 999 && principal < 1000001)
                break;
            System.out.println("Please enter a value between 1000 and 1000000");
        }

        // Interest rate calculated upon validation
        while (true) {
            System.out.print("Annual Interest Rate: ");
            annualInterest = scanner.nextFloat();
            if (annualInterest >= 1 && annualInterest <= 31)
                break;
            System.out.println("Please enter a value between 1 and 30 ");
        }

        // Payment period calculation upon value entered
        while (true) {
            System.out.print("Period (In Years): ");
            years = scanner.nextByte();
            if (years >= 1 && years <= 30)
                break;
            System.out.println("Please enter a value between 1 and 30");
}

        double mortgage = calcMortgage(principal, annualInterest, years);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Monthly Mortgage: " + mortgageFormatted);
    }

    public static double calcMortgage(
            int principal,
            float annualInterest,
            byte years) {

        final byte MONTHS_OF_YEAR = 12;
        final byte PERCENT = 100;

        float monthlyInterest = annualInterest / PERCENT / MONTHS_OF_YEAR;
        float numberOfPayments = years * MONTHS_OF_YEAR;
        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }

}
