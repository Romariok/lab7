package Auth;

import Database.TableManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Sol {
    private static TableManager manager = new TableManager("users");
    private static MessageDigest messageDigest;
    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    static String pepper;
    static String salt;

    private static void getSaltAndPepper() {
        try {
            String[] res = manager.selectCommand("(login, pass)", "where id = 0").replace("\n", "").split(",");
            pepper = res[0].replace(")", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private static void getSaltUser(String user){
        try {
            String[] res = manager.selectCommand("(login, salt)", "where login = " + "'" + user+"'").replace("\n", "").split(",");
            salt = res[1].replace(")", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Session getAuthorized(String user, String pass) throws Exception {
        getSaltAndPepper();
        getSaltUser(user);
        String saltedPass = saltAndPepperPass(pass);
        String res = manager.selectCommand("(id)", "where login = " + "'" + user + "'" + " and pass = " + "'" + saltedPass + "'");
        if (!res.equals("")) {
            Session t = new Session(user);
            t.setAuthorized(true);
            return t;
        } else {
            throw new Exception("Login or password are incorrect!");
        }
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


    private static void getSalt(){
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[12];
        random.nextBytes(bytes);
        salt = bytesToHex(bytes);
    }
    public static String signUp(String user,String pass){
        getSaltAndPepper();
        getSalt();
        try {
            manager.insertCommand("(login, pass, salt)", "('"+user+"','"+saltAndPepperPass(pass)+"','"+salt+"')");
            return "User " + user +" added!";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}

