
public class ReturnDecorator extends ProgramDecorator {
    public ReturnDecorator(Program decoratedProgram) {
        super(decoratedProgram);
    }

    public int evaluate(String instr, Context context) {
        return decoratedProgram.evaluate(instr, context);
    }
}
