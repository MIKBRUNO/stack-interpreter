package interpreter.commands;

public class MulCommand extends BinaryCommand {
    @Override
    public String getName() {
        return "MUL";
    }

    @Override
    public double operation(double a, double b) {
        return a * b;
    }
}
