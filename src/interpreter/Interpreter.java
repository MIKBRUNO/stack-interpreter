package interpreter;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Interpreter {
    public Interpreter(InputStream in, PrintStream out) {
        reader = new Scanner(in);
        writer = out;
    }

    public void run() {
        boolean ctrl = true;
        while (ctrl && reader.hasNext()) {
            Command cmd = Parser.parse(reader.next());
            if (cmd != null) {
//                cmd.doAction();
            }
        }
    }

    private Scanner reader;
    private PrintStream writer;
}
