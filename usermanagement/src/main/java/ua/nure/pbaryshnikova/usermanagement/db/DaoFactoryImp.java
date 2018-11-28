package ua.nure.pbaryshnikova.db;

/**
 * Created by Polina on 27-Nov-18.
 */
public class DaoFactoryImp extends DaoFactory {
    public UserDao getUserDao(){
        UserDao result = null;
        try {
            Class clasz = Class.forName(properties.getProperty(USER_DAO));
            result = (UserDao) clasz.newInstance();
            result.setConnectionFactory(getConnectionFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
