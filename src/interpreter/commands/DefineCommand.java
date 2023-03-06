package interpreter.commands;

import interpreter.Command;
import interpreter.RuntimeContext;
import interpreter.exceptions.InterpreterException;

import java.util.List;

public class DefineCommand extends Command {
    @Override
    public void doAction(RuntimeContext context) {
        if (DefinitionValue == null || DefinitionName == null)
            throw new InterpreterException("no arguments for " + getName());
        context.getDefinitions().put(DefinitionName, DefinitionValue);
    }
    @Override
    public String getName() {
        return "DEFINE";
    }
    @Override
    public void takeArguments(List<Object> args) {
        if (args == null || args.size() != 2)
            throw new InterpreterException("bad number of arguments for " + getName());
        try {
            DefinitionName = (String) args.get(0);
            Object arg1 = args.get(1);
            if (arg1.getClass().equals(Double.class))
                DefinitionValue = (Double) arg1;
            else
                DefinitionValue = Double.valueOf((String)arg1);
        }
        catch (ClassCastException | NumberFormatException e) {
            throw new InterpreterException("bad arguments for " + getName());
        }
        if (Character.isDigit(DefinitionName.charAt(0)))
            throw new InterpreterException("definition name should not starts with digit in " + getName());
    }
    private String DefinitionName;
    private Double DefinitionValue;
}
