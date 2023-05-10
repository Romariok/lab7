package Auth;

import java.io.Serial;
import java.io.Serializable;

public class AuthResponse implements Serializable {
    @Serial
    private static final long serialVersionUID= 7932093985472624567L;
    private String command = "";
    private String args = "";
    private String object = "";
    private String user = "";
    private boolean autorized = false;
    public AuthResponse(String command){
        this.command = command;
    }

    public AuthResponse(String command, String user, boolean autorized, String args, String object){
        this.command = command;
        this.user = user;
        this.autorized = autorized;
        this.args = args;
        this.object = object;
    }

    public boolean isAutorized() {
        return autorized;
    }

    public String getUser() {
        return user;
    }

    public String getCommand() {
        return command;
    }

    public String getArgs() {
        return args;
    }

    public String getObject() {
        return object;
    }
}
