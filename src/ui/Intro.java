package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Splash/Intro Panel with built-in loading progress logic.
 */
public class Intro extends JPanel {

    public JProgressBar Myprogress;
    public JLabel jLabel1;
    public JLabel jLabel2;
    private JPanel jPanel1;
    private Timer timer;
    private int progress = 0;
    private Runnable onLoadingFinished; // callback

    public Intro() {
        initComponents();
        startLoading(); // Automatically start loading when created
    }

     private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Myprogress = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        setPreferredSize(new java.awt.Dimension(1000, 600));

        jPanel1.setBackground(new java.awt.Color(255, 189, 89));

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 1, 48)); // NOI18N
        jLabel1.setText("Restaurant System Managemment");

        Myprogress.setBackground(new java.awt.Color(0, 51, 51));

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Myprogress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(132, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(100, 100, 100))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(464, 464, 464)
                                .addComponent(jLabel2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(224, 224, 224)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Myprogress, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void startLoading() {
        timer = new Timer(30, null); // create timer without action
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (progress <= 100) {
                    Myprogress.setValue(progress);
                    jLabel2.setText(progress + "%");

                    // Simulate real case loading behavior
                    if (progress < 40) {
                        progress += 1; // normal speed
                        timer.setDelay(30);
                    } else if (progress < 50) {
                        progress += 1; // slower
                        timer.setDelay(80); // slow at ~40%
                    } else if (progress < 75) {
                        progress += 1; // normal again
                        timer.setDelay(30);
                    } else if (progress < 85) {
                        progress += 1; // slow again
                        timer.setDelay(100); // slow at ~80%
                    } else {
                        progress += 2; // speed up near end
                        timer.setDelay(10);
                    }
                } else {
                    timer.stop();
                    if (onLoadingFinished != null) {
                        onLoadingFinished.run();
                    }
                }
            }
        });
        timer.start();
    }

    /**
     * Register a callback that runs when loading is complete.
     */
    public void setOnLoadingFinished(Runnable callback) {
        this.onLoadingFinished = callback;
    }
}
