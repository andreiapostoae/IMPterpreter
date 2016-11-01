import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static int returnValue = -Integer.MAX_VALUE;

    /**
     * Parses the expression and then evaluates it using two stacks: opStack containing the operators and operands
     * and countStack containing 0, 1, or 2 depending on how many more operands there are needed for an operation.
     * Algorithm:
     * Every time an operand is found, the top value from countStack is decremented. When this top value reaches 0,
     * it means an operation is complete (containing 2 operands and an operator), which can be evaluated by popping
     * 3 values from opStack. The result of the operation is pushed back into opStack.
     * The process is repeated until opStack contains a single element, which will be the result.
     *
     * @param exp - a string, which represents an expression (that follows the specification in the homework);
     * @param c   - the context (a one-to-one association between variables and values);
     * @return - the result of the evaluation of the expression;
     */
    public static Integer evaluateExpression(String exp, Context c) {
        Stack<Operand> opStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        String currentValue = "";

        char[] expression = exp.toCharArray();
        int i = 0;

        boolean ok = false;
        for (int j = 0; j < expression.length; j++) {
            if (CharType.isOperator(expression[j])) {
                ok = true;
            }
        }

        if (ok == false) {
            if (CharType.isLetter(exp.charAt(0))) {
                int correctness = c.valueOf(exp);
                return correctness;
            }
            return Integer.parseInt(exp);
        }

        while (i <= expression.length - 2) {
            char ch = expression[i];
            char next = expression[i + 1];
            if (ch == ']' || ch == '[' || ch == ' ') {
                i++;
                continue;
            }
            Operand operand = new Operand(String.valueOf(ch));
            if (CharType.isOperator(ch)) {
                opStack.push(operand);
                countStack.push(2);
            } else {
                if (CharType.isDigit(ch) || CharType.isLetter(ch)) {
                    currentValue = currentValue + ch;
                    if ((CharType.isDigit(ch) && CharType.isDigit(next))
                            || (CharType.isLetter(ch) && CharType.isLetter(next))) {
                        i++;
                        continue;
                    }
                    operand = new Operand(currentValue);
                    currentValue = "";
                    opStack.push(operand);
                    if (countStack.isEmpty())
                        break;
                    int x = countStack.pop();
                    x--;
                    if (x != 0) {
                        countStack.push(x);
                    } else {
                        while (x == 0 && opStack.size() >= 3) {
                            int op1 = opStack.pop().getValue(c);
                            int op2 = opStack.pop().getValue(c);
                            Operand operator = opStack.pop();
                            Operand result;
                            if (operator.string.equals("*")) {
                                result = new Operand(String.valueOf(op1 * op2));
                            } else {
                                if (operator.string.equals("+")) {
                                    result = new Operand(String.valueOf(op1 + op2));
                                } else {
                                    result = new Operand(String.valueOf(op1 - op2));
                                }
                            }
                            opStack.push(result);
                            while (x == 0 && !countStack.isEmpty())
                                x = countStack.pop();
                            if (x != 0) {
                                x--;
                                countStack.push(x);
                            }
                        }
                    }
                }
            }
            i++;
        }

        int rez = opStack.pop().getValue(c);
        return rez;
    }

    /**
     * @param program - a string, which represents a list of instructions
     * @return - the result of the evaluation of the expression;
     */
    public static Integer evalProgram(String program) {
        Context context = new Context();
        evalInstruction(program, context);
        if (context.isCorrect == false)
            return -1;
        return returnValue;
    }

    /**
     * Evaluates an instruction. If it's a simple one, simply execute it, else split it into parts
     * using the ; delimiter, then execute those parts recursively.
     */
    public static int evalInstruction(String instruction, Context context) {
        if (instruction.charAt(1) != ';') {
            int k = executeInstruction(instruction, context);
            if (k == -1)
                return -1;
        } else {
            String cutProgram = instruction.substring(3, instruction.length() - 1);
            String[] instr = splitList(cutProgram);
            evalInstruction(instr[0], context);
            evalInstruction(instr[1], context);
        }
        return 0;
    }

    /**
     * Checks if a program is correct (if there are no uninitialized values)
     */
    public static Boolean checkCorrectness(String program) {
        try {
            int k = evalProgram(program);
            boolean isReturnLast = true;

            int returnIndex = program.indexOf("return");
            if (returnIndex == -1) {
                isReturnLast = false;
            } else {
                if (program.substring(returnIndex, program.length() - 1).contains(";")
                        || program.substring(returnIndex, program.length() - 1).contains("="))
                    isReturnLast = false;
            }

            if (isReturnLast && k != -1)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;

    }


    /**
     * Function that parses a program into a list of instructions.
     *
     * @param s - a string, that contains a list of programs, each
     *          program starting with a '[' and ending with a matching ']'.
     *          Programs are separated by the whitespace character;
     * @return - array of strings, each element in the array representing
     * a program;
     * Example: "[* [+ 1 2] 3] [* 4 5]" -> "[* [+ 1 2] 3]" & "[* 4 5]";
     */
    public static String[] splitList(String s) {
        List<String> l = new LinkedList<String>();
        int inside = 0;
        int start = 0, stop = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                inside++;
                stop++;
                continue;
            }
            if (s.charAt(i) == ']') {
                inside--;
                stop++;
                continue;
            }
            if (s.charAt(i) == ' ' && inside == 0) {
                l.add(s.substring(start, stop));
                start = i + 1; //starting after whitespace
                stop = start;

                continue;
            }
            stop++; //no special case encountered
        }
        if (stop > start) {
            l.add(s.substring(start, stop));
        }

        return l.toArray(new String[l.size()]);

    }

    /**
     * Uses Decorator classes for easy readability and understanding. The value of isCorrect is updated
     * in order to know if the program is correct.
     *
     * @param instr
     * @param context
     * @return
     */
    public static int executeInstruction(String instr, Context context) {
        int isCorrect = 1;
        switch (instr.charAt(1)) {
            case ';':
                Program semicolonProgram = new SemicolonDecorator(new Semicolon());
                isCorrect = semicolonProgram.evaluate(instr, context);
                break;
            case '=':
                Program assignmentProgram = new AssignmentDecorator(new Assignment());
                isCorrect = assignmentProgram.evaluate(instr, context);
                break;
            case 'w':
                Program whileProgram = new WhileDecorator(new While());
                isCorrect = whileProgram.evaluate(instr, context);
                break;
            case 'i':
                Program ifProgram = new IfDecorator(new If());
                isCorrect = ifProgram.evaluate(instr, context);
                break;
            case 'r':
                Program returnProgram = new ReturnDecorator(new Return());
                isCorrect = returnProgram.evaluate(instr, context);
                break;
        }

        if (isCorrect == -1)
            return -1;
        return 0;
    }


    public static void main(String[] args) {
        /* used for testing without JUnit */
    }
}
