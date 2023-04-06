package interpreter.exceptions;

public class ConfigurationException extends InterpreterException {
    public ConfigurationException(String s) {
        super(s, true);
    }
}
