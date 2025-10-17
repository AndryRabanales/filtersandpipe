package com.mycompany.kwic.filters;

import java.util.ArrayList;
import java.util.List;

public class CircularShifterFilterImpl implements IFilter {
    @Override
    public List<String> process(List<String> data) {
        System.out.println("FILTRO: Generando rotaciones (Circular Shift)...");
        List<String> rotations = new ArrayList<>();
        for (String line : data) {
            String[] words = line.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                StringBuilder shiftedLine = new StringBuilder();
                for (int j = 0; j < words.length; j++) {
                    shiftedLine.append(words[(i + j) % words.length]).append(" ");
                }
                rotations.add(shiftedLine.toString().trim());
            }
        }
        return rotations;
    }
}