package ui;

import DAO.UserDAO;
import Storage.User;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Login extends JPanel {

    private final UI parentUI;

    public Login(JPanel maiPanel, CardLayout cardLayout, UI ui) {
        initComponents();
        parentUI = ui;
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        RightPanel = new JPanel();
        SignUpNameLabel = new JLabel();
        LoginBtn = new RoundedButton("Login", 40);
        AlreadyHaveAnAccount = new JLabel();
        LoginName = new JTextField();
        LoginPass = new JPasswordField();
        LoginLabel = new JLabel();
        SignUpPassLabel = new JLabel();
        CreateAnAccount = new JLabel();
        BaiMak = new JLabel();
        Stock = new JLabel();
        Stock3 = new JLabel();
        ForgotPassword = new JLabel();
        RememberMe = new JLabel();
        LogoAtSignUp = new JLabel();
        Stock1 = new JLabel();
        Stock2 = new JLabel();
        Stock4 = new JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 189, 89));
        jPanel1.setLayout(null);

        RightPanel.setBackground(new java.awt.Color(255, 255, 255));
        RightPanel.setToolTipText("");
        RightPanel.setPreferredSize(new java.awt.Dimension(1200, 630));

        SignUpNameLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        SignUpNameLabel.setForeground(new java.awt.Color(51, 0, 51));
        SignUpNameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-name.gif"))); // NOI18N

        LoginBtn.setBackground(new java.awt.Color(255, 189, 89));
        LoginBtn.setFont(new java.awt.Font("Segoe UI Historic", 0, 16)); // NOI18N
        LoginBtn.setForeground(new java.awt.Color(8, 53, 46));
        LoginBtn.setText("Login");

        LoginBtn.addActionListener(evt -> LoginBtnActionPerformed(evt));

        AlreadyHaveAnAccount.setFont(new java.awt.Font("Segoe UI Historic", 0, 16)); // NOI18N
        AlreadyHaveAnAccount.setForeground(new java.awt.Color(8, 53, 46));
        AlreadyHaveAnAccount.setText("Don't have an account yet?");

        LoginName.setText("  Username");
        LoginPass.setForeground(Color.GRAY);
        Color defaultBorderColor = new Color(8, 53, 46);
        Color normalBorderColor = new Color(255, 189, 89);         // Default border
        Color validBorderColor = new Color(34, 139, 34);
        LoginName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, defaultBorderColor));
        LoginName.setForeground(Color.GRAY);
        LoginName.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (LoginName.getText().equals("  Username")) {
                    LoginName.setText("");
                    LoginName.setForeground(Color.BLACK);
                    LoginName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, normalBorderColor));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (LoginName.getText().isEmpty()) {
                    LoginName.setText("  Username");
                    LoginName.setForeground(Color.GRAY);
                    LoginName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, defaultBorderColor));
                }
            }
        });
        LoginName.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateBorder();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateBorder();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateBorder();
            }

            public void updateBorder() {
                String username = LoginName.getText().trim();
                if (username.length() >= 1) {
                    LoginName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, validBorderColor));
                } else {
                    LoginName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, normalBorderColor));
                }
            }
        });

        LoginName.addActionListener(evt -> LoginNameActionPerformed(evt));

        LoginPass.setText("  Password has at least 6 characters!!!");
        LoginPass.setForeground(Color.GRAY);
        LoginPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, defaultBorderColor));
        LoginPass.setForeground(Color.GRAY);
        LoginPass.setEchoChar((char) 0);
        LoginPass.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (String.valueOf(LoginPass.getPassword()).equals("  Password has at least 6 characters!!!")) {
                    LoginPass.setText("");
                    LoginPass.setForeground(Color.BLACK);
                    LoginPass.setEchoChar('*'); // Show masking character
                    LoginPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, normalBorderColor));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (String.valueOf(LoginPass.getPassword()).isEmpty()) {
                    LoginPass.setText("  Password has at least 6 characters!!!");
                    LoginPass.setForeground(Color.GRAY);
                    LoginPass.setEchoChar((char) 0); // Show text instead of mask
                    LoginPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, defaultBorderColor));
                }
            }
        });
        LoginPass.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateBorder();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateBorder();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateBorder();
            }

            public void updateBorder() {
                String password = LoginPass.getText().trim();
                if (password.length() >= 6) {
                    LoginPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, validBorderColor));
                } else {
                    LoginPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, normalBorderColor));
                }
            }
        });
        LoginPass.addActionListener(evt -> LoginPassActionPerformed(evt));

        LoginLabel.setBackground(new java.awt.Color(217, 236, 255));
        LoginLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        LoginLabel.setForeground(new java.awt.Color(8, 53, 46));
        LoginLabel.setText("Login with");

        SignUpPassLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        SignUpPassLabel.setForeground(new java.awt.Color(51, 0, 51));
        SignUpPassLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-password.gif"))); // NOI18N

        CreateAnAccount.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        CreateAnAccount.setForeground(new java.awt.Color(255, 189, 89));
        CreateAnAccount.setText("Create an account");
        CreateAnAccount.setFocusable(false);
        CreateAnAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        CreateAnAccount.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                parentUI.fadeSwitchTo("SignUp");
            }
        });

        BaiMak.setBackground(new java.awt.Color(217, 236, 255));
        BaiMak.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        BaiMak.setForeground(new java.awt.Color(255, 189, 89));
        BaiMak.setText("BaiMak");

        Stock.setFont(new java.awt.Font("Segoe UI Historic", 0, 15)); // NOI18N
        Stock.setForeground(new java.awt.Color(8, 53, 46));
        Stock.setText("Enter your username and password to access your dashboard.");

        Stock3.setFont(new java.awt.Font("Segoe UI Historic", 0, 15)); // NOI18N
        Stock3.setForeground(new java.awt.Color(8, 53, 46));
        Stock3.setText("Only authorized personnel are allowed.");

        ForgotPassword.setFont(new java.awt.Font("Segoe UI Historic", 0, 16)); // NOI18N
        ForgotPassword.setForeground(new java.awt.Color(8, 53, 46));
        ForgotPassword.setText("Forgot your password?");

        RememberMe.setFont(new java.awt.Font("Segoe UI Historic", 0, 16)); // NOI18N
        RememberMe.setForeground(new java.awt.Color(8, 53, 46));
        RememberMe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-tick-box.gif"))); // NOI18N
        RememberMe.setText("Remember me");

        javax.swing.GroupLayout RightPanelLayout = new javax.swing.GroupLayout(RightPanel);
        RightPanel.setLayout(RightPanelLayout);
        RightPanelLayout.setHorizontalGroup(
                RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RightPanelLayout.createSequentialGroup()
                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(RightPanelLayout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(LoginLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(BaiMak))
                                        .addGroup(RightPanelLayout.createSequentialGroup()
                                                .addGap(75, 75, 75)
                                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(Stock, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(Stock3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, RightPanelLayout.createSequentialGroup()
                                                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, RightPanelLayout.createSequentialGroup()
                                                                                .addComponent(RememberMe)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                                                                .addComponent(ForgotPassword))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, RightPanelLayout.createSequentialGroup()
                                                                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(SignUpNameLabel)
                                                                                        .addComponent(SignUpPassLabel))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(LoginName, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                                                                                        .addComponent(LoginPass))))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(RightPanelLayout.createSequentialGroup()
                                                .addGap(185, 185, 185)
                                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(AlreadyHaveAnAccount)
                                                        .addGroup(RightPanelLayout.createSequentialGroup()
                                                                .addGap(25, 25, 25)
                                                                .addComponent(CreateAnAccount))
                                                        .addComponent(LoginBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(59, Short.MAX_VALUE))
        );
        RightPanelLayout.setVerticalGroup(
                RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RightPanelLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(LoginLabel)
                                        .addComponent(BaiMak))
                                .addGap(26, 26, 26)
                                .addComponent(Stock)
                                .addGap(3, 3, 3)
                                .addComponent(Stock3)
                                .addGap(48, 48, 48)
                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(RightPanelLayout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(LoginName))
                                        .addComponent(SignUpNameLabel))
                                .addGap(18, 18, 18)
                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(SignUpPassLabel)
                                        .addComponent(LoginPass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(RememberMe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(AlreadyHaveAnAccount)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CreateAnAccount)
                                .addGap(55, 55, 55))
        );

        LoginBtn.setBackground(normalBorderColor);
        LoginBtn.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LoginBtn.setBackground(new Color(255, 170, 60)); // slightly darker
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LoginBtn.setBackground(new Color(255, 189, 89)); // original
            }
        });

        jPanel1.add(RightPanel);
        RightPanel.setBounds(0, 0, 550, 568);

        LogoAtSignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo.png"))); // NOI18N
        jPanel1.add(LogoAtSignUp);
        LogoAtSignUp.setBounds(550, 120, 380, 220);

        Stock1.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        Stock1.setForeground(new java.awt.Color(8, 53, 46));
        Stock1.setText("Restaurant Management System");
        jPanel1.add(Stock1);
        Stock1.setBounds(600, 430, 370, 32);

        Stock2.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        Stock2.setForeground(new java.awt.Color(8, 53, 46));
        Stock2.setText("Welcome To Our");
        jPanel1.add(Stock2);
        Stock2.setBounds(720, 390, 140, 25);

        Stock4.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        Stock4.setText("Just a couple of click, you can start");
        jPanel1.add(Stock4);
        Stock4.setBounds(640, 480, 290, 25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }

    private void LoginNameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void LoginPassFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void LoginPassActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        handleLogin();
    }

    private void handleLogin() {

        String name = LoginName.getText();
        String pw = new String(LoginPass.getPassword()); // assuming JPasswordField
        if (name.isEmpty() || pw.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both name and password.");
            return;
        }

        User user = new User(name, pw);
        boolean success = UserDAO.Login(user);

        if (success) {
            JOptionPane.showMessageDialog(null, "Login successful!");
            parentUI.fadeSwitchTo("Menu"); // Assuming you have a method to switch to the dashboard
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Invalid credentials.");
        }
    }

    public class RoundedButton extends JButton {

        private final int radius;

        public RoundedButton(String text, int radius) {
            super(text);
            this.radius = radius;
            setOpaque(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false); // prevent default background fill
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Background
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            super.paintComponent(g);
        }

        @Override
        public void setBackground(Color bg) {
            super.setBackground(bg);
            repaint();
        }
    }

    // Variables declaration - do not modify                     
    private JLabel AlreadyHaveAnAccount;
    private JLabel BaiMak;
    private JLabel CreateAnAccount;
    private JLabel ForgotPassword;
    private JButton LoginBtn;
    private JLabel LoginLabel;
    private JTextField LoginName;
    private JPasswordField LoginPass;
    private JLabel LogoAtSignUp;
    private JLabel RememberMe;
    private JPanel RightPanel;
    private JLabel SignUpNameLabel;
    private JLabel SignUpPassLabel;
    private JLabel Stock;
    private JLabel Stock1;
    private JLabel Stock2;
    private JLabel Stock3;
    private JLabel Stock4;
    private JPanel jPanel1;
    // End of variables declaration                   
}
