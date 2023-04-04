package client.setVariables;

import Data.*;

public class readHumanBeingFromConsole {
    public static void initializeHumanBeing(HumanBeing humanBeing){
        humanBeing.setCreationDate();
        humanBeing.setName(SetName.initializeName());
        Coordinates coordinates = new Coordinates();
        coordinates.setX(setCoordinates.setCoordinateX());
        coordinates.setY(setCoordinates.setCoordinateY());
        humanBeing.setCoordinates(coordinates);
        humanBeing.setRealHero(setRealHero.initializeRealHero());
        humanBeing.setHasToothpick(setHasToothpick.initializeHasToothpick());
        humanBeing.setImpactSpeed(setImpactSpeed.initializeImpactSpeed());
        humanBeing.setSoundtrackName(setSoundtrackName.initializeSoundtrackName());
        humanBeing.setWeaponType(setWeaponType.initializeWeaponType());
        humanBeing.setMood(setMood.initializeMood());
        Car car = new Car();
        car.setCool(setCar.initializeCar());
        humanBeing.setCar(car);
    }
}
