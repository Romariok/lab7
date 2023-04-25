package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;
import server.FileManagment.ParserXMLtoBD;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import static server.ServerMain.clientsDataPath;

/**
 * Class for the update command. Updating element by his index
 */
public class Update extends Command_abstract implements CommandResponse {
    private String output;

    public Update(){
    }
    @Override
    public void execute() {
        new ParserXMLtoBD(clientsDataPath,getCollectionManager()).parseData();
        CopyOnWriteArrayList<HumanBeing> humans = getCollectionManager().getConcurrentCollection();
        HumanBeing humanBeing = null;
        Long id = Long.parseLong(getArgs()[0]);
        setBd(true);
        for (HumanBeing human : humans) {
            if (id.equals(human.getId())) {
                humanBeing = human;
            }
        }
        if (humanBeing != null) {
            HumanBeing humanBeingNew = (HumanBeing) getValue();
            setSuccess(getCollectionManager().getDBManager().updateCommand(CollectionManager.bdSetColumns,getCollectionManager().getValues(humanBeingNew,false,true),"id = "+id));
            if(isSuccess()) {
                output = "Ваш элемент успешно обновлён!\n";
            }
            else {
                output = getCollectionManager().getDBManager().getLastE();
            }
        } else {
            output = "Объекта по id - " +id + " не существует в коллекции!\n";
        }
    }
    @Override
    public Response getResponse(){
        return new Response("update", output);
    }
}
