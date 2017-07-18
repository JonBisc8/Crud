/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author jonlima
 */
public class ConectaBD {
    
    public static Connection conectabd() throws ClassNotFoundException{
        String senha = "5214583";
        //String senha = "postgres";
    try{
    
    Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Crud","postgres", senha);
    //JOptionPane.showMessageDialog(null,"Jesus Esta comigo!!");
    return con;
    
    }
    catch(SQLException er){
    
    JOptionPane.showMessageDialog(null, er);
    return null;
        
    }
        
    }
    
}
