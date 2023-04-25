package Auth;

import Database.TableManager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Sol {
    private static TableManager manager = new TableManager("users");
    static String pepper;
    static String salt;

    private static void getSalt() {
        try {
            String[] res = manager.selectCommand("(login, pass)", "where id = 0").split("\n");
            salt = res[0];
            pepper = res[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String saltAndPepperPass(String pass) {
        if (pepper == null || salt == null) {
            getSalt();
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            pass += salt;
            byte[] passBytes = pass.getBytes(StandardCharsets.UTF_8);
            return new String(md.digest(passBytes)) + pepper;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Session getAuthorized(String user, String pass) throws Exception {
        String saltedPass = saltAndPepperPass(pass);
        String res = manager.selectCommand("(id)", "where login = " + user + " and pass = " + saltedPass);
        if (!res.equals("")) {
            return new Session(user);
        } else {
            throw new Exception("Login or password are incorrect!");
        }
    }
}

