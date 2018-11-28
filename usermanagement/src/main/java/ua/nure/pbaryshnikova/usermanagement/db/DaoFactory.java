package ua.nure.pbaryshnikova.db;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Polina on 27-Nov-18.
 */
public abstract class DaoFactory {

    protected static final String USER_DAO = "dao.ua.nure.pbaryshnikova.java.1.db.UserDao";
    private static final String DAO_FACTORY = "dao.factory";
    protected static Properties properties;

    static {
        properties = new Properties();
        try{
            properties.load(DaoFactory.class.getClassLoader().getResourceAsStream("setting.properties"));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public static synchronized DaoFactory getInstance() {
        if(instance == null) {
            try {
                Class factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
                instance = (DaoFactory) factoryClass.newInstance();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    private static DaoFactory instance;

    protected DaoFactory(){

    }

    public static void init(Properties pro){
        properties = pro;
        instance = null;
    }

    protected ConnectionFactory getConnectionFactory(){
        return new ConnectionFactoryImpl(properties);
    }

    public abstract UserDao getUserDao();
}
