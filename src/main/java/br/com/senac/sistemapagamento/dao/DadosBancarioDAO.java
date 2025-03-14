package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.DadosBancario;
import jakarta.persistence.EntityManager;

/**
 * Classe DAO (Data Access Object) responsável pelas operações de persistência
 * para a entidade DadosBancario. Extende a classe GenericDAO para herdar
 * funcionalidades genéricas de persistência.
 *
 * @author alanm
 */
public class DadosBancarioDAO extends GenericDAO<DadosBancario> {

    // Construtor que passa a classe DadosBancario para a classe pai (GenericDAO)
    public DadosBancarioDAO() {
        super(DadosBancario.class);
    }

    /**
     * Método para excluir os dados bancários no banco de dados pelo ID.
     *
     * @param id O ID do dado bancário a ser excluído.
     */
    public void excluirDadosBancarios(int id) {
        // Obtém o EntityManager para gerenciar as transações com o banco de dados
        EntityManager em = JPAUtil.getEntityManager();

        try {
            // Inicia a transação no banco de dados
            em.getTransaction().begin();

            // Busca os dados bancários pelo ID
            DadosBancario dadosBancarios = em.find(DadosBancario.class, id);
            if (dadosBancarios != null) {
                // Se os dados bancários foram encontrados, remove-os do banco
                em.remove(dadosBancarios);
            }

            // Commit da transação, confirmando a exclusão no banco de dados
            em.getTransaction().commit();
        } catch (Exception e) {
            // Se ocorrer algum erro, realiza o rollback da transação para garantir que nenhuma alteração seja feita
            em.getTransaction().rollback();
            // Imprime o erro no console para análise
            e.printStackTrace();
        } finally {
            // Fecha o EntityManager após o uso para liberar recursos
            em.close();
        }
    }
}
