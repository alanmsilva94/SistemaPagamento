package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável pelas operações de persistência relacionadas ao Login.
 *
 * @author alanm
 */
public class LoginDAO extends GenericDAO<Login> {

    // Construtor que chama o construtor da classe pai (GenericDAO)
    public LoginDAO() {
        super(Login.class);
    }

    /**
     * Método para validar as credenciais de um usuário no banco de dados
     *
     * @param login Objeto Login contendo as credenciais informadas.
     * @return Um objeto Login se as credenciais forem válidas; null caso
     * contrário.
     */
    public static Login validarUsuario(Login login) {
        String sql = "SELECT * FROM logins WHERE nome = ? AND senha = ?";
        Login usuarioEncontrado = null;
        try {
            // Estabelece a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sistema_cadastro_pagamentos",
                    "root",
                    "alanmoura1994");

            // Prepara a consulta SQL
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, login.getLogin());
            st.setString(2, login.getSenha());

            // Executa a consulta e obtém os resultados
            ResultSet rs = st.executeQuery();

            // Processa o resultado
            while (rs.next()) {
                usuarioEncontrado = new Login();
                usuarioEncontrado.setId(rs.getInt("id"));
                usuarioEncontrado.setLogin(rs.getString("nome"));
                usuarioEncontrado.setSenha(rs.getString("senha"));
            }
        } catch (SQLException ex) {
            System.out.println("Sintaxe de comando inválida");
        }
        return usuarioEncontrado;
    }

    /**
     * Método para verificar se um nome de usuário já existe no banco de dados.
     *
     * @param nomeUsuario Nome do usuário a ser verificado.
     * @return true se o usuário existir; false caso contrário.
     */
    public static boolean verificarUsuarioPorNome(String nomeUsuario) {
        String sql = "SELECT * FROM logins WHERE nome = ?";

        try (
                // Estabelece conexão com o banco e prepara a consulta SQL
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sistema_cadastro_pagamentos",
                        "root",
                        "alanmoura1994"); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, nomeUsuario);

            // Executa a consulta e verifica se encontrou resultados
            ResultSet rs = st.executeQuery();
            return rs.next(); // Retorna true se encontrar o usuário

        } catch (SQLException ex) {
            System.out.println("Erro ao verificar o usuário: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Método para atualizar a senha de um usuário.
     *
     * @param nomeUsuario Nome do usuário cuja senha será alterada.
     * @param novaSenha Nova senha a ser definida.
     * @return true se a senha for atualizada com sucesso; false caso contrário.
     */
    public static boolean atualizarSenha(String nomeUsuario, String novaSenha) {
        String sql = "UPDATE logins SET senha = ? WHERE nome = ?";
        try (
                // Estabelece conexão com o banco e prepara a consulta SQL
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sistema_cadastro_pagamentos",
                        "root",
                        "alanmoura1994"); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, novaSenha);
            st.setString(2, nomeUsuario);

            // Executa a atualização e verifica quantas linhas foram afetadas
            int linhasAfetadas = st.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se pelo menos uma linha for atualizada
            
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar a senha: " + ex.getMessage());
            return false;
        }
    }
}
