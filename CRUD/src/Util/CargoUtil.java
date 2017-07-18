/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DAO.ConectaBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author jonlima
 */
public class CargoUtil {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public CargoUtil() throws ClassNotFoundException{
    
        con = ConectaBD.conectabd();
    
    }
    
    public void listar(JTable tb1){
    
        String sql = "select * from cargo2 order by id;";
        
    try{
    
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        tb1.setModel(DbUtils.resultSetToTableModel(rs));
        
    }
    catch(SQLException er){JOptionPane.showMessageDialog(null, er);}
    
    }
    
    public void resizeCol(JTable tb1){
    
        tb1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb1.getColumnModel().getColumn(0).setPreferredWidth(30);
        tb1.getColumnModel().getColumn(1).setPreferredWidth(200);
    
    }
    
    public void cleanFields(JTextField id, JTextField nome){
    
    id.setText("");
    nome.setText("");
    
    }
    
    public void cadastrar(JTextField nome) throws ClassNotFoundException{
     
     String sql = "insert into cargo2(nome) values(?);";
     
    try{
    
        pst = con.prepareStatement(sql);
        
        pst.setString(1, nome.getText());
        
        pst.execute();
        JOptionPane.showMessageDialog(null,"Cadastrado com sucesso!");
        
        nome.setText("");
        
    }
    catch(SQLException er){JOptionPane.showMessageDialog(null, er);}
        
    }
    
    public void editar(JTextField id1, 
                       JTextField nome1){
    
        String sqlUpdate = "update cargo2 set nome = ? where id = ?;";
        int id = Integer.parseInt(id1.getText());
        
        try{
        
        int reply = JOptionPane.showConfirmDialog(null, 
                "Este cargo já existe, deseja alterá-lo?", "Cargo existente!", 
                JOptionPane.YES_NO_OPTION);
        
        if (reply == JOptionPane.YES_OPTION) {
            
            pst = con.prepareStatement(sqlUpdate);
        
        pst.setString(1, nome1.getText());
        pst.setInt(2, id);
        
        pst.executeUpdate();
        //listar();
        //resizeCol();
        //cleanFields();
        
        JOptionPane.showMessageDialog(null, "Cargo alterado com sucesso!");
        
        }
                   
        }catch(SQLException er){
            JOptionPane.showMessageDialog(null, "Não foi possível alterar: "+er);}
    
    }
    
    public void excluir(JTextField id1, JTable tb1){
    
    String sql = "delete from cargo2 where id=?;";
    int id = Integer.parseInt(id1.getText());
            
    int reply = JOptionPane.showConfirmDialog(null, 
                "Deseja mesmo excluir este cargo?", "Exclusão de cargo!", 
                JOptionPane.YES_NO_OPTION);
        
        if (reply == JOptionPane.YES_OPTION) {
        
    try{
    
        pst = con.prepareStatement(sql);
        
        pst.setInt(1, id);
        pst.execute();
        
        JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");
        
    }
    
    catch(SQLException er){JOptionPane.showMessageDialog(null,
            "Erro na exclusão: "+er);}
    }
        
    }
    
    public void fillFields(JTable tb1, JTextField id1, JTextField nome1){
    
        int sel = tb1.getSelectedRow();
        
        String id = tb1.getModel().getValueAt(sel, 0).toString();
        String nome = tb1.getModel().getValueAt(sel, 1).toString();
        
        id1.setText(id);
        nome1.setText(nome);
    
    }
    
}
