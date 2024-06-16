// Define o pacote ao qual a classe pertence.
package projetoFinal;

// Importa as bibliotecas necessárias para a criação da interface gráfica e o tratamento de eventos.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Declaração da classe InterfaceApp, que herda de JFrame para criar uma janela.
public class InterfaceApp extends JFrame {
    // Declaração de campos de texto e combobox para entrada de dados do usuário.
    private JTextField pesoField, alturaField, idadeField, preferenciasField, objetivoField;
    private JComboBox<String> sexoBox, classeSocialBox, nivelAtividadeBox;
    // Área de texto para exibir o resultado.
    private JTextArea resultadoArea;
    // Botão para enviar os dados.
    private JButton enviarButton;

    // Construtor que chama o método createGUI para construir a interface.
    public InterfaceApp() {
        createGUI();
    }

    // Método para configurar e montar a interface gráfica.
    private void createGUI() {
        // Configurações básicas da janela.
        setTitle("Crônograma Alimentar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Painel para os campos de entrada.
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        addInputFields(inputPanel); // Método que adiciona os campos ao painel.
        add(inputPanel, BorderLayout.CENTER);

        // Configuração e adição do botão enviar.
        enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(this::submitData); // Associação do evento de clique com o método submitData.
        add(enviarButton, BorderLayout.SOUTH);

        // Área de texto para o resultado, dentro de um JScrollPane para suportar rolagem.
        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);
        add(new JScrollPane(resultadoArea), BorderLayout.EAST);

        // Empacotar a janela e definir sua localização.
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para adicionar os campos de entrada ao painel.
    private void addInputFields(JPanel panel) {
        // Adiciona rótulos e campos para cada propriedade do usuário.
        panel.add(new JLabel("Peso (kg):"));
        pesoField = new JTextField(5);
        panel.add(pesoField);

        // Repete o processo para cada propriedade: altura, idade, sexo, classe social, nível de atividade, preferências alimentares e objetivo.
        panel.add(new JLabel("Altura (m):"));
        alturaField = new JTextField(5);
        panel.add(alturaField);

        panel.add(new JLabel("Idade:"));
        idadeField = new JTextField(5);
        panel.add(idadeField);

        panel.add(new JLabel("Sexo:"));
        sexoBox = new JComboBox<>(new String[]{"M", "F"});
        panel.add(sexoBox);

        panel.add(new JLabel("Classe Social:"));
        classeSocialBox = new JComboBox<>(new String[]{"Baixa", "Média", "Alta"});
        panel.add(classeSocialBox);

        panel.add(new JLabel("Nível de Atividade Física:"));
        nivelAtividadeBox = new JComboBox<>(new String[]{"Baixo", "Moderado", "Alto"});
        panel.add(nivelAtividadeBox);

        panel.add(new JLabel("Preferências Alimentares:"));
        preferenciasField = new JTextField(20);
        panel.add(preferenciasField);

        panel.add(new JLabel("Objetivo:"));
        objetivoField = new JTextField(20);
        panel.add(objetivoField);
    }

    // Método chamado ao clicar no botão Enviar, que cria um usuário, faz a requisição e exibe a resposta.
    private void submitData(ActionEvent event) {
        // Criação de um objeto Usuario com os dados dos campos de entrada.
        Usuario usuario = new Usuario(
                Double.parseDouble(pesoField.getText()),
                Double.parseDouble(alturaField.getText()),
                Integer.parseInt(idadeField.getText()),
                sexoBox.getSelectedItem().toString(),
                classeSocialBox.getSelectedItem().toString(),
                nivelAtividadeBox.getSelectedItem().toString(),
                preferenciasField.getText(),
                objetivoField.getText()
        );

        // Chamada ao método da API para obter o plano alimentar e formatação da resposta.
        String response = ApiOpenAi.getDietPlan(usuario);
        response = FormatarResposta.formatar(response);
        resultadoArea.setText(response);
    }

    // Método principal que inicia a aplicação.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfaceApp().setVisible(true));
    }
}
