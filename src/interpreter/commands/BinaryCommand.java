package interpreter.commands;

import interpreter.Command;
import interpreter.RuntimeContext;
import interpreter.exceptions.InterpreterException;

import java.util.EmptyStackException;

public abstract class BinaryCommand extends Command {
    @Override
    public void doAction(RuntimeContext context) throws InterpreterException {
        try {
            double a = context.getDataStack().pop();
            double b = context.getDataStack().pop();
            context.getDataStack().push(operation(a, b));
        }
        catch (EmptyStackException e) {
            throw new InterpreterException("not enough elements in stack for " + getName());
        }
    }
    public abstract double operation(double a, double b);
}
