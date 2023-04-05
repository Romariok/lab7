package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;

import java.util.LinkedList;

/**
 * Class for the update command. Updating element by his index
 */
public class Update extends Command_abstract implements CommandResponse {
    private String output;

    public Update(){
    }
    @Override
    public void execute() {
        LinkedList<HumanBeing> humans = getCollectionManager().getCollection();
        HumanBeing humanBeing = null;
        Integer id = Integer.parseInt(getArgs()[0]);

        for (int i = 0; i < humans.size(); i++) {
            if (id == humans.get(i).getId()) {
                humanBeing = humans.get(i);
            }
        }
        if (humanBeing != null) {
//            humans.re
            output = "Ваш элемент успешно обновлён!\n";
        } else {
            output = "Объекта по id - " +id + " не существует в коллекции!\n";
        }
    }
    @Override
    public Response getResponse(){
        return new Response("update", output);
    }
}
