package validator;

import java.util.regex.Pattern;

public class Validator {

    // Basic email format regex (simple, not strict RFC)
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    public static boolean isValidUsername(String username) {
        return username != null && !username.trim().isEmpty();
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.trim().length() >= 6;
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    // Full validation method (returns null if all valid, or message if not)
    public static String validateUser(String username, String password, String email) {
        if (!isValidUsername(username)) {
            return "Username cannot be empty.";
        }

        if (!isValidPassword(password)) {
            return "Password must be at least 6 characters.";
        }

        if (!isValidEmail(email)) {
            return "Invalid email format.";
        }

        return null; // all valid
    }
}
