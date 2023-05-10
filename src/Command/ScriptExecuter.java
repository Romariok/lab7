package Command;

import Auth.AuthResponse;
import Auth.Session;
import Command.Commands.*;
import server.FileManagment.ParserfromBD;
import server.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static server.Connections.Connection.manager;

public class ScriptExecuter {

    private final ArrayList<CommandResponse> commandlist = new ArrayList<>();
    private final ArrayDeque<File> scriptsDeque = new ArrayDeque<>();
    private File script;
    private StringBuilder output = new StringBuilder();
    private Session session = new Session();

    public ScriptExecuter(File script, String user) {
        this.script = script;
        session.setUser(user);
    }

    public ArrayList<CommandResponse> getCommandlist() {
        return this.commandlist;
    }
    public StringBuilder getOutput(){
        return output;
    }
    public void execute() {
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
                    command = commandFactory.getCommand(commandName, commandArgs, scanner, true);
                    if (command != null) {
                        commandlist.add(command);
                        command.setCollectionManager(manager);
                        command.setUser(session.getUser());
                        if (session.isAuthoriazed() || command instanceof Auth || command instanceof Register || command instanceof Info || command instanceof Show ||command instanceof Execute_script) {
                            command.execute();
                            if (command instanceof Auth) {
                                session.setAuthoriazed(((Auth) command).getSession().isAuthoriazed());
                                session.setUser(((Auth) command).getSession().getUser());
                            }
                            output.append(command.getResponse().getOutput());
                        }
                        else {
                            output.append("Cannot done this command without getting authorized");
                        }
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
