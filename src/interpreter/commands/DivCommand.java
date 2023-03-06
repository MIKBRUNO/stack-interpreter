package interpreter.commands;

public class DivCommand extends BinaryCommand {
    @Override
    public String getName() {
        return "Div";
    }

    @Override
    public double operation(double a, double b) {
        return a/b;
    }
}
