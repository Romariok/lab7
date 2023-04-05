package client.setVariables;

import Data.*;

import java.util.Scanner;

public class readHumanBeingFromConsole {
    public static void initializeHumanBeing(HumanBeing humanBeing, Scanner input){
        humanBeing.setCreationDate();
        humanBeing.setName(SetName.initializeName(input));
        Coordinates coordinates = new Coordinates();
        coordinates.setX(setCoordinates.setCoordinateX(input));
        coordinates.setY(setCoordinates.setCoordinateY(input));
        humanBeing.setCoordinates(coordinates);
        humanBeing.setRealHero(setRealHero.initializeRealHero(input));
        humanBeing.setHasToothpick(setHasToothpick.initializeHasToothpick(input));
        humanBeing.setImpactSpeed(setImpactSpeed.initializeImpactSpeed(input));
        humanBeing.setSoundtrackName(setSoundtrackName.initializeSoundtrackName(input));
        humanBeing.setWeaponType(setWeaponType.initializeWeaponType(input));
        humanBeing.setMood(setMood.initializeMood(input));
        Car car = new Car();
        car.setCool(setCar.initializeCar(input));
        humanBeing.setCar(car);
    }



}
