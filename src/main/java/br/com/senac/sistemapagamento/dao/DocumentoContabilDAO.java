package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.DocumentoContabil;

/**
 *
 * Classe DAO (Data Access Object) responsável pelas operações de persistência para a entidade DocumentoContabil.
 * Esta classe herda as funcionalidades genéricas de persistência da classe GenericDAO. 
 * 
 * @author alanm
 */
public class DocumentoContabilDAO extends GenericDAO<DocumentoContabil> {

     // Construtor que passa a classe DocumentoContabil para a classe pai (GenericDAO)
    public DocumentoContabilDAO() {
        super(DocumentoContabil.class);
    }
}
