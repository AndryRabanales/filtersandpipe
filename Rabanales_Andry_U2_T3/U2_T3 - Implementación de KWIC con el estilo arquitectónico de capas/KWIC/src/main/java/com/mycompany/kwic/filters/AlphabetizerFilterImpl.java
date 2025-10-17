package com.mycompany.kwic.filters;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class AlphabetizerFilterImpl implements IFilter {
    @Override
    public List<String> process(List<String> data) {
        System.out.println("FILTRO: Ordenando alfabéticamente...");
        List<String> sortedList = new ArrayList<>(data);
        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
        return sortedList;
    }
}