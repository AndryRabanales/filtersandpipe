package com.mycompany.kwic.data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReaderServiceImpl {

    private static final Charset[] CANDIDATES = new Charset[] {
            StandardCharsets.UTF_8,
            StandardCharsets.ISO_8859_1,
            StandardCharsets.UTF_16LE,
            StandardCharsets.UTF_16BE
    };

    public List<String> readLines(Path path) throws Exception {
        List<String> lines;
        for (Charset cs : CANDIDATES) {
            lines = Files.readAllLines(path, cs);
            lines = trimNonEmpty(lines);
            if (!lines.isEmpty()) return lines;
        }
        return trimNonEmpty(Files.readAllLines(path, StandardCharsets.UTF_8));
    }

    private List<String> trimNonEmpty(List<String> in) {
        List<String> out = new ArrayList<>();
        for (String s : in) {
            if (s != null) {
                String t = s.trim();
                if (!t.isEmpty()) out.add(t);
            }
        }
        return out;
    }
}