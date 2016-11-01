
public class WhileDecorator extends ProgramDecorator {
    public WhileDecorator(Program decoratedProgram) {
        super(decoratedProgram);
    }

    public int evalueaza(String instr, Context context) {
        return decoratedProgram.evaluate(instr, context);
    }
}
