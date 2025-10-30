import java.io*;
import java.util.*;

public class Bank implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static final String DATA_FILE = "bank_data.ser";
    private static final String ADMIN_PASSWORD = "admin123";

    private String bankName;
    private Map<String, Client> clients;
    private Map<String, Account> accounts;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.clients = new HashMap<>();
        this.accounts = new HashMap<>();
    }
public Client createClient(String name, String password, int pin) {
        Client client = new Client(name, password, pin);
        clients.put(client.getClientId(), client);
        return client;
    }

public Account createAccount(String clientId, String accountType, double initialDeposit) {
        Client client = getClientById(clientId);
        if (client == null || initialDeposit < 0) {
            return null;
        }
      Account account;
      if (accountType.equalsIgnoreCase("Savings")) {
          account = new SavingsAccount(clientId, initialDeposit);
      } else if (accountType.equalsIgnoreCase("Checking")) {
          account = new CheckingAccount(clientId, initialDeposit);
      } else {
          return null;
      }
        accounts.put(account);
        client.addAccount(account.getAccountId());
        return account;
}
