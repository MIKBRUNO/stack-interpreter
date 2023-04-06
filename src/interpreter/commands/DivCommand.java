package interpreter.commands;

public class DivCommand extends BinaryCommand {
    @Override
    public String getName() {
        return "DIV";
    }

    @Override
    public double operation(double a, double b) {
        return a/b;
    }
}
