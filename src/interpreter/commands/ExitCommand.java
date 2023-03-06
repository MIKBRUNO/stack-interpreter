package interpreter.commands;

import interpreter.Command;
import interpreter.RuntimeContext;

public class ExitCommand extends Command {
    @Override
    public void doAction(RuntimeContext context) {
        context.isRunning = false;
    }
    @Override
    public String getName() {
        return "EXIT";
    }
}
