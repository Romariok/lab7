package Data.setVariables;

import Auth.User;

import java.util.Scanner;

public class setUser {
    public static User initializeUser(Scanner scanner, boolean bool){
        User user = new User();
        user.setUser(initializeLogin(scanner,bool));
        user.setPass(initializePass(scanner,bool));
        return user;
    }
    private static String initializeLogin(Scanner scanner, boolean bool){
        String line;
        if(!bool){
            System.out.println("Введите имя пользователя:\n");
        }
        line = scanner.nextLine();
        if(!line.equals("\n")&&!line.equals("")){
            return line;
        } else {
            if (!bool) {
                System.out.println("Имя пользователя не может быть пустым!");
            }
            return initializeLogin(scanner,bool);
        }
    }
    private static String initializePass(Scanner scanner, boolean bool){
        String line;
        if(!bool){
            System.out.println("Введите пароль пользователя:\n");
        }
        line = scanner.nextLine();
        if(!line.equals("\n")&&!line.equals("")){
            return line;
        } else {
            if (!bool) {
                System.out.println("Пароль не может быть пустым!");
            }
            return initializePass(scanner,bool);
        }
    }

}
