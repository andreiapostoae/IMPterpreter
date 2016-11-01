
public class AssignmentDecorator extends ProgramDecorator {
    public AssignmentDecorator(Program decoratedProgram) {
        super(decoratedProgram);
    }

    public int evaluate(String instr, Context context) {
        return decoratedProgram.evaluate(instr, context);
    }
}
