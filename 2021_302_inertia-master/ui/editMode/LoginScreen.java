package ui.editMode;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.database.Database;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;

public class LoginScreen {
    private static LoginScreen instance;
    PlayModeObserver obs = PlayModeObserver.getInstance();
    Database db = Database.getInstance();

    private LoginScreen() {
    }

    public static LoginScreen getInstance() {
        if (instance == null)
            instance = new LoginScreen();
        return instance;
    }

    public void showLoadScreen(JFrame frame) {
        JTextField username = new JTextField();
        Object[] message = {
                "Username:", username,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (db.checkIfUserExist(username.getText())) {

                obs.notify(EventName.LOAD_GAME,
                        db.loadFromDatabase(username.getText()));
                obs.notify(EventName.PAUSE_GAME, null);
            } else {
                JOptionPane.showMessageDialog(frame, "No data linked with " + username.getText(), "Login Error!",
                        JOptionPane.ERROR_MESSAGE);
                showLoadScreen(frame);
            }
        } else {
            obs.notify(EventName.PAUSE_GAME, null);
        }
    }

    public void showSaveScreen(JFrame frame) {
        JTextField username = new JTextField();
        Object[] message = {
                "Username:", username,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {

            obs.notify(EventName.SAVE_GAME, username.getText());
            obs.notify(EventName.PAUSE_GAME, null);

            // JOptionPane.showMessageDialog(frame, username + " data saved to database!");

        } else {
            obs.notify(EventName.PAUSE_GAME, null);
        }
    }
}
