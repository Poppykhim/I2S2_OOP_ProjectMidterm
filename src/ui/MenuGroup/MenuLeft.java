package ui.MenuGroup;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import ui.UI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author User
 */
public class MenuLeft extends javax.swing.JPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private UI ui;

    public MenuLeft(JPanel mainPanel, CardLayout cardLayout, UI ui) {
        initComponents();
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.ui = ui;
        setHoverEffects();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(140, 600));

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(242, 242, 242)); // light gray/white

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bars-solid (5).png"))); // NOI18N
        jLabel4.setText("   Menu");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ui.fadeSwitchTo("Menu");
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 189, 89));
        jLabel5.setText("BAI MAK");

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chart-simple-solid.png"))); // NOI18N
        jLabel6.setText("   DashBoard");
        jLabel6.setForeground(new java.awt.Color(242, 242, 242)); // light gray/white

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/box-solid.png"))); // NOI18N
        jLabel7.setText("   Product");
        jLabel7.setForeground(new java.awt.Color(242, 242, 242)); // light gray/white
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ui.fadeSwitchTo("Product");
            }
        });
        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/right-from-bracket-solid.png"))); // NOI18N
        jLabel8.setText("   Log out");

        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8.setForeground(java.awt.Color.RED);
                jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 0, 17)); // NOI18N
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8.setForeground(java.awt.Color.BLACK);
                jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ui.fadeSwitchTo("Login");
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/flag-regular.png"))); // NOI18N
        jLabel9.setText("   Report");
        jLabel9.setForeground(new java.awt.Color(242, 242, 242)); // light gray/white

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(29, 29, 29)
                                                                .addComponent(jLabel5))
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel6))
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel4))
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel7))
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel8)))
                                                .addGap(0, 21, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(73, 73, 73)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

    }// </editor-fold>                        

    private void setHoverEffect(JLabel label) {
        java.awt.Color defaultColor = java.awt.Color.BLACK; // Black by default
        java.awt.Color hoverColor = new java.awt.Color(255, 153, 51); // Orange on hover

        label.setForeground(defaultColor); // Ensure it's black initially

        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setForeground(hoverColor);
                label.setFont(new java.awt.Font("Segoe UI Historic", 0, 17)); // NOI18N
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setForeground(defaultColor);
                label.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
            }
        });
    }

    private void setHoverEffects() {
        setHoverEffect(jLabel4);
        setHoverEffect(jLabel6);
        setHoverEffect(jLabel7);
        setHoverEffect(jLabel9);
    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration                   
}
