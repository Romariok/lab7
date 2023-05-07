package Auth;

import java.io.Serializable;

public class AuthResponse implements Serializable {
    private static final long serialVersionUID= 5574747289423911621L;
    private String command;
    private transient String user =  null;
    private transient String password = null;
    public AuthResponse(String command){
        this.command = command;
    }

    public AuthResponse(String command, String user, String password){
        this.command = command;
        this.user = user;
        this.password = password;
    }
}
