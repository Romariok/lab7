package Command.Commands;

import Command.CommandResponse;
import Command.Command_abstract;
import Data.HumanBeing;
import DataStructure.Response;

import java.util.LinkedList;


/**
 * Class for the info command. Print information about collection
 */
public class Info extends Command_abstract implements CommandResponse {

    private String output;

    public Info(){
    }
    @Override
    public void execute(){
        LinkedList<HumanBeing> humans = getCollectionManager().getCollection();
        output = "Тип коллекции: " + humans.getClass() + "\n"
                + "Дата инициализации: " + getCollectionManager().getIndate() + "\n"
                + "Размер коллекции: " + humans.size() + "\n";
    }

    @Override
    public Response getResponse(){
        return new Response("info", output);
    }
}
