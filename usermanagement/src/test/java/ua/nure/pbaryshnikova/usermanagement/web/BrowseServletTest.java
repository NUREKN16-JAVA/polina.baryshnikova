package ua.nure.pbaryshnikova.usermanagement.web;

import ua.nure.pbaryshnikova.usermanagement.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Polina on 20-Dec-18.
 */
public class BrowseServletTest extends MockServletTestCase {

    protected void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    public void testBrowse() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        List list = Collections.singletonList(user);
        getMockUserDao().expectAndReturn("findAll", list);
        doGet();
        Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull("Could not find list of users in session", collection);
        assertSame(list, collection);
    }

    public void testEdit() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        getMockUserDao().expectAndReturn("find", new Long(1000), user);
        addRequestParameter("editButton", "Edit");
        addRequestParameter("id", "1000");
        doPost();
        User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull("Could not find user in session", user);
        assertSame(user, userInSession);
    }

    public void testEditWithoutId() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        addRequestParameter("editButton", "Edit");
        doPost();
        assertNotNull("Could not find error message", getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }
}