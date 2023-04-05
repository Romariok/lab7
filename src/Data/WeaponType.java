package Data;

import java.io.Serializable;

/**
 * Enum {@code WeaponType} lists all the possible weapon types
 * @author Roman Kobelev
 */
public enum WeaponType implements Serializable {
    SHOTGUN("Shotgun"),
    RIFLE("Rifle"),
    KNIFE("Knife"),
    MACHINE_GUN("Machine gun");
    /**
     * Weapon type
     */
    private final String name;

    /**
     * Constructor of {@code WeaponType} with parameter
     * @param name weapon type
     */
    WeaponType(String name) {
        this.name = name;
    }

    /**
     * Used to print information about {@code WeaponType}
     */
    @Override
    public String toString() {
        return this.name;
    }
}
