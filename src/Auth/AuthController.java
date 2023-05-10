package Auth;

import Database.AuthTableManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AuthController {
    private static final String pepper="S6obA9gp$hU";
    private static final AuthTableManager manager = new AuthTableManager();
    private static final MessageDigest messageDigest;
    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    static String salt;
    private static void getSalt(){
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[12];
        random.nextBytes(bytes);
        salt = bytesToHex(bytes);
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder str = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            String hexadecimal = Integer.toHexString(b & 0xFF);
            if (hexadecimal.length() == 1) {
                str.append('0');
            }
            str.append(hexadecimal);
        }
        return str.toString();
    }
    private static String saltAndPepperPass(String pass) {
        try {
            pass = pepper+pass+salt;
            byte[] passBytes = messageDigest.digest(pass.getBytes());
            return bytesToHex(passBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
    }
    private static void getSaltUser(String user) throws Exception {
        salt = manager.getSalt(user);
    }

    public static Session getAuthorized(String user, String pass) throws Exception {
        getSaltUser(user);
        String saltedPass = saltAndPepperPass(pass);
        String res = manager.selectCommand(user,saltedPass);
        if (!res.equals("")) {
            Session t = new Session();
            t.setAuthoriazed(true);
            t.setUser(user);
            return t;
        } else {
            throw new Exception("Login or password are incorrect!");
        }
    }
    public static String signUp(String user,String pass){
        getSalt();
        String salted = saltAndPepperPass(pass);
        try {
            if(manager.insertCommand(new String[]{user, salted, salt}))
            {
                return "User " + user + " added!";
            }
            return manager.getLastE();
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
