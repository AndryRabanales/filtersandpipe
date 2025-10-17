package com.mycompany.kwic.ui;

import com.mycompany.kwic.core.KwicPipeline;
import com.mycompany.kwic.filters.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IKwicAppFrame extends JFrame {
    private Path inputFile;
    private Path outputFile;
    private final JTextField inputField = new JTextField(25);
    private final JTextField outputField = new JTextField(25);

    public IKwicAppFrame() {
        setTitle("Generador KWIC (Pipes and Filters)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(550, 200));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        leftPanel.add(new JLabel("Archivo de Entrada (.txt):"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        inputField.setEditable(false);
        leftPanel.add(inputField, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        JButton selectInputButton = new JButton("Examinar...");
        leftPanel.add(selectInputButton, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        leftPanel.add(new JLabel("Guardar PDF como:"), gbc);
        gbc.gridx = 1;
        outputField.setEditable(false);
        leftPanel.add(outputField, gbc);
        gbc.gridx = 2;
        JButton selectOutputButton = new JButton("Examinar...");
        leftPanel.add(selectOutputButton, gbc);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton generateButton = new JButton("Generar PDF");
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
        rightPanel.add(generateButton, BorderLayout.CENTER);

        selectInputButton.addActionListener(e -> selectInputFile());
        selectOutputButton.addActionListener(e -> selectOutputFile());
        generateButton.addActionListener(e -> generateKwic());

        add(leftPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        pack();
    }

    private void selectInputFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecciona el archivo de entrada");
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos de Texto", "txt"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            inputFile = chooser.getSelectedFile().toPath();
            inputField.setText(inputFile.toAbsolutePath().toString());
        }
    }

    private void selectOutputFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guarda el archivo PDF de salida");
        chooser.setSelectedFile(new java.io.File("salida.pdf"));
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            outputFile = chooser.getSelectedFile().toPath();
            if (!outputFile.toString().toLowerCase().endsWith(".pdf")) {
                outputFile = Paths.get(outputFile.toString() + ".pdf");
            }
            outputField.setText(outputFile.toAbsolutePath().toString());
        }
    }

    private void generateKwic() {
        if (inputFile == null || outputFile == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un archivo de entrada y una ubicación de salida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            KwicPipeline pipeline = new KwicPipeline();
            pipeline.addFilter(new InputReaderFilterImpl(inputFile));
            pipeline.addFilter(new CircularShifterFilterImpl());
            pipeline.addFilter(new AlphabetizerFilterImpl());
            pipeline.addFilter(new OutputWriterFilterImpl(outputFile));
            pipeline.execute();
            JOptionPane.showMessageDialog(this, "¡PDF generado con éxito con Pipes and Filters!\nUbicación: " + outputFile.toAbsolutePath(), "Proceso Completado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocurrió un error al generar el PDF:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}