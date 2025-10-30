//Finalized


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Transaction record for account history

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private String type;
    private double amount;
    private String description;
    private LocalDateTime timestamp;
    
    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }
    
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }
    
    @Override
    public String toString() {
        return String.format("[%s] %s: $%.2f - %s", 
            timestamp.format(FORMATTER), type, amount, description);
    }
}
