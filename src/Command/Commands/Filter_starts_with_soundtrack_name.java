package Command.Commands;

import Command.*;
import Data.HumanBeing;
import DataStructure.CollectionManager;
import DataStructure.Response;

import java.util.LinkedList;

/**
 * Class for the filter_starts_with_soundtrack_name. Print items whose soundtrackName field value starts with the specified substring
 */
public class Filter_starts_with_soundtrack_name extends Command_abstract implements CommandResponse {
    private String output;

    public Filter_starts_with_soundtrack_name(){
    }
    @Override
    public void execute() {
        LinkedList<HumanBeing> humans = getCollectionManager().getCollection();
        String soundtrackName = getArgs()[0];
        humans.forEach((humanBeing -> {
            if (humanBeing.getSoundtrackName().contains(soundtrackName)) {
                output += humanBeing.getSoundtrackName()+"\n";
            }
        }));

    }

    @Override
    public Response getResponse() {
        return new Response("filter_starts_with_soundtrack_name", output);
    }
}
