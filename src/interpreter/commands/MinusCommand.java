package interpreter.commands;

public class MinusCommand extends BinaryCommand {
    @Override
    public String getName() {
        return "MINUS";
    }

    @Override
    public double operation(double a, double b) {
        return a - b;
    }
}
