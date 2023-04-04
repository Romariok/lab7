package client.setVariables;

import java.util.Scanner;

/**
 * Class for initializing field soundtrackName
 *
 * @author Roman Kobelev
 */
public class setSoundtrackName {
    public static String initializeSoundtrackName() {
        String line;
//        if (Linkedlist.count != 0){
//            line = Linkedlist.getScript_input()[Linkedlist.count].trim();
//            Linkedlist.count++;
//        }
//        else{
        System.out.println("Введите название саунтрека: ");
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
//        }
        if (line != "\n") {
            if (line != null && line != "") {
                return line;
            } else {
                System.out.println("Введено некорректное название! Введите название ещё раз!");
                return initializeSoundtrackName();
            }
        } else {
            System.out.println("Введено некорректное название! Введите название ещё раз!");
            return initializeSoundtrackName();
        }

    }
}
