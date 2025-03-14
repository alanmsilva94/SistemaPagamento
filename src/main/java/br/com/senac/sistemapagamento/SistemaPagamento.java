package br.com.senac.sistemapagamento;

import br.com.senac.sistemapagamento.dao.DepartamentoDAO;
import br.com.senac.sistemapagamento.models.Departamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SistemaPagamento {

    public static void main(String[] args) {

        EntityManagerFactory fabricaEntidade = Persistence.createEntityManagerFactory("Sistema-PU");
        EntityManager manager = fabricaEntidade.createEntityManager();

        Departamento depart = new Departamento();
        depart.setNome_depart("Audio Visual");
        
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        
        departamentoDAO.cadastrar(depart);
        
        System.out.println("Dados cadastrados com sucesso!");
        
        manager.close();
        fabricaEntidade.close();

    }
}
