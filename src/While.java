/**
 * Handles while loops.
 * e.g. while(a < 3)
 * a = a + 1;
 */
public class While implements Program {

    @Override
    public int evaluate(String instr, Context context) {
        Context context2 = context.copy();
        String toExecute = instr.substring(7, instr.length() - 1);
        String[] commands = Main.splitList(toExecute);

        char operator = instr.charAt(8);
        int cutPos = 0;
        if (operator == '<')
            cutPos = 3;
        else if (operator == '=') {
            cutPos = 4;
        }
        String condition = commands[0].substring(cutPos, commands[0].length() - 1);
        String[] sides = Main.splitList(condition);
        int expr1 = Main.evaluateExpression(sides[0], context2);
        int expr2 = Main.evaluateExpression(sides[1], context2);
        if (expr1 == Integer.MIN_VALUE || expr2 == Integer.MIN_VALUE) {
            return -1;
        }

        if (operator == '<')
            while (expr1 < expr2) {
                Main.executeInstruction(commands[1], context2);
                expr1 = Main.evaluateExpression(sides[0], context2);
                expr2 = Main.evaluateExpression(sides[1], context2);
                if (expr1 == Integer.MIN_VALUE || expr2 == Integer.MIN_VALUE) {
                    return -1;
                }
            }

        if (operator == '=')
            while (expr1 == expr2) {
                Main.executeInstruction(commands[1], context2);
                expr1 = Main.evaluateExpression(sides[0], context2);
                expr2 = Main.evaluateExpression(sides[1], context2);
                if (expr1 == Integer.MIN_VALUE || expr2 == Integer.MIN_VALUE) {
                    return -1;
                }
            }
        context.overwrite(context2);

        return 0;
    }

}
