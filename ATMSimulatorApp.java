import java.util.*;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private String pin;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }

    public boolean authenticate(String inputPin) {
        return pin.equals(inputPin);
    }

    public double getBalance() { 
        return balance; 
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful: Rs " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount!");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful: Rs " + amount);
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (recipient == null) {
            System.out.println("Recipient account not found!");
            return;
        }
        if (amount <= 0) {
            System.out.println("Invalid transfer amount!");
        } else if (amount > balance) {
            System.out.println("Insufficient balance for transfer!");
        } else {
            balance -= amount;
            recipient.balance += amount;
            System.out.println("Transfer successful: Rs " + amount + " to " + recipient.getAccountNumber());
        }
    }

    public void checkBalance() {
        System.out.println("Your current balance is: Rs " + balance);
    }
}

class ATM {
    private HashMap<String, BankAccount> accounts;
    private Scanner scanner;

    public ATM() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);

        // Sample accounts
        accounts.put("123456", new BankAccount("123456", "Roshan Singh", "4321", 10000));
        accounts.put("654321", new BankAccount("654321", "Alice Johnson", "1234", 5000));
    }

    public void start() {
        System.out.println("\n\n==== Welcome to ATM Simulator ====\n\n");
        BankAccount currentAccount = authenticateUser();
        if (currentAccount == null) {
            System.out.println("Too many failed attempts. Exiting...");
            return;
        }

        int choice = 0;
        do {
            System.out.println("\nWelcome, " + currentAccount.getAccountHolder());
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number 1-5.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: Rs ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        currentAccount.withdraw(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount!");
                    }
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: Rs ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        currentAccount.deposit(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount!");
                    }
                    break;
                case 3:
                    System.out.print("Enter recipient account number: ");
                    String recipientAccNum = scanner.nextLine();
                    BankAccount recipient = accounts.get(recipientAccNum);
                    System.out.print("Enter transfer amount: Rs ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        currentAccount.transfer(recipient, amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount!");
                    }
                    break;
                case 4:
                    currentAccount.checkBalance();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Please enter a valid choice (1-5).");
            }
        } while (choice != 5);
    }

    private BankAccount authenticateUser() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter your account number: ");
            String accNum = scanner.nextLine();
            if (!accounts.containsKey(accNum)) {
                System.out.println("Account not found. Try again.");
                attempts++;
                continue;
            }
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();
            BankAccount acc = accounts.get(accNum);
            if (acc.authenticate(pin)) {
                return acc;
            } else {
                System.out.println("Incorrect PIN. Try again.");
                attempts++;
            }
        }
        return null;
    }
}

public class ATMSimulatorApp {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
