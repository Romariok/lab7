package client.setVariables;

import Data.*;

import java.util.Scanner;

public class readHumanBeingFromConsole {
    public static void initializeHumanBeing(HumanBeing humanBeing, Scanner input, boolean bool){
        humanBeing.setCreationDate();
        humanBeing.setName(SetName.initializeName(input, bool));
        Coordinates coordinates = new Coordinates();
        coordinates.setX(setCoordinates.setCoordinateX(input, bool));
        coordinates.setY(setCoordinates.setCoordinateY(input, bool));
        humanBeing.setCoordinates(coordinates);
        humanBeing.setRealHero(setRealHero.initializeRealHero(input, bool));
        humanBeing.setHasToothpick(setHasToothpick.initializeHasToothpick(input, bool));
        humanBeing.setImpactSpeed(setImpactSpeed.initializeImpactSpeed(input, bool));
        humanBeing.setSoundtrackName(setSoundtrackName.initializeSoundtrackName(input, bool));
        humanBeing.setWeaponType(setWeaponType.initializeWeaponType(input, bool));
        humanBeing.setMood(setMood.initializeMood(input, bool));
        Car car = new Car();
        car.setCool(setCar.initializeCar(input, bool));
        humanBeing.setCar(car);
    }



}
