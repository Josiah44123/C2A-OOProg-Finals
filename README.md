[OOProg - Part 1 _ FINALS.md](https://github.com/user-attachments/files/22788145/OOProg.-.Part.1._.FINALS.md)
**Advanced Banking System**   
---

**Overview**   
The Advanced Banking System is a terminal-based Java application designed to simulate the daily operations of a banking institution. It demonstrates core Object-Oriented Programming (OOP) principles such as Encapsulation, Abstraction, Inheritance, and Polymorphism through a structured, class-based design.

This system provides a secure, modular, and easy-to-use interface where users can create accounts, perform transactions, and view their financial records, all through text-based menus.

An administrator mode is included, allowing staff to manage accounts and monitor key system activities.

**Scenario**  
A small community cooperative wants to modernize its manual bookkeeping system. They request a simple and offline banking prototype that will allow users to open accounts, deposit or withdraw funds, and keep track of their balances and transactions digitally, all while running smoothly on any local computer without an internet connection or external database.

**Initial Scope aka Core Features**

1. **Account Creation and Management**

Register new customers with unique account IDs.  
Savings, Checking, or Business.

2. **User Authentication**

Login system using a combination of account number, password, and PIN.

3. **Bank Transactions**

Deposit, withdraw, and transfer funds

4. **Admin Dashboard**  
5. **File Handling for Data Storage**  
6. **Error Handling and Validation**

**OOP Concepts Applied**  
**Encapsulation:** Private attributes for account and user details, accessible only via getter/setter methods.  
**Abstraction:** Abstract base classes for Account and User defining standard behaviors.  
**Inheritance:** Specialized classes (SavingsAccount, CheckingAccount, etc.) extend to the base Account class.  
**Polymorphism:** Overridden methods for interest calculation, transaction limits, and service fees.

**Final Project Scope(More Comprehensive for Group):**

1. **Account Creation and Management**

Register new customers with unique account IDs.  
Support multiple account types: Savings, Checking, or Business.  
Edit account information or close an account through admin access.  
Automatically record account creation date and initial balance.

2. **User Authentication**

Login system using a combination of account number, password, and PIN.  
Three-attempt login limit before temporary lockout.  
Basic password strength and input validation.

3. **Bank Transactions**

Deposit, withdraw, and transfer funds between existing accounts.  
Validate sufficient balance before each transaction.  
Automatically generate transaction IDs and timestamps.  
Update both sender and receiver balances instantly.

4. **Interest and Fee Computation**

Monthly interest applied to Savings accounts based on rate settings.  
Service fees for overdrafts or low balances (wherever applicable).  
Adjustable rates and fee percentages via admin configuration.

5. **Admin Dashboard**

Access all account records and perform administrative tasks.  
Configure global system settings such as interest rate and fee thresholds.  
Generate transaction summaries or simple financial reports in text format.

6. **File Handling for Data Storage**

Store all accounts and transactions using structured text or serialized object files.  
Load data on startup and save automatically on program exit.   
Prevent data loss through error-handled file writing and reading.

7. **Error Handling and Validation**

Prevent invalid inputs such as negative amounts or non-existent accounts.  
Handle corrupted files gracefully by rebuilding defaults.  
Provide clear user prompts and input re-entry options.



___________________________________________________________

                PART 2 : UML CLASS DIAGRAM
___________________________________________________________
![https://www.figma.com/board/4xTBRMSzdMeRowc6OUie8O/Banking_Class_Diagram_FINALS?node-id=8452-198&t=5Ve6MXncGXJavryB-0](image.png)
