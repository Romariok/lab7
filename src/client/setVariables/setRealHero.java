package client.setVariables;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for initializing field realHero
 *
 * @author Roman Kobelev
 */
public class setRealHero {
    public static boolean initializeRealHero(Scanner scanner, boolean bool) {
        String line;
        if (!bool) {
            System.out.println("Является ли человек настоящим героем? (Да/Нет): ");
        }

        line = scanner.nextLine();
        switch (line) {
            case "Да":
                return true;
            case "Нет":
                return false;
            default:
                if (!bool) {
                    System.out.println("Введено некорректное значение! Введите значение ещё раз!");
                }
                return initializeRealHero(scanner, bool);

        }
    }
}
