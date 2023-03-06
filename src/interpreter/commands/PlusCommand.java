package interpreter.commands;

public class PlusCommand extends BinaryCommand {
    @Override
    public String getName() {
        return "PLUS";
    }
    @Override
    public double operation(double a, double b) {
        return a + b;
    }
}
