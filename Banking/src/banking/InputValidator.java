package banking;

import java.util.regex.Pattern;

// Input validation utility using da regex
public class InputValidator {
    
    // Regex patterns
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s'-]+$");
    private static final Pattern PIN_PATTERN = Pattern.compile("^\\d{4}$");
    private static final Pattern AMOUNT_PATTERN = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
    private static final Pattern ACCOUNT_ID_PATTERN = Pattern.compile("^[A-Z0-9]{8,}$");
    private static final Pattern CLIENT_ID_PATTERN = Pattern.compile("^CLT\\d{4}$");
    
    // Validates client name - At least 1 character and Only letters, spaces, hyphens, apostrophes

    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        String trimmedName = name.trim();
        return trimmedName.length() >= 1 && NAME_PATTERN.matcher(trimmedName).matches();
    }
    
    // Validates PiN - Exactly 4 digits
    
    public static boolean isValidPin(String pin) {
        return pin != null && PIN_PATTERN.matcher(pin).matches();
    }
    
    /**
     * Validates monetary amount
     * - Non-negative number
     * - Up to 2 decimal places
     */
    public static boolean isValidAmount(String amount) {
        if (amount == null || !AMOUNT_PATTERN.matcher(amount).matches()) {
            return false;
        }
        try {
            double value = Double.parseDouble(amount);
            return value >= 0 && value <= 999999999.99;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Validates account ID format - 8+ alphanumeric characters
     
    public static boolean isValidAccountId(String accountId) {
        return accountId != null && ACCOUNT_ID_PATTERN.matcher(accountId).matches();
    }
    
    // Validates client ID format
  //   - Format: CLT followed by 4 digits
  
    public static boolean isValidClientId(String clientId) {
        return clientId != null && CLIENT_ID_PATTERN.matcher(clientId).matches();
    }
    
    //Get validation error message for da name
 
    public static String getNameErrorMessage() {
        return "Name must contain only letters, spaces, hyphens, or apostrophes.";
    }
    
    // Get validation error message for amount
    
    public static String getAmountErrorMessage() {
        return "Amount must be a non-negative number with up to 2 decimal places (max $999,999,999.99).";
    }
}