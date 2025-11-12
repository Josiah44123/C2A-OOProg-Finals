package banking;

import java.util.List;
import java.util.Scanner;


public class BankingSystemApp {
    private static Bank bank;
    private static Scanner scanner;
    private static Client currentClient;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        
        bank = new Bank("Advanced Banking System");
        createSampleData();
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   ADVANCED BANKING SYSTEM - v1.0       ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        mainMenu();
        
        scanner.close();
    }
    
    private static void mainMenu() {
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Client Login");
            System.out.println("2. New Client Registration");
            System.out.println("3. Admin Mode");
            System.out.println("4. Exit");
            System.out.print("Select option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    clientLogin();
                    break;
                case 2:
                    clientRegistration();
                    break;
                case 3:
                    adminMode();
                    break;
                case 4:
                    System.out.println("Thank you for using " + bank.getBankName());
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    private static void clientLogin() {
        System.out.print("\nEnter Client Name: ");
        String name = scanner.nextLine();
        
        if (!InputValidator.isValidName(name)) {
            System.out.println("Invalid name format. " + InputValidator.getNameErrorMessage());
            return;
        }
        
        Client client = bank.findClientByName(name);
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }
        
        if (client.isLocked()) {
            System.out.println("Account is locked. Please contact admin.");
            return;
        }
        
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        
        if (!InputValidator.isValidPin(pin)) {
            System.out.println("PIN must be exactly 4 digits.");
            return;
        }
        
        if (client.authenticate(pin)) {
            currentClient = client;
            System.out.println("Login successful! Welcome, " + client.getName());
            clientMenu();
        } else {
            System.out.println("Invalid PIN. Attempts remaining: " + (3 - client.getFailedLoginAttempts()));
            if (client.isLocked()) {
                System.out.println("Account locked due to multiple failed attempts.");
            }
        }
    }
    
    private static void clientRegistration() {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();
        
        if (!InputValidator.isValidName(name)) {
            System.out.println("Invalid name format. " + InputValidator.getNameErrorMessage());
            return;
        }
        
        System.out.print("Create a 4-digit PIN: ");
        String pin = scanner.nextLine();
        
        if (!InputValidator.isValidPin(pin)) {
            System.out.println("PIN must be exactly 4 digits.");
            return;
        }
        
        Client client = bank.createClient(name, pin);
        System.out.println("Registration successful!");
        System.out.println("Your Client ID: " + client.getClientId());
        
        // Create initial account
        System.out.println("\nCreate your first account:");
        System.out.println("1. Savings Account (3% interest, $5 fee if balance < $500)");
        System.out.println("2. Checking Account (1% interest, $10 fee, overdraft protection)");
        System.out.println("3. Business Account (2% interest, $25 fee, corporate features)");
        System.out.print("Select account type: ");
        
        int accountType = getIntInput();
        System.out.print("Initial deposit amount: $");
        String depositStr = scanner.nextLine();
        
        if (!InputValidator.isValidAmount(depositStr)) {
            System.out.println("Invalid amount format. " + InputValidator.getAmountErrorMessage());
            return;
        }
        
        double deposit = Double.parseDouble(depositStr);
        
        String type = "";
        if (accountType == 1) type = "SAVINGS";
        else if (accountType == 2) type = "CHECKING";
        else if (accountType == 3) type = "BUSINESS";
        else {
            System.out.println("Invalid selection.");
            return;
        }
        
        Account account = bank.createAccount(client.getClientId(), type, deposit);
        
        if (account != null) {
            System.out.println("Account created successfully!");
            System.out.println("Account ID: " + account.getAccountId());
        } else {
            System.out.println("Failed to create account.");
        }
    }
    
    private static void clientMenu() {
        while (true) {
            System.out.println("\n=== CLIENT MENU ===");
            System.out.println("1. View Accounts");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Transaction History");
            System.out.println("6. Open New Account");
            System.out.println("7. Change PIN");
            System.out.println("8. Logout");
            System.out.print("Select option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    viewAccounts();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    openNewAccount();
                    break;
                case 7:
                    changePin();
                    break;
                case 8:
                    currentClient = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    
    private static void viewAccounts() {
        List<Account> accounts = bank.getClientAccounts(currentClient.getClientId());
        
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        
        System.out.println("\n=== YOUR ACCOUNTS ===");
        for (Account account : accounts) {
            System.out.println(account);
            System.out.println("  Interest Rate: " + (account.getInterestRate() * 100) + "%");
            System.out.println("  Service Fee: $" + account.getServiceFee());
        }
    }
    
    private static void deposit() {
        Account account = selectAccount();
        if (account == null) return;
        
        System.out.print("Enter deposit amount: $");
        String amountStr = scanner.nextLine();
        
        if (!InputValidator.isValidAmount(amountStr)) {
            System.out.println("Invalid amount format. " + InputValidator.getAmountErrorMessage());
            return;
        }
        
        double amount = Double.parseDouble(amountStr);
        
        if (account.deposit(amount)) {
            System.out.println("Deposit successful! New balance: $" + String.format("%.2f", account.getBalance()));
        } else {
            System.out.println("Deposit failed. Invalid amount.");
        }
    }
    
    private static void withdraw() {
        Account account = selectAccount();
        if (account == null) return;
        
        System.out.print("Enter withdrawal amount: $");
        String amountStr = scanner.nextLine();
        
        if (!InputValidator.isValidAmount(amountStr)) {
            System.out.println("Invalid amount format. " + InputValidator.getAmountErrorMessage());
            return;
        }
        
        double amount = Double.parseDouble(amountStr);
        
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful! New balance: $" + String.format("%.2f", account.getBalance()));
        } else {
            System.out.println("Withdrawal failed. Insufficient funds or invalid amount.");
        }
    }
    
    private static void transfer() {
        System.out.println("Select source account:");
        Account sourceAccount = selectAccount();
        if (sourceAccount == null) return;
        
        System.out.print("Enter target account ID: ");
        String targetAccountId = scanner.nextLine();
        
        if (!InputValidator.isValidAccountId(targetAccountId)) {
            System.out.println("Invalid account ID format.");
            return;
        }
        
        Account targetAccount = bank.getAccount(targetAccountId);
        
        if (targetAccount == null) {
            System.out.println("Target account not found.");
            return;
        }
        
        System.out.print("Enter transfer amount: $");
        String amountStr = scanner.nextLine();
        
        if (!InputValidator.isValidAmount(amountStr)) {
            System.out.println("Invalid amount format. " + InputValidator.getAmountErrorMessage());
            return;
        }
        
        double amount = Double.parseDouble(amountStr);
        
        if (sourceAccount.transfer(targetAccount, amount)) {
            System.out.println("Transfer successful!");
            System.out.println("Source balance: $" + String.format("%.2f", sourceAccount.getBalance()));
        } else {
            System.out.println("Transfer failed. Insufficient funds or invalid amount.");
        }
    }
    
    private static void viewTransactionHistory() {
        Account account = selectAccount();
        if (account == null) return;
        
        List<Transaction> transactions = account.getTransactionHistory();
        
        System.out.println("\n=== TRANSACTION HISTORY ===");
        System.out.println("Account: " + account.getAccountId());
        
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
    
    private static void openNewAccount() {
        System.out.println("\n=== OPEN NEW ACCOUNT ===");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        System.out.println("3. Business Account");
        System.out.print("Select account type: ");
        
        int choice = getIntInput();
        System.out.print("Initial deposit: $");
        String depositStr = scanner.nextLine();
        
        if (!InputValidator.isValidAmount(depositStr)) {
            System.out.println("Invalid amount format. " + InputValidator.getAmountErrorMessage());
            return;
        }
        
        double deposit = Double.parseDouble(depositStr);
        
        String type = "";
        if (choice == 1) type = "SAVINGS";
        else if (choice == 2) type = "CHECKING";
        else if (choice == 3) type = "BUSINESS";
        else {
            System.out.println("Invalid selection.");
            return;
        }
        
        Account account = bank.createAccount(currentClient.getClientId(), type, deposit);
        
        if (account != null) {
            System.out.println("Account created successfully!");
            System.out.println("Account ID: " + account.getAccountId());
        } else {
            System.out.println("Failed to create account.");
        }
    }
    
    private static void changePin() {
        System.out.print("Enter new 4-digit PIN: ");
        String newPin = scanner.nextLine();
        
        if (!InputValidator.isValidPin(newPin)) {
            System.out.println("PIN must be exactly 4 digits.");
            return;
        }
        
        currentClient.changePin(newPin);
        System.out.println("PIN changed successfully!");
    }
    
    private static void adminMode() {
        System.out.print("\nEnter admin password: ");
        String password = scanner.nextLine();
        
        if (!bank.authenticateAdmin(password)) {
            System.out.println("Invalid admin password.");
            return;
        }
        
        while (true) {
            System.out.println("\n=== ADMIN MODE ===");
            System.out.println("1. View All Clients");
            System.out.println("2. View All Accounts");
            System.out.println("3. Unlock Client Account");
            System.out.println("4. Apply Monthly Interest & Fees");
            System.out.println("5. View Bank Statistics");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    viewAllClients();
                    break;
                case 2:
                    viewAllAccounts();
                    break;
                case 3:
                    unlockClientAccount();
                    break;
                case 4:
                    applyMonthlyOperations();
                    break;
                case 5:
                    viewBankStatistics();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    
    private static void viewAllClients() {
        System.out.println("\n=== ALL CLIENTS ===");
        for (Client client : bank.getAllClients()) {
            System.out.println(client);
            System.out.println("  Locked: " + (client.isLocked() ? "YES" : "NO"));
        }
    }
    
    private static void viewAllAccounts() {
        System.out.println("\n=== ALL ACCOUNTS ===");
        for (Account account : bank.getAllAccounts()) {
            System.out.println(account);
        }
    }
    
    private static void unlockClientAccount() {
        System.out.print("Enter client ID to unlock: ");
        String clientId = scanner.nextLine();
        
        if (!InputValidator.isValidClientId(clientId)) {
            System.out.println("Invalid client ID format. Must be CLT followed by 4 digits.");
            return;
        }
        
        Client client = bank.getClient(clientId);
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }
        
        client.unlockAccount();
        System.out.println("Client account unlocked successfully.");
    }
    
    private static void applyMonthlyOperations() {
        System.out.println("Applying monthly interest and service fees...");
        bank.applyMonthlyInterestAndFees();
        System.out.println("Monthly operations completed.");
    }
    
    private static void viewBankStatistics() {
        System.out.println("\n== BANK STATISTICS ==");
        System.out.println("Bank Name: " + bank.getBankName());
        System.out.println("Total Clients: " + bank.getAllClients().size());
        System.out.println("Total Accounts: " + bank.getAllAccounts().size());
        System.out.println("Total Bank Balance: $" + String.format("%.2f", bank.getTotalBankBalance()));
    }
    
    private static Account selectAccount() {
        List<Account> accounts = bank.getClientAccounts(currentClient.getClientId());
        
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return null;
        }
        
        if (accounts.size() == 1) {
            return accounts.get(0);
        }
        
        System.out.println("\nSelect account:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i));
        }
        System.out.print("Choice: ");
        
        int choice = getIntInput();
        if (choice < 1 || choice > accounts.size()) {
            System.out.println("Invalid selection.");
            return null;
        }
        
        return accounts.get(choice - 1);
    }
    
    private static void createSampleData() {
        // Create sample clients
        Client client1 = bank.createClient("John Doe", "1234");
        Client client2 = bank.createClient("Jane Smith", "5678");
        
        // Create sample accounts
        bank.createAccount(client1.getClientId(), "SAVINGS", 1000.0);
        bank.createAccount(client1.getClientId(), "CHECKING", 500.0);
        bank.createAccount(client2.getClientId(), "BUSINESS", 5000.0);
        
        System.out.println("Sample data created for testing.");
    }
    
    private static int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    private static double getDoubleInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid amount: ");
            }
        }
    }
}

    
//NOTE: Pagkakasunod-sunod (sana)
//Abstract
//Savings
//Checking
//Business
//Transaction
//Client
//Admin
//Main

//Expected Output 
// ===========================================
//    WELCOME TO ADVANCED BANKING SYSTEM
// ===========================================

// --- MAIN MENU ---
// 1. Register New Client
// 2. Client Login
// 3. Admin Login
// 4. Exit
// Choose option: 
