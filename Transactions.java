//package ollnor1;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* Egen klass för transaktioner. Gjordes för att det lättare kan hållas isär och följa. Blir även enklare att navigera i min arraylist med transaktioner.
    * @author Olle Nordlander - ollnor1
    *
     */
public class Transactions {


    private double amount;
    private String transactionDate;
    private double balance;

    // Konstruktor, vad som utgör en transaction. tid och datum sparas i transactionDate
    public Transactions(double balance, double amount){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());
        this.transactionDate = strDate;
        this.balance = balance;
        this.amount = amount;
    }
    // toString metod som returnerar tid, saldo och mängd.
    public String toString(){
        // ändrat och tagit formatet från tidigare tostring metoder.
        String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(this.balance);
        String amountStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(this.amount);
        return this.transactionDate + " " + amountStr + " Saldo: "+ balanceStr;
    }


}
