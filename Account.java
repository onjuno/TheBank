// @author Olle Nordlander - ollnor-1
public class Account {

    private int balance;
    private double interestRate;
    private int accountNumber;
    private String accountType;
    private static int lastAssignedNumber = 1000;
    private static final int INTEREST_DIVIDER = 100;


    public Account(int balance, double interestRate, String accountType){

        lastAssignedNumber++;
        accountNumber = lastAssignedNumber;
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
    }

    public void withdrawMoney(int x){
        balance -= x;
    }

    public void depositMoney(int y) {
        balance += y;
    }

    public int getBalance() {
        return balance;
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public double getInterest(){
        return balance * interestRate / INTEREST_DIVIDER;
    }

    public String toString() {
        return accountNumber + " " + balance + " " + accountType + " " + interestRate;
    }





}
