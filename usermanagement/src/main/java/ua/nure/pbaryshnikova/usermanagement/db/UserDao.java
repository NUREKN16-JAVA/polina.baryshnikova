package ua.nure.pbaryshnikova.db;

import ua.nure.pbaryshnikova.User;

import java.util.Collection;

public interface UserDao {

    User create(User user) throws DatabaseException;

    void update(User user) throws DatabaseException;

    void delete(User user) throws DatabaseException;

    User find(Long id) throws DatabaseException;

    Collection findAll() throws DatabaseException;

    public void setConnectionFactory(ConnectionFactory connectionFactory);
}
