package server.FileManagment;

import Data.*;
import DataStructure.CollectionManager;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ParserXMLtoBD extends ParserXML {
    private CollectionManager collectionManager;

    public ParserXMLtoBD(String path, CollectionManager collectionManager) {
        super(path);
        this.collectionManager = collectionManager;
    }

    public void parseData() {
        try {
            String rows = collectionManager.getDBManager().selectCommand("*");
            collectionManager.getConcurrentCollection().clear();
            for (String row : rows.split("\n\n")) {
                try {
                    if (row.equals(""))
                        break;
                    String[] params = row.split("\n");
                    HumanBeing hb = new HumanBeing();
                    Long id = Long.parseLong(params[0]);
                    hb.setId(id);
                    String name = params[1];
                    hb.setName(name);
                    Coordinates coordinates = new Coordinates(Integer.parseInt(params[2]), Long.parseLong(params[3]));
                    hb.setCoordinates(coordinates);
                    ZonedDateTime date = ZonedDateTime.ofInstant(Timestamp.valueOf(params[4]).toInstant(), ZoneId.systemDefault());
                    hb.setCreationDate(date);
                    boolean realHero = Boolean.parseBoolean(params[5]);
                    hb.setRealHero(realHero);
                    boolean hasToothpick = Boolean.parseBoolean(params[6]);
                    hb.setHasToothpick(hasToothpick);
                    long impactspeed = Long.parseLong(params[7]);
                    hb.setImpactSpeed(impactspeed);
                    String soundtrackname = params[8];
                    hb.setSoundtrackName(soundtrackname);
                    String weapontype = params[9];
                    switch (weapontype) {
                        case "Shotgun" -> hb.setWeaponType(WeaponType.SHOTGUN);
                        case "Rifle" -> hb.setWeaponType(WeaponType.RIFLE);
                        case "Knife" -> hb.setWeaponType(WeaponType.KNIFE);
                        case "Machine gun" -> hb.setWeaponType(WeaponType.MACHINE_GUN);
                    }
                    String mood = params[10];
                    switch (mood) {
                        case "Longing" -> hb.setMood(Mood.LONGING);
                        case "Gloom" -> hb.setMood(Mood.GLOOM);
                        case "Frenzy" -> hb.setMood(Mood.FRENZY);
                    }
                    boolean cool = Boolean.parseBoolean(params[11]);
                    Car car = new Car(cool);
                    hb.setCar(car);
                    collectionManager.getConcurrentCollection().add(hb);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            writeData(collectionManager.getConcurrentCollection());
            collectionManager.getConcurrentCollection().sort(HumanBeing::compareTo);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}