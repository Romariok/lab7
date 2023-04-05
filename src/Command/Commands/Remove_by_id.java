package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;

import java.util.LinkedList;

/**
 * Class for the remove_by_id command. Removing element by id
 */
public class Remove_by_id extends Command_abstract implements CommandResponse {
    private String output;

    public Remove_by_id(){
    }
    @Override
    public void execute(){
        LinkedList<HumanBeing> humans = getCollectionManager().getCollection();
        int index = Integer.parseInt(getArgs()[0]);
        try {
            humans.remove(index);
        } catch (Exception ex) {
            output = "Возникла непредвиденная ошибка! Элемент не удалён!\n";
            ex.getStackTrace();
        }
        output = index + "-й элемент успешно удалён!\n";
    }
    @Override
    public Response getResponse(){
        return new Response("remove by id", output);
    }
}
