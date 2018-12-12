package ua.nure.pbaryshnikova.gui;

import ua.nure.pbaryshnikova.db.DaoFactory;
import ua.nure.pbaryshnikova.db.UserDao;
import ua.nure.pbaryshnikova.util.Messages;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Created by Polina on 10-Dec-18.
 */
public class MainFrame extends JFrame{

    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 800;
    private UserDao dao;
    private JPanel contentPanel;
    private JPanel browsePanel;
    private AddPanel addPanel;

    public MainFrame() {
        super();
        dao = DaoFactory.getInstance().getUserDao();
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(Messages.getString("MainFrame.user_management"));
        this.setContentPane(getContentPanel());
    }

    private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }
        return contentPanel;
    }

    private JPanel getBrowsePanel() {
        if (browsePanel == null) {
            browsePanel = new BrowsePanel(this);
        }
        ((BrowsePanel) browsePanel).initTable();
        return browsePanel;
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    public void showAddPanel() {
        showPanel(getAddPanel());
    }

    public UserDao getDao() {
        return dao;
    }

    private void showPanel(JPanel panel) {
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
    }


    public AddPanel getAddPanel() {
        if (addPanel == null)
            addPanel = new AddPanel(this);
        return addPanel;
    }

    public void showBrowsPanel() {

    }
}
