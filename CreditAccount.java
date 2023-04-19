//package ollnor1;

/* Klassen som utgör ett kredit konto. Extends från account och ägs/används av en kund.
* Transaktionerna som görs sparas i en arraylist.
* @author Olle Nordlander - ollnor1
 */

import java.text.NumberFormat;
import java.util.Locale;

public class CreditAccount extends Account {

    private double interestRate = 0.5;
    private String accountType = "Kreditkonto";
    private int creditLimit = -5000;
    private static final int INTEREST_DIVIDER = 100;

    public CreditAccount() {

    }


    /* Metod som sätter in pengar på ett kreditkort.
    * @param int x - hur mycket man vill sätta in.
    *  @return void - men uppdaterar saldot samt räntan och lägger in transaktionen i arraylist.
     */
    public void depositMoney(int x){
        this.balance+=x;
        //ändat >= istället för >
        if(this.balance >= 0){
            this.interestRate = 0.5;
        } else {
            this.interestRate = 7.0;
        }
        Transactions theDeposit = new Transactions(this.balance, x);
        TransactionList.add(theDeposit);
    }


    /* Metod som tar ut pengar från ett konto.
    * @param int x - hur mycket som ska tas ut.
    *  @return void - men ändrar saldot samt lägger in transaction i listan.
     */
    public void withdrawMoney(int x){
        if((this.balance - x) >= creditLimit) {
            this.balance = this.balance - x;
            //ändrat till >= istället för >
            if (this.balance >= 0) {
                this.interestRate = 0.5;
            } else {
                this.interestRate = 7.0;
            }
            Transactions theWithdraw = new Transactions(this.balance, -1 * x);
            TransactionList.add(theWithdraw);
        }

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
