/**
 * Handles return statements such as "return 1+2"
 */
public class Return implements Program {

    @Override
    public int evaluate(String instr, Context context) {
        String toExecute = instr.substring(8, instr.length() - 1);
        Main.returnValue = Main.evaluateExpression(toExecute, context);
        if (Main.returnValue == Integer.MIN_VALUE) {
            return -1;
        }
        return 0;
    }

}
