package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class for the insert_at command. Insert element in collection by index
 */
public class Insert_at extends Command_abstract implements CommandResponse {
    private String output;

    public Insert_at(){
    }
    @Override
    public void execute(){
        long index = Long.parseLong(getArgs()[0]);
        HumanBeing humanBeing = (HumanBeing) getValue();
        output = "Ваш элемент успешно добавлен в коллекцию на " + index + " позицию!\n";
        humanBeing.setId(index);
        setSuccess(getCollectionManager().getDBManager().insertCommand(humanBeing));
        setBd(true);
        if(!isSuccess()) {
            output = "Возникла ошибка при добавлении элемента на " + index + "-ю позицию!\n"+getCollectionManager().getDBManager().getLastE()+"\n";
        }
    }

    @Override
    public Response getResponse(){
        return new Response("insert at", output);
    }
}
