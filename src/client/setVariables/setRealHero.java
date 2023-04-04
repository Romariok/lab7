package client.setVariables;

import java.util.Scanner;

/**
 * Class for initializing field realHero
 *
 * @author Roman Kobelev
 */
public class setRealHero {
    public static boolean initializeRealHero() {
        String line;
//        if (Linkedlist.count != 0){
//            line = Linkedlist.getScript_input()[Linkedlist.count].trim();
//            Linkedlist.count++;
//        }
//        else{
        System.out.println("Является ли человек настоящим героем? (Да/Нет): ");
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
//        }
        switch (line) {
            case "Да":
                return true;
            case "Нет":
                return false;
            default:
                System.out.println("Введено некорректное значение! Введите значение ещё раз!");
                return initializeRealHero();

        }
    }
}
