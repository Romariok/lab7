package client.setVariables;

import Data.Mood;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for initializing field Mood
 *
 * @author Roman Kobelev
 * @see Mood
 */
public class setMood {
    public static Mood initializeMood(Scanner scanner) {
        String line;
        System.out.println("Введите настроение (Longing, Gloom, Frenzy): ");
        line = scanner.nextLine();
        switch (line) {
            case "Longing":
                return Mood.LONGING;
            case "Gloom":
                return Mood.GLOOM;
            case "Frenzy":
                return Mood.FRENZY;
            default:
                System.out.println("Введён некорректный тип оружия! Введите тип ещё раз!");
                return initializeMood(scanner);
        }
    }
}
