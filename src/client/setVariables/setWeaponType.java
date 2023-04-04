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
    public static WeaponType initializeWeaponType() {
        String line;
//        if (Linkedlist.count != 0){
//            line = Linkedlist.getScript_input()[Linkedlist.count].trim();
//            Linkedlist.count++;
//        }
//        else{
        System.out.println("Введите тип оружия (Shotgun, Rifle, Machine gun, Knife): ");
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
//        }
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
                System.out.println("Введён некорректный тип оружия! Введите тип ещё раз!");
                return initializeWeaponType();
        }
    }
}
