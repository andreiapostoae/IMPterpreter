import java.util.HashMap;
import java.util.Map;

/**
 * Contains the variable names with their values and a flag that becomes false if the grammar is not used properly.
 */
public class Context {
    public boolean isCorrect = true;
    Map<String, Integer> variables = new HashMap<>(20);

    public void add(String v, Integer i) {
        if (variables.containsValue(v))
            variables.remove(v);
        variables.put(v, i);
    }

    public Integer valueOf(String v) {
        if (variables.containsKey(v))
            return variables.get(v);

        if (!v.equals("while") && !v.equals("return") && !v.equals("if")) {
            isCorrect = false;
            throw new NumberFormatException("the variable " + v + " doesn't exist in scope");
        }
        return -1;
    }

    public String toString() {
        return variables.toString();
    }

    public void overwrite(Context context) {
        for (String key : variables.keySet())
            for (String key2 : context.variables.keySet())
                if (key.equals(key2))
                    add(key, context.variables.get(key2));
    }

    public Context copy() {
        Context c = new Context();
        for (String key : variables.keySet())
            c.add(key, variables.get(key));
        return c;
    }
}