package Command.Commands;

import Command.CommandResponse;
import Command.Command_abstract;
import Data.HumanBeing;
import DataStructure.Response;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class for the show command. Printing information about elements in collection
 */
public class Show extends Command_abstract implements CommandResponse {
    private String output = "";

    public Show(){
    }
    @Override
    public void execute() {
        CopyOnWriteArrayList<HumanBeing> humans = getCollectionManager().getConcurrentCollection();
        if (humans.size() != 0) humans.forEach(humanBeing -> {
            output += humanBeing.toString();
        });
        else output = "Collection is empty\n";
    }

    @Override
    public Response getResponse(){
        return new Response("show", output);
    }
}
