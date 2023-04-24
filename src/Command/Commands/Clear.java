package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.Response;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Clear extends Command_abstract implements CommandResponse{
    String output;

    public Clear(){
    }

    @Override
    public void execute(){
        CopyOnWriteArrayList<HumanBeing> humans = getCollectionManager().getConcurrentCollection();
        humans.clear();
        output = "Collection successfully cleared!\n";

    }
    @Override
    public Response getResponse(){
        return new Response("clear", output);
    }
}
