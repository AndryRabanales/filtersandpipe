package com.mycompany.kwic.filters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class InputReaderFilterImpl implements IFilter {
    private final Path inputPath;

    public InputReaderFilterImpl(Path inputPath) {
        this.inputPath = inputPath;
    }

    @Override
    public List<String> process(List<String> data) {
        System.out.println("FILTRO: Leyendo archivo de entrada...");
        try {
            return Files.lines(inputPath)
                        .map(String::trim)
                        .filter(line -> !line.isEmpty())
                        .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al leer el archivo de entrada.", e);
        }
    }
}
