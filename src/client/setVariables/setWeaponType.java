package client.setVariables;

import Data.WeaponType;

import java.util.Scanner;

/**
 * Class for initializing field WeaponType
 *
 * @author Roman Kobelev
 * @see WeaponType
 */
public class setWeaponType {
    public static WeaponType initializeWeaponType(Scanner scanner, boolean bool) {
        String line;
        if (!bool) {
            System.out.println("Введите тип оружия (Shotgun, Rifle, Machine gun, Knife): ");
        }

        line = scanner.nextLine();
        switch (line) {
            case "Shotgun":
                return WeaponType.SHOTGUN;
            case "Knife":
                return WeaponType.KNIFE;
            case "Machine gun":
                return WeaponType.MACHINE_GUN;
            case "Rifle":
                return WeaponType.RIFLE;
            default:
                if (!bool) {
                    System.out.println("Введён некорректный тип оружия! Введите тип ещё раз!");
                }

                return initializeWeaponType(scanner, bool);
        }
    }
}
