import java.util.Locale;
import java.util.Scanner;
import java.lang.Math;
import java.text.NumberFormat;

public class MortgageCalc {
    public static void main(String[] args) {
        
        int principal = 0;
        float annualInterest = 0;
        byte years = 0;
        
        principal = (int)readNum("Principal: ", 1000, 1_000_000);
        
        annualInterest = (float)readNum("Annual Interest Rate: ", 0, 60);
        years = (byte)readNum("Period (Years): ", 0, 30);
        
        double mortgage = calculateMortgage(principal, annualInterest, years);
        
        String formattedMortgage = NumberFormat.getCurrencyInstance(Locale.US).format(mortgage);  
        System.out.println("Mortgage: " + formattedMortgage);
    }
    
    public static double readNum(String prompt, double min, double max) {
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
    
    public static double calculateMortgage(
        int principal,
        float annualInterest,
        byte years) {
                      
            final byte MONTHS_IN_YEAR = 12;
            final byte PERCENT =  100;
            
            float noOfPayments = years * MONTHS_IN_YEAR;
            float monthlyInterest = (float)(annualInterest / PERCENT / MONTHS_IN_YEAR);

            double mortgage = principal * (monthlyInterest * Math.pow(1 + monthlyInterest, noOfPayments)) / (Math.pow(1+ monthlyInterest, noOfPayments)-1);
            
            return mortgage;
    }
}
    

