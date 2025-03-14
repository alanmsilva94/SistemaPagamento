package br.com.senac.sistemapagamento.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author alanm
 */
@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome_fun;

    @Column(name = "email")
    private String email_fun;

    @Column(name = "telefone")
    private String telefone_fun;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pagamento> pagamentos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_fun() {
        return nome_fun;
    }

    public void setNome_fun(String nome_fun) {
        this.nome_fun = nome_fun;
    }

    public String getEmail_fun() {
        return email_fun;
    }

    public void setEmail_fun(String email_fun) {
        this.email_fun = email_fun;
    }

    public String getTelefone_fun() {
        return telefone_fun;
    }

    public void setTelefone_fun(String telefone_fun) {
        this.telefone_fun = telefone_fun;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

}
