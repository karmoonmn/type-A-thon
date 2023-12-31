package assignment;

public class TimedMode extends javax.swing.JFrame {

    public TimedMode() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton15s1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        isPunctuationCheckBox = new javax.swing.JCheckBox();
        jbutton30s = new javax.swing.JButton();
        Jbutton15s = new javax.swing.JButton();
        jbutton45s = new javax.swing.JButton();
        jbutton60s = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        ExitButton = new javax.swing.JButton();

        jButton15s1.setBackground(new java.awt.Color(0, 153, 153));
        jButton15s1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton15s1.setForeground(new java.awt.Color(255, 255, 255));
        jButton15s1.setText("15 s");

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 700));

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 700));

        jLabel1.setBackground(new java.awt.Color(204, 255, 204));
        jLabel1.setFont(new java.awt.Font("Gill Sans Ultra Bold Condensed", 0, 70)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(" TIMED MODE");

        isPunctuationCheckBox.setBackground(new java.awt.Color(0, 51, 51));
        isPunctuationCheckBox.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        isPunctuationCheckBox.setForeground(new java.awt.Color(255, 255, 204));
        isPunctuationCheckBox.setText(" Punctuation Mode");
        isPunctuationCheckBox.setBorderPainted(true);
        isPunctuationCheckBox.setBorderPaintedFlat(true);
        isPunctuationCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isPunctuationCheckBoxActionPerformed(evt);
            }
        });

        jbutton30s.setBackground(new java.awt.Color(153, 255, 204));
        jbutton30s.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jbutton30s.setForeground(new java.awt.Color(51, 51, 51));
        jbutton30s.setText("30 s");
        jbutton30s.setPreferredSize(new java.awt.Dimension(70, 30));
        jbutton30s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutton30sActionPerformed(evt);
            }
        });

        Jbutton15s.setBackground(new java.awt.Color(153, 255, 204));
        Jbutton15s.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        Jbutton15s.setForeground(new java.awt.Color(51, 51, 51));
        Jbutton15s.setText("15 s");
        Jbutton15s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbutton15sActionPerformed(evt);
            }
        });

        jbutton45s.setBackground(new java.awt.Color(153, 255, 204));
        jbutton45s.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jbutton45s.setForeground(new java.awt.Color(51, 51, 51));
        jbutton45s.setText("45 s");
        jbutton45s.setPreferredSize(new java.awt.Dimension(70, 30));
        jbutton45s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutton45sActionPerformed(evt);
            }
        });

        jbutton60s.setBackground(new java.awt.Color(153, 255, 204));
        jbutton60s.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jbutton60s.setForeground(new java.awt.Color(51, 51, 51));
        jbutton60s.setText("60 s");
        jbutton60s.setPreferredSize(new java.awt.Dimension(70, 30));
        jbutton60s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutton60sActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Select time here :");

        ExitButton.setBackground(new java.awt.Color(204, 204, 204));
        ExitButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ExitButton.setForeground(new java.awt.Color(255, 51, 51));
        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ExitButton)
                .addGap(52, 52, 52))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(269, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbutton60s, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbutton45s, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbutton30s, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Jbutton15s, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isPunctuationCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(249, 249, 249))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(isPunctuationCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Jbutton15s, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jbutton30s, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jbutton45s, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jbutton60s, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void isPunctuationCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isPunctuationCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isPunctuationCheckBoxActionPerformed

    private void Jbutton15sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbutton15sActionPerformed
        // TODO add your handling code here:
        boolean isPunctuationEnabled = isPunctuationCheckBox.isSelected();
        if (isPunctuationEnabled) {
            new MainGame(15, "C:\\Users\\joans\\OneDrive\\Documents\\WIX1002\\Assignment\\dictionary2.txt", (int)(15*3.4)).setVisible(true);
            this.dispose();
        }
        else {
            new MainGame(15, "C:\\Users\\joans\\OneDrive\\Documents\\WIX1002\\Assignment\\dictionary.txt", (int)(15*3.4)).setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_Jbutton15sActionPerformed

    private void jbutton60sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutton60sActionPerformed
        // TODO add your handling code here:
        boolean isPunctuationEnabled = isPunctuationCheckBox.isSelected();
        if (isPunctuationEnabled) {
            new MainGame(60, "C:\\Users\\joans\\OneDrive\\Documents\\WIX1002\\Assignment\\dictionary2.txt", (int)(60*3.4)).setVisible(true);
            this.dispose();
        }
        else {
            new MainGame(60, "C:\\Users\\joans\\OneDrive\\Documents\\WIX1002\\Assignment\\dictionary.txt", (int)(60*3.4)).setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_jbutton60sActionPerformed

    private void jbutton45sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutton45sActionPerformed
        // TODO add your handling code here:
        boolean isPunctuationEnabled = isPunctuationCheckBox.isSelected();
        if (isPunctuationEnabled) {
            new MainGame(45, "C:\\Users\\joans\\OneDrive\\Documents\\WIX1002\\Assignment\\dictionary2.txt", (int)(45*3.4)).setVisible(true);
            this.dispose();
        }
        else {
            new MainGame(45, "C:\\Users\\joans\\OneDrive\\Documents\\WIX1002\\Assignment\\dictionary.txt", (int)(45*3.4)).setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_jbutton45sActionPerformed

    private void jbutton30sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutton30sActionPerformed
        // TODO add your handling code here:
        boolean isPunctuationEnabled = isPunctuationCheckBox.isSelected();
        if (isPunctuationEnabled) {
            new MainGame(30, "C:\\Users\\joans\\OneDrive\\Documents\\WIX1002\\Assignment\\dictionary2.txt", (int)(30*3.4)).setVisible(true);
            this.dispose();
        }
        else {
            new MainGame(30, "C:\\Users\\joans\\OneDrive\\Documents\\WIX1002\\Assignment\\dictionary.txt", (int)(30*3.4)).setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_jbutton30sActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        // TODO add your handling code here:
        Menu2 MenuFrame = new Menu2();
        MenuFrame.setVisible(true);
        MenuFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_ExitButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TimedMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimedMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimedMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimedMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimedMode().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton Jbutton15s;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox isPunctuationCheckBox;
    private javax.swing.JButton jButton15s1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbutton30s;
    private javax.swing.JButton jbutton45s;
    private javax.swing.JButton jbutton60s;
    // End of variables declaration//GEN-END:variables
}
