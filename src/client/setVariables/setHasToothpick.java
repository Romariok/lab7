package client.setVariables;


import java.util.Scanner;

/**
 * Class for initializing field HasToothpick
 *
 * @author Roman Kobelev
 */
public class setHasToothpick {
    public static Boolean initializeHasToothpick() {
        String line;
//        if (Linkedlist.count != 0){
//            line = Linkedlist.getScript_input()[Linkedlist.count].trim();
//            Linkedlist.count++;
//        }
//        else{
        System.out.println("Имеется ли у человека зубочистка во рту? (Да/Нет): ");
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
                return initializeHasToothpick();

        }
    }
}
