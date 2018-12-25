package ua.nure.pbaryshnikova.usermanagement.web;

import ua.nure.pbaryshnikova.usermanagement.db.DaoFactory;
import ua.nure.pbaryshnikova.usermanagement.db.MockDaoFactory;
import ua.mockobjects.dynamic.Mock;
import ua.mockrunner.servlet.BasicServletTestCaseAdapter;

import java.util.Properties;

/**
 * Created by Polina on 20-Dec-18.
 */
public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

    private Mock mockUserDao;

    protected void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
        properties.setProperty("dao.factory", MockDaoFactory.class.getName());
        DaoFactory.init(properties);
        setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
    }

    protected void tearDown() throws Exception {
        getMockUserDao().verify();
        super.tearDown();
    }

    public Mock getMockUserDao() {
        return mockUserDao;
    }

    public void setMockUserDao(Mock mockUserDao) {
        this.mockUserDao = mockUserDao;
    }
}