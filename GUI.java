import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//@author Olle Nordlander - ollnor1
public class GUI extends JFrame{

    private static final int FRAME_WIDTH = 950;
    private static final int FRAME_HEIGHT = 600;
    private BankLogic bankLogic;
    private Customer customer;
    private Account account;

    private int customerIndex;

    // Nedan utgör första sidan (Customer)
    private JPanel customerPanel;
    private JLabel customerTextLabel = new JLabel();
    private JList customerList;
    private JTextField pNrField;
    private JTextField nameField;
    private JTextField surnameField;


    // Nedan utgör andra sidan (Accounts)
    private JPanel accountPanel;
    private JList accountList;
    private JList transactionList;
    private JTextField ssnField;
    private JTextField accountField;
    private JTextField amountField;
    private JLabel accountTextLabel = new JLabel();
    private JTextField customerField = new JTextField();


    // Kör igång programmet
    public static void main(String[] args) {
        GUI frame = new GUI();
        frame.setVisible(true);
    }

    public GUI(){
        createComponents();
        setTitle("Olles Bank");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createComponents(){

        /* För testning
        *
         */
        bankLogic = new BankLogic();

        bankLogic.createCustomer("olle","nord","960628");
        bankLogic.createCustomer("trifah","murre","970219");
        bankLogic.createCustomer("erik","smokin","969901");
        bankLogic.createCustomer("bisan","mclovin","960707");
        bankLogic.createSavingsAccount("960628");
        bankLogic.createCreditAccount("960628");
        bankLogic.createSavingsAccount("970219");
        bankLogic.createCreditAccount("970219");
        bankLogic.deposit("960628",1001, 500);
        bankLogic.withdraw("960628",1001, 100);
        bankLogic.deposit("960628",1002, 500);
        bankLogic.withdraw("960628",1002, 200);
        bankLogic.deposit("960628",1002, 400);

        customerPanel = new JPanel(new BorderLayout());
        customerPanel.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));

        accountPanel = new JPanel(new BorderLayout());
        accountPanel.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));

        JPanel framePanel = new JPanel(); //switcheroo

        createCustomerPanel();
        framePanel.add(customerPanel);

        createAccountPanel();
        framePanel.add(accountPanel);

        add(framePanel);

        pack();

        customerList.setListData(bankLogic.getAllCustomers().toArray());

    }

    private void createAccountPanel() {

        ActionListener listener = new ClickAccountListener();

        customerField.setEditable(false);
        customerField.setHorizontalAlignment(JTextField.CENTER);

        JPanel headerPanel = new JPanel(new GridLayout(1,2));
        JPanel leftPanel = new JPanel(new GridLayout(9,1));

        buildMenu();

        ssnField = new JTextField(15);
        ssnField.setBorder(BorderFactory.createTitledBorder("Social Security Number"));
        leftPanel.add(ssnField);

        accountField = new JTextField(15);
        accountField.setBorder(BorderFactory.createTitledBorder("Account id"));
        leftPanel.add(accountField);

        amountField = new JTextField(15);
        amountField.setBorder(BorderFactory.createTitledBorder("Amount"));
        leftPanel.add(amountField);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(listener);
        leftPanel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(listener);
        leftPanel.add(depositButton);

        JButton savingButton = new JButton("Create Savings Account");
        savingButton.addActionListener(listener);
        leftPanel.add(savingButton);

        JButton creditButton = new JButton("Create Credit Account");
        creditButton.addActionListener(listener);
        leftPanel.add(creditButton);

        JButton removeAccountButton = new JButton("Remove Account");
        removeAccountButton.addActionListener(listener);
        leftPanel.add(removeAccountButton);

        JButton saveTransButton = new JButton("Save transactions");
        // add to actionListener senare.
        leftPanel.add(saveTransButton);

        headerPanel.add(leftPanel);

        transactionList = new JList();
        transactionList.setBorder(BorderFactory.createTitledBorder("Transactions"));

        transactionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        accountList = new JList();
        accountList.setBorder(BorderFactory.createTitledBorder("Accounts"));

        accountList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                getAccountInfo();
                transactionList.setVisible(true);
                showTransactions();
            }
        });

        headerPanel.add(accountList);
        headerPanel.add(transactionList);


        accountPanel.add(headerPanel);
        accountPanel.add(accountTextLabel, BorderLayout.SOUTH);
        accountPanel.add(customerField, BorderLayout.NORTH);
        accountPanel.setVisible(false);

    }

    private void createCustomerPanel() {

        ActionListener listener = new ClickCustomerListener();

        JPanel headerPanel = new JPanel(new GridLayout(1,2));
        JPanel leftPanel = new JPanel(new GridLayout(7,1));

        buildMenu();

        nameField = new JTextField(15);
        nameField.setBorder(BorderFactory.createTitledBorder("Name"));
        leftPanel.add(nameField);

        surnameField = new JTextField(15);
        surnameField.setBorder(BorderFactory.createTitledBorder("Surname"));
        leftPanel.add(surnameField);

        pNrField = new JTextField(15);
        pNrField.setBorder(BorderFactory.createTitledBorder("Social Security Number"));
        leftPanel.add(pNrField);

        JButton addButton = new JButton("Add Customer");
        addButton.addActionListener(listener);
        leftPanel.add(addButton);

        JButton changeButton = new JButton("Change Name");
        changeButton.addActionListener(listener);
        leftPanel.add(changeButton);

        JButton deleteButton = new JButton("Delete Customer");
        deleteButton.addActionListener(listener);
        leftPanel.add(deleteButton);

        /*
        JButton saveButton = new JButton("Save Customers");
        // add to actionListener senare.
        leftPanel.add(saveButton);

        JButton loadButton = new JButton("Load Customers");
        // add to actionListener senare.
        leftPanel.add(loadButton);

         */

        JButton viewAccountButton = new JButton("View Customer Accounts");
        viewAccountButton.addActionListener(listener);
        leftPanel.add(viewAccountButton);

        headerPanel.add(leftPanel);

        customerList = new JList();
        customerList.setBorder(BorderFactory.createTitledBorder("Customers"));

        customerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                getCustomerInfo();
            }
        });

        headerPanel.add(customerList);
        customerPanel.add(customerTextLabel, BorderLayout.SOUTH);


        customerPanel.add(headerPanel);
        customerPanel.setVisible(true);
    }
    private void buildMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("Customer Page");
        menuItem.addActionListener(new BankListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Save Customers");
        menu.add(menuItem);
        menuItem = new JMenuItem("Load Customers");
        menu.add(menuItem);
        menuItem = new JMenuItem("Exit Program");
        menuItem.addActionListener(new ExitListener());
        menu.add(menuItem);

        setJMenuBar(menuBar);


    }

    private class BankListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {
            customerPanel.setVisible(true);
            accountPanel.setVisible(false);
        }
    }

    private class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            System.exit(0);
        }
    }

    public class ClickCustomerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String button = e.getActionCommand();
            if(button.equals("Add Customer")){
                addCustomer();
            }
            if(button.equals("Change Name")){
                changeCustomerName();
            }

            if(button.equals("Delete Customer")){
                deleteCustomer();
            }
            if(button.equals("View Customer Accounts")){
                if(showCustomersAccounts() != -1) {
                    accountTextLabel.setText("");
                    customerPanel.setVisible(false);
                    accountPanel.setVisible(true);
                    transactionList.setVisible(false);
                    clear();
                    setSsnField();
                }
            }
        }
    }

    public class ClickAccountListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String button = e.getActionCommand();
            if(button.equals("Withdraw")){
                withdrawFromAccount();
            }
            if(button.equals("Deposit")){
                depositToAccount();
            }
            if(button.equals("Create Savings Account")){
                createSaveAccount();
            }
            if(button.equals("Create Credit Account")){
                createCreditAccount();
            }
            if(button.equals("Remove Account")){
                removeAccount();
            }

        }
    }


    /*
    * Metod som lägger till en kund till banken och GUI. Utgår från BankLogic createCustomer metod.
     */
    private void addCustomer(){
        if(nameField.getText().isEmpty() || surnameField.getText().isEmpty() || pNrField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill in name, surname or pNr");
        } else {
            bankLogic.createCustomer(nameField.getText(), surnameField.getText(), pNrField.getText());
            customerList.setListData(bankLogic.getAllCustomers().toArray());
            clear();
        }

    }
    /*
    * Tar bort en customer från listan, samt har ett error meddelande om inget markerats.
    *
     */
    private void deleteCustomer(){
        int selected = customerList.getSelectedIndex();
        String info = bankLogic.getCustomerInfoByIndex(selected);
        String accountInfo = String.valueOf(bankLogic.getCustomersClosedAccounts(selected));
        if(selected != -1){
            bankLogic.deleteCustomerByIndex(selected);
            customerList.setListData(bankLogic.getAllCustomers().toArray());
            JOptionPane.showMessageDialog(null,"This customer was deleted: " + info + "\nClosed accounts: " + accountInfo);
            clear();
        } else {
            JOptionPane.showMessageDialog(null,"Please select Customer to remove");
        }
    }

    private void removeAccount(){
        int selected = accountList.getSelectedIndex();
        String info = bankLogic.getCustomersAccountByPositionToClosedString(customerIndex, selected);
        if(selected != -1){
            bankLogic.deleteAccountByIndex(customerIndex,selected);
            accountList.setListData(bankLogic.getCustomersAccountsByIndex(customerIndex).toArray());
            JOptionPane.showMessageDialog(null, "This account has been removed: " + info);
        } else {
            JOptionPane.showMessageDialog(null, "Please select account to remove");
        }
    }

    /*
    * Metod som ändrar en kunds namn och uppdaterar customerList med ändrade namnen.
    *
     */
    private void changeCustomerName(){
        if(pNrField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in pNr");
            return;
        }

        int selected = customerList.getSelectedIndex();
        String info = bankLogic.getCustomerInfoByIndex(selected);
        if(selected != -1){
            bankLogic.changeCustomerName(nameField.getText(),surnameField.getText(),pNrField.getText());
            customerList.setListData(bankLogic.getAllCustomers().toArray());
            JOptionPane.showMessageDialog(null,"Name was changed from: " + info + "\n to: " + bankLogic.getCustomerInfoByIndex(selected));
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Please select Customer to remove");
        }
    }

    private void withdrawFromAccount(){
        int selected = accountList.getSelectedIndex();
        if(selected!=-1){
            if(amountField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Please fill in amount!");
            } else {
                bankLogic.withdraw(ssnField.getText(), Integer.parseInt(accountField.getText()), Integer.parseInt(amountField.getText()));
                accountList.setListData(bankLogic.getCustomersAccountsByIndex(customerIndex).toArray());
                transactionList.setListData(bankLogic.getTransactions(ssnField.getText(), Integer.parseInt(accountField.getText())).toArray());
                amountClear();
            }
        } else {
            JOptionPane.showMessageDialog(null,"Please select an account to withdraw from");
        }
    }

    private void depositToAccount(){
        int selected = accountList.getSelectedIndex();
        if(selected!=-1){
            if(amountField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill in amount!");
            } else {
                bankLogic.deposit(ssnField.getText(), Integer.parseInt(accountField.getText()), Integer.parseInt(amountField.getText()));
                accountList.setListData(bankLogic.getCustomersAccountsByIndex(customerIndex).toArray());
                transactionList.setListData(bankLogic.getTransactions(ssnField.getText(), Integer.parseInt(accountField.getText())).toArray());
                amountClear();
            }
        } else {
            JOptionPane.showMessageDialog(null,"Please select an account to deposit to");
        }
    }

    private void createSaveAccount(){
        bankLogic.createSavingsAccount(ssnField.getText());
        accountList.setListData(bankLogic.getCustomersAccountsByIndex(customerIndex).toArray());
    }

    private void createCreditAccount(){
        bankLogic.createCreditAccount(ssnField.getText());
        accountList.setListData(bankLogic.getCustomersAccountsByIndex(customerIndex).toArray());
    }

    private void getCustomerInfo(){
        int position = customerList.getSelectedIndex();
        if(position > -1){
            String customerInfo = bankLogic.getCustomerByPosition(position);
            customerTextLabel.setText(customerInfo);
            customerField.setText(customerInfo);
        }
    }

    private void getAccountInfo(){
        int position = accountList.getSelectedIndex();
        if (position > -1){
            String accountInfo = bankLogic.getCustomersAccountByPositionToString(customerIndex, position);
            accountTextLabel.setText(accountInfo);
            accountField.setText(Integer.toString(bankLogic.getCustomersAccountByPositionToInt(customerIndex,position)));
        }
    }

    private void showTransactions() {
        int position = accountList.getSelectedIndex();
        if (position > -1) {
            if (bankLogic.getTransactions(ssnField.getText(), Integer.parseInt(accountField.getText())) == null) {
                return;
            } else {
                transactionList.setListData(bankLogic.getTransactions(ssnField.getText(), Integer.parseInt(accountField.getText())).toArray());
            }
        }
    }


    /*
    private void getTransactionInfo(){
        int accountPosition = accountList.getSelectedIndex();
        int position = transactionList.getSelectedIndex();
        if(accountPosition < -1){
            JOptionPane.showMessageDialog(null, "Please select an Account");
            return;
        }
        if(position > -1) {
            String transactionInfo = bankLogic.getCustomersTransactionByPosition(customerIndex, accountList.getSelectedIndex(), position);
            accountTextLabel.setText(transactionInfo);
            accountField.setText(transactionInfo);

        }
    }


     */



    private int showCustomersAccounts(){
        int position = customerList.getSelectedIndex();
        if(position > -1){
            accountList.setListData(bankLogic.getCustomersAccountsByIndex(position).toArray());
            customerIndex = position;
        } else {
            JOptionPane.showMessageDialog(null, "Please select a customer");
        }
        return position;
    }

    private void setSsnField(){
        int position = customerList.getSelectedIndex();
        ssnField.setText(bankLogic.getCustomersPNrByPosition(position));
    }

    /*
    * rensar allt som skrivits in i textfälten i GUI. används efter varje lyckad handling.
     */
    private void clear(){
        nameField.setText("");
        surnameField.setText("");
        pNrField.setText("");
        accountField.setText("");
        amountField.setText("");
        ssnField.setText("");
    }

    private void amountClear(){
        amountField.setText("");
    }


}
