/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.jorge;
import tela.listagem.ListagemJorges;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Nicolodi
 */
public class DaoJorge {
    public static boolean inserir(jorge objeto) {
        String sql = "INSERT INTO jorge (nome, skill) VALUES (?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getSkill());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
     public static void main(String[] args) {
        jorge objeto = new jorge();
        objeto.setNome("JorgeTeste");
        objeto.setSkill("verifica se o programa está funcionando");
        
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
     
       public static boolean alterar(jorge objeto) {
        String sql = "UPDATE jorge SET nome = ?, skill = ? WHERE id=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome()); 
            ps.setString(2, objeto.getSkill());
            ps.setInt(3, objeto.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       
         public static boolean excluir(jorge objeto) {
        String sql = "DELETE FROM jorge WHERE id=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         
    public static List<jorge> consultar() {
        List<jorge> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT id, nome, skill FROM jorge";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jorge objeto = new jorge();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setId(rs.getInt("id"));
                objeto.setNome(rs.getString("nome"));
                objeto.setSkill(rs.getString("skill"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
