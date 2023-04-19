/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import model.VendaLivro;

/**
 *
 * @author 182120039
 */
public class VendaLivroDAO {
    public  void cadVendaLivroDAO(VendaLivro vlVO){
    try{
       Connection con = Conexao.getConexao();
       String sql = "insert into pedidos values (null,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        java.sql.Date dataAtual = java.sql.Date.valueOf(vlVO.getDataVenda());
        pst.setDate(1, dataAtual);
        pst.setInt(2, vlVO.getIdCliente().getIdCliente());
        pst.setFloat(3, vlVO.getSubTotal());
        pst.executeUpdate();
        
        String sqlIdPedido = "select max(idPedido) from pedidos";
        PreparedStatement pst2 = con.prepareStatement(sqlIdPedido);
        ResultSet rsIdPed = pst2.executeQuery();
        int idPedido;
        while (rsIdPed.next()){
            idPedido = rsIdPed.getInt("idpedido");
        }
    }catch(SQLException e){
        System.out.println("Erro ao finalizar venda." + e.getMessage());
    }    
    }
    
}
