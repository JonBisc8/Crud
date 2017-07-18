/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author jonlima
 */
public class UsuarioDAO {
    private String nome;
    private String cargo;
    private String contato;
    
    //Name settings
    public String getNome(){ return this.nome;}
    public void setNome(String nome){ this.nome = nome;}
    
    //Cargo settings
    public String getCargo(){ return this.cargo;}
    public void setCargo(){this.cargo = cargo;}
    
    //Contato settings
    public String getContato(){ return this.contato;}
    public void setContato(){this.contato = contato;}
}
