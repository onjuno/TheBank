import java.util.ArrayList;

public class BankLogic {


    private ArrayList<Customer> customerList = new ArrayList<>();
    //private Customer customers = new Customer();

    public ArrayList<String> getAllCustomers(){
        ArrayList<String> customerString = new ArrayList<String>();
        for(int i=0; i<customerList.size(); i++){
            customerString.add(customerList.get(i).toString());
        }
        return customerString;
    }


    public boolean createCustomer(String name, String surname, String pNo){
        for(int i=0; i<customerList.size(); i++){
            if(pNo.equalsIgnoreCase(customerList.get(i).getPNo())){
                return false;
            }
            new Customer(name, surname, pNo);
            return true;
        }
    }

    public ArrayList<String> getCustomer(String pNo){

    }

    public boolean changeCustomerName(String name, String surname, String pNo){
        for(int i=0; i<customerList.size(); i++){
            if(pNo.equalsIgnoreCase(customerList.get(i).getPNo())){
                customerList.set(i, new Customer(name, surname, pNo));
                // hur gör jag med tomma namn?
                return true;
            }
            return false;
        }
    }

    public int createSavingsAccount(String pNo){

    }

    public String getAccount(String pNo, int accountId){

    }

    public boolean deposit(String pNo, int accountId, int amount){

    }

    public boolean withdraw(String pNo, int accountId, int amount){

    }

    public String closeAccount(String pNo, int accountId){

    }

    public ArrayList<String> deleteCustomer(String pNo){

    }
}
