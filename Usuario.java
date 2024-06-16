// Especifica o pacote ao qual a classe pertence.
package projetoFinal;

// Declaração da classe pública Usuario.
public class Usuario {
    // Declaração de variáveis privadas para armazenar as propriedades do usuário.
    private double peso;
    private double altura;
    private int idade;
    private String sexo;
    private String classeSocial;
    private String nivelAtividade;
    private String preferenciasAlimentares;
    private String objetivo;

    // Construtor da classe que inicializa as variáveis com valores fornecidos.
    public Usuario(double peso, double altura, int idade, String sexo, String classeSocial, String nivelAtividade, String preferenciasAlimentares, String objetivo) {
        this.peso = peso;
        this.altura = altura;
        this.idade = idade;
        this.sexo = sexo;
        this.classeSocial = classeSocial;
        this.nivelAtividade = nivelAtividade;
        this.preferenciasAlimentares = preferenciasAlimentares;
        this.objetivo = objetivo;
    }

    // Métodos 'getters' para acessar os valores das propriedades do usuário.
    public double getPeso() { return peso; }
    public double getAltura() { return altura; }
    public int getIdade() { return idade; }
    public String getSexo() { return sexo; }
    public String getClasseSocial() { return classeSocial; }
    public String getNivelAtividade() { return nivelAtividade; }
    public String getPreferenciasAlimentares() { return preferenciasAlimentares; }
    public String getObjetivo() { return objetivo; }
}