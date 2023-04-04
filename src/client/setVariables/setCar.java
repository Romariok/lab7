package client.setVariables;

import java.util.Scanner;


public class setCar {
    public static boolean initializeCar() {
        String line;
//        if (Linkedlist.count != 0){
//            line = Linkedlist.getScript_input()[Linkedlist.count].trim();
//            Linkedlist.count++;
//            if (Linkedlist.count == Linkedlist.script_input.length){
//                Linkedlist.count = 0;
//                Linkedlist.script_input = new String[]{};
//            }
//        }
//        else{
        System.out.println("Крутая ли у человека машина? (Да/Нет): ");
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
                return initializeCar();

        }
    }
}
