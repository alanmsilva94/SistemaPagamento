package br.com.senac.sistemapagamento.models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe utilitária para realizar operações de criptografia.
 *
 * Esta classe fornece um método para gerar o hash MD5 de uma string, que pode
 * ser utilizado para armazenamento seguro de senhas ou outras informações.
 *
 * @author alanm
 */
public class Criptografia {

    /**
     * Gera o hash MD5 de uma string fornecida.
     *
     * Este método utiliza a API {@link MessageDigest} para calcular o hash MD5
     * de um texto e retorna o resultado como uma string hexadecimal de 32
     * caracteres.
     *
     *
     *
     * @param texto A string a ser criptografada.
     * @return O hash MD5 da string fornecida em formato hexadecimal.
     */
    public static String getMD5(String texto) {
        try {
            // Obtém uma instância do algoritmo MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Calcula o hash do texto e converte para um array de bytes
            byte[] messageDigest = md.digest(texto.getBytes());

            // Converte o array de bytes para um número inteiro grande (BigInteger)
            BigInteger no = new BigInteger(1, messageDigest);

            // Converte o BigInteger para uma string hexadecimal
            String hashtext = no.toString(16);

            // Adiciona zeros à esquerda se o texto hexadecimal tiver menos de 32 caracteres
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            // Lança uma exceção se o algoritmo MD5 não estiver disponível
            throw new RuntimeException(e);
        }
    }
}
