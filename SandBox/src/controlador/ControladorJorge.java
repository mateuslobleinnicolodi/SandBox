/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DaoJorge;
import javax.swing.JOptionPane;
import modelo.jorge;
import tela.manutencao.ManutencaoJorge;

import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nicolodi
 */
public class ControladorJorge {

    public static void inserir(ManutencaoJorge man){
        jorge objeto = new jorge();
        objeto.setNome(man.jtfNome.getText());
        objeto.setSkill(man.jtfSkill.getText());
        
        boolean resultado = DaoJorge.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

    public static void alterar(ManutencaoJorge man){
        jorge objeto = new jorge();
        //definir todos os atributos
        objeto.setId(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setNome(man.jtfNome.getText());
        objeto.setSkill(man.jtfSkill.getText());
        
        boolean resultado = DaoJorge.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

     public static void excluir(ManutencaoJorge man){
        jorge objeto = new jorge();
        objeto.setId(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoJorge.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    
    public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Id");
        modelo.addColumn("Nome");
        modelo.addColumn("Skill");
        List<jorge> resultados = DaoJorge.consultar();
        for (jorge objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getId());
            linha.add(objeto.getNome());
            linha.add(objeto.getSkill());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
}
