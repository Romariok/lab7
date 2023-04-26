package Command.Commands;

import Command.CommandResponse;
import Command.Command_abstract;
import DataStructure.Response;
import Auth.*;

public class Register extends Command_abstract implements CommandResponse {
    private String user;
    private String output = "";
    private String pass;


    public Register(){

    }
    @Override
    public void execute() {
        user = getArgs()[0];
        pass = getArgs()[1];
        output = Sol.signUp(user,pass);
    }

    @Override
    public Response getResponse() {
        return new Response("register",output);
    }
}
