package HW5;
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
    private InputStreamReader reader;
    public static void main(String [] args){
    Checkbook megansCheckBook = null;
    Scanner input = new Scanner(System.in);
    ArrayList<Check> checkList = new ArrayList<Check>();
    ObjectInputStream in;
    BufferedReader reader;
    try{
     //menu display; user chooses which operations they wish to perform
    System.out.println("Press 1 to create a new checkbook");
    System.out.println("Press 2 to access an existing checkbook");
    System.out.println("Press 3 to write a check");
    System.out.println("Press 4 to get current balance");
    System.out.println("Press 5 to quit the program");
    while (input.hasNext()) {
        String menuDisplay = input.nextLine();
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
            while (newCheck != null){
            checkList = (ArrayList<Check>)in.readObject();
            System.out.println("Here is your checkbook: ");
            System.out.println(newCheck);
            in.close();
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
            System.out.println("Please enter amount.");
            String checkAmount = reader.readLine();
            System.out.println("Please enter checkNumber.");
            String checkNumber = reader.readLine();
            System.out.println("Please enter payee.");
            String checkPayee = reader.readLine();
            System.out.println("Please enter reason.");
            //converts string values for amount and checkNumber to double values
            double doubleAmount = Double.parseDouble(checkAmount);
            int intCheckNumber = Integer.parseInt(checkNumber);
            String checkReason = reader.readLine();
            Check newCheck = new Check(doubleAmount, intCheckNumber, checkPayee, checkReason);
            System.out.println("Here is your new check.");
            System.out.println("Megan Niemitalo" + "                       " + intCheckNumber);
            System.out.println("Pay to the order of " + checkPayee + " $" + doubleAmount);
            System.out.println("For " + checkReason);
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
                System.out.println("Goodbye!");
                input.close();
            }
            break;
        default: break;
    }
}    
    } catch (Exception e){
    }
    }
}
