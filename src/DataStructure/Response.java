package DataStructure;

import java.io.Serializable;

public class Response implements Serializable {
    public String command;
    public String output;
    public Response(String command, String output) {
        this.command = command;
        this.output = output;
    }
    public Response(String output) {
        this.output = output;
    }

    public String getCommand() {
        return command;
    }

    public String getOutput() {
        return output;
    }

}
