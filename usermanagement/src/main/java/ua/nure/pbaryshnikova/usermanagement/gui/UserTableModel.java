package ua.nure.pbaryshnikova.usermanagement.gui;

import ua.nure.pbaryshnikova.usermanagement.User;
import ua.nure.pbaryshnikova.usermanagement.Messages;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Polina on 10-Dec-18.
 */
public class UserTableModel extends AbstractTableModel
{

    private static final String[] COLUMN_NAMES = { Messages.getString("UserTableModel.id"), Messages.getString("UserTableModel.first_name"), Messages.getString("UserTableModel.last_name") };
    private static final Class[] COLUMN_CLASSES = { Long.class, String.class, String.class };
    private List users = null;

    public UserTableModel(Collection users) {
        this.users = new ArrayList(users);
    }

    public int getRowCount() {
        return users.size();
    }

    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    public Class getColumnClass(int columnIndex) {
        return COLUMN_CLASSES[columnIndex];
    }

    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = (User) users.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getFirstname();
            case 2:
                return user.getLastNam();
        }
        return null;
    }

    public User getUser(int index) {
        return (User) users.get(index);
    }

    public void addUsers(Collection users) {
        this.users.addAll(users);

    }

    public void clearUsers() {
        this.users = new ArrayList();
    }

}
