

import java.util.Scanner;

public class BankingSystemApp {
    private static Bank bank;
    private static Scanner scanner;
    private static Client currentClient;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Bank bank = new Bank("Bank-System");

   
       
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   WELCOME TO ADVANCED BANKING SYSTEM   ║");
        System.out.println("╚════════════════════════════════════════╝");

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

    public static char displayMainMenu(Scanner scanner){
        System.out.println("--- MAIN MENU ---");
        System.out.println("1. Register New Client");
        System.out.println("2. Client Login");
        System.out.println("3. Admin Login");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
        char option = scanner.next().charAt(0);

        return option;
    }

    public static void registerNewClient(){ //lagyan regex
        String name;
        String password;
        int pin;

        System.out.println("--- REGISTER NEW CLIENT ---");
        System.out.print("Enter Client Name: ");
        name = scanner.nextLine();
        System.out.print("Enter Password: ");
        password = scanner.nextLine();
        System.out.print("Enter 4-digit Pin: ");
        pin = scanner.nextInt(); 

        bank.createClient(name, password, pin);
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
