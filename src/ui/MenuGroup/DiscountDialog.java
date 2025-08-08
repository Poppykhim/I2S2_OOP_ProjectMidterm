package ui.MenuGroup;

import java.awt.*;
import javax.swing.*;

public class DiscountDialog extends JDialog {

    private JTextField txtDiscount;
    private JRadioButton rbFixed, rbPercent;
    private JButton btnApply, btnCancel;

    private double discountValue;
    private boolean isPercent;
    private boolean applied = false;

    public DiscountDialog(Frame parent) {
        super(parent, "Apply Discount", true);
        initUI();
        setSize(300, 180);
        setLocationRelativeTo(parent);
    }

    private void initUI() {
        JLabel lbl = new JLabel("Enter Discount:");
        txtDiscount = new JTextField(10);

        rbFixed = new JRadioButton("Fixed ($)");
        rbPercent = new JRadioButton("Percent (%)");
        ButtonGroup group = new ButtonGroup();
        group.add(rbFixed);
        group.add(rbPercent);

        btnApply = new JButton("Apply");
        btnCancel = new JButton("Cancel");

        btnApply.addActionListener(e -> {
            try {
                discountValue = Double.parseDouble(txtDiscount.getText().trim());
                isPercent = rbPercent.isSelected();
                applied = true;
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid discount value.");
            }
        });

        btnCancel.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(lbl);
        panel.add(txtDiscount);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(rbFixed);
        radioPanel.add(rbPercent);
        panel.add(radioPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnApply);
        panel.add(buttonPanel);

        add(panel);
    }

    public boolean isApplied() {
        return applied;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public boolean isPercent() {
        return isPercent;
    }
}
