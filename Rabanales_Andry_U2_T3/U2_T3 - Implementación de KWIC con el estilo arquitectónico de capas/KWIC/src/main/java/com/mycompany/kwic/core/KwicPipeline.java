package com.mycompany.kwic.core;

import com.mycompany.kwic.filters.IFilter;
import java.util.ArrayList;
import java.util.List;

public class KwicPipeline {
    private final List<IFilter> filters = new ArrayList<>();

    public void addFilter(IFilter filter) {
        this.filters.add(filter);
    }

    public void execute() {
        List<String> pipeData = new ArrayList<>();
        for (IFilter filter : filters) {
            pipeData = filter.process(pipeData);
        }
    }
}