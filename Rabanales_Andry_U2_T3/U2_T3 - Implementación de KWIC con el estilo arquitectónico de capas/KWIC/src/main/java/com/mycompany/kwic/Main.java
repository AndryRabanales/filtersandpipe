package com.mycompany.kwic;

// Importa las INTERFACES
import com.mycompany.kwic.business.IKwicService;
import com.mycompany.kwic.data.IExporterService;
import com.mycompany.kwic.data.IReaderService;
import com.mycompany.kwic.presentation.IKwicController;

// Importa las IMPLEMENTACIONES (¡solo aquí!)
import com.mycompany.kwic.business.KwicServiceImpl;
import com.mycompany.kwic.data.FileReaderServiceImpl;
import com.mycompany.kwic.data.PdfExporterServiceImpl;
import com.mycompany.kwic.presentation.KwicControllerImpl;

import com.mycompany.kwic.model.KwicRequest;
import java.nio.file.Paths;

/**
 * Este es el "Ensamblador".
 * Construye las capas de abajo hacia arriba.
 */
public class Main {

    public static void main(String[] args) {

        // --- 1. CONSTRUCCIÓN (De abajo hacia arriba) ---
        // Capa 3 (Datos)
        IReaderService reader = new FileReaderServiceImpl();
        IExporterService exporter = new PdfExporterServiceImpl();

        // Capa 2 (Negocio) - Se le inyecta la Capa 3
        IKwicService kwicService = new KwicServiceImpl(reader, exporter);

        // Capa 1 (Presentación) - Se le inyecta la Capa 2
        IKwicController controller = new KwicControllerImpl(kwicService);

        // --- 2. PREPARACIÓN ---
        // Asegúrate de que "entrada.txt" esté en la raíz de tu proyecto KWIC
        KwicRequest request = new KwicRequest(
                Paths.get("entrada.txt"),
                Paths.get("ejecucion_capas.pdf") // Nuevo nombre de salida
        );

        // --- 3. EJECUCIÓN (Una sola llamada) ---
        try {
            controller.execute(request); // <-- La única llamada
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}