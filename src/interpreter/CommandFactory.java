package interpreter;

import interpreter.exceptions.ConfigurationException;
import interpreter.exceptions.InterpreterException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandFactory {
    public static void initFactory() throws ConfigurationException {
        InputStream in = CommandFactory.class.getResourceAsStream(ConfigFile);
        if (in == null)
            throw new ConfigurationException("cannot open configuration file: " + ConfigFile);
        else {
            Scanner scanner = new Scanner(in);
            while (scanner.hasNext()) {
                String[] pair = scanner.nextLine().split("\\s*->\\s*");
                if (pair.length < 2)
                    throw new ConfigurationException("bad configuration file " + ConfigFile);
                try {
                    Commands.put(pair[0], (Class<Command>) Class.forName(pair[1]));
                } catch (ClassNotFoundException | ClassCastException e) {
                    throw new ConfigurationException("bad configuration file" + ConfigFile);
                }
            }
        }
    }
    public static Command create(String commandName) throws InterpreterException {
        if (null == commandName || !Commands.containsKey(commandName))
            throw new InterpreterException("bad command name " + commandName);
        try {
            return Commands.get(commandName).getDeclaredConstructor().newInstance();
        }
        catch (Exception e) {
            throw new InterpreterException("cannot initialize command" + commandName);
        }
    }
    private static final String ConfigFile = "factory_configuration.txt";
    private static final Map<String, Class<Command>> Commands = new HashMap<>();
}
