package client.setVariables;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;


public class setCar {
    public static boolean initializeCar(Scanner scanner, boolean bool) {
        String line;
        if (!bool){
            System.out.println("Крутая ли у человека машина? (Да/Нет): ");
        }

        line = scanner.nextLine();
        switch (line) {
            case "Да":
                return true;
            case "Нет":
                return false;
            default:
                if (!bool){
                    System.out.println("Введено некорректное значение! Введите значение ещё раз!");
                }
                return initializeCar(scanner, bool);

        }
    }

}
