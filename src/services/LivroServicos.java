/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOFactory;
import dao.LivroDAO;
import java.util.ArrayList;
import model.Livro;

/**
 *
 * @author 182120039
 */
public class LivroServicos {
    
    public void cadLivro(Livro lVO) {
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        lDAO.cadastrarLivroDAO(lVO);
    }
    
    public void atualizarLivro(Livro lVO){
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        lDAO.atualizaLivroByDoc(lVO);
    }
    
    public void deletarLivro(String isbn){
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        lDAO.deletarLivroDAO(isbn);
    }
    
    public Livro buscarLivrobyISBN(String isbn){
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        return lDAO.getLivroByDoc(isbn);
    }
    
    public ArrayList<Livro> getLivros(){
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        return lDAO.getLivroDAO();
    }
    
    
}
