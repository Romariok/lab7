package client.setVariables;

import java.util.Scanner;

/**
 * Class for initializing field name
 *
 * @author Roman Kobelev
 */
public class SetName {
    public static String initializeName() {
        String line;
//        if (Linkedlist.count != 0){
//            line = Linkedlist.getScript_input()[Linkedlist.count].trim();
//            Linkedlist.count++;
//        }
//        else{
        System.out.println("Введите имя человека: ");
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
//        }
        if (line != "\n") {
            if (line != null && line != "") {
                return line;
            } else {
                System.out.println("Введено некорректное имя! Введите имя ещё раз!");
                return initializeName();
            }
        } else {
            System.out.println("Введено некорректное имя! Введите имя ещё раз!");
            return initializeName();
        }

    }


}
