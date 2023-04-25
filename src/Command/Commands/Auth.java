package Command.Commands;

import Command.CommandResponse;
import Command.Command_abstract;
import DataStructure.Response;
import Auth.*;

public class Auth extends Command_abstract implements CommandResponse {
    private String user;
    private String output;
    private boolean authorized;
    private String pass;
    private Session session = null;
    public Auth(){
        authorized = false;
    }

    @Override
    public Response getResponse() {
        return new Response("auth",output);
    }

    @Override
    public void execute() {
        setBd(true);
        user = getArgs()[0];
        pass = getArgs()[1];
        try {
            session = Sol.getAuthorized(user, pass);
        }
        catch (Exception e){
            output = e.getMessage();
        }
    }

    public Session getSession() {
        return session;
    }
}
