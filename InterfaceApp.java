package projetoFinal;
// InterfaceApp.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InterfaceApp extends JFrame {
    private JTextField pesoField, alturaField, idadeField, preferenciasField, objetivoField;
    private JComboBox<String> sexoBox, classeSocialBox, nivelAtividadeBox;
    private JTextArea resultadoArea;
    private JButton enviarButton;

    public InterfaceApp() {
        createGUI();
    }

    private void createGUI() {
        setTitle("Aplicativo de Dieta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        addInputFields(inputPanel);
        add(inputPanel, BorderLayout.CENTER);

        enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(this::submitData);
        add(enviarButton, BorderLayout.SOUTH);

        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);
        add(new JScrollPane(resultadoArea), BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addInputFields(JPanel panel) {
        panel.add(new JLabel("Peso (kg):"));
        pesoField = new JTextField(5);
        panel.add(pesoField);

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

    private void submitData(ActionEvent event) {
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

        String response = ApiOpenAi.getDietPlan(usuario);
        response = FormatarResposta.formatar(response);
        resultadoArea.setText(response);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfaceApp().setVisible(true));
    }
}