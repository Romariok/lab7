package Command;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import Command.Commands.*;
import Data.HumanBeing;
import client.setVariables.readHumanBeingFromConsole;

public class CommandFactory {
    private Map<String, CommandResponse> commands = new HashMap<>();
    private HashSet<String> commandsWithObject = new HashSet<>();
    private HashSet<String> commandsWithArgs = new HashSet<>();

    public CommandFactory() {
        commands.put("add", new Add());
        commands.put("clear", new Clear());
        commands.put("count_greater_than_car", new Count_greater_than_car());
        commands.put("execute_script", new Execute_script());
        commands.put("filter_starts_with_soundtrack", new Filter_starts_with_soundtrack_name());
        commands.put("help", new Help());
        commands.put("info", new Info());
        commands.put("insert_at", new Insert_at());
        commands.put("remove_by_id", new Remove_by_id());
        commands.put("remove_greater", new Remove_greater());
        commands.put("remove_lower", new Remove_lower());
        commands.put("show", new Show());
        commands.put("sum_of_impact_speed", new Sum_of_impact_speed());
        commands.put("update", new Update());
        commandsWithObject.add("add");
        commandsWithArgs.add("update");
        commandsWithObject.add("update");
        commandsWithObject.add("insert_at");
        commandsWithArgs.add("count_greater_than_car");
        commandsWithArgs.add("execute_script");
        commandsWithArgs.add("filter_starts_with_soundtrack");
        commandsWithArgs.add("remove_by_id");
        commandsWithArgs.add("remove_greater");
        commandsWithArgs.add("remove_lower");
    }

    public CommandResponse getCommand(String commandName, String[] commandArgs, Scanner scanner, boolean bool) {
        if (commands.containsKey(commandName)) {
            CommandResponse command = commands.get(commandName);
            if (commandsWithObject.contains(commandName)) {
                HumanBeing humanBeing = new HumanBeing();
                readHumanBeingFromConsole.initializeHumanBeing(humanBeing, scanner, bool);
                command.setValue(humanBeing);
            }  if (commandsWithArgs.contains(commandName)) {
                if (commandArgs.length == 0){
                    return null;
                }
                else{
                    command.setArgs(commandArgs);
                }

            }
            return command;
        } else {
            return null;
        }

    }


}
