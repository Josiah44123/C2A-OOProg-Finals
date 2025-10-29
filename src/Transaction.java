//Transaction Part of the Banking System

// Transaction class
class Transaction {
    private String transactionId;
    private String type;
    private double amount;
    private String description;
    private LocalDateTime timestamp;
    private String senderAccount;
    private String receiverAccount;
    
    public Transaction(String type, double amount, String description, String sender, String receiver) {
        this.transactionId = "TXN-" + System.currentTimeMillis();
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
        this.senderAccount = sender;
        this.receiverAccount = receiver;
    }
    
    public String getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "[" + transactionId + "] " + type + " - $" + amount + 
               " | " + description + " | " + timestamp.format(formatter);
    }
}
