import interpreter.Interpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Program {
    private static final Logger logger = LoggerFactory.getLogger(Program.class);
    public static void main(String[] argv) {
        InputStream in = null;
        if (argv.length > 0) {
            try {
                logger.info("The input was read from file " + argv[0]);
                in = new FileInputStream(argv[0]);
            }
            catch (IOException e) {
//                System.err.println(e.getMessage());
                logger.error("Cannot open file " + argv[0]);
                System.exit(EXIT_FAILURE);
            }
        }
        else {
            logger.info("The input is reading from standard input");
            in = System.in;
        }

        Interpreter inter = new Interpreter(in, System.out, System.err);
        inter.run();

        try {
            in.close();
        }
        catch (IOException e) {
//            e.printStackTrace();
            logger.error("An error occurred while closing input stream");
            System.exit(EXIT_FAILURE);
        }
        System.exit(EXIT_SUCCESS);
    }

    public static final int EXIT_FAILURE = 1;
    public static final int EXIT_SUCCESS = 0;
}
