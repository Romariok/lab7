package server.FileManagment;

import Data.*;
import DataStructure.CollectionManager;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParserfromBD {
    private CollectionManager collectionManager;

    public ParserfromBD(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void parseData() {
        try {
            String rows = collectionManager.getDBManager().selectCommand(CollectionManager.bdColumns);
            CopyOnWriteArrayList<HumanBeing> ls = collectionManager.getConcurrentCollection();
            ls.clear();
            for (String row : rows.split("\n\n")) {
                row = row.replace("(", "");
                row = row.replace(")", "");
                if (!rows.equals("")) {
                    String[] params = row.split(",");
                    HumanBeing hb = new HumanBeing();
                    Long id = Long.parseLong(params[0]);
                    hb.setId(id);
                    String name = params[1];
                    hb.setName(name);
                    Coordinates coordinates = new Coordinates(Integer.parseInt(params[2]), Long.parseLong(params[3]));
                    hb.setCoordinates(coordinates);
                    String time = params[4].substring(1, 20);
                    ZonedDateTime date = ZonedDateTime.ofInstant(Timestamp.valueOf(time).toInstant(), ZoneId.systemDefault());
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
                    hb.setLogin(params[12]);
                    ls.add(hb);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}