package Command;

import Auth.Session;
import DataStructure.Response;

public interface CommandResponse extends Command{
    Response getResponse();
    Session getSession();
    void setSession(Session s);
}
