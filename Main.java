  
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.*;
import java.util.*;
/**
 * Homework 5 Main class interacts with user through a menu display. 
 * 
 * Megan Niemitalo
 */
public class Main
{

    // Checkbook megansCheckBook;
    static Scanner input = new Scanner(System.in);
    static Check[] checkList = new Check[10];
    static ObjectInputStream in;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int counter = 0;
    static String menuDisplay;
    static double doubleAmount;
    static double checkAmount;
    static int intCheckNumber;
    static String checkReason;
    static Checkbook megansCheckBook;
    
    private static void askUser(){
        System.out.println("Press 1 to create a new checkbook");
        System.out.println("Press 2 to access an existing checkbook");
        System.out.println("Press 3 to write a check");
        System.out.println("Press 4 to get current balance");
        System.out.println("Press 5 to quit the program");
} 

    private static String getValue(String s) {
        String r;
        System.out.println(s);
        try {
            r = reader.readLine();
            return r;
        } catch (Exception e) {
            System.out.println("getValue failed " + e.toString());
        }
        return ("Error");
    }
    
    private static void printCheck(Check theCheck) {
            System.out.println("Here is your new check.");
            System.out.println("Megan Niemitalo" + "                       " + theCheck.getCheckNumber());
            System.out.println("Pay to the order of " + theCheck.getPayee() + " $" + theCheck.getAmount());
            System.out.println("For " + theCheck.getReason());
    }

    public static void main(String [] args) {
        String checkAmount;
            String checkNumber;
            String checkPayee;
            String checkReason;
    
    try{
     //menu display; user chooses which operations they wish to perform
        askUser();
    while (input.hasNext()) {
        menuDisplay = input.nextLine();
        
    switch (menuDisplay) {
        case "1":
            //creates new checkbook object
            megansCheckBook = new Checkbook();
            System.out.println("");
            System.out.println("Here is your new checkbook: " + megansCheckBook);
            break;
            
        case "2":
            //accesses exisiting checkbook object
        try{
            in = new ObjectInputStream(new FileInputStream("checkFile.txt"));
            while ((checkList[counter] = (Check)in.readObject()) != null){
                System.out.println("Here is your checkbook: ");
                System.out.println(checkList[counter]);
                counter = counter + 1;
            }
        } catch(Exception e){
            System.out.println("Option 2 " + e.toString());
        }
            break;
            
        case "3": 
            //writes a new check, adds the check to the checkbook, and appends the check to a
            //serialized file
            if(megansCheckBook == null){
                System.out.println("Please create checkbook");
                break;
            } else {
            reader = new BufferedReader(new InputStreamReader(System.in));
            
            checkAmount = getValue("Please enter amount.");
            checkNumber = getValue("Please enter checkNumber.");
            checkPayee = getValue("Please enter payee.");
            checkReason = getValue("Please enter reason.");
            
            //converts string values for amount and checkNumber to double values
            doubleAmount = Double.parseDouble(checkAmount);
            intCheckNumber = Integer.parseInt(checkNumber);
            
            Check newCheck = new Check(doubleAmount, intCheckNumber, checkPayee, checkReason);
            printCheck(newCheck);
            megansCheckBook.addCheck(newCheck);
            megansCheckBook.appendToFile(newCheck);
        }
            break;    
            
        case "4":
            //calculates the current balance of the checkbook
            if(megansCheckBook == null){
                System.out.println("Please create checkbook");
                break;
            } else {
                double balance = megansCheckBook.getBalance();
                System.out.println("Your current balance is " + balance);
            }
            break;
        case "5":
            //quits the program and saves checkbook to a serialized file
            if(megansCheckBook == null){
                System.out.println("Please create checkbook");
                break;
            } else {
                megansCheckBook.saveOverFile();
                System.out.println("Your checkbook is saved in checkFile.txt");
                System.out.println("Goodbye!");
                input.close();
            }
            break;
        default: break;
    }
}    
    } catch (Exception e){
            System.out.println(" Died at the beginning " + e.toString());
    }
    }
}
