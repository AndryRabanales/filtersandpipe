package com.mycompany.kwic.filters;

import java.util.List;

/**
 * Interfaz que define el contrato para un filtro en el pipeline.
 * Cumple con el prefijo "I" como se solicitó.
 */
public interface IFilter {
    /**
     * Procesa una lista de strings y retorna la lista transformada.
     * @param data Los datos que entran al filtro a través de un "pipe".
     * @return Los datos procesados, listos para el siguiente "pipe".
     */
    List<String> process(List<String> data);
}