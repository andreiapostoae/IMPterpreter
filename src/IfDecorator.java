
public class IfDecorator extends ProgramDecorator {

    public IfDecorator(Program decoratedProgram) {
        super(decoratedProgram);
    }

    public int evaluate(String instr, Context context) {
        return decoratedProgram.evaluate(instr, context);
    }
}
