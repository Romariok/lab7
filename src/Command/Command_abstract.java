package Command;

import DataStructure.CollectionManager;

import java.io.Serializable;

public abstract class Command_abstract implements Command, Serializable {
    private CollectionManager collectionManager;
    private boolean success;

    public Command_abstract(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.success = true;
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


    private String[] args;
    private Object value;
    public Object getValue(){
        return value;
    }
    public void setValue(Object value){
        this.value = value;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args){
        this.args = args;
    }


    public abstract void execute();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
