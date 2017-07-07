package HW5; 
import java.util.*;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.nio.file.*;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

/**
 * Checkbook records checks and calculates the running balance.
 * Megan Niemitalo
 * 
 */
public class Checkbook
{
    private double balance;
    private ArrayList<Check> checkList;
    private double amount;
    private int numberOfChecks;
    private ObjectOutputStream os;
    private final String FILE_NAME = "checkFile.txt";
    /**
     * Constructor for objects of class Checkbook
     */
    public Checkbook()
    {
        balance = 5000.00;
        checkList = new ArrayList<Check>();
        amount = 0;
        Path destination = Paths.get(FILE_NAME);
        try{
            os = new ObjectOutputStream(
                                    new FileOutputStream(
                                        destination.toString(), true));
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }  
    //public void setName (String name)
    {
        //name = name;
    }
    /**
     * Calculates the running balance as user adds checks.
     */
    public double getBalance()
    {
        balance = balance - amount; 
        return balance;
    }
    /**
     * Creates list of checks as user adds individual checks to checkbook.
     */
    public void addCheck (Check newCheck)
    {
        ++numberOfChecks;
        checkList.add(newCheck);
        balance = balance - newCheck.getAmount();
    } 
    /**
     * Retrieves number of checks in chechbook.
     */
    public int getNumberOfCheckList()
    {
        return checkList.size();
    }
    /**
     * Moves value of amount from Check class into Checkbook class. 
     */
    public void enterAmount(double dollarAmount)
    {
        amount = dollarAmount;
    }
    /**
     * Displays amount that the user has entered.
     */
    public double getAmount()
    {
        return amount;
    }
    /**
     * User saves check objects one by one to a text file in their serialized format.
     */
    public void appendToFile(Check checkName)
    {
        try{
            os.writeObject(checkName);
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    /**
     * Once the user has appended all checks to the text file, the user must saveOverFile to close
     * the output stream and save all entries to the file. 
     */
    public void saveOverFile(){
        try{
             os.close();
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }
    public void depersist(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("checkFile.txt"));
            Checkbook checkList = (Checkbook)in.readObject();
            System.out.println("Here is your checkbook: ");
            System.out.println(checkList);
            in.close();
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
