// Definição do pacote ao qual a classe pertence.
package projetoFinal;

// Declaração da classe pública FormatarResposta.
public class FormatarResposta {
    // Método estático formatar que aceita uma string como argumento e retorna uma string formatada.
    public static String formatar(String resposta) {
        // Substituição de todas as sequências de "\\n" na string por "\n", que é o caractere de quebra de linha real.
        resposta = resposta.replaceAll("\\\\n", "\n");
        
        // Substituição de texto entre dois pares de asteriscos duplos (**texto**) por um texto destacado entre linhas de igual (====).

        resposta = resposta.replaceAll("(\\*\\*)([^*]+)(\\*\\*)", "\n==== $2 ====\n");
        
        // Substituição de texto entre um par de asteriscos (*texto*) por um texto destacado entre linhas de traço (---).

        resposta = resposta.replaceAll("\\*(.*?)\\*", "\n--- $1 ---\n");
        
        // Retorna a string formatada.
        return resposta;
    }
}