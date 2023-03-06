package interpreter;

import interpreter.exceptions.InterpreterException;

import java.util.List;

public abstract class Command {
    public abstract void doAction(RuntimeContext context);
    public abstract String getName();
    public void takeArguments(List<Object> args) {
        if (args.size() > 0) {
            throw new InterpreterException("wrong number of arguments for " + getName());
        }
    }
}
