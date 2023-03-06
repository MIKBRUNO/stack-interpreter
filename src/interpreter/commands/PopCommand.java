package interpreter.commands;

import interpreter.Command;
import interpreter.RuntimeContext;

public class PopCommand extends Command {
    @Override
    public void doAction(RuntimeContext context) {
        if (!context.getDataStack().isEmpty())
            context.getDataStack().pop();
    }
    @Override
    public String getName() {
        return "POP";
    }
}
