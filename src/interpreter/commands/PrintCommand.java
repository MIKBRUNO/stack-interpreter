package interpreter.commands;

import interpreter.Command;
import interpreter.RuntimeContext;

import java.util.EmptyStackException;

public class PrintCommand extends Command {
    @Override
    public void doAction(RuntimeContext context) {
        try {
            context.getOutputStream().println(context.getDataStack().peek());
        }
        catch (EmptyStackException e) {
            context.getOutputStream().println();
        }
    }
    @Override
    public String getName() {
        return "PRINT";
    }
}
