package client.setVariables;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for initializing field soundtrackName
 *
 * @author Roman Kobelev
 */
public class setSoundtrackName {
    public static String initializeSoundtrackName(Scanner scanner, boolean bool) {
        String line;
        if (!bool) {
            System.out.println("Введите название саунтрека: ");
        }

        line = scanner.nextLine();
        if (line != "\n") {
            if (line != null && line != "") {
                return line;
            } else {
                if (!bool) {
                    System.out.println("Введено некорректное название! Введите название ещё раз!");
                }

                return initializeSoundtrackName(scanner, bool);
            }
        } else {
            if (!bool) {
                System.out.println("Введено некорректное название! Введите название ещё раз!");
            }

            return initializeSoundtrackName(scanner, bool);
        }

    }
}
