package projetoFinal;
// ApiOpenAi.java

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiOpenAi {
    private static final String API_KEY = "sk-proj-WFaA4Xy9AWoxfQZybgPGT3BlbkFJ7zw4ZhFsXjX0WGjHWCHe";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static String getDietPlan(Usuario usuario) {
        String prompt = String.format("Crie um plano alimentar variado e detalhado para cada dia da semana incluindo sábado e domingo (mais livre porém ainda restrito) para uma pessoa de %d anos, sexo %s, com peso de %.2f kg e altura de %.2f m. Classe social %s, nível de atividade física %s, preferências alimentares: %s, e objetivo de %s. Inclua horários e quantidades específicas. Evite monotonia, variando os alimentos diariamente.",
                usuario.getIdade(), usuario.getSexo(), usuario.getPeso(), usuario.getAltura(), usuario.getClasseSocial(), usuario.getNivelAtividade(), usuario.getPreferenciasAlimentares(), usuario.getObjetivo());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString("{\"model\": \"gpt-4\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return "Error accessing the API: " + response.statusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to process the API response.";
        }
    }
}

