package projetoFinal;
// FormatarResposta.java

public class FormatarResposta {
    public static String formatar(String resposta) {
        resposta = resposta.replaceAll("\\\\n", "\n");
        resposta = resposta.replaceAll("(\\*\\*)([^*]+)(\\*\\*)", "\n==== $2 ====\n");
        resposta = resposta.replaceAll("\\*(.*?)\\*", "\n--- $1 ---\n");
        return resposta;
    }
}