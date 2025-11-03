//Client part of the Banking System

// Client class
//updated (hopefully final) client code 1/11/2025

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int clientClient = 1000;
    
    private String clientId;
    private String name;
    private String pin;
    private int failedLoginAttempts;
    private boolean isLocked;
    private List<String> accountIds;
    
    public Client(String name, String pin) {
        this.clientId = generateClientId();
        this.name = name;
        this.pin = pin;
        this.failedLoginAttempts = 0;
        this.isLocked = false;
        this.accountIds = new ArrayList<>();
    }
    
    private static synchronized String generateClientId() {
        return "CLI-" + (clientClient++);
    }
    
    public boolean authenticate(String inputPin) {
        if (isLocked) {
            return false;
        }
        
        if (pin.equals(inputPin)) {
            failedLoginAttempts = 0;
            return true;
        } else {
            failedLoginAttempts++;
            if (failedLoginAttempts >= 3) {
                isLocked = true;
            }
            return false;
        }
    }
    
    public void unlockAccount() {
        isLocked = false;
        failedLoginAttempts = 0;
    }
    
    public void changePin(String newPin) {
        this.pin = newPin;
    }
    
    public void addAccount(String accountId) {
        accountIds.add(accountId);
    }
    
    // Getters
    public String getClientId() { return clientId; }
    public String getName() { return name; }
    public boolean isLocked() { return isLocked; }
    public int getFailedLoginAttempts() { return failedLoginAttempts; }
    public List<String> getAccountIds() { return new ArrayList<>(accountIds); }
    
    @Override
    public String toString() {
        return String.format("%s - %s (%d accounts)", clientId, name, accountIds.size());
    }
}
