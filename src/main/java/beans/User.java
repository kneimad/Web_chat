package beans;

/**
 * Represents a user of the chat.
 *
 * @author Damien
 */
public class User {
    /**
     * Name of a user.
     */
    private final String name;
    /** Color of user's name and messages. */
    //private Color color;

    /**
     * Solo constructor of user.
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Gets user's name.
     *
     * @return name of a user.
     */
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

}
