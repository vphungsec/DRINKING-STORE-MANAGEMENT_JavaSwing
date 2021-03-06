/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlqcafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nhom 40
 */
public class NVLamViec extends javax.swing.JFrame {   
    private NguoiDung staff;
    public static DefaultTableModel dtm;   
    
    public NVLamViec(NguoiDung user) {
        initComponents();
        this.staff = user;
        setResizable(false);
        setLocationRelativeTo(this);        
        layLSLamViecNV();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_phienLamViec = new javax.swing.JTable();
        jButton_home = new javax.swing.JButton();
        jTextField_timKiem = new javax.swing.JTextField();
        jButton_timKiem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Phiên Làm Việc Của Nhân Viên");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 10, 343, 37));

        jTable_phienLamViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Giờ vào ", "Giờ ra"
            }
        ));
        jScrollPane1.setViewportView(jTable_phienLamViec);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 65, 628, 285));

        jButton_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photo/smart-home.png"))); // NOI18N
        jButton_home.setBorder(new javax.swing.border.SoftBevelBorder(0));
        jButton_home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_homeActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 51, 35));

        jTextField_timKiem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(jTextField_timKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 261, 27));

        jButton_timKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photo/search (1).png"))); // NOI18N
        jButton_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_timKiemActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_timKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(573, 360, 69, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tìm kiếm nhân viên theo mã");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 200, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_homeActionPerformed
        GiaoDienChung gdc = new GiaoDienChung(this.staff);
        this.setVisible(false);
        gdc.setVisible(true);
    }//GEN-LAST:event_jButton_homeActionPerformed

    private void jButton_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_timKiemActionPerformed
        String sql = "select * from PHIENLAMVIEC where MANV like N'%"+jTextField_timKiem.getText() + "%'";
        layDulieu(sql);
        jTextField_timKiem.setText("");
    }//GEN-LAST:event_jButton_timKiemActionPerformed
    
    private String LayTenNV(String id){
        String ten = null;
        Connection cn = ConnectDB.SQLConnect();
        String sql  = "select * from NHANVIEN where MANV = '" +id+"'";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ten = rs.getString("TENNV");
            }
        } catch (SQLException e) {
            Logger.getLogger(DoUong.class.getName()).log(Level.SEVERE, null, e);
        }
        return ten;
    }
    
    private void layLSLamViecNV(){
        String sql= "select * from PHIENLAMVIEC";
        layDulieu(sql);
    }
    
    private void layDulieu(String sql){
        dtm = (DefaultTableModel)jTable_phienLamViec.getModel();
        dtm.setNumRows(0);        
       
        Vector vt ;
        try{
            Connection cn = ConnectDB.SQLConnect();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vt = new Vector();
                vt.add(rs.getString("MANV"));
                String ten = LayTenNV(rs.getString("MANV"));
                vt.add(ten);
                vt.add(rs.getString("CHECKIN"));
                vt.add(rs.getString("CHECKOUT"));
                dtm.addRow(vt);
            }
            jTable_phienLamViec.setModel(dtm);
            rs.close();
            ps.close();
            cn.close();
        }
        catch(SQLException e){
             Logger.getLogger(DoUong.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_home;
    private javax.swing.JButton jButton_timKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_phienLamViec;
    private javax.swing.JTextField jTextField_timKiem;
    // End of variables declaration//GEN-END:variables
}
