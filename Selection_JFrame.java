/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.objectdetectdb;

import java.util.List;
//import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rahul
 */
public class Selection_JFrame extends javax.swing.JFrame {

    /**
     * Creates new form Selection_JFrame
     */
    public Selection_JFrame() {
        initComponents();
    }
    
    Image_JFrame im = new Image_JFrame();
    LocalVideo_JFrame lo = new LocalVideo_JFrame();
    LiveVideo_JFrame li = new LiveVideo_JFrame();
    //String path;
    
    String k;
    String path;
    
    // fetching Table_data from the database and inserting into Jtable
    public void Display_table(String Dtname,javax.swing.JTable Tname ){
    
            try {Class.forName("com.mysql.cj.jdbc.Driver");

                String DB_URL = "jdbc:mysql://127.0.0.1:3306/obdep";
                String DB_USER = "root";
                String DB_PASSWORD = "Password";

                try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    System.out.println("Connection established..." + con);

                    String query = "select * from "+Dtname;
                    System.out.println(Dtname);
                    try (PreparedStatement st = con.prepareStatement(query)){
                           
                            ResultSet rs = st.executeQuery();

                            System.out.println("Query executed");

                            while (rs.next()) {
                                String Session_id = rs.getString("Session_id");
                                String Name = rs.getString("Name");
                                String Time = rs.getString("Time");
                                String Length = rs.getString("Length");
                                String Total_objects = rs.getString("Total_objects");
                                
                                String[] row = {Session_id, Name, Time, Length, Total_objects};
                                DefaultTableModel model = (DefaultTableModel) Tname.getModel();
                                model.addRow(row);
                            }

                            System.out.println("Rows added");
                        }
                        con.close();
                }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Selection_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        PathBox = new javax.swing.JTextField();
        LaunchButt = new javax.swing.JButton();
        HisMod = new javax.swing.JComboBox<>();
        HisLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        DetModBox = new javax.swing.JComboBox<>();
        ShowData = new javax.swing.JButton();
        AttachButt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        PathBox.setText("Type Path here...");
        PathBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PathBoxActionPerformed(evt);
            }
        });

        LaunchButt.setBackground(new java.awt.Color(255, 255, 0));
        LaunchButt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LaunchButt.setText("Launch");
        LaunchButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaunchButtActionPerformed(evt);
            }
        });

        HisMod.setBackground(new java.awt.Color(102, 255, 255));
        HisMod.setForeground(new java.awt.Color(255, 255, 255));
        HisMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Image_data", "LocalVideo_data", "LiveVideo_data" }));
        HisMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HisModActionPerformed(evt);
            }
        });

        HisLabel.setBackground(new java.awt.Color(255, 255, 255));
        HisLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        HisLabel.setForeground(new java.awt.Color(255, 255, 255));
        HisLabel.setText("History");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Choose the File name of Selected mode ");

        jLabel3.setBackground(new java.awt.Color(0, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("WELCOME TO OBJECT DETECTION APPLICATION");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Choose the Detection Modes");

        DetModBox.setBackground(new java.awt.Color(102, 255, 255));
        DetModBox.setForeground(new java.awt.Color(255, 255, 255));
        DetModBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Image_Detection", "LocalVideo_Detection", "LiveVideo_Detection" }));
        DetModBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetModBoxActionPerformed(evt);
            }
        });

        ShowData.setBackground(new java.awt.Color(0, 51, 51));
        ShowData.setForeground(new java.awt.Color(255, 255, 255));
        ShowData.setText("Show Data");
        ShowData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowDataActionPerformed(evt);
            }
        });

        AttachButt.setBackground(new java.awt.Color(0, 51, 51));
        AttachButt.setForeground(new java.awt.Color(255, 255, 255));
        AttachButt.setText("Attach");
        AttachButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AttachButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(HisLabel)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(HisMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ShowData, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(DetModBox, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(127, 127, 127))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(PathBox, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(AttachButt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(148, 148, 148))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LaunchButt, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(304, 304, 304))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(219, 219, 219))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DetModBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PathBox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AttachButt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ShowData, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(LaunchButt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HisMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HisLabel))))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 703, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void LaunchButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaunchButtActionPerformed
        // TODO add your handling code here:
        
                // creating list of process
       
 
        // checking the directory, on which currently
        // working on
        //System.out.println("directory: " + build.directory());
        
        String j = DetModBox.getSelectedItem().toString();
        k = PathBox.getText();
        
        if ((j.equals("Image_Detection")) && ((k.contains(".jpg")) || (k.contains(".png")) || (k.contains(".jpeg")))){
            
            //PyProcess();   
            
            
            
             //running the required image and displaying result 
            {
                    // creating list of process
                    List<String> list = new ArrayList<>();
                    list.add("python");
                    list.add("run_img.py");
                    list.add(path);
                    
                    // creating the process
                    ProcessBuilder pb = new ProcessBuilder(list);
        
                    //starting the process
                    System.out.println(list);
                    // setting the directory
                    pb.directory(new File("F:/Jupyter/Objectdetection/virtualenv"));
        
                    pb.inheritIO();
        
                    try {
                            Process process = pb.start();
                        } 
                    catch (IOException ex) 
                        {
                            Logger.getLogger(Selection_JFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
          
            }
         
    

            
            
            
           /* Image_JFrame h =new Image_JFrame();
            h.setVisible(true);
            h.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
    } 
        else if((j.equals("LocalVideo_Detection")) && ((k.contains(".mp4")) || (k.contains(".webm")) || (k.contains(".mov")))){
            
            {
                    // creating list of process
                    List<String> list = new ArrayList<>();
                    list.add("python");
                    list.add("run_video.py");
                    list.add(path);
                    
                    // creating the process
                    ProcessBuilder pb = new ProcessBuilder(list);
        
                    //starting the process
                    System.out.println(list);
                    // setting the directory
                    pb.directory(new File("F:/Jupyter/Objectdetection/virtualenv"));
        
                    pb.inheritIO();
        
                    try {
                            Process process = pb.start();
                        } 
                    catch (IOException ex) 
                        {
                            Logger.getLogger(Selection_JFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
          
            }
                
                
                
                    
        
        }else if(j.equals("LiveVideo_Detection")){
            {
                    // creating list of process
                    List<String> list = new ArrayList<>();
                    list.add("python");
                    list.add("run_live.py");
                    list.add("0");
                    // creating the process
                    ProcessBuilder pb = new ProcessBuilder(list);

                    //starting the process


                    // setting the directory
                    pb.directory(new File("F:/Jupyter/Objectdetection/virtualenv"));

                    pb.inheritIO();

                    try 
                    {
                        Process process = pb.start();
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(Selection_JFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
           
                    /*LiveVideo_JFrame h =new LiveVideo_JFrame();
                    h.setVisible(true);
                    h.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
                                        
                                        
                }else{
                    JOptionPane.showMessageDialog(this,"Enter valid Filename!!.");
                }
        
    }//GEN-LAST:event_LaunchButtActionPerformed

    private void HisModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HisModActionPerformed
        // TODO add your handling code here:
        
          String j = HisMod.getSelectedItem().toString();
        /*if(null == j){
            LiveVideo_JFrame h =new LiveVideo_JFrame();
            h.setVisible(true);
            h.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        else*/ switch (j) {
            case "Image_data" ->                 {
                
                    
                    
                    im.setVisible(true);
                    im.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            case "LocalVideo_data" ->                 {
                    
                    lo.setVisible(true);
                    lo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            default ->                 {
                    
                    li.setVisible(true);
                    li.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
        }                             
    }//GEN-LAST:event_HisModActionPerformed

    private void PathBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PathBoxActionPerformed
        // TODO add your handling code here:
        /*String r = jTextField4.showSaveDialog(null);
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());*/
        
        
    }//GEN-LAST:event_PathBoxActionPerformed

    private void DetModBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetModBoxActionPerformed
        // TODO add your handling code here:
        
          //String k = jComboBox2.getSelectedItem().toString();
    }//GEN-LAST:event_DetModBoxActionPerformed

    private void ShowDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowDataActionPerformed
        // TODO add your handling code here:
        
        switch (DetModBox.getSelectedItem().toString()) {
            case "Image_Detection" ->                 {
                    Display_table("image_data",im.Image_table);
                    im.setVisible(true);
                    im.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            case "LocalVideo_Detection" ->                 {
                    Display_table("localvideo_data",lo.Local_table);
                    lo.setVisible(true);
                    lo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            default ->                 {
                    Display_table("livevideo_data",li.Live_table);
                    li.setVisible(true);
                    li.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
        }   
    }//GEN-LAST:event_ShowDataActionPerformed

    private void AttachButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AttachButtActionPerformed
        // TODO add your handling code here:
        
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        PathBox.setText(filename);
        path = filename;
        
    }//GEN-LAST:event_AttachButtActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Selection_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Selection_JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AttachButt;
    private javax.swing.JComboBox<String> DetModBox;
    private javax.swing.JLabel HisLabel;
    private javax.swing.JComboBox<String> HisMod;
    private javax.swing.JButton LaunchButt;
    private javax.swing.JTextField PathBox;
    private javax.swing.JButton ShowData;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
