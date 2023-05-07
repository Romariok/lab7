package Auth;

import Database.AuthTableManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthController {
    private static final AuthTableManager manager = new AuthTableManager();
    private static MessageDigest messageDigest;
    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
    static String pepper;
    static String salt;

    //auth+sigh up
}
