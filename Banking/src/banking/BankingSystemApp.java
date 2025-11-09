package banking;


import java.util.Scanner;

public class BankingSystemApp {
    private static Bank bank;
    private static Scanner scanner;
    private static Client currentClient;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Bank bank = new Bank("Bank-System");

   
       
        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║   WELCOME TO DLSL ADVANCED BANKING SYSTEM   ║");
        System.out.println("╚═════════════════════════════════════════════╝");

        while (true) {
            char option = displayMainMenu(scanner);

            switch (option) {
                case '1' : {registerNewClient(); break;}
                case '2' : 
                case '3' :
                case '4' : System.exit(0);
                default : break;
            }
        }
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
