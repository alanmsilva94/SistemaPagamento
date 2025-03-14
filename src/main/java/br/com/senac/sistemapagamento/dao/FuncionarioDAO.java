package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.Funcionario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável pelas operações de persistência
 * para a entidade Funcionario. Esta classe estende a GenericDAO para herdar
 * funcionalidades genéricas de persistência.
 *
 * @author alanm
 */
public class FuncionarioDAO extends GenericDAO<Funcionario> {

    // Construtor que passa a classe Funcionario para a classe pai (GenericDAO)
    public FuncionarioDAO() {
        super(Funcionario.class);
    }

    /**
     * Método para excluir um funcionário com base no ID.
     *
     * @param id
     */
    public void excluirFuncionario(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Inicia a transação
            em.getTransaction().begin();

            // Busca o funcionário pelo ID
            Funcionario funcionario = em.find(Funcionario.class, id);

            // Se o funcionário for encontrado, remove-o
            if (funcionario != null) {
                em.remove(funcionario);
            }

            // Finaliza a transação confirmando a exclusão
            em.getTransaction().commit();
        } catch (Exception e) {
            // Em caso de erro, faz o rollback para desfazer as alterações
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Fecha o EntityManager para liberar recursosv
            em.close();
        }
    }

    /**
     * Método estático para buscar um funcionário pelo nome.
     * 
     * @param nome O nome do funcionário a ser buscado.
     * @return O funcionário encontrado ou null se não existir.
     */
    public static Funcionario buscarPorNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        Funcionario funcionario = null;

        try {
             // Cria a consulta para buscar o funcionário pelo nome
            String textoQuery = "SELECT f FROM Funcionario f WHERE f.nome_fun = :nome";
            TypedQuery<Funcionario> consulta = em.createQuery(textoQuery, Funcionario.class);
            
            // Define o parâmetro da consulta com o nome informado
            consulta.setParameter("nome", nome);

            // Executa a consulta e armazena o resultado
            List<Funcionario> resultado = consulta.getResultList();
            
            // Se houver resultados, pega o primeiro da lista
            if (!resultado.isEmpty()) {
                funcionario = resultado.get(0);
            }
        } catch (Exception e) {
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
        } finally {
            // Fecha o EntityManager para liberar recursos
            em.close();
        }
        return funcionario;
    }
}
