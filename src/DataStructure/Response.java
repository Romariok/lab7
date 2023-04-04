package DataStructure;

import java.io.Serializable;

public class Response implements Serializable {
    public String command;
    public String output;
    public String[] args;
    public Serializable object;

    public Response(String command, String[] args, String output) {
        this.command = command;
        this.output = output;
        this.args = args;
    }
    public Response(String command, String[] args, String output, Serializable object) {
        this.command = command;
        this.output = output;
        this.args = args;
        this.object = object;
    }

    public String getCommand() {
        return command;
    }

    public String getOutput() {
        return output;
    }

    public Serializable getObject() {
        return object;
    }
}
