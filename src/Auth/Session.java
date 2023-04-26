package Auth;

import java.io.Serializable;

public class Session implements Serializable {
    private String user;
    private boolean authorized;
    public Session(String user){
        this.user = user;
        authorized = false;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getUser(){
        return this.user;
    }
}
