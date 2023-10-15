import java.util.Locale;
import java.util.Scanner;
import java.lang.Math;
import java.text.NumberFormat;

public class MortgagePaymentSchedule {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT =  100;
    public static void main(String[] args) {
        int principal = 0;
        float annualInterest = 0;
        byte years = 0;
        
        principal = (int)askDetails("Principal: ", 1000, 1_000_000);      
        annualInterest = (float)askDetails("Annual Interest Rate: ", 0, 60);
        years = (byte)askDetails("Period (Years): ", 0, 30);
        
        double mortgage = calculateMortgage(principal, annualInterest, years);
        
        printMortgage(mortgage);        
        printPaymentSchedule(principal, annualInterest, years);
    }

    private static void printMortgage(double mortgage) {
        String formattedMortgage = NumberFormat.getCurrencyInstance(Locale.US).format(mortgage);  
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + formattedMortgage);
    }

    private static void printPaymentSchedule(int principal, float annualInterest, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for(short month = 1; month <= years * MONTHS_IN_YEAR; month++) {
            double balance = calculateBalance(principal, annualInterest, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }
    
    public static double askDetails(
        String prompt, 
        double min, 
        double max) {            
        double value;
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            System.out.print(prompt);
            value = sc.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between" + min + "and" + max);
            sc.close();
        }
        return value;
    }
    
    public static double calculateBalance(
        int principal,
        float annualInterest,
        byte years,
        short noOfPaymentsMade) {
        
        float noOfPayments = years * MONTHS_IN_YEAR;
        float monthlyInterest = (float)(annualInterest / PERCENT / MONTHS_IN_YEAR);
        
        double balance = principal * 
            (Math.pow(1 + monthlyInterest, noOfPayments) - 
            Math.pow(1 + monthlyInterest, noOfPaymentsMade)) / (Math.pow(1+monthlyInterest, noOfPayments) - 1);
            
        return balance;
    }
    
    public static double calculateMortgage(
        int principal,
        float annualInterest,
        byte years) { 
                   
        float noOfPayments = years * MONTHS_IN_YEAR;
        float monthlyInterest = (float)(annualInterest / PERCENT / MONTHS_IN_YEAR);
                
        double mortgage = principal * (monthlyInterest * Math.pow(1 + monthlyInterest, noOfPayments)) / (Math.pow(1+ monthlyInterest, noOfPayments)-1);
        
        return mortgage;
    }
}
