package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.DadosBancario;
import br.com.senac.sistemapagamento.models.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável pelas operações de persistência
 * para a entidade Empresa. Esta classe estende a GenericDAO para herdar
 * funcionalidades genéricas de persistência.
 *
 * @author alanm
 */
public class EmpresaDAO extends GenericDAO<Empresa> {

    // Construtor que passa a classe Empresa para a classe pai (GenericDAO)
    public EmpresaDAO() {
        super(Empresa.class);
    }

    /**
     * Método estático para buscar uma empresa pelo CNPJ.
     *
     * @param cnpj O CNPJ da empresa a ser buscada.
     * @return A empresa encontrada ou null se não existir.
     */
    public static Empresa BuscarPorCnpj(String cnpj) {

        EntityManager em = JPAUtil.getEntityManager();
        Empresa empresa = null;

        try {
            // Define a query para buscar a empresa pelo CNPJ
            String textoQuery = "SELECT e FROM Empresa e WHERE e.cnpj = :cnpj";
            TypedQuery<Empresa> consulta = em.createQuery(textoQuery, Empresa.class);
            consulta.setParameter("cnpj", cnpj);

            // Executa a consulta e obtém o resultado
            List<Empresa> resultado = consulta.getResultList();
            if (!resultado.isEmpty()) {
                empresa = resultado.get(0);
            }
        } catch (Exception e) {
            // Caso ocorra erro, imprime o stack trace
            e.printStackTrace();
        } finally {
            // Fecha o EntityManager
            em.close();
        }
        return empresa;
    }

    /**
     * Método para listar todas as empresas com seus dados bancários associados.
     *
     * @return Lista de empresas com dados bancários.
     */
    public List<Empresa> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Consulta para listar empresas e seus dados bancários com LEFT JOIN
            TypedQuery<Empresa> consulta = em.createQuery(
                    "SELECT e FROM Empresa e LEFT JOIN FETCH e.dadosBancarios", Empresa.class);
            return consulta.getResultList();
        } finally {
            // Fecha o EntityManager
            em.close();
        }
    }

    /**
     * Método para excluir uma empresa pelo ID.
     *
     * @param id O ID da empresa a ser excluída.
     */
    public void excluirEmpresa(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            // Buscar e excluir a empresa pelo ID
            Empresa empresa = em.find(Empresa.class, id);
            if (empresa != null) {
                em.remove(empresa);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            // Caso ocorra erro, faz o rollback e imprime o erro
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Fecha o EntityManager
            em.close();
        }
    }

    /**
     * Método para cadastrar uma empresa e seus dados bancários.
     *
     * @param empresa A empresa a ser cadastrada.
     * @param dadosBancarios Lista de dados bancários a serem associados à
     * empresa.
     */
    public void cadastrarEmpresa(Empresa empresa, List<DadosBancario> dadosBancarios) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            // Associa cada dado bancário à empresa
            for (DadosBancario dadosBancario : dadosBancarios) {
                dadosBancario.setEmpresa(empresa);
                empresa.getDadosBancarios().add(dadosBancario);
            }
            // Persiste a empresa e seus dados bancários no banco de dados
            em.persist(empresa);
            em.getTransaction().commit();
            System.out.println("Empresa e dados bancários cadastrados com sucesso!");
        } catch (Exception e) {
            // Caso ocorra erro, faz o rollback e imprime a falha
            em.getTransaction().rollback();
            System.out.println("Falha ao cadastrar empresa e dados bancários.");
            e.printStackTrace();
        } finally {
            // Fecha o EntityManager
            em.close();
        }
    }

    /**
     * Método para listar empresas com filtro de nome.
     *
     * @param filtroEmpresa O filtro de nome da empresa.
     * @return Lista de empresas filtradas.
     */
    public List<Empresa> listarEmpresa(String filtroEmpresa) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Empresa> empresa = new ArrayList<>();

        try {
            // Consulta para listar empresas com base no filtro de nome
            String textoQuery = "SELECT e FROM Empresa e LEFT JOIN FETCH e.dadosBancarios WHERE (:nome IS NULL OR e.nomeEmpresa LIKE :nome)";
            TypedQuery<Empresa> consulta = em.createQuery(textoQuery, Empresa.class);

            consulta.setParameter("nome", filtroEmpresa.isEmpty() ? null : "%" + filtroEmpresa + "%");

            // Executa a consulta e retorna a lista de empresas
            empresa = consulta.getResultList();
        } catch (Exception e) {
            // Caso ocorra erro, imprime o erro
            e.printStackTrace();
        } finally {
            // Fecha o EntityManager
            JPAUtil.closeEntityManager();
        }
        return empresa;
    }
}
