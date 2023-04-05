package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;

import java.util.Comparator;
import java.util.LinkedList;


/**
 * Class for tha add command. Add an element.
 */
public class Add extends Command_abstract implements CommandResponse{
    public Add(){
    }

    @Override
    public void execute(){
        LinkedList<HumanBeing> humans = getCollectionManager().getCollection();
        HumanBeing humanBeing = (HumanBeing) getValue();
        Comparator<HumanBeing> comparator = getCollectionManager().getComparator();
        humans.add(humanBeing);
        humans.sort(comparator);
    }
    @Override
    public Response getResponse(){
        return new Response("add", "Command Add had executed\n");
    }
}
