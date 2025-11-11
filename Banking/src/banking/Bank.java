package banking;

import java.util.*;

// Hopefully final na rin 
public class Bank {
    private static final String ADMIN_PASSWORD = "admin123";
    
    private String bankName;
    private Map<String, Client> clients;
    private Map<String, Account> accounts;
    
    public Bank(String bankName) {
        this.bankName = bankName;
        this.clients = new HashMap<>();
        this.accounts = new HashMap<>();
    }
    
    // Client Management
    public Client createClient(String name, String pin) {
        Client client = new Client(name, pin);
        clients.put(client.getClientId(), client);
        return client;
    }
    
    public Client getClient(String clientId) {
        return clients.get(clientId);
    }
    
    public Client findClientByName(String name) {
        for (Client client : clients.values()) {
            if (client.getName().equalsIgnoreCase(name)) {
                return client;
            }
        }
        return null;
    }
    
    // Account Management
    public Account createAccount(String clientId, String accountType, double initialDeposit) {
        Client client = clients.get(clientId);
        if (client == null || initialDeposit < 0) {
            return null;
        }
        
        Account account;
        if (accountType.equalsIgnoreCase("SAVINGS")) {
            account = new SavingsAccount(clientId, initialDeposit);
        } else if (accountType.equalsIgnoreCase("CHECKING")) {
            account = new CheckingAccount(clientId, initialDeposit);
        } else if (accountType.equalsIgnoreCase("BUSINESS")) {
            account = new BusinessAccount(clientId, initialDeposit);
        } else {
            return null;
        }
        
        accounts.put(account.getAccountId(), account);
        client.addAccount(account.getAccountId());
        return account;
    }
    
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }
    
    public List<Account> getClientAccounts(String clientId) {
        List<Account> clientAccounts = new ArrayList<>();
        Client client = clients.get(clientId);
        if (client != null) {
            for (String accountId : client.getAccountIds()) {
                Account account = accounts.get(accountId);
                if (account != null) {
                    clientAccounts.add(account);
                }
            }
        }
        return clientAccounts;
    }
    
    // Monthly Operations
    public void applyMonthlyInterestAndFees() {
        for (Account account : accounts.values()) {
            account.applyInterest();
            account.applyServiceFee();
        }
    }
    
    // Admin Functions
    public boolean authenticateAdmin(String password) {
        return ADMIN_PASSWORD.equals(password);
    }
    
    public Collection<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }
    
    public Collection<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }
    
    public double getTotalBankBalance() {
        return accounts.values().stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }
    
    public String getBankName() {
        return bankName;
    }
}
