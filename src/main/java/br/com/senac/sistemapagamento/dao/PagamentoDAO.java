package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.Empresa;
import br.com.senac.sistemapagamento.models.Pagamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Classe responsável pelas operações CRUD relacionadas à entidade Pagamento.
 *
 * @author alanm
 */
public class PagamentoDAO extends GenericDAO<Pagamento> {

    public PagamentoDAO() {
        super(Pagamento.class);
    }

    /**
     * Lista todos os pagamentos cadastrados, incluindo informações sobre
     * empresa, funcionário e departamento.
     *
     * @return Lista de pagamentos
     */
    public List<Pagamento> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Pagamento> consulta = em.createQuery(
                    "SELECT p FROM Pagamento p "
                    + "LEFT JOIN FETCH p.empresa "
                    + "LEFT JOIN FETCH p.funcionario f "
                    + "LEFT JOIN FETCH f.departamento", Pagamento.class);
            return consulta.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Exclui um pagamento com base no ID informado.
     *
     * @param id Identificador do pagamento
     */
    public void excluirPagamento(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Pagamento pagamento = em.find(Pagamento.class, id);
            if (pagamento != null) {
                em.remove(pagamento);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Lista os pagamentos filtrados por uma ou mais empresas.
     *
     * @param empresas Lista de empresas para filtrar os pagamentos
     * @return Lista de pagamentos vinculados às empresas fornecidas
     */
    public List<Pagamento> listarPagamentosPorEmpresa(List<Empresa> empresas) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Pagamento> consulta = em.createQuery(
                    "SELECT p FROM Pagamento p "
                    + "LEFT JOIN FETCH p.empresa e "
                    + "LEFT JOIN FETCH p.funcionario f "
                    + "LEFT JOIN FETCH f.departamento "
                    + "WHERE e IN :empresas", Pagamento.class);
            consulta.setParameter("empresas", empresas);
            return consulta.getResultList();
        } finally {
            em.close();
        }
    }
}
