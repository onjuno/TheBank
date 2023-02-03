import java.util.ArrayList;

public class Customer {

    private String firstName;
    private String lastName;
    private static String pNo;

    private ArrayList<Account> accounts = new ArrayList<>();

    public void getAccounts(){
        for(int i = 0 ; i<accounts.size() ; i++){
            System.out.println(accounts.get(i));
        };
    }

    public Customer(String firstName, String lastName, String pNo){
        this.firstName = firstName;
        this.lastName = lastName;
        this.pNo = pNo;
    }

    public String getPNo(){
        return pNo;
    }

    public String toString(){
        return pNo + " " + firstName + " " + lastName;
    }

}
