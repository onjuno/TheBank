//package ollnor1;


import java.util.ArrayList;
/* Klassen BankLogic som utgör en samling av metoder som tillsammans bildar detta "miniprogram".
* En metod per "funktion" har gjorts och kallar oftast på de andra klasserna och dess metoder för att fungera.
* @author Olle Nordlander - ollnor-1
 */

public class BankLogic {


    private ArrayList<Customer> customerList = new ArrayList<>();

    /*
    * Metod som returnerar alla transaktioner som gjorts av ett konto.
    * @ param String pNo och int accountId.
    * @return arraylist av klassen transactions, annars null.
     */
    public ArrayList<String> getTransactions(String pNo, int accountId){
        ArrayList<String> customerString = new ArrayList<>();
        ArrayList<Transactions> transactions = new ArrayList<>();

        for (Customer customer: customerList){
            if(pNo.equalsIgnoreCase(customer.getPNo())){
                if(customer.getAccountByInt(accountId)!= null){
                    // ändrade till addAll så lades allt till.
                    transactions.addAll(customer.getAccountByInt(accountId).getAccountsTransactions());
                    for (Transactions transaction : transactions) {
                        customerString.add(transaction.toString());
                    }
                    return customerString;
                }
                return null;
            }

        }
        return null;
    }
    /*
     * Metod som returnerar en arraylist med alla kunder och dess info
     * @param ingen parameter.
     * @return arrayList String - returnerar kunder som strängar
     */
    public ArrayList<String> getAllCustomers(){
        ArrayList<String> customerString = new ArrayList<>();
        for (Customer customer : customerList) {
            customerString.add(customer.toString());
        }
        return customerString;
    }

    /*  SKapar en ny customer och lägger den i en arraylist
    *
    * @param String name, surname, pNo.
     */ // @return boolean true om lyckas false om misslyckat.
    public boolean createCustomer(String name, String surname, String pNo){
        for (Customer customer : customerList) {
            if (pNo.equalsIgnoreCase(customer.getPNo())) {
                return false;
            }
        }
        Customer newCustomer = new Customer(name, surname, pNo);
        customerList.add(newCustomer);
        return true;
    }

    /*
    * Metod som returnerar en lista med info om kunden som efterfrågas. (med konton.)
    *
    * @param String pNo
    * @return ArrayList<String> */
    public ArrayList<String> getCustomer(String pNo){
        ArrayList<String> customerString = new ArrayList<>();
        for (Customer customer : customerList) {
            if (customer.getPNo().equals(pNo)) {
                customerString.add(customer.toString());
                customerString.addAll(customer.getCustomerAccountInfo());
                return customerString;
            }
        }
        return null;
    }

    /*
    * Byter namn på vald kund, om personen lämnar tomt för eller efternamn behålls det nuvarande.
    * @param String: name, surname, pNo.
    * @return boolean. True om ett namn ändrades - false om kunden inte fanns.
     */
    public boolean changeCustomerName (String name, String surname, String pNo) {
        for (Customer customer : customerList) {
            if (customer.getPNo().equals(pNo)) {
                if (!surname.equals("") || !name.equals("")){
                    if (!name.equals("")){
                        customer.setFirstName(name);
                    }
                    if (!surname.equals("")){
                        customer.setLastName(surname);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /*
    * Skapar ett nytt sparkonto till kund. Returnerar en int med kontonummer om det lyckades, -1 om misslyckat.
    * @param - String, pNo.
    * @return int - kontonummer om lyckat, -1 om misslyckat.
     */
    public int createSavingsAccount (String pNo) {
        int returnCustomer = -1;
        for (Customer customer: customerList) {
            if (customer.getPNo().equals(pNo)) {
                customer.createSavingsAccount();
                returnCustomer = customer.getLastAccountNumber(customer);
                return returnCustomer;
            }
        }
        return returnCustomer;

    }

    /*Skapar ett nytt kreditkonto till kund. Returnerar en int med kontonummer om det lyckades, -1 om misslyckat.
    * @param String pNo.
    * @return int - kontonummer om lyckat, -1 om misslyckat.
     */
    public int createCreditAccount(String pNo){
        int returnCustomer = -1;
        for (Customer customer: customerList) {
            if (customer.getPNo().equals(pNo)) {
                customer.createCreditAccount();
                returnCustomer = customer.getLastAccountNumber(customer);
                return returnCustomer;
            }
        }
        return returnCustomer;

    }

    /*Returnerar en string med info om ett specifikt konto av en specifik kund.
    * @param String pNo. Int accountId
    * @return String - info om kontot.
     */
    public String getAccount(String pNo, int accountId){
        for (Customer customer : customerList) {
            if (pNo.equalsIgnoreCase(customer.getPNo())) {
                if (customer.getAccountByInt(accountId) != null) {
                    return customer.getAccountByInt(accountId).toString();
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    /* Gör en insättning på ett konto som identifieras via pNo och accountId. Insättningens mängd görs via amount.
    * @param String pNp, Int accountId, Int amount.
    * @return boolean. True om det gick bra. False om det misslyckades.
     */
    public boolean deposit(String pNo, int accountId, int amount){
         if(amount > 0){
             for (Customer customer : customerList) {
                 if (pNo.equalsIgnoreCase(customer.getPNo())) {
                     if (customer.getAccountByInt(accountId) != null) {
                         customer.getAccountByInt(accountId).depositMoney(amount);
                         return true;
                     } else {
                         return false;
                     }
                 }
             }
         } else {
             return false;
         }
         return false;
    }

    /*
    *   Gör ett uttag på ett konto som identifieras med pNo och accountId. Uttagets mängd görs via int amount.
    * @param String pNo, Int accountId, Int amount.
    *  @return boolean, true om det gick bra. False vid misslyckande.
     */

    public boolean withdraw(String pNo, int accountId, int amount){
        int compareB;
        if(amount > 0){
            for (Customer customer : customerList) {
                if (pNo.equalsIgnoreCase(customer.getPNo())) {
                    if (customer.getAccountByInt(accountId) != null) {
                        // ändrat då jag måste kolla om withdraw gått igenom. Detta genom att kolla om balance ändrats.
                        compareB = customer.getAccountByInt(accountId).getBalance();
                        customer.getAccountByInt(accountId).withdrawMoney(amount);
                        if(compareB != customer.getAccountByInt(accountId).getBalance()){
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }

    /* Metod som printar ut kontot ett konto som tas bort. (med omgjord ränta). Sparas i en string innan den tas bort.
    * @param - String pNo, Int accountId.
    * @return - String - med info om kontot.
     */
    public String closeAccount(String pNo, int accountId){
        String closedAccount = null;
        for (Customer customer : customerList) {
            if (pNo.equalsIgnoreCase(customer.getPNo())) {
                closedAccount = customer.getAccountByInt(accountId).toClosedString();
                customer.closeAccount(accountId);
            }
        }
        return closedAccount;
    }


    /* Metod som tar bort en specifik kund och alla hens konton. Returnerar info om kunden och kontona i en arraylist.
    * @param String pNo
    * @retur ArrayList String. Med info om kund och dess accounts.
     */
    public ArrayList<String> deleteCustomer(String pNo){
        ArrayList<String> customerString = new ArrayList<>();
        for(int i=0; i<customerList.size(); i++){
            if(customerList.get(i).getPNo().equals(pNo)){
                customerString.add(customerList.get(i).toString());
                customerString.addAll(customerList.get(i).getClosedCustomerAccountInfo());
                customerList.remove(customerList.get(i));
               return customerString;
            }
        }
        return null;
    }


    /*
    * Metod som tar bort en kund ur listan genom index. Används av GUI vid deleteCustomer.
    * @param int i - index. returnerar ingenting.
     */
    public void deleteCustomerByIndex(int i){
        customerList.remove(i);
    }

    public void deleteAccountByIndex(int customerIndex, int accountIndex){
        customerList.get(customerIndex).removeAccountByIndex(accountIndex);
    }

    public String getCustomerInfoByIndex(int position){
        return customerList.get(position).toString();
    }
    public ArrayList<String> getCustomersAccountsByIndex(int position){
        return customerList.get(position).getAllAccounts();
    }
    public String getCustomerByPosition(int position){
        return customerList.get(position).toString();
    }

    public String getCustomersAccountByPositionToString(int customerIndex, int accountIndex){
        return customerList.get(customerIndex).getGUIAccountByIndex(accountIndex);
    }

    public String getCustomersAccountByPositionToClosedString(int customerIndex, int accountIndex){
        return customerList.get(customerIndex).getClosedAccountByIndex(accountIndex);
    }

    public int getCustomersAccountByPositionToInt(int customerIndex, int accountIndex){
        return customerList.get(customerIndex).getGUIAccountByIndexToInt(accountIndex);
    }

    /*
    public String getCustomersTransactionByPosition(int customerIndex, int accountIndex, int transactionIndex){
        return customerList.get(customerIndex).getAccountByInt(accountIndex).getTransactionInfoByIndex(transactionIndex);
    }

    public int getAccountForGUI(int customerIndex, int accountID){
        return customerList.get(customerIndex).getAccountByInt(accountID).getAccountNumber();
    }

     */

    public String getCustomersPNrByPosition(int position){
        return customerList.get(position).getPNo();
    }

    public ArrayList<String> getCustomersClosedAccounts(int position){
        return customerList.get(position).getClosedCustomerAccountInfo();
    }
    /*
    public void createTransaction(String pNo, int accountId, double x, double y){
        Customer customer = getCustomer(pNo);
        Account account = customer.getAccountByInt(accountId);


    }
    */




}
