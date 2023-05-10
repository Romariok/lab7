package Command.Commands;

import Auth.AuthController;
import Command.CommandResponse;
import Command.Command_abstract;
import DataStructure.Response;
import Auth.User;

public class Register extends Command_abstract implements CommandResponse {


    private String output;

    public Register() {
    }

    @Override
    public void execute() {
        User user = (User) getValue();
        String login = user.getUser();
        String pass = user.getPass();
        output = AuthController.signUp(login,pass);
    }

    @Override
    public Response getResponse() {
        return new Response("register",output);
    }
}
