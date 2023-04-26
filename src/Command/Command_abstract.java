package Command;

import Auth.Session;
import DataStructure.CollectionManager;

import java.io.Serializable;

public abstract class Command_abstract implements Command, Serializable {
    private CollectionManager collectionManager;
    private boolean success;
    private boolean bd;
    private String output = "";

    private Session session;

    public Command_abstract(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.success = true;
        bd = false;
    }

    public Command_abstract() {
        this.success = true;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    private String[] args = new String[0];
    private Object value;

    public Object getValue() {
      return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }


    public abstract void execute();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isBd() {
        return bd;
    }

    public void setBd(boolean bd) {
        this.bd = bd;
    }

    @Override
    public void setOutput(String s) {
        this.output = s;
    }

    public void setSession(Session s) {
        this.session = s;
    }

    public Session getSession() {
        return this.session;
    }

    public String getOutput(){
        return this.output;
    }
}
