package com.mycompany.kwic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KwicServiceImpl {

    public List<String> rotateAndSort(List<String> lines) {
        List<String> all = new ArrayList<>();
        for (String line : lines) {

            all.addAll(rotate(line));
        }
        Collections.sort(all, String.CASE_INSENSITIVE_ORDER);
        return all;
    }

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