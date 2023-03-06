package interpreter.commands;

import interpreter.Command;
import interpreter.RuntimeContext;
import interpreter.exceptions.InterpreterException;

public class SqrtCommand extends Command {
    @Override
    public void doAction(RuntimeContext context) {
        if (context.getDataStack().isEmpty())
            throw new InterpreterException("bad command with an empty stack");
        double v = context.getDataStack().pop();
        context.getDataStack().push(Math.sqrt(v));
    }

    @Override
    public String getName() {
        return "SQRT";
    }
}
