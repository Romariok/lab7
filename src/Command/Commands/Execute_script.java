package Command.Commands;

import Command.*;
import DataStructure.Response;

import java.io.File;
import java.util.ArrayList;

/**
 * Class for the execute_script command. Execute script.
 */
public class Execute_script extends Command_abstract implements CommandResponse {
    private ArrayList<CommandResponse> commandList;
    private String output;
    private StringBuilder sb = new StringBuilder();

    public Execute_script() {
        this.commandList = new ArrayList<>();
    }

    @Override
    public void execute() {
        ScriptExecuter executer = new ScriptExecuter(new File(getArgs()[0]));
        executer.execute();
        sb =executer.getOutput();
//        this.commandList = executer.getCommandlist();
//        commandList.forEach(command -> {
//            command.execute();
//            sb.append(command.getResponse().getOutput());
//        });
//        for (CommandResponse command : (Iterable<CommandResponse>) () -> commandList.iterator()) {
//            command.execute();
//            sb.append(command.getResponse().getOutput());
//        }
//        for(int i=0;i< commandList.size();i++){
//            commandList.get(i).execute();
//            sb.append(commandList.get(i).getResponse().getOutput());
//        }


        sb.append("\n");
        output = sb.toString();
    }

    @Override
    public Response getResponse() {
        return new Response("execute script", output);
    }
}
