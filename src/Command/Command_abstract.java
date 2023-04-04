package Command;

import DataStructure.CollectionManager;

import java.io.Serializable;

public abstract class Command_abstract implements Command, Serializable {
    private CollectionManager collectionManager;

    public Command_abstract(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Command_abstract() {
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


}
