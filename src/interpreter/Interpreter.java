package interpreter;

import interpreter.exceptions.ConfigurationException;
import interpreter.exceptions.InterpreterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Interpreter {
    private final static Logger logger = LoggerFactory.getLogger(Interpreter.class);
    public Interpreter(InputStream in, PrintStream out, PrintStream err) {
        Input = new Scanner(in);
        Context = new RuntimeContext(out);
        try {
            CommandFactory.initFactory();
        }
        catch (ConfigurationException e) {
//            Error.print("Error occurred while initialising: " + e.getMessage() + '\n');
            logger.error("Unsuccessful initialization: " + e.getMessage());
            Context.isRunning = false;
        }
    }
    public void run() {
        while (Context.isRunning && Input.hasNext()) {
            try {
                Command cmd = CommandParser.parse(Input.nextLine());
                if (cmd != null) {
                    cmd.doAction(Context);
                    logger.info("Command " + cmd.getName() + " executed");
                }
            }
            catch (InterpreterException e) {
//                Error.print("Error while running: " + e.getMessage() + '\n');
                if (e.isCritical()) {
                    logger.error("Critical error occurred while operation: " + e.getMessage());
                    Context.isRunning = false;
                }
                else {
                    logger.warn("Not critical exception occurred: " + e.getMessage());
                }
            }
            catch (Exception e) {
//                e.printStackTrace(Error);
                logger.error("Critical unexpected exception occurred: ", e);
                Context.isRunning = false;
            }
        }
    }
    private final Scanner Input;
    private final RuntimeContext Context;
}
