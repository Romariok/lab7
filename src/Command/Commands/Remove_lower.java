package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;
import server.FileManagment.ParserfromBD;


import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import static server.ServerMain.clientsDataPath;

/**
 * Class for the remove_lower command. Removing elements in collection whose id is lower than specified
 */
public class Remove_lower extends Command_abstract implements CommandResponse {
    private String output;

    public Remove_lower() {
    }

    @Override
    public void execute() {
        long id = Long.parseLong(getArgs()[0]);
        CollectionManager manager = getCollectionManager();
        setSuccess(manager.getDBManager().deleteCommand(id,"<",getUser()));
        if(isSuccess()) {
            output = "Удалены элементы, id которых был меньше " + id + "!\n";
        }
        else{
            output = "Что-то не так!\n"+manager.getDBManager().getLastE();
        }
    }

    @Override
    public Response getResponse() {
        return new Response("remove lower", output);
    }
}
