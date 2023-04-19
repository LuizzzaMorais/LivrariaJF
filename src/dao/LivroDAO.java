/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Livro;
import services.EditoraServicos;
import services.ServicosFactory;

/**
 *
 * @author 182120039
 */
public class LivroDAO {

    public void cadastrarLivroDAO(Livro lVO) {
        try {
            Connection con = Conexao.getConexao();
            Statement stat = con.createStatement();
            String sql;
            sql = "insert into livros values (null,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, lVO.getTitulo());
            pst.setString(2, lVO.getAutor());
            pst.setString(3, lVO.getAssunto());
            pst.setString(4, lVO.getIsbn());
            pst.setInt(5, lVO.getEstoque());
            pst.setFloat(6, lVO.getPreco());
            pst.setInt(7, lVO.getIdEditora().getIdEditora());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar!\n" + ex.getMessage());
        }
    }

    public ArrayList<Livro> getLivroDAO() {
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select livros.*, e.cnpj from livros join editoras e using(ideditora)";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
            while (rs.next()) {
                Livro l = new Livro();
                l.setIdLivro(rs.getInt("idlivro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAssunto(rs.getString("assunto"));
                l.setIsbn(rs.getString("isbn"));
                l.setEstoque(rs.getInt("estoque"));
                l.setPreco(rs.getFloat("preco"));
                l.setIdEditora(editoraS.buscarEditorabyCNPJ(rs.getString("cnpj")));
                livros.add(l);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar!\n" + ex.getMessage());
        }
        return livros;
    }

    public Livro getLivroByDoc(String isbn) {
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        Livro l = new Livro();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select livros.*, e.cnpj from livros join editoras e using(ideditora) where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, isbn);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l.setIdLivro(rs.getInt("idlivro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAssunto(rs.getString("assunto"));
                l.setIsbn(rs.getString("isbn"));
                l.setEstoque(rs.getInt("estoque"));
                l.setPreco(rs.getFloat("preco"));
                l.setIdEditora(editoraS.buscarEditorabyCNPJ(rs.getString("cnpj")));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar ISBN!\n" + ex.getMessage());
        }
        return l;
    }

    public void deletarLivroDAO(String isbn) {

        try {
            Connection con = Conexao.getConexao();
            String sql = "delete from livros where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, isbn);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar ISBN!\n" + ex.getMessage());
        }
    }//fim deletarLivro

    public void atualizaLivroByDoc(Livro lVO) {

        try {
            Connection con = Conexao.getConexao();
            String sql = "update livros set estoque = ?, preco = ? where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, lVO.getEstoque());
            pst.setFloat(2, lVO.getPreco());
            pst.setString(3, lVO.getIsbn());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar livro!\n" + ex.getMessage());
        }
    }
}
