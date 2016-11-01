/**
 * Handles 'if' structure
 * e.g. if(a == b)
 * a = a + 1;
 * else
 * b = b + 1;
 */

public class If implements Program {

    @Override
    public int evaluate(String instruction, Context context) {
        Context context2 = context.copy();
        String toExecute = instruction.substring(4, instruction.length() - 1);
        String[] blocks = Main.splitList(toExecute);
        char operator = instruction.charAt(5);
        int cutPos = 0;

        if (operator == '<')
            cutPos = 3;
        else if (operator == '=')
            cutPos = 4;

        String condition = blocks[0].substring(cutPos, blocks[0].length() - 1);
        String[] sides = Main.splitList(condition);
        int expr1 = Main.evaluateExpression(sides[0], context2);
        int expr2 = Main.evaluateExpression(sides[1], context2);

        if (expr1 == Integer.MIN_VALUE || expr2 == Integer.MIN_VALUE)
            return -1;

        if (operator == '<')
            if (expr1 < expr2)
                Main.executeInstruction(blocks[1], context2);
            else if (blocks.length == 3)
                Main.executeInstruction(blocks[2], context2);

        if (operator == '=')
            if (expr1 == expr2)
                Main.executeInstruction(blocks[1], context2);
            else if (blocks.length == 3)
                Main.executeInstruction(blocks[2], context2);

        context.overwrite(context2);
        return 0;
    }
}
