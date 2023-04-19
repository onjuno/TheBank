//package ollnor1;

import java.util.ArrayList;
/* Klassen Customer som utgör en kund. Kunden har relation till accounts och kan ha en eller flera via en arraylist.
* Klassen har olika metoder som klassiska get metoder samt starta ett nytt konto och identifera specifika konton.
* @author Olle Nordlander - ollnor1
 */

public class Customer {

    private String firstName;
    private String lastName;
    private String pNo;
    private ArrayList<Account> accounts = new ArrayList<>();



    // Konstruktor - utgör objektet kund.
    public Customer(String firstName, String lastName, String pNo){
        accounts = new ArrayList<>();
        this.firstName = firstName;
        this.lastName = lastName;
        this.pNo = pNo;
    }
    /*
    * metod som printar alla accounts en customer har.
    * @param ingen parameter.
    * @return void - men printar accounts.
     */
    public void getAccounts(){
        for(int i = 0 ; i<accounts.size() ; i++){
            System.out.println(accounts.get(i));
        }
    }

    /*
    * Metod som returnerar ett specifikt konto beroende på vad som efterfrågas.
    * @param - customer vars account man ska hitta
    * @return int - kontonummer.
     */
    public int getLastAccountNumber(Customer x){
        return x.accounts.get(x.accounts.size()-1).getAccountNumber();
    }

    /* Metod som skapar ett nytt kreditkonto.
    * @param ingen parameter.
    *  @ return void -
     */
    public void createCreditAccount(){
        CreditAccount account = new CreditAccount();
        accounts.add(account);
    }

    /*
     * Metod som skapar ett nytt sparkonto.
     * @param ingen parameter.
     * @return void
     */
    public void createSavingsAccount(){
        SavingsAccount account = new SavingsAccount();
        accounts.add(account);
    }




    /*
     * Metod som returnerar personnummer som "text"
     * @param ingen parameter.
     * @return String - returnerar siffror som en string.
     */
    public String getPNo(){
        return pNo;
    }

    /* Metod som returnerar förnamn
     * @param ingen parameter.
     * @return String - förnamn som text.
     */
    public String getFirstName(){
        return firstName;
    }

    //likt ovan
    public String getLastName(){
        return lastName;
    }

    // Metod som ändrar firstname
    public void setFirstName(String s){
        this.firstName = s;
    }

    // Metod som ändrar lastname
    public void setLastName(String s){
        this.lastName=s;
    }


    /*
     * Metod som används för att hitta ett konto via accounttype string. Loopar via for loop för att hitta rätt account.
     * @param String - accounttype
     * @return int - returnerar accountnumber.
     */
    public int getAccountByString(String s){
        for(int i=0; i<accounts.size(); i++){
            if(accounts.get(i).getAccountType().equalsIgnoreCase(s)){
                return accounts.get(i).getAccountNumber();
            }
        }
        return 0;
    }

    /* Metod som returnerar enl lista med en kunds kontoinfo. (returneras med ränta som %).
    * @param ingen.
     */ // return@ en arraylist med info om en kunds konto.
    public ArrayList<String> getCustomerAccountInfo() {
        ArrayList<String> returnList = new ArrayList<String>();
        for (int i=0; accounts.size() > i; i++){
            returnList.add(accounts.get(i).toString());
        }
        return returnList;
    }

    /* Metod som returnerar enl lista med en kunds kontoinfo (Returneras med ränta som kr.).
     * @param ingen.
     */ // return@ en arraylist med info om en kunds konto.
    public ArrayList<String> getClosedCustomerAccountInfo() {
        ArrayList<String> returnList = new ArrayList<String>();
        for (int i=0; accounts.size() > i; i++){
            returnList.add(accounts.get(i).toClosedString());
        }
        return returnList;
    }

    /*
     * Likt ovan metod men man hittar ett account via accountnumber. Loopar med en for loop för att hitta rätt account
     * @param int - accountnumber
     * @return returnerar ett account via get(i).
     */
    public Account getAccountByInt(int s){
        Account findAccount = null;
        for (Account account : accounts) {
            if (account.getAccountNumber() == s) {
                findAccount = account;
            }
        }
        return findAccount;
    }

    /* Tar bort en kunds specifika account.
    * @param int accountID
    * @return void- ingenting. Men tar bort accountet.
     */
    public void closeAccount(int accountId){
        for(int i=0; i<accounts.size(); i++){
            if(accounts.get(i).getAccountNumber() == accountId){
                accounts.remove(i);
            }
        }
    }

    public ArrayList<String> getAllAccounts(){
        ArrayList<String> accountString = new ArrayList<>();
        for (Account account : accounts) {
            accountString.add(account.toString());
        }
        return accountString;
    }

    public String getClosedAccountByIndex(int x){
        return accounts.get(x).toClosedString();
    }

    public String getAccountByIndex(int x){
        return accounts.get(x).toString();
    }

    /* Tar bort en kunds alla accounts
    * @param ingen
    * @retur void- inget.
     */
    public void clearAccounts(){
        accounts.clear();
    }

    public String getGUIAccountByIndex(int x){
        return accounts.get(x).toString();
    }

    public Account getGUITransactionByIndex(int accountIndex){
        return accounts.get(accountIndex);
    }

    public int getGUIAccountByIndexToInt(int x){
        return accounts.get(x).getAccountNumber();
    }

    public void removeAccountByIndex(int x){
        this.accounts.remove(x);
    }


    /*
     * Klassisk toString metod som returnerar en kund som en String.
     * @param ingen parameter.
     * @return String - returnerar en kund som string.
     */
    public String toString(){
        return pNo + " " + firstName + " " + lastName;
    }

}
