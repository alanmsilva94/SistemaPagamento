package br.com.senac.sistemapagamento.dao;

import jakarta.persistence.EntityManager;

/**
 * Classe genérica para operações de persistência no banco de dados. Esta classe
 * utiliza a API JPA para gerenciar entidades de forma genérica.
 *
 * @param <T> Tipo da entidade que será manipulada pela DAO.
 * @author alanm
 */
public class GenericDAO<T> {

    // Classe da entidade que esta DAO manipula
    private Class<T> entityClass;

    /**
     * Construtor que recebe a classe da entidade que será gerenciada.
     *
     * @param entityClass Classe da entidade (ex.: Empresa.class,
     * Funcionario.class).
     */
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Método genérico para cadastrar uma entidade no banco de dados.
     *
     * @param entity A entidade que será persistida.
     */
    public void cadastrar(T entity) {
        // Obtém uma instância de EntityManager para gerenciar a transação
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Inicia a transação
            em.getTransaction().begin();
            // Persiste a entidade no banco de dados
            em.persist(entity);
            // Finaliza a transação confirmando a inserção
            em.getTransaction().commit();
        } catch (Exception e) {
            // Em caso de erro, desfaz as alterações com rollback
            em.getTransaction().rollback();
            throw e; // Relança a exceção para que ela possa ser tratada externamente
        } finally {
            // Fecha o EntityManager para liberar recursos
            JPAUtil.closeEntityManager();
        }
    }
}
