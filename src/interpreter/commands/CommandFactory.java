package interpreter.commands;

import interpreter.Command;
import interpreter.commands.exceptions.ConfigurationException;
import interpreter.exceptions.InterpreterException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandFactory {
    public CommandFactory() {
        Commands = new HashMap<>();
        InputStream in = this.getClass().getResourceAsStream(ConfigFile);
        if (in == null)
            throw new ConfigurationException("cannot open configuration file: " + ConfigFile);
        else {
            Scanner scanner = new Scanner(in);
            while (scanner.hasNext()) {
                String[] pair = scanner.nextLine().split("\\s*->\\s*");
                if (pair.length < 2)
                    throw new ConfigurationException("bad configuration file");
                try {
                    Commands.put(pair[0], (Class<Command>) Class.forName(pair[1]));
                } catch (ClassNotFoundException | ClassCastException e) {
                    throw new ConfigurationException("bad configuration file");
                }
            }
        }
    }
    public Command create(String commandName) {
        if (null == commandName || !Commands.containsKey(commandName))
            throw new InterpreterException("bad command name");
        try {
            return Commands.get(commandName).getDeclaredConstructor().newInstance();
        }
        catch (Exception e) {
            throw new InterpreterException("cannot initialize command");
        }
    }
    private static final String ConfigFile = "factory_configuration.txt";
    private final Map<String, Class<Command>> Commands;
}
