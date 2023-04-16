package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.Response;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Class for the insert_at command. Insert element in collection by index
 */
public class Insert_at extends Command_abstract implements CommandResponse {
    private String output;

    public Insert_at(){
    }
    @Override
    public void execute(){
        int index = Integer.parseInt(getArgs()[0]);
        HumanBeing humanBeing = (HumanBeing) getValue();
        LinkedList<HumanBeing> humans = getCollectionManager().getCollection();
        Comparator<HumanBeing> comparator = getCollectionManager().getComparator();
        try {
            humans.add(index, humanBeing);
        } catch (Exception ex) {
            ex.getStackTrace();
            output = "Возникла ошибка при добавлении элемента на " + index + "-ю позицию!\n";
        }
        humans.sort(comparator);
        output = "Ваш элемент успешно добавлен в коллекцию на " + index + " позицию!\n";
    }

    @Override
    public Response getResponse(){
        return new Response("insert at", output);
    }
}
