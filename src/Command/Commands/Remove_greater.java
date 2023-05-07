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
 * Class for the remove_greater command. Removing elements in collection whose id is greater than specified
 */
public class Remove_greater extends Command_abstract implements CommandResponse {
    private String output;

    public Remove_greater(){
    }
    @Override
    public void execute() {
        Long id = Long.parseLong(getArgs()[0]);
        CollectionManager manager = getCollectionManager();
        CopyOnWriteArrayList<HumanBeing> humans = getCollectionManager().getConcurrentCollection();
        new ParserfromBD(manager).parseData();
        setBd(true);
        int counting = 0;
        StringBuilder sql = new StringBuilder("where id in (0");
        for (HumanBeing h:humans) {
            if (h.getId() > id) {
                counting++;
                sql.append(",").append(h.getId());
            }
        }
        sql.append(")");
        setSuccess(manager.getDBManager().deleteCommand(sql.toString()));
        if(isSuccess()) {
            output = "Удалено " + counting + " элементов, id которых был больше " + id + "!\n";
        }
        else{
            output = "Что-то не так!\n"+manager.getDBManager().getLastE();
        }
    }

    @Override
    public Response getResponse(){
        return new Response("remove greater", output);
    }
}
