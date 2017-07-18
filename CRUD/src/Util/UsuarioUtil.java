/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DAO.ConectaBD;
import DAO.UsuarioDAO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author jonlima
 */
public class UsuarioUtil {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    UsuarioDAO usu = new UsuarioDAO();
    
    public UsuarioUtil() throws ClassNotFoundException {
        
        //initComponents();
        //this.setLocationRelativeTo(null);
        con = ConectaBD.conectabd();
        //listar();
        //fillCombo();
        
    }
    
    
    public void resizeColUsu(JTable tb1){
    
        tb1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb1.getColumnModel().getColumn(0).setPreferredWidth(30);
        tb1.getColumnModel().getColumn(1).setPreferredWidth(300);
        tb1.getColumnModel().getColumn(2).setPreferredWidth(120);
        tb1.getColumnModel().getColumn(3).setPreferredWidth(120);
    }
    
    public void listar(JComboBox ordem, JTable tb1){
        
        String noid = (String) ordem.getSelectedItem();
        String sql = "select * from usuario2 order by "+noid+";";
        
    try{
    
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        tb1.setModel(DbUtils.resultSetToTableModel(rs));
        //this.resizeColUsu();
        
    }
    catch(SQLException er){JOptionPane.showMessageDialog(null, er);}
    }
    
    public void cleanFields(JTextField id, 
                           JTextField nome, 
                           JComboBox cargo, 
                           JFormattedTextField contato,
                           JLabel foto){
    
        id.setText("");
        nome.setText("");
        cargo.setSelectedIndex(0);
        contato.setText("");
        foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pic/User.jpg")));
    
    }
    
    public void fillCombo(JComboBox cargo){
    
    String sql = "select * from cargo2;";
    
    try{
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while(rs.next()){
        
            cargo.addItem(rs.getString("nome"));
        }
    }
    catch(SQLException er){
        JOptionPane.showMessageDialog(null, "Não foi possível exibir o combo: "+er);}
        
    }
    
    public void search(JTextField search, JTable tb1){
    
        String sql = "select * from usuario2 where nome ilike ?";
        
    try{
        pst = con.prepareStatement(sql);
        pst.setString(1, search.getText()+"%");
        rs = pst.executeQuery();
        tb1.setModel(DbUtils.resultSetToTableModel(rs));
        
    }
    catch(SQLException er){JOptionPane.showMessageDialog(null, "Não foi possível listar: "+er);}
    
    }
    
    public void fillFields(JTable tb1, 
                           JTextField id1,
                           JTextField nome1,
                           JComboBox cargo1,
                           JFormattedTextField contato1,
                           JLabel foto){
    
        int sel = tb1.getSelectedRow();
        
        String id = tb1.getModel().getValueAt(sel, 0).toString();
        String nome = tb1.getModel().getValueAt(sel, 1).toString();                
        String car = tb1.getModel().getValueAt(sel, 2).toString();
        String contato = tb1.getModel().getValueAt(sel, 3).toString();
        
        //Settings for image casting
        String sql = "select image from usuario2 where id = "+id+";";
        ImageIcon image;
        InputStream is;
        
        try{
        
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
            is = rs.getBinaryStream(1);
            
            BufferedImage bi = ImageIO.read(is);
            image = new ImageIcon(bi);
            
            Image img = image.getImage();
            
            int w = foto.getWidth();
            int h = foto.getHeight();
            
            Image newimage = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
            
            ImageIcon newicon = new ImageIcon(newimage);
            foto.setIcon(newicon);
            }
        
        }
        catch(Exception e){
            //JOptionPane.showMessageDialog(null,"Não foi possível exibir imagem: "+e);
            foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pic/x.png")));
        }
        
        id1.setText(id);
        nome1.setText(nome);
        cargo1.setSelectedItem(car);
        contato1.setText(contato);
    
    }
    
    public void cadastrar(JTextField nome, 
                          JComboBox cargo, 
                          JFormattedTextField contato,
                          FileInputStream fis,
                          int longBytes){
        
     String sqlInsert = "insert into usuario2(nome, cargo, contato, image) values(?, ?, ?, ?);";
     String car = (String) cargo.getSelectedItem();
     
    try{
        pst = con.prepareStatement(sqlInsert);
        pst.setString(1, nome.getText());
        pst.setString(2, car);
        pst.setString(3, contato.getText());
        pst.setBinaryStream(4, fis, longBytes);
        
        pst.execute();
        //System.out.println(car);
        JOptionPane.showMessageDialog(null,"Cadastrado com sucesso!");
        
        
    }
    catch(SQLException er){
        JOptionPane.showMessageDialog(null, "Erro no cadastro: "+er);
    }
        
    }
    
    
    public void editar(JTextField id1, 
                       JComboBox cargo, 
                       JTextField nome, 
                       JFormattedTextField contato,
                          FileInputStream fis,
                          int longBytes){
    
      String sqlUpdate = "update usuario2 set nome = ?, cargo = ?, contato = ?, image = ? where id = ?;";
      int id = Integer.parseInt(id1.getText());
      String car = (String) cargo.getSelectedItem();
        
        try{
        
        int reply = JOptionPane.showConfirmDialog(null, 
                "Este regristro já existe, deseja alterá-lo?", "Registro existente!", 
                JOptionPane.YES_NO_OPTION);
        
        if (reply == JOptionPane.YES_OPTION) {
            
            pst = con.prepareStatement(sqlUpdate);
        
        pst.setString(1, nome.getText());
        pst.setString(2, car);
        pst.setString(3, contato.getText());
        pst.setBinaryStream(4, fis, longBytes);
        pst.setInt(5, id);
        
        pst.executeUpdate();
        //listar();
        //resizeCol();
        //cleanFields();
        
        JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
        
        }
                   
        }catch(SQLException er){
            JOptionPane.showMessageDialog(null, "Não foi possível editar: "+er);}
    
    }
    
    public void excluir(JTextField id1, JTable tb1){
    
    String sql = "delete from usuario2 where id=?;";
    int id = Integer.parseInt(id1.getText());
            
    try{
    
        int reply = JOptionPane.showConfirmDialog(null, 
                "Deseja mesmo excluir este registro?", "Exclusão de registro!", 
                JOptionPane.YES_NO_OPTION);
        
        if (reply == JOptionPane.YES_OPTION) {
        
        pst = con.prepareStatement(sql);
        
        pst.setInt(1, id);
        pst.execute();
        
        JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");}        
    }
    catch(SQLException er){JOptionPane.showMessageDialog(null,"Erro na exclusão: "+er);}
    
    }
    
}
