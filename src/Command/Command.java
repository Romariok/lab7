package Command;
import DataStructure.*;

import java.io.Serializable;

/**
 * The {@code src.Command} interface is used to implement the command pattern
 * @author Roman Kobelev
 */
public interface Command extends Serializable {
    void execute();
    String[] getArgs();
    void setArgs(String[] args);
    Object getValue();
    void setValue(Object object);
    void setCollectionManager(CollectionManager collectionManager);
    CollectionManager getCollectionManager();
}
