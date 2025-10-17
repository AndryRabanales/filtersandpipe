package com.mycompany.kwic.business; // <-- Paquete nuevo

// Importa las interfaces, no las clases
import com.mycompany.kwic.data.IExporterService;
import com.mycompany.kwic.data.IReaderService;
import com.mycompany.kwic.model.KwicRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KwicServiceImpl implements IKwicService { // <-- Implementa interfaz

    // 1. Dependencias como campos 'final'
    private final IReaderService readerService;
    private final IExporterService exporterService;

    // 2. Constructor que RECIBE las dependencias
    public KwicServiceImpl(IReaderService readerService, IExporterService exporterService) {
        this.readerService = readerService;
        this.exporterService = exporterService;
    }

    // 3. Lógica de orquestación (lo que antes hacía el controlador)
    @Override
    public void runKwicProcess(KwicRequest request) throws Exception {
        List<String> lines = readerService.readLines(request.getInputFile());
        List<String> allRotations = new ArrayList<>();
        for (String line : lines) {
            allRotations.addAll(rotate(line));
        }
        Collections.sort(allRotations, String.CASE_INSENSITIVE_ORDER);
        exporterService.export(allRotations, request.getOutputFile());
    }

    // Tu método 'rotate' original
    private List<String> rotate(String sentence) {
        List<String> out = new ArrayList<>();
        if (sentence == null) return out;
        String[] words = sentence.trim().split("\\s+");
        if (words.length == 0) return out;
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < words.length; j++) {
                sb.append(words[(i + j) % words.length]).append(" ");
            }
            out.add(sb.toString().trim());
        }
        return out;
    }
}