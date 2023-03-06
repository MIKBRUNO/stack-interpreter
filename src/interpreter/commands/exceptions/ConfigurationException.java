package interpreter.commands.exceptions;

import interpreter.exceptions.InterpreterException;

public class ConfigurationException extends InterpreterException {
    public ConfigurationException(String s) {
        super("configuration file exception: " + s, true);
    }
}
