package client.setVariables;


import java.util.Scanner;

/**
 * Class for initializing field Coordinates
 *
 * @author Roman Kobelev
 */
public class setCoordinates {
    public static Long setCoordinateY(Scanner scanner, boolean bool) {
        String line;
        if (!bool) {
            System.out.println("Введите координату y: ");
        }
        line = scanner.nextLine();
//        }
        if (line != "\n" && line != "" && line != null) {
            try {
                if (Long.parseLong(line) <= 493) {
                    return Long.parseLong(line);
                } else {
                    if (!bool) {
                        System.out.println("Введено некорректное значение для координаты y! Введите координату ещё раз!");
                    }

                    return setCoordinateY(scanner, bool);
                }
            } catch (NumberFormatException ex) {
                if (!bool) {
                    System.out.println("То, что вы ввели не является числом");
                }

                return setCoordinateY(scanner, bool);
            }
        } else {
            if (!bool) {
                System.out.println("Введено некорректное значение для координаты y! Введите координату ещё раз!");
            }

            return setCoordinateY(scanner, bool);
        }

    }

    public static int setCoordinateX(Scanner scanner, boolean bool) {
        String line;
        if (!bool) {
            System.out.println("Введите координату x: ");
        }

        line = scanner.nextLine();
//        }
        if (line != "\n" && line != null && line != "") {
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                if (!bool) {
                    System.out.println("То, что вы ввели не является числом");
                }

                return setCoordinateX(scanner, bool);
            }
        } else return 0;

    }
}