package client.setVariables;

import Data.Mood;

import java.util.Scanner;

/**
 * Class for initializing field Mood
 *
 * @author Roman Kobelev
 * @see Mood
 */
public class setMood {
    public static Mood initializeMood() {
        String line;
//        if (Linkedlist.count != 0){
//            line = Linkedlist.getScript_input()[Linkedlist.count].trim();
//            Linkedlist.count++;
//        }
//        else{
        System.out.println("Введите настроение (Longing, Gloom, Frenzy): ");
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
//        }
        switch (line) {
            case "Longing":
                return Mood.LONGING;
            case "Gloom":
                return Mood.GLOOM;
            case "Frenzy":
                return Mood.FRENZY;
            default:
                System.out.println("Введён некорректный тип оружия! Введите тип ещё раз!");
                return initializeMood();
        }
    }
}
