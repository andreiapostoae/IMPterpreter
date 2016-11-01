
public class Semicolon implements Program {

    @Override
    public int evaluate(String instr, Context context) {
        String instructions = instr.substring(3, instr.length() - 1);
        String[] list = Main.splitList(instructions);
        Main.executeInstruction(list[0], context);
        Main.executeInstruction(list[1], context);
        return 0;
    }


}
