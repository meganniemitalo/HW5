package HW5;
/**
 * System for printing checks.
 *
 * Megan Niemitalo
 */
public class Check
implements java.io.Serializable
{
    public int checkNumber;
    private String payee;
    public double amount;
    private String reason;
    private final static String name = "Megan Niemitalo";

    /**
     * Constructor for objects of class Check
     */
    public Check(double dollarAmount)
    {
        amount = dollarAmount;
        checkNumber = 1;
        payee = "unknown";
        reason = "unknown";
        
    }
    /**
     * Second constructor allows user to enter all check information into one window.  
     */
    public Check(double amount, int checkNumber, String payee, String reason)
    {
       this.amount = amount;
       this.checkNumber = checkNumber;
       this.payee = payee;
       this.reason = reason;
    }
    
    /**
     * User indicates the reason for writing the check, ex. groceries or utilities.  
     */
    public void writeReason(String purpose)
    {
        reason = purpose;
    }
    
    /**
     * User indicates the person or thing for whom they are writing the check.
     */
    public void writePayee(String name)
    {
        payee = name;

    }
    
    /**
     * User notes the check number.
     */
    public void writeCheckNumber(int number)
    {
        checkNumber = number;
    }
    
    /**
     * Retrieves the check amount.
     */
    public double getAmount()
    {
        return amount;
    }
    
    /**
     * Retrieves the reason for which the user wrote the check.
     */
    public String getReason()
    {
        return reason;
    }
    
    /**
     * Retrieves the payee of the check.
     */
    public String getPayee()
    {
        return payee;
    }
    
    /**
     * Retrieves the check number.
     */
    public int getCheckNumber()
    {
        return checkNumber;
    }
    
    /**
     * Retrieves the name of the user who is writing the check.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Prints the check.
     */
    public void printCheck()
    {
        System.out.println(name + "                       " + checkNumber);
        System.out.println("Pay to the order of " + payee + " $" + amount);
        System.out.println("For " + reason);
    }
}
