package com.mycompany.kwic.filters;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.nio.file.Path;
import java.util.List;

public class OutputWriterFilterImpl implements IFilter {
    private final Path outputPath;

    public OutputWriterFilterImpl(Path outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public List<String> process(List<String> data) {
        System.out.println("FILTRO: Escribiendo resultado en PDF...");
        try (PdfWriter writer = new PdfWriter(outputPath.toFile());
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            document.add(new Paragraph("Resultado del Algoritmo KWIC").setBold());
            for (String line : data) {
                document.add(new Paragraph(line));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al escribir el PDF.", e);
        }
        // Este es el último filtro (sink), no retorna datos para otro pipe.
        return null;
    }
}
