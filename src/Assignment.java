/**
 * Handles assignments using the '=' sign
 * e.g. a = b
 */
public class Assignment implements Program {

    @Override
    public int evaluate(String instruction, Context context) {
        String[] parts = Main.splitList(instruction.substring(3, instruction.length() - 1));
        String name = parts[0];
        String expression = parts[1];

        int value = Main.evaluateExpression(expression, context);
        if (value == Integer.MIN_VALUE)
            return -1;

        context.add(name, value);
        return 0;
    }

}
