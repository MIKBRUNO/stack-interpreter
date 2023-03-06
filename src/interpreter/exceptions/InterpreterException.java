package interpreter.exceptions;

public class InterpreterException extends RuntimeException {
    public InterpreterException(String s, boolean isCritical) {
        super("Interpreter exception: " + s);
        IsCritical = isCritical;
    }
    public InterpreterException(String s) {
        super("Interpreter exception: " + s);
        IsCritical = false;
    }
    public boolean isCritical() {
        return IsCritical;
    }
    private boolean IsCritical;
}
