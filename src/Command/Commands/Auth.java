package Command.Commands;

import Command.CommandResponse;
import Command.Command_abstract;
import DataStructure.Response;
import Auth.*;

public class Auth extends Command_abstract implements CommandResponse {
    private String user;
    private boolean authorized;
    private String pass;
    private Session session = null;
    public Auth(){
        authorized = false;
    }

    @Override
    public Response getResponse() {
    }

    @Override
    public void execute() {
        setBd(true);
        user = getArgs()[0];
        pass = getArgs()[1];
        session = S
    }
}
