package Auth;

public class User {
    private String user;
    private String pass;

    public User(){

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public String toString(){
        return this.user+"\n"+this.pass;
    }
}
