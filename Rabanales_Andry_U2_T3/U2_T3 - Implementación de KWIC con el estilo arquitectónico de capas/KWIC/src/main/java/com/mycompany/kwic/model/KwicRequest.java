package com.mycompany.kwic.model;

import java.nio.file.Path;

public class KwicRequest {
    private final Path inputFile;
    private final Path outputFile;

    public KwicRequest(Path inputFile, Path outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public Path getInputFile() {
        return inputFile;
    }

    public Path getOutputFile() {
        return outputFile;
    }
}