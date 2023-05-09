package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class for the remove_by_id command. Removing element by id
 */
public class Remove_by_id extends Command_abstract implements CommandResponse {
    private String output;

    public Remove_by_id(){
    }
    @Override
    public void execute(){
        Long index = Long.parseLong(getArgs()[0]);
        output = index + "-й элемент успешно удалён!\n";
        setBd(true);
        CollectionManager manager = getCollectionManager();
        setSuccess(manager.getDBManager().deleteCommand(index,"=",getUser()));
        if(!isSuccess()) {
            output = "Возникла непредвиденная ошибка! Элемент не удалён!\n"+getCollectionManager().getDBManager().getLastE()+"\n";
        }
    }
    @Override
    public Response getResponse(){
        return new Response("remove by id", output);
    }
}
