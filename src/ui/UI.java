package ui;

import Netbean.MenuSection;
import java.awt.*;
import javax.swing.*;
import ui.ProductGroup.AddProduct;
import ui.ProductGroup.ProductRight;

public class UI {

    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Product product;
    private ProductRight productRight;
    private FadeTransitionPane fadePane;
    private MenuSection menuSection; // Add this field
    private Menu menu; // Add this field

    public void createUI() {
        frame = new JFrame("Stock Management System");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Intro introPanel = new Intro();
        // introPanel.setOnLoadingFinished(() -> fadeSwitchTo("SignUp")); // 🔁 Switch after loading
        // mainPanel.add(introPanel, "Intro");
        mainPanel.add(new Signup(mainPanel, cardLayout, this), "SignUp");
        mainPanel.add(new Login(mainPanel, cardLayout, this), "Login");

        menu = new Menu(mainPanel, cardLayout, this);
        mainPanel.add(menu, "Menu");

        product = new Product(mainPanel, cardLayout, this);
        mainPanel.add(product, "Product");

        mainPanel.add(new AddProduct(mainPanel, cardLayout, this), "AddProduct");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.add(mainPanel);

        fadePane = new FadeTransitionPane(mainPanel);
        fadePane.setVisible(false);
        frame.setGlassPane(fadePane);

        frame.setVisible(true);

    }

    public Product getProduct() {
        return product;
    }

    public ProductRight getProductRight() {
        return productRight;
    }

    public void fadeSwitchTo(String cardName) {
        fadePane.startFade(cardName);
    }

    public MenuSection getMenuSection() {
        return menuSection;
    }

    public Menu getMenu() {
        return menu;
    }

    private static class FadeTransitionPane extends JComponent {

        private static final int TIMER_DELAY = 15; // ms between frames (~25fps)
        private static final float FADE_STEP = 0.1f; // fade alpha increment

        private float alpha = 0f; // current opacity (0.0 - 1.0)
        private boolean fadingOut = true; // track if fading out or in
        private String nextCard = null; // card to switch to after fade out
        private final JPanel parentPanel;
        private final Timer timer;

        public FadeTransitionPane(JPanel parentPanel) {
            this.parentPanel = parentPanel;
            setOpaque(false);

            timer = new Timer(TIMER_DELAY, e -> onTimerTick());
        }

        public void startFade(String cardName) {
            if (timer.isRunning()) {
                return; // prevent concurrent fades

            }
            this.nextCard = cardName;
            this.alpha = 0f;
            this.fadingOut = true;
            setVisible(true);
            timer.start();
        }

        private void onTimerTick() {
            if (fadingOut) {
                alpha += FADE_STEP;
                if (alpha >= 0.5f) {
                    alpha = 0.5f;
                    // Switch card when fully faded out
                    CardLayout cl = (CardLayout) parentPanel.getLayout();
                    cl.show(parentPanel, nextCard);
                    fadingOut = false; // now fade in
                }
            } else {
                alpha -= FADE_STEP;
                if (alpha <= 0f) {
                    alpha = 0f;
                    timer.stop();
                    setVisible(false);
                }
            }
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (!isVisible()) {
                return;
            }

            Graphics2D g2d = (Graphics2D) g.create();
            // Paint black overlay with current alpha
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }
    }

}
