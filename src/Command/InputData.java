package Command;

import Auth.Session;

import java.io.Serializable;

public class InputData implements Serializable {
    private static final long serialVersionUID = -6583043369271055876L;
    private CommandResponse commandResponse;
    private Session session;

    public InputData(){

    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setCommandResponse(CommandResponse commandResponse) {
        this.commandResponse = commandResponse;
    }

    public CommandResponse getCommandResponse() {
        return commandResponse;
    }

    public Session getSession() {
        return session;
    }
}
