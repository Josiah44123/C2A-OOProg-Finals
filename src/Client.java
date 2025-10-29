//Client part of the Banking System

// Client class
class Client {
    private String clientId;
    private String name;
    private String password;
    private int pin;
    private boolean locked;
    private int failedLoginAttempts;
    private int clientCounter;

    public Client(String name, String password, int pin) {
        this.clientId = "CLI-" + System.currentTimeMillis();
        this.name = name;
        this.password = password;
        this.pin = pin;
        this.locked = false;
        this.failedLoginAttempts = 0;
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public boolean checkPassword(String password, int pin) {
        if (locked) {
            System.out.println("Account is locked due to too many failed attempts!");
            return false;
        }

        if (this.password.equals(password) && this.pin == pin) {
            failedLoginAttempts = 0;
            return true;
        } else {
            failedLoginAttempts++;
            if (failedLoginAttempts >= 3) {
                locked = true;
                System.out.println("Account locked due to 3 failed login attempts!");
            }
            return false;
        }
    }

    public boolean isLocked() {
        return locked;
    }

    public void unlockAccount() {
        locked = false;
        failedLoginAttempts = 0;
    }
}
