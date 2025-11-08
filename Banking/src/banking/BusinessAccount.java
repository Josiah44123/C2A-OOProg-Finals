package banking;

public class BusinessAccount extends Account {
    private static final double INTEREST_RATE = 0.02; // 2% annual
    private static final double SERVICE_FEE = 25.0;
    private static final double TRANSACTION_LIMIT = 100000.0; // Daily transaction limit
    
    public BusinessAccount(String clientId, double initialDeposit) {
        super(clientId, initialDeposit);
    }
    
    @Override
    public boolean transfer(Account targetAccount, double amount) {
        if (amount <= 0 || amount > TRANSACTION_LIMIT || amount > this.getBalance()) {
            return false;
        }
        return super.transfer(targetAccount, amount); 
    }
    
    @Override
    public void applyInterest() {
  
    }
    
    @Override
    public void applyServiceFee() {
        
    }
    
    @Override
    public String getAccountType() {
        return "BUSINESS";
    }
    
    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }
    
    @Override
    public double getServiceFee() {
        return SERVICE_FEE;
    }
}
