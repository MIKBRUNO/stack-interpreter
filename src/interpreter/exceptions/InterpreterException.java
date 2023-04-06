package interpreter.exceptions;

public class InterpreterException extends Exception {
    public InterpreterException(String s, boolean isCritical) {
        super(s);
        IsCritical = isCritical;
    }
    public InterpreterException(String s) {
        this(s, false);
    }
    public boolean isCritical() {
        return IsCritical;
    }
    private boolean IsCritical;
}
