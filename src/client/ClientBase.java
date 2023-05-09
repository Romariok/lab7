package client;

import Auth.AuthResponse;
import Auth.Session;
import Command.CommandResponse;
import Command.CommandFactory;
import Data.HumanBeing;


import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static client.ClientMain.arg;
import static Command.Serializer.serialize;
public class ClientBase implements Runnable {
    private final Connection connection;
    private Session session = new Session();

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
        if (arg.length >= 3){
            if (Objects.equals(arg[1], "-exec")){
                CommandResponse execute_script = commandFactory.getCommand("execute_script", new String[]{arg[2]}, scanner, false);
                try{
                    connection.send(serialize(execute_script));
                    AuthResponse response = connection.recieve();
                    if (!response.getCommand().isEmpty()) System.out.println(response);
                    session.setUser(response.getUser());
                    session.setAuthoriazed(response.isAutorized());
                }
                catch (Exception ex){
                    System.err.println(ex.getMessage());
                }
            }
        }


        while (true) {
            List<String> input = Arrays.stream(scanner.nextLine().split(" ")).toList();
            commandName = input.get(0);
            commandArgs = input.subList(1, input.size()).toArray(new String[0]);

            if (commandName.equals("exit")) {
                System.out.println("Bye!");
                System.exit(1);
            }
            CommandResponse command = commandFactory.getCommand(commandName, commandArgs, scanner, false);
            AuthResponse sending;
            if (command != null){
                try{
                    if(command.getValue()==null) {
                        sending = new AuthResponse(commandName + " " + String.join(" ", commandArgs), session.getUser(), session.isAuthoriazed());
                    }
                    else if(command.getArgs()==null||command.getArgs().length == 0){
                        sending = new AuthResponse(commandName + " "+ command.getValue().toString(), session.getUser(), session.isAuthoriazed());
                    }
                    else{
                        sending = new AuthResponse(commandName+" "+String.join(" ", commandArgs)+ " "+ command.getValue().toString());
                    }
                    connection.send(serialize(sending));
                    AuthResponse response = connection.recieve();
                    if (!response.getCommand().isEmpty()) System.out.println(response.getCommand());
                    session.setUser(response.getUser());
                    session.setAuthoriazed(response.isAutorized());
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
