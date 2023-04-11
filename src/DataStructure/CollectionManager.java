package DataStructure;


import server.FileManagment.ParserXML;
import Data.*;


import java.io.*;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.*;


public class CollectionManager {
    /**
     * LinkedList collection that contains HumanBeing objects
     */
    private LinkedList<HumanBeing> humans = new LinkedList<HumanBeing>();
    /**
     * Counter for lines in script
     */
    public static int count = 0;
    /**
     * Value for initialized time
     */
    private final ZonedDateTime indate;
    /**
     * ParserXML used for execute and write data to file
     *
     * @see ParserXML
     */
    private ParserXML parserXML;
    /**
     * Initialize {@code Comparator}
     */
    Comparator<HumanBeing> comparator = (o1, o2) -> o2.compareTo(o1);

    /**
     * Constructor of {@code Linkedlist} where we initialize date of creation of collection
     * Also checking file and loading data from xml file
     *
     * @param path path to the file
     * @see ParserXML
     */
    public CollectionManager(String path) {
        try {
            if (path == null) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.err.println("Введите путь до файла в виде аргумента!");
            System.exit(1);
        }
        indate = ZonedDateTime.now();


        parserXML = new ParserXML(path);
        load();
    }

    public ZonedDateTime getIndate(){
        return indate;
    }

    public Comparator<HumanBeing> getComparator() {
        return comparator;
    }


    public LinkedList<HumanBeing> getCollection(){
        return humans;
    }
    public void setCollection(LinkedList<HumanBeing> linkedList){
        this.humans = linkedList;
    }


    /**
     * Used to load data from file to collection
     */
    private void load() {
        try {
            parserXML.parseData(humans);

        } catch (Exception ex) {
            System.err.println("Возникла непредвиденная ошибка! Файл не загрузился!");
            System.exit(1);
        }
        System.out.println("Файл успешно загружен в коллекцию!");
    }
}
