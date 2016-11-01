/**
 * Mini library for character type identification.
 */
public class CharType {
    public static boolean isOperator(char c) {
        if (c == '*' || c == '-' || c == '+' || c == '=' || c == '<')
            return true;
        return false;
    }

    public static boolean isDigit(char c) {
        if (c >= '1' && c <= '9')
            return true;
        return false;
    }

    public static boolean isLetter(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
            return true;
        return false;
    }
}
