package ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import ui.MenuGroup.MenuLeft;
import ui.MenuGroup.MenuRight;
import ui.MenuGroup.MenuSection;
import ui.MenuGroup.SearchPanel;

public class Menu extends JPanel {

    private final MenuSection middlePanel;
    private final MenuRight rightPanel;

    ;

    public Menu(JPanel mainPanel, CardLayout layout, UI ui) {
        setLayout(new BorderLayout());

        // === Left Panel ===
        JPanel leftPanel = new MenuLeft(mainPanel, layout, ui);

        // === Right Panel ===
        rightPanel = new MenuRight();

        // === Center Panels ===
        middlePanel = new MenuSection(rightPanel); // Main content
        JPanel upPanel = new SearchPanel(rightPanel); // Search bar at top

        // === Wrapper Panel ===
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(upPanel, BorderLayout.NORTH); // Top search bar
        wrapper.add(middlePanel, BorderLayout.CENTER); // Center content

        // === Add to main layout ===
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(wrapper, BorderLayout.CENTER); // Add wrapped content to center
    }

    public MenuSection getMiddlePanel() {
        return middlePanel; // Allow access to the middle panel
    }

    public MenuRight getRightPanel() {
        return rightPanel;
    }
}
