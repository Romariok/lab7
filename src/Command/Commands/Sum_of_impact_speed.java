package Command.Commands;

import Command.CommandResponse;
import Command.Command_abstract;
import Data.HumanBeing;
import DataStructure.Response;

import java.util.LinkedList;

/**
 * Class for the sum_of_impact_speed. Printing sum of impact speed of elements in collection
 */
public class Sum_of_impact_speed extends Command_abstract implements CommandResponse {
    private String output;

    public Sum_of_impact_speed(){
    }
    @Override
    public void execute(){
        int sum = 0;
        LinkedList<HumanBeing> humans = getCollectionManager().getCollection();
        for (int i = 0; i < humans.size(); i++) {
            sum += humans.get(i).getImpactSpeed();
        }
        output = "Сумма значений поля impactSpeed для всех элементов коллекции: " + sum+"\n";
    }

    @Override
    public Response getResponse(){
        return new Response("sum of impact speed", output);
    }
}
