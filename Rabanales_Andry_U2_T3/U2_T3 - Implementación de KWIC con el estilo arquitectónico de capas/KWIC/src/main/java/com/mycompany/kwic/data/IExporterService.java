package com.mycompany.kwic.data;
import java.nio.file.Path;
import java.util.List;

public interface IExporterService {
    void export(List<String> lines, Path output) throws Exception;
}