package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.Response;

import java.util.Comparator;
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
        Long id = Long.parseLong(getArgs()[0]);
        int in = 0;
        for (int i = 0; i < humans.size(); i++) {
            if (id == humans.get(i).getId()) {
                humanBeing = humans.get(i);
                in = i;
            }
        }
        if (humanBeing != null) {
            HumanBeing humanBeingNew = (HumanBeing) getValue();
            humans.set(in, humanBeingNew);
            Comparator<HumanBeing> comparator = getCollectionManager().getComparator();
            humans.sort(comparator);
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
