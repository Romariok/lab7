package Command;

import server.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

import static server.Connections.Connection.manager;

public class ScriptExecuter {

    private final ArrayList<CommandResponse> commandlist = new ArrayList<>();
    private final ArrayDeque<File> scriptsDeque = new ArrayDeque<>();
    private File script;

    public ScriptExecuter(File script) {
        this.script = script;
    }

    public ArrayList<CommandResponse> getCommandlist() {
        return this.commandlist;
    }

    public void read() {
        String commandName;
        String[] commandArgs;
        CommandFactory commandFactory = new CommandFactory();
        Scanner scanner;
        try {
            scanner = new Scanner(script);
            if (!scriptsDeque.contains(script)) {
                scriptsDeque.add(script);
                while (scanner.hasNext()) {
                    List<String> input = Arrays.stream(scanner.nextLine().split(" ")).toList();
                    commandName = input.get(0);
                    commandArgs = input.subList(1, input.size()).toArray(new String[0]);
                    CommandResponse command;
                    command = commandFactory.getCommand(commandName, commandArgs, scanner);
                    command.setCollectionManager(manager);
                    if (command != null) {
                        commandlist.add(command);
                    }
                }
                scriptsDeque.pop();
            } else {
                Log.getLogger().warning("You don't have permission to run script recursively");
            }
        } catch (FileNotFoundException ex) {
            Log.getLogger().warning(ex.getMessage());
        }


    }

}
