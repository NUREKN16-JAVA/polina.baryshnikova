package ua.nure.pbaryshnikova.db;

import ua.nure.pbaryshnikova.User;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Polina on 27-Nov-18.
 */
class HsqlUserDao implements UserDao {

    public static final String INSERT_QUERY = "Insert Into users(firstname, lastname, dateofbirth) Values (?, ?, ?)";
    public static final String SELECT_ALL_QUERY = "Select id, firstname, lastname, dateofbirth From users";
    private static final String UPDATE_QUERY = "Update users Set firstname=?, lastname=?, dateofbirth=? Where id=?";
    private static final String DELETE_QUERY = "Delete From users Where id=?";
    private static final String SELECT_BY_ID = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id=?";

    private ConnectionFactory connectionFactory;

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public HsqlUserDao(ConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;
    }
    public HsqlUserDao(){

    }

    @Override
    public User create(User user) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastNam());
            statement.setDate(3, new Date(user.getDateOfBirthd().getTime()));
            if(statement.executeUpdate() != 1)
                throw new DatabaseException("№ of inserted rows: " + statement.executeUpdate());
            CallableStatement callableStatement = connection.prepareCall("Call Identity()");
            ResultSet keys = callableStatement.executeQuery();
            if(keys.next())
                user.setId(new Long(keys.getLong(0)));
            System.out.print("\n\n\n\n\n\n" + user.getId() + "\n\n\n\n");
            keys.close();
            callableStatement.close();
            statement.close();
            connection.close();
            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(User user) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastNam());
            statement.setDate(3, new Date(user.getDateOfBirthd().getTime()));
            statement.setLong(4, user.getId().longValue());
            if (statement.executeUpdate() != 1)
                throw new DatabaseException("№ of updated rows: " + statement.executeUpdate());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(User user) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, user.getId().longValue());
            if (statement.executeUpdate() != 1)
                throw new DatabaseException("№ of deleted rows: " + statement.executeUpdate());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public User find(Long id) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id.longValue());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
                throw new DatabaseException("Couldn't find user id: " + id);
            User result = new User();
            result.setId(new Long(resultSet.getLong(1)));
            result.setFirstname(resultSet.getString(2));
            result.setLastNam(resultSet.getString(3));
            result.setDateOfBirthd(resultSet.getDate(4));
            return result;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Collection findAll() throws DatabaseException {
        Collection result = new LinkedList();
        try {
            Connection connection = connectionFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()){
                User user = new User();
                user.setId(new Long(resultSet.getLong(1)));
                user.setFirstname(resultSet.getString(2));
                user.setLastNam(resultSet.getString(3));
                user.setDateOfBirthd(resultSet.getDate(4));
                result.add(user);
            }
        } catch (SQLException e) {
           throw new DatabaseException(e);
        }
        return null;
    }
}
