package ui.editMode;

import java.awt.event.*;
import javax.swing.*;

import domain.Constants;
import domain.editMode.EditModeInputManager;
import domain.editMode.ObstacleCounter;

public class EditModeTextFields implements ActionListener {
    JTextField simpleText;
    JTextField firmText;
    JTextField explosiveText;
    JTextField giftText;
    JLabel simpleLabel;
    JLabel firmLabel;
    JLabel explosiveLabel;
    JLabel giftLabel;
    JButton submitButton = new JButton("Add Obstacles");
    JFrame frame;
    EditModeWindow editModeWindow;
    int width = Constants.SCREEN_WIDTH;

    public EditModeTextFields(EditModeWindow editModeWindow, JFrame frame) {
        this.editModeWindow = editModeWindow;
        this.frame = frame;

        simpleLabel = new JLabel("Number of Simple Obstacles");
        simpleText = new JTextField("75", 3);
        simpleLabel.setBounds(width / 100, width / 600, width / 5, width / 50);
        simpleText.setBounds(17 * width / 200, width / 50, width / 20, width / 40);
        simpleLabel.setVisible(true);
        simpleText.setVisible(true);
        editModeWindow.add(simpleLabel);
        editModeWindow.add(simpleText);
        simpleText.addActionListener(this);

        firmLabel = new JLabel("Number of Firm Obstacles");
        firmText = new JTextField("10", 3);
        firmLabel.setBounds(width / 100 + 11 * width / 50, width / 600, width / 5, width / 50);
        firmText.setBounds(17 * width / 200 + 11 * width / 50, width / 50, width / 20, width / 40);
        firmLabel.setVisible(true);
        firmText.setVisible(true);
        editModeWindow.add(firmLabel);
        editModeWindow.add(firmText);
        firmText.addActionListener(this);

        explosiveLabel = new JLabel("Number of Explosive Obstacles");
        explosiveText = new JTextField("5", 3);
        explosiveLabel.setBounds(width / 100 + 22 * width / 50, width / 600, width / 5, width / 50);
        explosiveText.setBounds(17 * width / 200 + 22 * width / 50, width / 50, width / 20, width / 40);
        explosiveLabel.setVisible(true);
        explosiveText.setVisible(true);
        editModeWindow.add(explosiveLabel);
        editModeWindow.add(explosiveText);
        explosiveText.addActionListener(this);

        giftLabel = new JLabel("Number of Gift Obstacles");
        giftText = new JTextField("10", 3);
        giftLabel.setBounds(width / 100 + 33 * width / 50, width / 600, width / 5, width / 50);
        giftText.setBounds(17 * width / 200 + 33 * width / 50, width / 50, width / 20, width / 40);
        giftLabel.setVisible(true);
        giftText.setVisible(true);
        editModeWindow.add(giftLabel);
        editModeWindow.add(giftText);
        giftText.addActionListener(this);

        submitButton.setBounds(85 * width / 100, width / 50, width / 10, width / 50);
        submitButton.addActionListener(this);
        editModeWindow.add(submitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            int simpleNumber = Integer.parseInt(simpleText.getText());
            int firmNumber = Integer.parseInt(firmText.getText());
            int explosiveNumber = Integer.parseInt(explosiveText.getText());
            int giftNumber = Integer.parseInt(giftText.getText());

            if (!inputChecking(simpleNumber, firmNumber, explosiveNumber,giftNumber)) {
                JOptionPane.showMessageDialog(frame, "Wrong Inputs", "Obstacle Inputs",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                (new ObstacleCounter(Constants.L, simpleNumber, firmNumber, explosiveNumber, giftNumber)).placeRandom();
            }
            EditModeInputManager.getInstance().Able();
        }
    }

    public static boolean inputChecking(int simpleNumber, int firmNumber, int explosiveNumber, int giftNumber){

        // REQUIRES: simpleNumber, firmNumber, explosiveNumber, giftNumber
        // EFFECTS: This method takes the number of the obstacles and checks
        // whether the input is in line with the required numbers
        
        if(simpleNumber < 75 || firmNumber < 10 || explosiveNumber < 5 || giftNumber < 10){
            return false;
        }else {
            return true;
        }
    }

}
