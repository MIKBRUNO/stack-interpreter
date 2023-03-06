import interpreter.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Program {
    public static void main(String[] argv) {
        InputStream in = null;
        if (argv.length > 1) {
            try {
                in = new FileInputStream(argv[1]);
            }
            catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(EXIT_FAILURE);
            }
        }
        else {
            in = System.in;
        }

        Interpreter inter = new Interpreter(in, System.out, System.err);
        inter.run();

        try {
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(EXIT_FAILURE);
        }
        System.exit(EXIT_SUCCESS);
    }

    public static final int EXIT_FAILURE = 1;
    public static final int EXIT_SUCCESS = 0;
}
