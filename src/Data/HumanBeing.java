package Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Class {@code HumanBeing} represents a description of human being which
 * includes itself id, name, coordinates, creation date, status of real hero, status of toothpick,
 * impact speed, soundtrack name, weapon type, mood and status of car
 * @author Roman Kobelev
 */

public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    private static Long nextId = 1L;
    /**
     * id of human being
     * Generates automatically, can't be null, greater than zero
     */
    private Long id;
    /**
     * Name of human being
     * can't be null, string can't be empty
     */
    private String name;
    /**
     * Coordinates of human being
     * can't be null
     * @see Coordinates
     */
    private Coordinates coordinates;
    /**
     * Creation date of human being
     * can't be null, generates automatically
     */
    private ZonedDateTime creationDate;
    /**
     * Status of real hero of human being
     */
    private boolean realHero;
    /**
     * Status of toothpick of human being
     * can't be null
     */
    private Boolean hasToothpick;
    /**
     * Impact speed of human being
     * max value is 572
     */
    private long impactSpeed;
    /**
     * Soundtrack name that corresponds to human being
     * can't be null
     */
    private String soundtrackName;
    /**
     * Type of weapon that human being have
     * can't be null
     * @see WeaponType
     */
    private WeaponType weaponType;
    /**
     * Mood of human being
     * can't be null
     * @see Mood
     */
    private Mood mood;
    /**
     * Human being's car
     * can't be null
     * @see Car
     */
    private Car car;

    /**
     * Constructor of {@code HumanBeing} class with all parameters
     * @param name
     * @param coordinates
     * @param realHero
     * @param hasToothpick
     * @param impactSpeed
     * @param soundtrackName
     * @param weaponType
     * @param mood
     * @param car
     * @see WeaponType
     * @see Coordinates
     * @see Mood
     * @see Car
     */
    public HumanBeing(String name, Coordinates coordinates, boolean realHero, Boolean hasToothpick, long impactSpeed, String soundtrackName, WeaponType weaponType, Mood mood, Car car){
        this.id = nextId++;
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.soundtrackName = soundtrackName;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    /**
     * Constructor of {@code HumanBeing} class without parameters
     */
    public HumanBeing(){
        this.id = nextId++;
    }

    /**
     * @return creation date of human being
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Set current creation date for human being
     */
    public void setCreationDate() {
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Set particular creation date for human being
     * @param date
     */
    public void setCreationDate(ZonedDateTime date){
        this.creationDate = date;
    }

    /**
     * @return id of human being
     */
    public Long getId(){
        return this.id;
    }

    /**
     * Set id for human being
     * @param id
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * @return name of human being
     */
    public String getName(){
        return this.name;
    }

    /**
     * Set name for human being
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @see Coordinates
     * @return field {@code Coordinates} of human being
     */
    public Coordinates getCoordinates(){
        return this.coordinates;
    }

    /**
     * Set field {@code Coordinates} for human being
     * @param coordinates
     * @see Coordinates
     */
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }

    /**
     * @return status of realHero of human being
     */
    public boolean isRealHero(){
        return this.realHero;
    }

    /**
     * Set status of realHero gor human being
     * @param realHero
     */
    public void setRealHero(boolean realHero){
        this.realHero = realHero;
    }

    /**
     * @return status of hasToothpick of human being
     */
    public Boolean getHasToothpick(){
        return this.hasToothpick;
    }

    /**
     * Set status of hasToothpick for human being
     * @param hasToothpick
     */
    public void setHasToothpick(Boolean hasToothpick){
        this.hasToothpick = hasToothpick;
    }

    /**
     * @return impact speed of human being
     */
    public long getImpactSpeed(){
        return this.impactSpeed;
    }

    /**
     * Set impact speed for human being
     * @param impactSpeed
     */
    public void setImpactSpeed(long impactSpeed){
        this.impactSpeed = impactSpeed;
    }

    /**
     * @return soundtrack name of human being
     */
    public String getSoundtrackName(){
        return this.soundtrackName;
    }

    /**
     * Set soundtrack name for human being
     * @param soundtrackName
     */
    public void setSoundtrackName(String soundtrackName){
        this.soundtrackName = soundtrackName;
    }

    /**
     * @see WeaponType
     * @return value weaponType of human being
     */
    public WeaponType getWeaponType(){
        return this.weaponType;
    }

    /**
     * Set weapon type for human being
     * @param weaponType
     * @see WeaponType
     */
    public void setWeaponType(WeaponType weaponType){
        this.weaponType = weaponType;
    }

    /**
     * @return mood of human being
     * @see Mood
     */
    public Mood getMood(){
        return this.mood;
    }

    /**
     * Set mood for human being
     * @param mood
     * @see Mood
     */
    public void setMood(Mood mood){
        this.mood = mood;
    }

     /**
     * @return field Car of human being
     * @see Car
     */
    public Car getCar(){
        return this.car;
    }

    /**
     * Set field Car for human being
     * @param car
     * @see Car
     */
    public void setCar(Car car){
        this.car = car;
    }

    /**
     * @return information about human being
     */
    @Override
    public String toString(){
        return "-id: "+ id+"\n"+"   -name: "+this.name+"\n"+"   -coordinates: "+this.coordinates+
                "\n"+"   -Creation date: "+this.creationDate+"\n"
                +"   -realHero: "+this.realHero+"\n"+"   -hasToothpick: "+this.hasToothpick+"\n"+
                "   -impact speed: "+this.impactSpeed+"\n"+"   -soundtrack name: "+this.soundtrackName+"\n"+
                "   -weapon type: "+this.weaponType+"\n"+"   -mood: "+this.mood+"\n"+"   -car: "+this.car+"\n";
    }
    @Override
    public boolean equals(Object obj){
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HumanBeing other = (HumanBeing) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.creationDate, other.creationDate)) {
            return false;
        }
        if (!Objects.equals(this.getCoordinates().getX(), other.getCoordinates().getX())) {
            return false;
        }
        if (!Objects.equals(this.getCoordinates().getY(), other.getCoordinates().getY())) {
            return false;
        }
        if (!Objects.equals(this.realHero, other.realHero)) {
            return false;
        }
        if (!Objects.equals(this.hasToothpick, other.hasToothpick)) {
            return false;
        }
        if (!Objects.equals(this.impactSpeed, other.impactSpeed)) {
            return false;
        }
        if (!Objects.equals(this.soundtrackName, other.soundtrackName)) {
            return false;
        }
        if (!Objects.equals(this.weaponType.toString(), other.weaponType.toString())) {
            return false;
        }
        if (!Objects.equals(this.mood.toString(), other.mood.toString())) {
            return false;
        }
        if (!Objects.equals(this.car.getCool(), other.car.getCool())) {
            return false;
        }

        return true;
    }
    /**
     * Used to compare human beings
     * @param humanBeing the object to be compared.
     */
    @Override
    public int compareTo(HumanBeing humanBeing){
        if (humanBeing.getId() > this.getId()){
            return 1;
        }
        else if (humanBeing.getId() < this.getId()){
            return -1;
        }
        else
        {
            return 0;
        }
    }


}
