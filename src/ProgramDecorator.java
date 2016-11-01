

public class ProgramDecorator implements Program {
    protected Program decoratedProgram;

    public ProgramDecorator(Program decoratedProgram) {
        this.decoratedProgram = decoratedProgram;
    }

    @Override
    public int evaluate(String instr, Context context) {
        return decoratedProgram.evaluate(instr, context);
    }
}
