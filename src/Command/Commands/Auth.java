package Command.Commands;

import Command.CommandResponse;
import Command.Command_abstract;
import DataStructure.Response;
import Auth.*;

public class Auth extends Command_abstract implements CommandResponse {
    private String user;
    private String output;
    private String pass;
    public Auth(){
    }

    @Override
    public Response getResponse() {
        return new Response("auth",output);
    }

    @Override
    public void execute() {
        user = getArgs()[0];
        pass = getArgs()[1];
        try {
            Session t = Sol.getAuthorized(user,pass);
            this.getSession().setAuthorized(t.isAuthorized());
            output = "Sign in:" + t.isAuthorized();
        }
        catch (Exception e){
            this.getSession().setAuthorized(false);
            output = e.getMessage();
        }
    }
}
