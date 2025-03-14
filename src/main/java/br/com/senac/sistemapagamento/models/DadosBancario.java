package br.com.senac.sistemapagamento.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import br.com.senac.sistemapagamento.models.Empresa;

/**
 *
 * @author alanm
 */
@Entity
@Table(name = "dados_bancarios")
public class DadosBancario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer codigo_banco;
    private String nome_banco;
    private String tipo_conta;
    private Integer agencia;
    private Integer digito_agencia;
    private Integer conta;
    private Integer digito_conta;
    private String chave_pix;
    private String nome_recebedor;
    private String cnpj_recebedor;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo_banco() {
        return codigo_banco;
    }

    public void setCodigo_banco(Integer codigo_banco) {
        this.codigo_banco = codigo_banco;
    }

    public String getNome_banco() {
        return nome_banco;
    }

    public void setNome_banco(String nome_banco) {
        this.nome_banco = nome_banco;
    }

    public String getTipo_conta() {
        return tipo_conta;
    }

    public void setTipo_conta(String tipo_conta) {
        this.tipo_conta = tipo_conta;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getDigito_agencia() {
        return digito_agencia;
    }

    public void setDigito_agencia(Integer digito_agencia) {
        this.digito_agencia = digito_agencia;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public Integer getDigito_conta() {
        return digito_conta;
    }

    public void setDigito_conta(Integer digito_conta) {
        this.digito_conta = digito_conta;
    }

    public String getChave_pix() {
        return chave_pix;
    }

    public void setChave_pix(String chave_pix) {
        this.chave_pix = chave_pix;
    }

    public String getNome_recebedor() {
        return nome_recebedor;
    }

    public void setNome_recebedor(String nome_recebedor) {
        this.nome_recebedor = nome_recebedor;
    }

    public String getCnpj_recebedor() {
        return cnpj_recebedor;
    }

    public void setCnpj_recebedor(String cnpj_recebedor) {
        this.cnpj_recebedor = cnpj_recebedor;
    }
}
