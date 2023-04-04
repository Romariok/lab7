package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;

import java.util.LinkedList;


/**
 * Class for the info command. Print information about collection
 */
public class Info extends Command_abstract implements CommandResponse {

    private String output;

    public Info(CollectionManager collectionManager){
        super(collectionManager);
    }
    public Info(){
    }
    @Override
    public void execute(){
        LinkedList<HumanBeing> humans = getCollectionManager().getCollection();
        output = "Тип коллекции: " + humans.getClass() + "\n"
                + "Дата инициализации: " + getCollectionManager().getIndate() + "\n"
                + "Размер коллекции: " + humans.size() + "";
    }

    @Override
    public Response getResponse(){
        return new Response("info", getArgs(), output);
    }
}
