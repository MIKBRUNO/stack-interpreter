package interpreter.commands;

import interpreter.Command;
import interpreter.RuntimeContext;
import interpreter.exceptions.InterpreterException;

import java.util.List;

public class PushCommand extends Command {
    @Override
    public void doAction(RuntimeContext context) throws InterpreterException {
        try {
            double value;
            try {
                value = Double.parseDouble(Value);
            }
            catch (NumberFormatException e) {
                value = context.getDefinitions().get(Value);
            }
            context.getDataStack().push(value);
        }
        catch (NullPointerException | NumberFormatException | ClassCastException e) {
            throw new InterpreterException("bad arguments for" + getName());
        }
    }
    @Override
    public String getName() {
        return "PUSH";
    }
    @Override
    public void takeArguments(List<Object> args) throws InterpreterException {
        if (args.size() != 1)
            throw new InterpreterException("wrong number of arguments for" + getName());
        try {
            Object arg = args.get(0);
            if (arg.getClass().equals(Double.class))
                Value = String.valueOf(arg);
            else
                Value = (String) arg;
        }
        catch (ClassCastException e) {
            throw new InterpreterException("bad argument for" + getName());
        }
    }
    private String Value;
}
