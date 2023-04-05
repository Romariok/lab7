package client.setVariables;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for initializing field realHero
 *
 * @author Roman Kobelev
 */
public class setRealHero {
    public static boolean initializeRealHero(Scanner scanner) {
        String line;
//        if (Linkedlist.count != 0){
//            line = Linkedlist.getScript_input()[Linkedlist.count].trim();
//            Linkedlist.count++;
//        }
//        else{
        System.out.println("Является ли человек настоящим героем? (Да/Нет): ");
        line = scanner.nextLine();
//        }
        switch (line) {
            case "Да":
                return true;
            case "Нет":
                return false;
            default:
                System.out.println("Введено некорректное значение! Введите значение ещё раз!");
                return initializeRealHero(scanner);

        }
    }
}
