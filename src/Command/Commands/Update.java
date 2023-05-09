package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;
import server.FileManagment.ParserfromBD;
import server.FileManagment.ParserfromBD;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import static server.ServerMain.clientsDataPath;

/**
 * Class for the update command. Updating element by his index
 */
public class Update extends Command_abstract implements CommandResponse {
    private String output;

    public Update() {
    }

    @Override
    public void execute() {
        long id = Long.parseLong(getArgs()[0]);
        setBd(true);
        HumanBeing humanBeingNew = (HumanBeing) getValue();
        setSuccess(getCollectionManager().getDBManager().updateCommand(humanBeingNew, id, getUser()));
        if (isSuccess()) {
            output = "Ваш элемент успешно обновлён!\n";
        } else {
            output = getCollectionManager().getDBManager().getLastE();
        }
    }

    @Override
    public Response getResponse() {
        return new Response("update", output);
    }
}
