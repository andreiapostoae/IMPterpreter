
public class SemicolonDecorator extends ProgramDecorator {
    public SemicolonDecorator(Program decoratedProgram) {
        super(decoratedProgram);
    }

    public int evaluate(String instr, Context context) {
        return decoratedProgram.evaluate(instr, context);
    }
}
