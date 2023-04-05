package client.setVariables;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for initializing field impactSpeed
 *
 * @author Roman Kobelev
 */
public class setImpactSpeed {
    public static long initializeImpactSpeed(Scanner scanner) {
        String line;

        System.out.println("Введите скорость удара (max 572): ");
        line = " ";
        try {
            line = scanner.nextLine();
        } catch (Exception ex) {
            System.out.println("Неверное значение");
        }
        if (line != "\n") {
            try {
                if (line != null && Long.parseLong(line) <= 572 && Long.parseLong(line) >= 0 && line != "") {
                    return Long.parseLong(line);
                } else {
                    System.out.println("Введено некорректная скорость! Введите скорость ещё раз!");
                    return initializeImpactSpeed(scanner);
                }
            } catch (NumberFormatException ex) {
                System.out.println("То, что вы ввели не является числом");
                return initializeImpactSpeed(scanner);

            }
        } else {
            System.out.println("Введено некорректная скорость! Введите скорость ещё раз!");
            return initializeImpactSpeed(scanner);
        }

    }
}

