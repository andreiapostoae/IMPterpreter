
public class Operand {
    public String string;

    public Operand(String s) {
        string = s;
    }

    public int getValue(Context context) {
        char[] characters = string.toCharArray();
        char firstChar = characters[0];

        if (CharType.isLetter(firstChar))
            return context.valueOf(string);

        if (CharType.isDigit(firstChar))
            return Integer.parseInt(string);
        return Integer.MIN_VALUE;
    }

    public String toString() {
        return string;
    }
}
