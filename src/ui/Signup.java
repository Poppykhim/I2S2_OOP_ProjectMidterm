package ui;

import DAO.UserDAO;
import Storage.User;
import validator.Validator;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Signup extends JPanel {

    // private JPanel mainPanel;
    // private CardLayout cardLayout;
    private final UI parentUI;

    public Signup(JPanel mainPanel, CardLayout cardLayout, UI ui) {
        initComponents();
        // this.mainPanel = mainPanel;
        // this.cardLayout = cardLayout;
        this.parentUI = ui;
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        RightPanel = new JPanel();
        SignUpNameLabel = new JLabel();
        SignUpBtn = new RoundedButton("Sign Up", 40);
        AlreadyHaveAnAccount = new JLabel();
        SignUpName = new JTextField();
        SignUpPass = new JPasswordField();
        SignUpLabel = new JLabel();
        SignUpPassLabel = new JLabel();
        ConfirmPassLabel = new JLabel();
        SignUpEmailLabel = new JLabel();
        SignUpEmail = new JTextField();
        SignUpConfirmPass = new JPasswordField();
        LoginBtnAtSignUp = new JLabel();
        BaiMak = new JLabel();
        Stock = new JLabel();
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

        SignUpBtn.setBackground(new java.awt.Color(255, 189, 89));
        SignUpBtn.setFont(new java.awt.Font("Segoe UI Historic", 0, 16)); // NOI18N
        SignUpBtn.setForeground(new java.awt.Color(8, 53, 46));
        SignUpBtn.setText("Sign up");

        SignUpBtn.addActionListener(evt -> SignUpBtnActionPerformed(evt));

        AlreadyHaveAnAccount.setFont(new java.awt.Font("Segoe UI Historic", 0, 16)); // NOI18N
        AlreadyHaveAnAccount.setForeground(new java.awt.Color(8, 53, 46));
        AlreadyHaveAnAccount.setText("Already have an account?");

        SignUpName.setText("  Enter your name here!!!");
        SignUpName.setForeground(Color.GRAY);
        Color defaultBorderColor = new Color(8, 53, 46); // dark green
        Color normalBorderColor = new Color(255, 189, 89);   // Orange
        Color validBorderColor = new Color(34, 139, 34);     // light Green
        SignUpName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, defaultBorderColor));

        SignUpName.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (SignUpName.getText().equals("  Enter your name here!!!")) {
                    SignUpName.setText("");
                    SignUpName.setForeground(Color.BLACK);
                    SignUpName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, normalBorderColor));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (SignUpName.getText().isEmpty()) {
                    SignUpName.setText("  Enter your name here!!!");
                    SignUpName.setForeground(Color.GRAY);
                    SignUpName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, defaultBorderColor));
                }
            }
        });

        SignUpName.addActionListener(evt -> SignUpNameActionPerformed(evt)); // to perform action on Sign Up Name

        SignUpName.getDocument().addDocumentListener(new DocumentListener() {

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
                String username = SignUpName.getText().trim();
                if (username.length() >= 1) {
                    SignUpName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, validBorderColor));
                } else {
                    SignUpName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, normalBorderColor));
                }
            }
        });

        SignUpPass.setText("  Password has at least 6 characters!!!");
        SignUpPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, defaultBorderColor));
        SignUpPass.setForeground(Color.GRAY);
        SignUpPass.setEchoChar((char) 0);
        SignUpPass.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (String.valueOf(SignUpPass.getPassword()).equals("  Password has at least 6 characters!!!")) {
                    SignUpPass.setText("");
                    SignUpPass.setForeground(Color.BLACK);
                    SignUpPass.setEchoChar('*'); // Show masking character
                    SignUpPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, normalBorderColor));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (String.valueOf(SignUpPass.getPassword()).isEmpty()) {
                    SignUpPass.setText("  Password has at least 6 characters!!!");
                    SignUpPass.setForeground(Color.GRAY);
                    SignUpPass.setEchoChar((char) 0); // Show text instead of mask
                    SignUpPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, defaultBorderColor));
                }
            }
        });

        SignUpPass.getDocument().addDocumentListener(new DocumentListener() {

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
                String password = SignUpPass.getText().trim();
                if (password.length() >= 6) {
                    SignUpPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, validBorderColor));
                } else {
                    SignUpPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, normalBorderColor));
                }
            }
        });

        SignUpLabel.setBackground(new java.awt.Color(217, 236, 255));
        SignUpLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        SignUpLabel.setForeground(new java.awt.Color(8, 53, 46));
        SignUpLabel.setText("Sign up to");

        SignUpPassLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        SignUpPassLabel.setForeground(new java.awt.Color(51, 0, 51));
        SignUpPassLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-password.gif"))); // NOI18N

        ConfirmPassLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        ConfirmPassLabel.setForeground(new java.awt.Color(8, 53, 46));
        ConfirmPassLabel.setText("Confirm Password:");

        SignUpEmailLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        SignUpEmailLabel.setForeground(new java.awt.Color(51, 0, 51));
        SignUpEmailLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-email.gif"))); // NOI18N

        SignUpEmail.setText("  Example@gmail.com");
        SignUpEmail.setForeground(Color.GRAY);
        SignUpEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, defaultBorderColor));
        SignUpEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (SignUpEmail.getText().equals("  Example@gmail.com")) {
                    SignUpEmail.setText("");
                    SignUpEmail.setForeground(Color.BLACK);
                    SignUpEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, normalBorderColor));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (SignUpEmail.getText().isEmpty()) {
                    SignUpEmail.setText("  Example@gmail.com");
                    SignUpEmail.setForeground(Color.GRAY);
                    SignUpEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, defaultBorderColor));
                }
            }
        });

        SignUpConfirmPass.setText("  Confirm your password!!!");
        SignUpConfirmPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, defaultBorderColor));
        SignUpConfirmPass.setForeground(Color.GRAY);
        SignUpConfirmPass.setEchoChar((char) 0);
        SignUpConfirmPass.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (String.valueOf(SignUpConfirmPass.getPassword()).equals("  Confirm your password!!!")) {
                    SignUpConfirmPass.setText("");
                    SignUpConfirmPass.setForeground(Color.BLACK);
                    SignUpConfirmPass.setEchoChar('•'); // Show masking character
                    SignUpConfirmPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, normalBorderColor));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (String.valueOf(SignUpConfirmPass.getPassword()).isEmpty()) {
                    SignUpConfirmPass.setText("  Confirm your password!!!");
                    SignUpConfirmPass.setForeground(Color.GRAY);
                    SignUpConfirmPass.setEchoChar((char) 0); // Show text instead of mask
                    SignUpConfirmPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, defaultBorderColor));
                }
            }
        });

        SignUpConfirmPass.getDocument().addDocumentListener(new DocumentListener() {

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
                String ConfirmPass = SignUpConfirmPass.getText().trim();
                String Pass = SignUpPass.getText().trim();
                if (ConfirmPass.length() >= 6 && ConfirmPass.equals(Pass)) {
                    SignUpConfirmPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, validBorderColor));
                } else {
                    SignUpConfirmPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, normalBorderColor));
                }
            }
        });

        LoginBtnAtSignUp.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        LoginBtnAtSignUp.setForeground(new java.awt.Color(255, 189, 89));
        LoginBtnAtSignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-profile.gif"))); // NOI18N
        LoginBtnAtSignUp.setText("Login");
        LoginBtnAtSignUp.setFocusable(false);
        LoginBtnAtSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BaiMak.setBackground(new java.awt.Color(217, 236, 255));
        BaiMak.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        BaiMak.setForeground(new java.awt.Color(255, 189, 89));
        BaiMak.setText("BaiMak");
        BaiMak.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                parentUI.fadeSwitchTo("Menu");
            }
        });
        LoginBtnAtSignUp.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                parentUI.fadeSwitchTo("Login");
            }
        });

        Stock.setFont(new java.awt.Font("Segoe UI Historic", 0, 15)); // NOI18N
        Stock.setForeground(new java.awt.Color(8, 53, 46));
        Stock.setText(" We just need a little more information to set up your account !!!");

        javax.swing.GroupLayout RightPanelLayout = new javax.swing.GroupLayout(RightPanel);
        RightPanel.setLayout(RightPanelLayout);
        RightPanelLayout.setHorizontalGroup(
                RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RightPanelLayout.createSequentialGroup()
                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(RightPanelLayout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(SignUpLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(BaiMak))
                                        .addGroup(RightPanelLayout.createSequentialGroup()
                                                .addGap(75, 75, 75)
                                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(RightPanelLayout.createSequentialGroup()
                                                                .addGap(128, 128, 128)
                                                                .addComponent(SignUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(Stock, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(RightPanelLayout.createSequentialGroup()
                                                                        .addComponent(AlreadyHaveAnAccount)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(LoginBtnAtSignUp))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, RightPanelLayout.createSequentialGroup()
                                                                        .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(SignUpEmailLabel)
                                                                                .addComponent(SignUpNameLabel)
                                                                                .addComponent(SignUpPassLabel))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(ConfirmPassLabel)
                                                                                .addComponent(SignUpName)
                                                                                .addComponent(SignUpEmail)
                                                                                .addComponent(SignUpPass)
                                                                                .addComponent(SignUpConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addContainerGap(39, Short.MAX_VALUE))
        );
        RightPanelLayout.setVerticalGroup(
                RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RightPanelLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(SignUpLabel)
                                        .addComponent(BaiMak))
                                .addGap(26, 26, 26)
                                .addComponent(Stock)
                                .addGap(34, 34, 34)
                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(SignUpNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightPanelLayout.createSequentialGroup()
                                                .addComponent(SignUpName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(SignUpEmailLabel)
                                        .addGroup(RightPanelLayout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(SignUpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(SignUpPassLabel)
                                        .addComponent(SignUpPass, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ConfirmPassLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SignUpConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(SignUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(AlreadyHaveAnAccount)
                                        .addComponent(LoginBtnAtSignUp))
                                .addGap(49, 49, 49))
        );

        SignUpBtn.setBackground(normalBorderColor);
        SignUpBtn.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SignUpBtn.setBackground(new Color(255, 170, 60)); // slightly darker
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SignUpBtn.setBackground(new Color(255, 189, 89)); // original
            }
        });

        SignUpEmail.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateEmail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateEmail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateEmail();
            }

            private void validateEmail() {
                String email = SignUpEmail.getText().trim();
                String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
                if (email.matches(emailRegex)) {
                    SignUpEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, validBorderColor));
                } else {
                    SignUpEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, normalBorderColor));
                }
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

    private void SignUpNameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SignUpPassActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SignUpEmailActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SignUpConfirmPassFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void SignUpConfirmPassActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }

    private void SignUpBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        handleRegister();
    }

    private void handleRegister() {
        String username = SignUpName.getText().trim();
        String password = new String(SignUpPass.getPassword()).trim(); // assuming JPasswordField
        String email = SignUpEmail.getText().trim();
        if (UserDAO.usernameExists(username)) {
            JOptionPane.showMessageDialog(null, "Username already exists.");
            return;
        }
        String error = Validator.validateUser(username, password, email);
        if (error != null) {
            JOptionPane.showMessageDialog(null, error);
            return;
        }
        if (!SignUpConfirmPass.getText().trim().equals(password)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
            return;
        }
        // Create a user object
        User user = new User(username, password, email);

        // Try to register
        boolean success = UserDAO.Register(user);

        if (success) {
            JOptionPane.showMessageDialog(null, "Registration successful! Please log in.");
            parentUI.fadeSwitchTo("Login");
        } else {
            JOptionPane.showMessageDialog(null, "Registration failed. Please check your input.");
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
    private javax.swing.JLabel AlreadyHaveAnAccount;
    private javax.swing.JLabel BaiMak;
    private javax.swing.JLabel ConfirmPassLabel;
    private javax.swing.JLabel LoginBtnAtSignUp;
    private javax.swing.JLabel LogoAtSignUp;
    private javax.swing.JPanel RightPanel;
    private javax.swing.JButton SignUpBtn;
    private javax.swing.JPasswordField SignUpConfirmPass;
    private javax.swing.JTextField SignUpEmail;
    private javax.swing.JLabel SignUpEmailLabel;
    private javax.swing.JLabel SignUpLabel;
    private javax.swing.JTextField SignUpName;
    private javax.swing.JLabel SignUpNameLabel;
    private javax.swing.JPasswordField SignUpPass;
    private javax.swing.JLabel SignUpPassLabel;
    private javax.swing.JLabel Stock;
    private javax.swing.JLabel Stock1;
    private javax.swing.JLabel Stock2;
    private javax.swing.JLabel Stock4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration                   
}
