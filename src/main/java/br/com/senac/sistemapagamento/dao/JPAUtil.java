package br.com.senac.sistemapagamento.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe utilitária para gerenciar as instâncias do EntityManager e
 * EntityManagerFactory. Essa classe facilita a conexão e manipulação do banco
 * de dados usando JPA.
 *
 * @author alanm
 */
public class JPAUtil {

    // Nome da unidade de persistência definida no arquivo persistence.xml
    private static final String PERSISTENCE_UTIL = "Sistema-PU";

    // EntityManager gerencia as operações com o banco de dados
    private static EntityManager em;

    // EntityManagerFactory é responsável por criar instâncias de EntityManager
    private static EntityManagerFactory emf;

    /**
     * Método para obter uma instância de EntityManager. Se a fábrica Se a
     * fábrica (EntityManagerFactory) não estiver criada ou estiver fechada, ela
     * é instanciada. Da mesma forma, uma nova instância de EntityManager é
     * criada caso ainda não exista ou esteja fechada.
     *
     * @return Uma instância ativa de EntityManager
     */
    public static EntityManager getEntityManager() {
        // Verifica se a fábrica está nula ou fechada, e cria uma nova se necessário
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UTIL);
        }

        // Verifica se o EntityManager está nulo ou fechado, e cria uma nova instância se necessário
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }

    /**
     * Método para fechar o EntityManager e o EntityManagerFactory. 
     * Isso libera os recursos utilizados durante a execução da aplicação. Essa ação deve
     * ser feita apenas uma vez no encerramento da aplicação
     */
    public static void closeEntityManager() {
          // Fecha o EntityManager se estiver aberto
        if (em != null && em.isOpen()) {
            em.close();
        }

        // Fecha o EntityManagerFactory se estiver aberto
        if (emf != null && emf.isOpen()) {
            emf.close(); // Fechar o EntityManagerFactory apenas uma vez, no encerramento da aplicação
        }
    }
}
