package DataStructure;


import Database.TableManager;
import server.FileManagment.ParserXML;
import Data.*;
import server.FileManagment.ParserXMLtoBD;


import java.io.*;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionManager {
    /**
     * LinkedList collection that contains HumanBeing objects
     */
    private LinkedList<HumanBeing> humans = new LinkedList<>();
    private CopyOnWriteArrayList<HumanBeing> concurrentHumans = new CopyOnWriteArrayList<>();

    /**
     * Value for initialized time
     */
    private final ZonedDateTime indate;
    /**
     * ParserXML used for execute and write data to file
     *
     * @see ParserXMLtoBD
     */
    private ParserXMLtoBD parserXMLtoBD;
    /**
     * Initialize {@code Comparator}
     */
    Comparator<HumanBeing> comparator = (o1, o2) -> o2.compareTo(o1);

    /**
     * Constructor of {@code Linkedlist} where we initialize date of creation of collection
     * Also checking file and loading data from xml file
     *
     * @param path path to the file
     * @see ParserXMLtoBD
     */

    private TableManager hbManager = new TableManager("humanbeing");

    public static String bdColumns = "(name, x, y, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, carCool)";
    public CollectionManager(String path) {
        try {
            if (path == null) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.err.println("Введите путь до файла в виде аргумента!");
            System.exit(1);
        }
        indate = ZonedDateTime.now();


        parserXMLtoBD = new ParserXMLtoBD(path,this);
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
    public CopyOnWriteArrayList<HumanBeing> getConcurrentCollection(){return concurrentHumans;}
    public void setCollection(LinkedList<HumanBeing> linkedList){
        this.humans = linkedList;
    }
    public void setConcurrentCollection(CopyOnWriteArrayList<HumanBeing> ls){this.concurrentHumans = ls;}

    /**
     * Used to load data from file to collection
     */
    private void load() {
        try {
            parserXMLtoBD.parseData(concurrentHumans);
        } catch (Exception ex) {
            System.err.println("Возникла непредвиденная ошибка! Файл не загрузился!");
            System.exit(1);
        }
        System.out.println("Файл успешно загружен в коллекцию!");
    }

    public TableManager getDBManager(){
        return this.hbManager;
    }
}
