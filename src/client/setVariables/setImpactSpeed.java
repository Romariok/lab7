package client.setVariables;

import java.util.Scanner;

/**
 * Class for initializing field impactSpeed
 *
 * @author Roman Kobelev
 */
public class setImpactSpeed {
    public static long initializeImpactSpeed(Scanner scanner, boolean bool) {
        String line;
        if (!bool) {
            System.out.println("Введите скорость удара (max 572): ");
        }

        line = " ";
        try {
            line = scanner.nextLine();
        } catch (Exception ex) {
            if (!bool) {
                System.out.println("Неверное значение");
            }

        }
        if (line != "\n") {
            try {
                if (line != null && Long.parseLong(line) <= 572 && Long.parseLong(line) >= 0 && line != "") {
                    return Long.parseLong(line);
                } else {
                    if (!bool) {
                        System.out.println("Введено некорректная скорость! Введите скорость ещё раз!");
                    }

                    return initializeImpactSpeed(scanner, bool);
                }
            } catch (NumberFormatException ex) {
                if (!bool) {
                    System.out.println("То, что вы ввели не является числом");
                }

                return initializeImpactSpeed(scanner, bool);

            }
        } else {
            if (!bool) {
                System.out.println("Введено некорректная скорость! Введите скорость ещё раз!");
            }

            return initializeImpactSpeed(scanner, bool);
        }

    }
}

