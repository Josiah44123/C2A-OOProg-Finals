

import java.util.Scanner;

public class BankingSystemApp {
    private static Bank bank;
    private static Scanner scanner;
    private static Client currentClient;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        
   
       
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   WELCOME TO ADVANCED BANKING SYSTEM   ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        
        scanner.close();
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
