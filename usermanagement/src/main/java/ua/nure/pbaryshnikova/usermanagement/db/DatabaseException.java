package ua.nure.pbaryshnikova.db;

/**
 * Created by Polina on 27-Nov-18.
 */
public class DatabaseException extends Exception {
    public DatabaseException(Throwable cause) {
        super(cause);
    }

    public DatabaseException() {
        super();
    }

    public DatabaseException(String message) {
        super(message);
    }
}
