package Command.Commands;

import Auth.AuthController;
import Auth.Session;
import Command.CommandResponse;
import Command.Command_abstract;
import DataStructure.Response;
import Auth.User;

public class Auth extends Command_abstract implements CommandResponse {

    private String output;

    public Auth() {

    }

    @Override
    public void execute() {
        User user = (User) getValue();
        String login = user.getUser();
        String pass = user.getPass();
        try {
            Session session = AuthController.getAuthorized(login, pass);
            setSuccess(true);
            output = "Success!";
        } catch (Exception e) {
            setSuccess(false);
            output = e.getMessage();
        }
    }

    @Override
    public Response getResponse() {
        return new Response("auth", output);
    }
}
