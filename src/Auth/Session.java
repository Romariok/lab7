package Auth;

import java.io.Serializable;

public class Session implements Serializable {
    private String user;
    public Session(String user){
        this.user = user;
    }

    public String getLogin() {
        return this.user;
    }
}
