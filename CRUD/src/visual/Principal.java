/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import DAO.ConectaBD;
import Util.UsuarioUtil;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
//import net.proteanit.sql.DbUtils;

import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;
//import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author jonathas.lima
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    
    UsuarioUtil usu = new UsuarioUtil();
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    JFileChooser fc = new JFileChooser();
    
    //Photo variables
    FileInputStream fis;
    int longBytes;
    
    public Principal() throws ClassNotFoundException {
        
        initComponents();
        this.setLocationRelativeTo(null);
        con = ConectaBD.conectabd();
        usu.listar(jCBOrdem, jTB1);
        usu.fillCombo(jCBCargo);
        usu.resizeColUsu(jTB1);
        
        jLBFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pic/User.jpg")));
        
    }
    
    //JComboBox cb1 = new JComboBox();
    
    public void setCombo(){
    
        usu.fillCombo(jCBCargo);
    
    }
    
    public void cadEdit(){
        
    try{
        //file input stream & longbytes for image data storage
        fis = new FileInputStream(fc.getSelectedFile());
        this.longBytes = (int) fc.getSelectedFile().length();
        
    //If the ID field is empty the add it!
    if(jTFId.getText().equals("")){
        
    usu.cadastrar(jTFNome, jCBCargo, jFTContato, fis, longBytes);
    
    //If there is a register with the same id then edit it!
    }else{usu.editar(jTFId, jCBCargo, jTFNome, jFTContato, fis, longBytes);}
    
    }catch(Exception err){JOptionPane.showMessageDialog(null,
            "Erro na alteração de registro: "+err);}
    
    }
    
    
    public void actionPerformed(ActionEvent e){
        
        fc.setCurrentDirectory(new File (System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("", "*.image","gif", "png");
        fc.addChoosableFileFilter(filter);
        
        int result = fc.showSaveDialog(null);
        
        if(result == JFileChooser.APPROVE_OPTION){
        
        File selectedFile = fc.getSelectedFile();
        String path = selectedFile.getAbsolutePath();
        jLBFoto.setIcon(ResizeImage(path));
        }
        
        else if(result == JFileChooser.CANCEL_OPTION){System.out.println("No file selected!");}
    }
    
    public ImageIcon ResizeImage(String imagePath){
    
        ImageIcon myImage = new ImageIcon(imagePath);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(jLBFoto.getWidth(), 
                                             jLBFoto.getHeight(), 
                                             Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTB1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jCBCargo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jFTContato = new javax.swing.JFormattedTextField();
        jBTCargo = new javax.swing.JButton();
        jBTLimpar = new javax.swing.JButton();
        jLBFoto = new javax.swing.JLabel();
        jBTBuscar = new javax.swing.JButton();
        jBTCadastrar = new javax.swing.JButton();
        jBTExcluir = new javax.swing.JButton();
        jBTSair = new javax.swing.JButton();
        jTFSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCBOrdem = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTB1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTB1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTB1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTB1);

        jPanel1.setBackground(new java.awt.Color(250, 225, 229));

        jLabel1.setText("Cadastro:");

        jLabel2.setText("Id:");

        jTFId.setEnabled(false);

        jLabel3.setText("Nome:");

        jLabel4.setText("Cargo:");

        jCBCargo.setMaximumRowCount(5);

        jLabel5.setText("Contato:");

        try {
            jFTContato.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jBTCargo.setText("Novo Cargo");
        jBTCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTCargoActionPerformed(evt);
            }
        });

        jBTLimpar.setText("Limpar campos");
        jBTLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTLimparActionPerformed(evt);
            }
        });

        jLBFoto.setBackground(new java.awt.Color(151, 187, 174));
        jLBFoto.setForeground(new java.awt.Color(92, 69, 69));

        jBTBuscar.setText("Buscar");
        jBTBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFId, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jFTContato, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBTLimpar))))
                    .addComponent(jLabel1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(78, 78, 78)
                            .addComponent(jBTCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLBFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jBTBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLBFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBTBuscar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTFId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jCBCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jFTContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTLimpar)
                    .addComponent(jBTCargo))
                .addContainerGap())
        );

        jBTCadastrar.setText("Cadastrar");
        jBTCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTCadastrarActionPerformed(evt);
            }
        });

        jBTExcluir.setText("Excluir");
        jBTExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTExcluirActionPerformed(evt);
            }
        });

        jBTSair.setText("Sair");
        jBTSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTSairActionPerformed(evt);
            }
        });

        jTFSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFSearchKeyReleased(evt);
            }
        });

        jLabel6.setText("Buscar:");

        jLabel7.setText("Ordenar por:");

        jCBOrdem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id", "Nome", "Cargo" }));
        jCBOrdem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBOrdemItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBTCadastrar)
                                .addGap(18, 18, 18)
                                .addComponent(jBTExcluir)
                                .addGap(18, 18, 18)
                                .addComponent(jBTSair, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCBOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jCBOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBTCadastrar)
                    .addComponent(jBTExcluir)
                    .addComponent(jBTSair))
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTCargoActionPerformed
        // TODO add your handling code here:
        Cargo c = null;
        try {
            c = new Cargo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setVisible(true);
        
    }//GEN-LAST:event_jBTCargoActionPerformed

    private void jBTCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTCadastrarActionPerformed
          // TODO add your handling code here:
            cadEdit();
            usu.listar(jCBOrdem, jTB1);
            usu.cleanFields(jTFId, jTFNome, jCBCargo, jFTContato, jLBFoto);
            usu.resizeColUsu(jTB1);
    }//GEN-LAST:event_jBTCadastrarActionPerformed

    private void jTFSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFSearchKeyReleased
        // TODO add your handling code here:
        usu.search(jTFSearch, jTB1);
        usu.resizeColUsu(jTB1);
    }//GEN-LAST:event_jTFSearchKeyReleased

    private void jTB1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTB1MouseClicked
        // TODO add your handling code here:
        usu.fillFields(jTB1, jTFId, jTFNome, jCBCargo, jFTContato, jLBFoto);
    }//GEN-LAST:event_jTB1MouseClicked

    private void jBTExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTExcluirActionPerformed
        // TODO add your handling code here:
        usu.excluir(jTFId, jTB1);
        usu.listar(jCBOrdem, jTB1);
        usu.resizeColUsu(jTB1);
        usu.cleanFields(jTFId, jTFNome, jCBCargo, jFTContato, jLBFoto);
    }//GEN-LAST:event_jBTExcluirActionPerformed

    private void jBTSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTSairActionPerformed
        // TODO add your handling code here:
        System.exit(0); //dispose();
    }//GEN-LAST:event_jBTSairActionPerformed

    private void jBTLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTLimparActionPerformed
        // TODO add your handling code here:
        usu.cleanFields(jTFId, jTFNome, jCBCargo, jFTContato, jLBFoto);
    }//GEN-LAST:event_jBTLimparActionPerformed

    private void jCBOrdemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBOrdemItemStateChanged
        // TODO add your handling code here:
        usu.listar(jCBOrdem, jTB1);
        usu.resizeColUsu(jTB1);
    }//GEN-LAST:event_jCBOrdemItemStateChanged

    private void jBTBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTBuscarActionPerformed
        // TODO add your handling code here:
        actionPerformed(evt);
    }//GEN-LAST:event_jBTBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTBuscar;
    private javax.swing.JButton jBTCadastrar;
    private javax.swing.JButton jBTCargo;
    private javax.swing.JButton jBTExcluir;
    private javax.swing.JButton jBTLimpar;
    private javax.swing.JButton jBTSair;
    private javax.swing.JComboBox<String> jCBCargo;
    private javax.swing.JComboBox<String> jCBOrdem;
    private javax.swing.JFormattedTextField jFTContato;
    private javax.swing.JLabel jLBFoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTB1;
    private javax.swing.JTextField jTFId;
    private javax.swing.JTextField jTFNome;
    private javax.swing.JTextField jTFSearch;
    // End of variables declaration//GEN-END:variables
}
