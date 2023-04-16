package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.Response;

import java.util.LinkedList;

public class Clear extends Command_abstract implements CommandResponse{
    String output;

    public Clear(){
    }

    @Override
    public void execute(){
        LinkedList<HumanBeing> humans = getCollectionManager().getCollection();
        humans.clear();
        output = "Collection successfully cleared!\n";

    }
    @Override
    public Response getResponse(){
        return new Response("clear", output);
    }
}
