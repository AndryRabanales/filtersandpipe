package com.mycompany.kwic.data; // <-- Asegúrate que el PAQUETE sea 'data'

import com.mycompany.kwic.data.IExporterService; // <-- Importa la interfaz
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

//                ¡¡AQUÍ ESTÁ EL ARREGLO!!
public class PdfExporterServiceImpl implements IExporterService {

    @Override
    public void export(List<String> lines, Path output) throws Exception {
        if (output.getParent() != null) Files.createDirectories(output.getParent());

        try (PdfWriter writer = new PdfWriter(output.toString());
             PdfDocument pdf = new PdfDocument(writer);
             Document doc = new Document(pdf)) {

            doc.add(new Paragraph("Resultado del Algoritmo KWIC").setBold());
            doc.add(new Paragraph("\n"));

            if (lines == null || lines.isEmpty()) {
                doc.add(new Paragraph("(Sin contenido)"));
            } else {
                for (String l : lines) doc.add(new Paragraph(l));
            }
        }
    }
}