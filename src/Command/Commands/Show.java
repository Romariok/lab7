package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;

import java.util.LinkedList;

/**
 * Class for the show command. Printing information about elements in collection
 */
public class Show extends Command_abstract implements CommandResponse {
    private String output = "";

    public Show(){
    }
    @Override
    public void execute() {
        LinkedList<HumanBeing> humans = getCollectionManager().getCollection();
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
