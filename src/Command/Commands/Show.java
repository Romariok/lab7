package Command.Commands;

import Command.CommandResponse;
import Command.Command_abstract;
import Data.HumanBeing;
import DataStructure.Response;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class for the show command. Printing information about elements in collection
 */
public class Show extends Command_abstract implements CommandResponse {
    private String output = "";

    public Show(){
    }
    @Override
    public void execute() {
        CopyOnWriteArrayList<HumanBeing> humans = getCollectionManager().getConcurrentCollection();
        if (humans.size() != 0) humans.forEach(humanBeing -> {
            HumanBeing h = new HumanBeing(){
                @Override
                public String toString() {
                    return "-id: " + this.getId() + "\n" + "   -name: " + this.getName() + "\n" + "   -coordinates: " + this.getCoordinates() +
                            "\n" + "   -Creation date: " + this.getCreationDate() + "\n"
                            + "   -realHero: " + this.isRealHero() + "\n" + "   -hasToothpick: " + this.getHasToothpick() + "\n" +
                            "   -impact speed: " + this.getImpactSpeed() + "\n" + "   -soundtrack name: " + this.getSoundtrackName() + "\n" +
                            "   -weapon type: " + this.getWeaponType() + "\n" + "   -mood: " + this.getMood() + "\n" + "   -car: " + this.getCar() + "\n"+"   -user: "+this.getLogin();
                }
            };
            h.setId(humanBeing.getId());
            h.setName(humanBeing.getName());
            h.setCreationDate(humanBeing.getCreationDate());
            h.setCoordinates(humanBeing.getCoordinates());
            h.setRealHero(humanBeing.isRealHero());
            h.setHasToothpick(humanBeing.getHasToothpick());
            h.setImpactSpeed(humanBeing.getImpactSpeed());
            h.setSoundtrackName(humanBeing.getSoundtrackName());
            h.setWeaponType(humanBeing.getWeaponType());
            h.setMood(humanBeing.getMood());
            h.setCar(humanBeing.getCar());
            output += h.toString();
        });
        else output = "Collection is empty\n";
    }

    @Override
    public Response getResponse(){
        return new Response("show", output);
    }
}
