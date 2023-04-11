package server.FileManagment;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilderFactory;

import Data.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

/**
 * A class that contains methods for parsing collection data into XML and vice versa
 * Also methods for loading and saving data
 * @author Roman Kobelev
 */
public class ParserXML {
    /**
     * Document that contains XML
     */
    private Document d;
    /**
     * The file in which the data will be stored and execute
     */
    private File f;
    /**
     * Path to XML file
     */
    private String path;

    /**
     * Constructor of {@code ParserXML} class with parameter
     * Constructor initialize file and checking rights
     * @param path path to the file
     */
    public ParserXML(String path){
        this.path = path;
        f = new File(path);
        checkRights();
    }

    /**
     * Used for reading data in file
     */
    protected void readData(){
        char[] array = new char[(int)f.length()];
        try (InputStreamReader isr = new InputStreamReader( new FileInputStream(f), StandardCharsets.UTF_8)) {
            isr.read(array);
            String readedFile = String.valueOf(array).trim();
            d = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new InputSource(new StringReader(readedFile)));

        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }

    /**
     * Used to check rights of file
     */
    protected void checkRights(){
        try{
            if (!f.canRead() || !f.canWrite()) throw new SecurityException();
        }
        catch (SecurityException ex){
            System.out.println("Для данного файла нет прав записи или чтения. Для работы нужны оба разрешения!");
            System.exit(1);
        }
    }

    /**
     * Used for parsing XML file to collection
     * @param ls Collection
     * @throws ParseException
     */
    public void parseData(LinkedList<HumanBeing> ls) throws ParseException{
        readData();
        NodeList object = d.getElementsByTagName("humanbeing");
        for (int i = 0; i < object.getLength();i++){
            NodeList tags = object.item(i).getChildNodes();
            HumanBeing hb = new HumanBeing();
            for(int j = 0;j < tags.getLength();j++){
                Node item = tags.item(j);
                try{
                    switch (item.getNodeName()){
                        case "id":
                            try {
                                Long id = Long.parseLong(item.getTextContent());
                                hb.setId(id);
                            } catch (Exception e) {
                                throw new ParseException("Incorrect XML format", 1);
                            }
                            break;
                        case "name":
                            try {
                                String name = item.getTextContent();
                                hb.setName(name);
                            } catch (Exception e) {
                                throw new ParseException("Incorrect XML format", 1);
                            }
                            break;
                        case "coordinates":
                            try {
                                NodeList coordinatesNodes = item.getChildNodes();
                                String x = coordinatesNodes.item(1).getTextContent().trim();
                                String y = coordinatesNodes.item(3).getTextContent().trim();
                                Coordinates coordinates = new Coordinates(Integer.parseInt(x), Long.parseLong(y));
                                hb.setCoordinates(coordinates);
                            } catch (Exception e) {
                                throw new ParseException("Incorrect XML format", 1);
                            }
                            break;
                        case "creationDate":
                            try {
                                ZonedDateTime date = ZonedDateTime.parse(item.getTextContent());
                                hb.setCreationDate(date);
                            } catch (Exception e) {
                                throw new ParseException("Incorrect XML format", 1);
                            }
                            break;
                        case "realHero":
                            try {
                                boolean realHero = Boolean.parseBoolean(item.getTextContent());
                                hb.setRealHero(realHero);
                            } catch (Exception e) {
                                throw new ParseException("Incorrect XML format", 1);
                            }
                            break;
                        case "hasToothpick":
                            try {
                                boolean hasToothpick = Boolean.parseBoolean(item.getTextContent());
                                hb.setHasToothpick(hasToothpick);
                            } catch (Exception e) {
                                throw new ParseException("Incorrect XML format", 1);
                            }
                            break;
                        case "impactSpeed":
                            try {
                                Long impactspeed = Long.parseLong(item.getTextContent());
                                hb.setImpactSpeed(impactspeed);
                            } catch (Exception e) {
                                throw new ParseException("Incorrect XML format", 1);
                            }
                            break;
                        case "soundtrackName":
                            try {
                                String soundtrackname = item.getTextContent();
                                hb.setSoundtrackName(soundtrackname);
                            } catch (Exception e) {
                                throw new ParseException("Incorrect XML format", 1);
                            }
                            break;
                        case "weaponType":
                            try {
                                String weapontype = item.getTextContent();
                                switch (weapontype){
                                    case "Shotgun" -> hb.setWeaponType(WeaponType.SHOTGUN);
                                    case "Rifle" -> hb.setWeaponType(WeaponType.RIFLE);
                                    case "Knife" ->hb.setWeaponType(WeaponType.KNIFE);
                                    case "Machine gun" ->hb.setWeaponType(WeaponType.MACHINE_GUN);
                                    default ->  throw new ParseException("Incorrect XML format", 1);
                                }
                            } catch (Exception e) {
                                throw new ParseException("Incorrect XML format", 1);
                            }
                            break;
                        case "mood":
                            try {
                                String mood = item.getTextContent();
                                switch (mood){
                                    case "Longing" -> hb.setMood(Mood.LONGING);
                                    case "Gloom" -> hb.setMood(Mood.GLOOM);
                                    case "Frenzy" -> hb.setMood(Mood.FRENZY);
                                    default ->  throw new ParseException("Incorrect XML format", 1);
                                }
                            } catch (Exception e) {
                                throw new ParseException("Incorrect XML format", 1);
                            }
                            break;
                        case "car":
                            try {
                                Boolean cool = Boolean.parseBoolean(item.getTextContent());
                                Car car = new Car(cool);
                                hb.setCar(car);
                            } catch (Exception e) {
                                throw new ParseException("Incorrect XML format", 1);
                            }
                            break;
                    }
                }
                catch(ParseException ex){
                    throw new ParseException("Incorrect XML format", 1);
                }
            }
            ls.add(hb);
        }
    }

    /**
     * Used for parsing collection to XML
     * @param ls Collection
     */
    public void writeData(LinkedList<HumanBeing> ls){
        checkRights();
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(f))){
            printWriter.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<HumanBeing>\n");
            for(int i = 0;i< ls.size();i++){
                HumanBeing humanBeing = ls.get(i);
                printWriter.append("\t<humanbeing>\n");
                printWriter.append("\t\t<id>"+humanBeing.getId()+"</id>\n");
                printWriter.append("\t\t<name>"+humanBeing.getName().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("'", "&apos;")
                        .replace("\"", "&quot;")+"</name>\n");
                printWriter.append("\t\t<coordinates>\n\t\t\t<x>"+humanBeing.getCoordinates().getX()+"</x>\n\t\t\t<y>"+humanBeing.getCoordinates().getY()+"</y>\n\t\t</coordinates>\n");
                printWriter.append("\t\t<creationDate>"+humanBeing.getCreationDate()+"</creationDate>\n");
                printWriter.append("\t\t<realHero>"+humanBeing.isRealHero()+"</realHero>\n");
                printWriter.append("\t\t<hasToothpick>"+humanBeing.getHasToothpick()+"</hasToothpick>\n");
                printWriter.append("\t\t<impactSpeed>"+humanBeing.getImpactSpeed()+"</impactSpeed>\n");
                printWriter.append("\t\t<soundtrackName>"+humanBeing.getSoundtrackName().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("'", "&apos;")
                        .replace("\"", "&quot;")+"</soundtrackName>\n");
                printWriter.append("\t\t<weaponType>"+humanBeing.getWeaponType()+"</weaponType>\n");
                printWriter.append("\t\t<mood>"+humanBeing.getMood()+"</mood>\n");
                printWriter.append("\t\t<car>"+humanBeing.getCar()+"</car>\n");
                printWriter.append("\t</humanbeing>\n");
                printWriter.flush();
            }
            printWriter.append("</HumanBeing>");
            printWriter.flush();
        }
        catch (IOException ex){
            ex.getStackTrace();
        }


    }

    public void clear_file(){
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(f))){
            printWriter.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<HumanBeing>\n");
            printWriter.append("</HumanBeing>");
            printWriter.flush();
        }
        catch (IOException ex){
            ex.getStackTrace();
        }
    }


}
