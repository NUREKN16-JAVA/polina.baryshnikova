package ua.nure.pbaryshnikova.usermanagement.gui;

import ua.nure.pbaryshnikova.usermanagement.User;
import ua.nure.pbaryshnikova.usermanagement.db.DaoFactory;
import ua.nure.pbaryshnikova.usermanagement.db.MockDaoFactory;
import ua.nure.pbaryshnikova.usermanagement.util.Messages;
import ua.mockobjects.dynamic.Mock;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import org.junit.After;
import org.junit.Before;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Polina on 10-Dec-18.
 */
public class MainFrameTest extends JFCTestCase{

    private MainFrame mainFrame;
    private Mock mockUserDao;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        try {
            Properties properties = new Properties();
            properties.setProperty("dao.factory", MockDaoFactory.class.getName());
            DaoFactory.getInstance().init(properties);
            mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
            mockUserDao.expectAndReturn("findAll", new ArrayList());
            setHelper(new JFCTestHelper());
            mainFrame = new MainFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainFrame.setVisible(true);
    }

    @After
    protected void tearDown() throws Exception {
        try {
            mockUserDao.verify();
            mainFrame.setVisible(false);
            getHelper().cleanUp(this);
            super.tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Component find(Class componentClass, String name){
        NamedComponentFinder finder;
        finder = new NamedComponentFinder(componentClass, name);
        finder.setWait(0);
        Component component = finder.find(mainFrame, 0);
        assertNotNull("Component not found '" + name + "'", component);
        return component;
    }

    public void testBrowseControls() {
        find(JPanel.class, "browsePanel");
        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(3, table.getColumnCount());
        assertEquals(Messages.getString("UserTableModel.id"), table.getColumnName(0));
        assertEquals(Messages.getString("UserTableModel.first_name"), table.getColumnName(1));
        assertEquals(Messages.getString("UserTableModel.last_name"), table.getColumnName(2));
        find(JButton.class, "addButton");
        find(JButton.class, "editButton");
        find(JButton.class, "deleteButton");
        find(JButton.class, "detailsButton");
    }

    public void testAddUser() {
        String firstName = "Bob";
        String lastName = "Dylan";
        Date nowDate = new Date();
        User user = new User(firstName, lastName, nowDate);
        User expectedUser = new User(new Long(1), firstName, lastName, nowDate);
        mockUserDao.expectAndReturn("create", user, expectedUser);
        ArrayList users = new ArrayList();
        users.add(expectedUser);
        mockUserDao.expectAndReturn("findAll", users);
        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(0, table.getRowCount());
        JButton addButton = (JButton) find(JButton.class, "addButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
        find(JPanel.class, "addPanel");
        JTextField firstNameField = (JTextField) find(JTextField.class, "firstName");
        JTextField lastNameField = (JTextField) find(JTextField.class, "lastName");
        JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirth");
        getHelper().sendString(new StringEventData(this, firstNameField, firstName));
        getHelper().sendString(new StringEventData(this, lastNameField, lastName));
        DateFormat formatter = DateFormat.getDateInstance();
        String date = formatter.format(nowDate);
        getHelper().sendString(new StringEventData(this, dateOfBirthField, date));
        JButton okButton = (JButton) find(JButton.class, "okButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
        find(JButton.class, "cancelButton");
        find(JPanel.class, "browsePanel");
        table = (JTable) find(JTable.class, "userTable");
        assertEquals(1, table.getRowCount());
    }
}