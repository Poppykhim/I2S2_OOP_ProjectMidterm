package ui.MenuGroup;

import java.awt.*;
import javax.swing.*;

public class DrinkCustomizationDialog extends JDialog {

    private final JRadioButton sizeSmall, sizeRegular, sizeLarge;
    private final JRadioButton sugarNone, sugar25, sugar50, sugar75, sugarNormal;
    private final JRadioButton icedLess, icedNormal, icedExtra;
    private boolean confirmed = false;

    public DrinkCustomizationDialog(JFrame parent) {
        super(parent, "Customize your drink", true);
        setLayout(new BorderLayout(5, 5));

        // Size Panel
        sizeSmall = new JRadioButton("Small");
        sizeRegular = new JRadioButton("Regular");
        sizeLarge = new JRadioButton("Large");
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(sizeSmall);
        sizeGroup.add(sizeRegular);
        sizeGroup.add(sizeLarge);
        sizeRegular.setSelected(true);  // default

        // Sugar Panel
        sugarNone = new JRadioButton("No Sugar");
        sugar25 = new JRadioButton("25% Sugar");
        sugar50 = new JRadioButton("50% Sugar");
        sugar75 = new JRadioButton("75% Sugar");
        sugarNormal = new JRadioButton("Normal Sugar");
        ButtonGroup sugarGroup = new ButtonGroup();
        sugarGroup.add(sugarNone);
        sugarGroup.add(sugar25);
        sugarGroup.add(sugar50);
        sugarGroup.add(sugar75);
        sugarGroup.add(sugarNormal);
        sugarNormal.setSelected(true);  // default

        // Ice Panel
        icedLess = new JRadioButton("Less Ice");
        icedNormal = new JRadioButton("Normal Ice");
        icedExtra = new JRadioButton("Extra Ice");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(icedLess);
        iceGroup.add(icedNormal);
        iceGroup.add(icedExtra);
        icedNormal.setSelected(true); // default

        // Size Panel (vertical)
        JPanel sizePanel = new JPanel();
        sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.Y_AXIS));
        sizePanel.setBorder(BorderFactory.createTitledBorder("Size: "));
        sizePanel.add(sizeSmall);
        sizePanel.add(sizeRegular);
        sizePanel.add(sizeLarge);

        // Ice Panel (horizontal)
        JPanel icePanel = new JPanel();
        icePanel.setLayout(new BoxLayout(icePanel, BoxLayout.Y_AXIS));
        icePanel.setBorder(BorderFactory.createTitledBorder("Ice Level: "));
        icePanel.add(icedLess);
        icePanel.add(icedNormal);
        icePanel.add(icedExtra);

        // Sugar Panel (horizontal)
        JPanel sugarPanel = new JPanel(new GridLayout(3, 2, 5, 5)); // 3 rows, 2 columns, 5px gaps
        sugarPanel.setBorder(BorderFactory.createTitledBorder("Sugar Level: "));
        sugarPanel.add(sugarNone);
        sugarPanel.add(sugar25);
        sugarPanel.add(sugar50);
        sugarPanel.add(sugar75);
        sugarPanel.add(sugarNormal);
        // Optional: Add a placeholder for last cell so layout stays neat
        sugarPanel.add(Box.createGlue()); // or add an empty JLabel()

        // Combine Size and Ice horizontally
        JPanel sizeAndIcePanel = new JPanel();
        sizeAndIcePanel.setLayout(new BoxLayout(sizeAndIcePanel, BoxLayout.X_AXIS));
        sizeAndIcePanel.add(sizePanel);
        sizeAndIcePanel.add(Box.createRigidArea(new Dimension(20, 0))); // spacing between size and ice
        sizeAndIcePanel.add(icePanel);

        // Now combine Size+Ice and Sugar vertically
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(sizeAndIcePanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // spacing between Size+Ice and Sugar
        centerPanel.add(sugarPanel);

        add(centerPanel, BorderLayout.CENTER);

        // Confirm button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });
        JPanel ConfirmBtn = new JPanel();
        ConfirmBtn.add(confirmButton);
        add(ConfirmBtn, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getSelectedSize() {
        if (sizeSmall.isSelected()) {
            return "Small";
        }
        if (sizeRegular.isSelected()) {
            return "Regular";
        }
        if (sizeLarge.isSelected()) {
            return "Large";
        }
        return "Regular";  // fallback
    }

    public String getSelectedSugar() {
        if (sugarNone.isSelected()) {
            return "No Sugar";
        }
        if (sugar25.isSelected()) {
            return "25% Sugar";
        }
        if (sugar50.isSelected()) {
            return "50% Sugar";
        }
        if (sugar75.isSelected()) {
            return "75% Sugar";
        }
        if (sugarNormal.isSelected()) {
            return "Normal Sugar";
        }
        return "Normal Sugar"; // fallback
    }

    public String getSelectedIce() {
        if (icedLess.isSelected()) {
            return "Less Ice";
        }
        if (icedNormal.isSelected()) {
            return "Normal Ice";
        }
        if (icedExtra.isSelected()) {
            return "Extra Ice";
        }
        return "Normal Ice"; // fallback
    }
}
