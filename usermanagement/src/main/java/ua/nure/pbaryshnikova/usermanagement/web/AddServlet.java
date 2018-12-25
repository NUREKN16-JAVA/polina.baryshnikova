package ua.nure.pbaryshnikova.usrmanagement.web;

import ua.nure.pbaryshnikova.usrmanagement.User;
import ua.nure.pbaryshnikova.usrmanagement.db.DaoFactory;
import ua.nure.pbaryshnikova.usrmanagement.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Polina on 20-Dec-18.
 */
public class AddServlet  extends EditServlet {

    protected void processUser(User user) throws DatabaseException {
        DaoFactory.getInstance().getUserDao().create(user);
    }

    protected void showPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }
}
