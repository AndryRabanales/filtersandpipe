package com.mycompany.kwic.presentation;

import com.mycompany.kwic.*;
import com.mycompany.kwic.business.IKwicService;
import com.mycompany.kwic.business.KwicServiceImpl;
import com.mycompany.kwic.data.FileReaderServiceImpl;
import com.mycompany.kwic.data.PdfExporterServiceImpl;
import com.mycompany.kwic.model.KwicRequest;
import java.util.List;

public class KwicController {

    private final FileReaderServiceImpl reader;
    private final KwicServiceImpl kwic;
    private final PdfExporterServiceImpl exporter;

    public KwicController() {
        this.reader = new FileReaderServiceImpl();
        this.kwic = new KwicServiceImpl();
        this.exporter = new PdfExporterServiceImpl();
    }

    public void execute(KwicRequest request) throws Exception {
        System.out.println("Iniciando proceso KWIC...");

        List<String> lines = reader.readLines(request.getInputFile());
        List<String> kwicLines = kwic.rotateAndSort(lines);
        exporter.export(kwicLines, request.getOutputFile());
        
        System.out.println("Proceso KWIC finalizado con éxito.");
    }
}