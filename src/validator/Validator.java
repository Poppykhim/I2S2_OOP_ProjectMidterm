package validator;
public class Validator {

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isPositiveInteger(String input) {
        try {
            return Integer.parseInt(input) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidPrice(String input) {
        try {
            return Double.parseDouble(input) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidCategory(String category) {
        return category != null && !category.trim().isEmpty();
    }
}
