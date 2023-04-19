//package ollnor1;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* @author Olle Nordlander - ollnor1
* Klassen som utgör ett sparkonto. Ägs av en kund. Har en unik ränta som används och ett fritt uttag.
 */
public class SavingsAccount extends Account {

    private String accountType = "Sparkonto";
    private double interestRate = 1.2;
    private boolean freeWithdraw = false;

    private static final int INTEREST_DIVIDER = 100;

    public SavingsAccount() {

    }

    /* Metod som tar ut pengar från detta konto. Har lite regler gällande första uttaget.
    * @param int x - hur mycket pengar som ska tas ut.
    *  @return void - men ändrar och lägger till transaction.
     */
    public void withdrawMoney(int x){
        double theFee = x * 0.02;
        double withdrawAndFee = x + theFee;

        //bytte plats på dessa.
        if(this.freeWithdraw && (this.balance - withdrawAndFee) >= 0){
            this.balance -= withdrawAndFee;
            Transactions theWithdraw = new Transactions(this.balance, (-1 * withdrawAndFee));
            TransactionList.add(theWithdraw);
        }

        if(!this.freeWithdraw && (this.balance - x) >= 0){
            this.balance -= x;
            this.freeWithdraw = true;
            Transactions theWithdraw = new Transactions(this.balance, (-1 * x));
            TransactionList.add(theWithdraw);
        }

    }
    /* Metod som sätter in pengar på kontot.
    * @param int amount
    * retur void - men lägger in transaktionen i en arraylist samt ändrar balance.
     */
    public void depositMoney(int x){
        this.balance += x;
        Transactions theDeposit = new Transactions(this.balance, x);
        TransactionList.add(theDeposit);
    }


    /* metod som returnerar typen av konto som string
     * @param inget.
     */ //@return string med konto typ.
    public String getAccountType(){
        return accountType;
    }

    // metod som ska returnera en double som interest
    public double getInterest(){
        return balance * interestRate / INTEREST_DIVIDER;
    }

    // toString metod som returnerar info om ett konto. (returnerar ränta i %)
    // @param ingen.
    // @return String - info om ett konto.
    public String toString() {
        // kr fix
        String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(balance);

        // % fix
        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv","SE"));
        percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
        String percentStr = percentFormat.format(interestRate/INTEREST_DIVIDER);

        return accountNumber + " " + balanceStr + " " + accountType + " " + percentStr;
    }

    /* Likt ovan, men denna metod returnerar ett konto fast med räntan i kr och inte % (returnerar ränta i kr)
     * @param ingen
     */ // @return String. info om kontot.
    public String toClosedString(){
        // kr fix
        String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(this.balance);

        // % fix
        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv","SE"));
        percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
        String percentStr = percentFormat.format(interestRate/INTEREST_DIVIDER);

        String interestStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(balance*interestRate/INTEREST_DIVIDER);

        return this.accountNumber + " " + balanceStr + " " + accountType + " " + interestStr;
    }







}



