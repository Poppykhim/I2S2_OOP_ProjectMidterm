package ui.MenuGroup;

import java.awt.*;
import javax.swing.*;

public class FoodCustomizationDialog extends JDialog {

    private final JTextArea noteField;
    private boolean confirmed = false;

    public FoodCustomizationDialog(JFrame parent) {
        super(parent, "Enter Special Instructions", true);
        noteField = new JTextArea(3, 15);
        noteField.setLineWrap(false);
        noteField.setWrapStyleWord(false);
        noteField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        noteField.setMargin(new Insets(5, 5, 5, 5));
        noteField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setFocusable(false);
        confirmBtn.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setMnemonic('C');
        cancelBtn.setFocusable(false);
        cancelBtn.addActionListener(e -> dispose());

        JPanel inputPanel = new JPanel(new BorderLayout(5, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        inputPanel.add(new JLabel("Remark: "), BorderLayout.NORTH);
        inputPanel.add(noteField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(confirmBtn);
        buttonPanel.add(cancelBtn);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    public String getNote() {
        return noteField.getText().trim();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
