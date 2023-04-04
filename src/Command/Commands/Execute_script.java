package Command.Commands;

import Command.*;
import DataStructure.CollectionManager;

/**
 * Class for the execute_script command. Execute script.
 */
public class Execute_script extends Command_abstract {
    private String output;

    public Execute_script(CollectionManager collectionManager){
        super(collectionManager);
    }
    public Execute_script(){
    }
    @Override
    public void execute(){
//        collectionManager.execute_script(path);
        System.out.println("execute_script");
    }
}
