/* Klassen Account som utgör ett konto. Kontot har flera faktorer och klassiska get metoder.
* En konstant används som interest divider, istället för att använda en "magisk" siffra 100.
* @author Olle Nordlander - ollnor-1
 */
//package ollnor1;

import java.util.ArrayList;

public class Account {

    protected int balance;
    protected int accountNumber;
    private static int lastAssignedNumber = 1000;
    protected ArrayList<Transactions> TransactionList = new ArrayList<Transactions>();
    protected Transactions transactions;


    // konstruktor, utgör ett konto.
    //public Account(int balance, double interestRate, String accountType){
    public Account(){
        balance = 0;
        lastAssignedNumber++;
        accountNumber = lastAssignedNumber;
        this.balance = balance;

    }

    // Tar ut pengar från kontot.
    // @param andelen pengar som ska tas bort.
    // @return void - balance ändras med metoden returenrar ingenting.
    public void withdrawMoney(int x){
        balance = balance - x;
    }

    // Sätter in pengar från kontot.
    // @param andelen pengar som ska sätts in bort.
    // @return void - balance ändras med metoden returenrar ingenting.
    public void depositMoney(int y) {
        balance += y;
    }

    /* metod som returnerar balance.
    * @param inget
     */ //@return int i form av balance.
    public int getBalance() {
        return balance;
    }

    // returnerar ett kontos accountnumber. @return int.
    public int getAccountNumber() {
        return accountNumber;
    }

    // metod som returnerar ett kontos transactions i en arraylist.
    public ArrayList<Transactions> getAccountsTransactions(){
        return TransactionList;
    }

    public String getTransactionInfoByIndex(int position){
        return TransactionList.get(position).toString();
    }

    public void createTransaction(double x, double y){
        new Transactions(x,y);
    }
    public String getAccountType(){
        return null;
    }
    public String toString() {
        return null;
    }
    public String toClosedString(){
        return null;
    }
   
}
