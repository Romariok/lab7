package Auth;

import java.io.Serial;
import java.io.Serializable;

public class AuthResponse implements Serializable {
    @Serial
    private static final long serialVersionUID= -4444266805917589812L;
    private String command = "";
    private String user = "";
    private boolean autorized = false;
    public AuthResponse(String command){
        this.command = command;
    }

    public AuthResponse(String command, String user, boolean autorized){
        this.command = command;
        this.user = user;
        this.autorized = autorized;
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
}
