package Command;

import DataStructure.Response;

public interface CommandResponse extends Command{
    Response getResponse();
    void setUser(String user);
}
