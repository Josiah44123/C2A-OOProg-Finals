package banking;

import java.util.regex.Pattern;

/**
 * Input validation utility using regex
 * Validates user inputs for security and correctness
 */
public class InputValidator {

    // Regex patterns
    private static final Pattern NAME_PATTERN = null;
    private static final Pattern PIN_PATTERN = null;
    private static final Pattern AMOUNT_PATTERN = null;
    private static final Pattern ACCOUNT_ID_PATTERN = null;
    private static final Pattern CLIENT_ID_PATTERN = null;

    // Validates client name
     
    public static boolean isValidName(String name) {
        // TODO: Implement name validation
        return false;
    }

    // Validates PIN
   
    public static boolean isValidPin(String pin) {
        // TODO: Implement PIN validation
        return false;
    }

    // Validates monetary amount
     
    public static boolean isValidAmount(String amount) {
        // TODO: Implement amount validation
        return false;
    }

    //Validates account ID format
     
    public static boolean isValidAccountId(String accountId) {
        // TODO: Implement account ID validation
        return false;
    }

    // Validates client ID format
     
    public static boolean isValidClientId(String clientId) {
        // TODO: Implement client ID validation
        return false;
    }

    // Get validation error message for name
    
    public static String getNameErrorMessage() {
        // TODO: Return name error message
        return null;
    }

    // Get validation error message for amount
     
    public static String getAmountErrorMessage() {
        // TODO: Return amount error message
        return null;
    }
}
