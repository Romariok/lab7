package client.setVariables;

import java.util.Scanner;

/**
 * Class for initializing field Coordinates
 *
 * @author Roman Kobelev
 * @see Coordinates
 */
public class setCoordinates {
    public static Long setCoordinateY() {
        String line;
//        if (Linkedlist.count != 0){
//            line = Linkedlist.getScript_input()[Linkedlist.count].trim();
//            Linkedlist.count++;
//        }
//        else{
        System.out.println("Введите координату y: ");
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
//        }
        if (line != "\n" && line != "" && line != null) {
            try {
                if (Long.parseLong(line) <= 493) {
                    return Long.parseLong(line);
                } else {
                    System.out.println("Введено некорректное значение для координаты y! Введите координату ещё раз!");
                    return setCoordinateY();
                }
            } catch (NumberFormatException ex) {
                System.out.println("То, что вы ввели не является числом");
                return setCoordinateY();
            }
        } else {
            System.out.println("Введено некорректное значение для координаты y! Введите координату ещё раз!");
            return setCoordinateY();
        }

    }

    public static int setCoordinateX() {
        String line;
//        if (Linkedlist.count != 0) {
//            line = Linkedlist.getScript_input()[Linkedlist.count].trim();
//            Linkedlist.count++;
//        } else {
        System.out.println("Введите координату x: ");
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
//        }
        if (line != "\n" && line != null && line != "") {
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("То, что вы ввели не является числом");
                return setCoordinateX();
            }
        } else return 0;

    }
}
//TODO