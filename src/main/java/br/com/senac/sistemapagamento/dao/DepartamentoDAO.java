package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.Departamento;
import jakarta.persistence.EntityManager;

/**
 * Classe DAO (Data Access Object) responsável pelas operações de persistência
 * para a entidade Departamento. Extende a classe GenericDAO para herdar
 * funcionalidades genéricas de persistência.
 *
 * @author alanm
 */
public class DepartamentoDAO extends GenericDAO<Departamento> {

    // Construtor que passa a classe Departamento para a classe pai (GenericDAO)
    public DepartamentoDAO() {
        super(Departamento.class);
    }

    /**
     * Método para excluir um departamento no banco de dados pelo ID.
     *
     * @param id O ID do departamento a ser excluído.
     */
    public void excluirDepartamento(int id) {
        // Obtém o EntityManager para gerenciar as transações com o banco de dados
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Inicia a transação no banco de dados
            em.getTransaction().begin();

            // Busca o departamento no banco de dados pelo ID
            Departamento departamento = em.find(Departamento.class, id);

            // Verifica se o departamento foi encontrado
            if (departamento != null) {
                // Se o departamento foi encontrado, remove-o do banco de dados
                em.remove(departamento);
            }
            // Commit da transação, confirmando a exclusão do departamento no banco de dados
            em.getTransaction().commit();
        } catch (Exception e) {
            // Caso ocorra algum erro, realiza o rollback da transação para garantir que nenhuma alteração seja feita
            em.getTransaction().rollback();
            // Imprime o erro no console para análise
            e.printStackTrace();
        } finally {
            // Fecha o EntityManager após o uso para liberar recursos
            em.close();
        }
    }
}
