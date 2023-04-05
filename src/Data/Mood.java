package Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Enum {@code Mood} lists all the possible moods
 * @author Roman Kobelev
 */
public enum Mood  implements Serializable {
    LONGING("Longing"),
    GLOOM("Gloom"),
    FRENZY("Frenzy");
    /**
     * Name of mood
     */
    private final String name;

    /**
     * Constructor of {@code Mood} with parameter
     * @param name name of mood
     */
    Mood(String name) {
        this.name = name;
    }

    /**
     * Used to print information about {@code Mood}
     */
    @Override
    public String toString() {
        return this.name;
    }
}
