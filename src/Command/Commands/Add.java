package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;
import Database.ServerConnection;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Class for tha add command. Add an element.
 */
public class Add extends Command_abstract implements CommandResponse{
    public Add(){
    }

    @Override
    public void execute(){
        CopyOnWriteArrayList<HumanBeing> humans = getCollectionManager().getConcurrentCollection();
        setBd(true);
        setSuccess(getCollectionManager().getDBManager().insertCommand(CollectionManager.bdColumns,getCollectionManager().getValues((HumanBeing) getValue(),false,false)));
        Comparator<HumanBeing> comparator = getCollectionManager().getComparator();
        humans.sort(comparator);
    }
    @Override
    public Response getResponse(){
        if (isSuccess()) {
            return new Response("add", "Command Add had executed\n");
        }
        return new Response("add", getCollectionManager().getDBManager().getLastE());
    }
}
