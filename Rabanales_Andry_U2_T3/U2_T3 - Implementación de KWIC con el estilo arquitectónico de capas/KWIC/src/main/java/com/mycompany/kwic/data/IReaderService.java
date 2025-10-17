package com.mycompany.kwic.data; // <-- El paquete es la carpeta
import java.nio.file.Path;
import java.util.List;

public interface IReaderService {
    List<String> readLines(Path path) throws Exception;
}