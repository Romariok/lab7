package client.setVariables;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for initializing field Coordinates
 *
 * @author Roman Kobelev
 */
public class setCoordinates {
    public static Long setCoordinateY(Scanner scanner) {
        String line;
        System.out.println("Введите координату y: ");
        line = scanner.nextLine();
//        }
        if (line != "\n" && line != "" && line != null) {
            try {
                if (Long.parseLong(line) <= 493) {
                    return Long.parseLong(line);
                } else {
                    System.out.println("Введено некорректное значение для координаты y! Введите координату ещё раз!");
                    return setCoordinateY(scanner);
                }
            } catch (NumberFormatException ex) {
                System.out.println("То, что вы ввели не является числом");
                return setCoordinateY(scanner);
            }
        } else {
            System.out.println("Введено некорректное значение для координаты y! Введите координату ещё раз!");
            return setCoordinateY(scanner);
        }

    }

    public static int setCoordinateX(Scanner scanner) {
        String line;
        System.out.println("Введите координату x: ");
        line = scanner.nextLine();
//        }
        if (line != "\n" && line != null && line != "") {
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("То, что вы ввели не является числом");
                return setCoordinateX(scanner);
            }
        } else return 0;

    }
}