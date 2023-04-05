package client.setVariables;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for initializing field name
 *
 * @author Roman Kobelev
 */
public class SetName {
    public static String initializeName(Scanner scanner) {
        String line;
        System.out.println("Введите имя человека: ");
        line = scanner.nextLine();
        if (line != "\n") {
            if (line != null && line != "") {
                return line;
            } else {
                System.out.println("Введено некорректное имя! Введите имя ещё раз!");
                return initializeName(scanner);
            }
        } else {
            System.out.println("Введено некорректное имя! Введите имя ещё раз!");
            return initializeName(scanner);
        }

    }


}
