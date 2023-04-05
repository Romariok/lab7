package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;

import java.util.LinkedList;

/**
 * Class for the remove_lower command. Removing elements in collection whose id is lower than specified
 */
public class Remove_lower extends Command_abstract implements CommandResponse {
    private String output;

    public Remove_lower(){
    }
    @Override
    public void execute() {
        Integer id = Integer.parseInt(getArgs()[0]);
        LinkedList<HumanBeing> humans = getCollectionManager().getCollection();
        int counting = 0;
        for (int i = 0; i < humans.size(); i++) {
            if (humans.get(i).getId() < id) {
                counting++;
                humans.remove(i);
            }
        }
        output = "Удалено " + counting + " элементов, id которых был меньше " + id + "!\n";
    }

    @Override
    public Response getResponse(){
        return new Response("remove lower", output);
    }
}
