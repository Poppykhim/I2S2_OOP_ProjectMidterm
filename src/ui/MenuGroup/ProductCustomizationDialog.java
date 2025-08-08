package ui.MenuGroup;

import java.awt.*;
import javax.swing.*;

public class ProductCustomizationDialog extends JDialog {

    private JComboBox<String> sugarCombo;
    private JComboBox<String> iceCombo;
    private boolean confirmed = false;

    public ProductCustomizationDialog(JFrame parent) {
        super(parent, "Customize Product", true);
        setLayout(new BorderLayout(10, 10));

        // Input Panel for options
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        inputPanel.add(new JLabel("Sugar Level:"));
        sugarCombo = new JComboBox<>(new String[]{"Normal", "No Sugar", "25%", "50%", "75%", "120%"});
        inputPanel.add(sugarCombo);

        inputPanel.add(new JLabel("Ice Level:"));
        iceCombo = new JComboBox<>(new String[]{"Normal Ice", "No Ice", "Less Ice", "Extra Ice"});
        inputPanel.add(iceCombo);

        add(inputPanel, BorderLayout.CENTER);

        // Button Panel (centered)
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.add(confirmButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 180);
        setLocationRelativeTo(parent);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getSugarLevel() {
        return (String) sugarCombo.getSelectedItem();
    }

    public String getIceLevel() {
        return (String) iceCombo.getSelectedItem();
    }
}
