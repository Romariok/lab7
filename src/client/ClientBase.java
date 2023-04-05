package client;

import Command.CommandResponse;
import Command.CommandFactory;


import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static Command.Serializer.serialize;
public class ClientBase implements Runnable {
    private final Connection connection;

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

        while (true) {
            List<String> input = Arrays.stream(scanner.nextLine().split(" ")).toList();
            commandName = input.get(0);
            commandArgs = input.subList(1, input.size()).toArray(new String[0]);

            if (commandName.equals("exit")) {
                System.out.println("Bye!");
                System.exit(1);
            }
            CommandResponse command = commandFactory.getCommand(commandName, commandArgs, scanner);
            if (command != null){
                try{
                    connection.send(serialize(command));
                    String response = connection.recieve();
                    if (!response.isEmpty()) System.out.println(response);
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
