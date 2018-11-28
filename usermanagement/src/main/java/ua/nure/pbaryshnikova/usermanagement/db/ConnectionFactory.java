package ua.nure.pbaryshnikova.db;

import java.sql.Connection;

/**
 * Created by Polina on 27-Nov-18.
 */
public interface ConnectionFactory {
    Connection createConnection() throws DatabaseException;
}
