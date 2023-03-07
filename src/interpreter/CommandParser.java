package interpreter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CommandParser {
    public static Command parse(String line) {
        if (line == null || line.equals(""))
            return null;
        line = line.trim();
        String[] splitLine = line.split("\\s+");
        if (splitLine[0].charAt(0) == '#')
            return null;
        List<Object> args = new LinkedList<>(Arrays.asList(splitLine));
        Command cmd = CommandFactory.create(splitLine[0]);
        args.remove(0);
        cmd.takeArguments(args);

        return cmd;
    }
}
