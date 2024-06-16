// Definição do pacote ao qual a classe pertence.
package projetoFinal;

// Importação das bibliotecas necessárias para trabalhar com requisições HTTP e URI.
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Declaração da classe pública ApiOpenAi.
public class ApiOpenAi {
    // Constantes para armazenar a chave da API e a URL base para requisições.
    private static final String API_KEY = "sk-proj-WFaA4Xy9AWoxfQZybgPGT3BlbkFJ7zw4ZhFsXjX0WGjHWCHe";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    // Método estático que recebe um objeto do tipo Usuario e retorna um String.
    public static String getDietPlan(Usuario usuario) {
        // Formatação da string 'prompt' que será enviada à API, utilizando dados do usuário.
        String prompt = String.format("Crie um plano alimentar variado e detalhado para cada dia da semana incluindo sábado e domingo (mais livre porém ainda restrito) para uma pessoa de %d anos, sexo %s, com peso de %.2f kg e altura de %.2f m. Classe social %s, nível de atividade física %s, preferências alimentares: %s, e objetivo de %s. Inclua horários e quantidades específicas. Evite monotonia, variando os alimentos diariamente.",
                usuario.getIdade(), usuario.getSexo(), usuario.getPeso(), usuario.getAltura(), usuario.getClasseSocial(), usuario.getNivelAtividade(), usuario.getPreferenciasAlimentares(), usuario.getObjetivo());

        // Criação do objeto HttpClient para fazer a requisição HTTP.
        HttpClient client = HttpClient.newHttpClient();
        // Construção da requisição HTTP, definindo URI, cabeçalhos e o corpo da mensagem.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString("{\"model\": \"gpt-4\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}"))
                .build();

        try {
            // Envio da requisição e recebimento da resposta.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Verificação do código de status da resposta e retorno do corpo da mensagem ou mensagem de erro.
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return "Error accessing the API: " + response.statusCode();
            }
        } catch (Exception e) {
            // Tratamento de exceções e retorno de mensagem de falha.
            e.printStackTrace();
            return "Failed to process the API response.";
        }
    }
}