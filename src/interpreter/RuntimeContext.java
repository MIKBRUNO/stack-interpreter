package interpreter;

import java.io.PrintStream;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class RuntimeContext {
    public RuntimeContext(PrintStream stream) {
        isRunning = true;
        DataStack = new Stack<>();
        Definitions = new TreeMap<>();
        OutputStream = stream;
    }
    public boolean isRunning;
    public PrintStream getOutputStream() {
        return OutputStream;
    }
    public Stack<Double> getDataStack() {
        return DataStack;
    }
    public Map<String, Double> getDefinitions() {
        return Definitions;
    }
    private final Stack<Double> DataStack;
    private final Map<String, Double> Definitions;
    private final PrintStream OutputStream;
}
