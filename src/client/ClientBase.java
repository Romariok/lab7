package client;

import Auth.Session;
import Command.CommandResponse;
import Command.CommandFactory;
import Command.InputData;


import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static client.ClientMain.arg;
import static Command.Serializer.serialize;
public class ClientBase implements Runnable {
    private final Connection connection;
    private Session session = new Session("");
    public ClientBase(InetAddress address, int port) {
        try {
            connection = new Connection(address, port);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void run() {
        String commandName;
        String[] commandArgs;
        Scanner scanner = new Scanner(System.in);
        CommandFactory commandFactory = new CommandFactory();
        /*
        if (arg.length >= 3){
            if (Objects.equals(arg[1], "-exec")){
                CommandResponse execute_script = commandFactory.getCommand("execute_script", new String[]{arg[2]}, scanner, false);
                try{
                    execute_script.setSession(session);
                    connection.send(serialize(execute_script));
                    String response = connection.receive();
                    if (!response.isEmpty()) System.out.println(response);
                }
                catch (Exception ex){
                    System.err.println(ex.getMessage());
                }
            }
        }
*/

        while (true) {
            List<String> input = Arrays.stream(scanner.nextLine().split(" ")).toList();
            commandName = input.get(0);
            commandArgs = input.subList(1, input.size()).toArray(new String[0]);

            if (commandName.equals("exit")) {
                System.out.println("Bye!");
                System.exit(1);
            }
            CommandResponse command = commandFactory.getCommand(commandName, commandArgs, scanner, false);
            if (command != null){
                try{
                    InputData inputData = new InputData();
                    inputData.setCommandResponse(command);
                    inputData.setSession(session);
                    connection.send(serialize(inputData));
                    InputData inputData1 = (InputData) connection.receive();
                    CommandResponse response = inputData1.getCommandResponse();
                    session = inputData1.getSession();
                    if (!response.getResponse().getOutput().isEmpty()) System.out.println(response.getResponse().getOutput());
                }
                catch (Exception ex){
                    System.err.println(ex.getMessage());
                }

            } else {
                System.err.println("You just entered an unknown command! For list of available commands type 'help'");
            }
        }
    }
}
