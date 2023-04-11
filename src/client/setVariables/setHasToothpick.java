package client.setVariables;


import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for initializing field HasToothpick
 *
 * @author Roman Kobelev
 */
public class setHasToothpick {
    public static Boolean initializeHasToothpick(Scanner scanner, boolean bool) {
        String line;
        if (!bool) {
            System.out.println("Имеется ли у человека зубочистка во рту? (Да/Нет): ");
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

                return initializeHasToothpick(scanner, bool);

        }
    }
}
