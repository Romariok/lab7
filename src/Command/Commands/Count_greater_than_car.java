package Command.Commands;

import Command.CommandResponse;
import Command.Command_abstract;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;

import java.util.LinkedList;

/**
 * Class for the count_greater_than_car command. Remove elements in collection whose status of car greater than specified
 */
public class Count_greater_than_car extends Command_abstract implements CommandResponse {
    private String output;

    public Count_greater_than_car(){
    }
    @Override
    public void execute(){
        LinkedList<HumanBeing> humans = getCollectionManager().getCollection();
        int counting = 0;
        boolean cool=  Boolean.parseBoolean(getArgs()[0]);
        if (!cool) {
            for (int i = 0; i < humans.size(); i++) {
                if (humans.get(i).getCar().getCool()) {
                    counting++;
                }
            }
        }
        output = "Количество элементов, значение поля car которых больше " + cool + " : " + counting+"\n";
    }

    @Override
    public Response getResponse(){
        return new Response("count greater than car", output);
    }
}
