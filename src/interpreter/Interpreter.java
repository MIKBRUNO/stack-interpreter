package interpreter;

import interpreter.commands.exceptions.ConfigurationException;
import interpreter.exceptions.InterpreterException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Interpreter {
    public Interpreter(InputStream in, PrintStream out, PrintStream err) {
        Input = new Scanner(in);
        Output = out;
        Error = err;
        Context = new RuntimeContext(Output);
        try {
            CommandFactory.initFactory();
        }
        catch (ConfigurationException e) {
            Error.print("Error occurred while initialising: " + e.getMessage() + '\n');
            Context.isRunning = false;
        }
    }
    public void run() {
        while (Context.isRunning && Input.hasNext()) {
            try {
                Command cmd = CommandParser.parse(Input.nextLine());
                if (cmd != null) {
                    cmd.doAction(Context);
                }
            }
            catch (InterpreterException e) {
                Error.print("Error while running: " + e.getMessage() + '\n');
                if (e.isCritical())
                    Context.isRunning = false;
            }
            catch (Exception e) {
                e.printStackTrace(Error);
                Context.isRunning = false;
            }
        }
    }
    private final Scanner Input;
    private final PrintStream Output;
    private final PrintStream Error;
    private final RuntimeContext Context;
}
