import java.util.Locale;
import java.util.Scanner;
import java.lang.Math;
import java.text.NumberFormat;

public class MortgageCalc {
    public static void main(String[] args) {
        
        int principal = 0;
        float annualInterest = 0;
        byte years = 0;
        
        // Scanner sc = new Scanner(System.in);
        
        principal = (int)readNum("Principal: ", 1000, 1_000_000);
        // while(true) {
        //     System.out.print("Principal: ");
        //     principal = sc.nextInt();
        //     if (principal >= 1000 && principal <= 1_000_000)
        //         break;
        //     System.out.println("Enter a value between 1000 and 1,000,000.");
        // }
        
        annualInterest = (float)readNum("Annual Interest Rate: ", 0, 60);
        // while(true) {
        //     System.out.print("Annual Interest Rate: ");
        //     annualInterest = sc.nextFloat();
        //     if (annualInterest <= 60 && annualInterest > 0)
        //         break;
        //     System.out.println("Enter a value between 0 and 60.");
        // }
        
        years = (byte)readNum("Period (Years): ", 0, 30);
         
        // while(true) {
        //     System.out.print("Period (Years): "); 
        //     years = sc.nextByte();
        //     if (years > 0 && years <= 30)
        //         break;
        //     System.out.println("Enter a value between 0 and 30.");
        // }
        
        // sc.close();
        
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
            
            //noOfPayments works but monthlyInterest does not - it comes to 0. Changed monthlyInterest to float & it works

            double mortgage = principal * (monthlyInterest * Math.pow(1 + monthlyInterest, noOfPayments)) / (Math.pow(1+ monthlyInterest, noOfPayments)-1);
            
            return mortgage;
    }
}
    

