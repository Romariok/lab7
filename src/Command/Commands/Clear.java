package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.Response;
import Database.ServerConnection;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Clear extends Command_abstract implements CommandResponse{
    String output;

    public Clear(){
    }

    @Override
    public void execute(){
        setSuccess(getCollectionManager().getDBManager().deleteCommand(" where user = '"+getSession().getUser()+"'"));
        setBd(true);
        if(isSuccess()) {
            output = "Collection successfully cleared!\n";
        }
        else{
            output = getCollectionManager().getDBManager().getLastE();
        }
    }
    @Override
    public Response getResponse(){
        return new Response("clear", output);
    }
}
