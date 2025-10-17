package com.mycompany.kwic.presentation; // <-- Asegúrate que el PAQUETE sea 'presentation'

import com.mycompany.kwic.business.IKwicService;
import com.mycompany.kwic.model.KwicRequest;
import com.mycompany.kwic.presentation.IKwicController; // <-- Importa la interfaz

//               ¡¡AQUÍ ESTÁN LOS ARREGLOS!!
public class KwicControllerImpl implements IKwicController {

    private final IKwicService kwicService;

    public KwicControllerImpl(IKwicService kwicService) {
        this.kwicService = kwicService;
    }

    @Override
    public void execute(KwicRequest request) throws Exception {
        System.out.println("Iniciando proceso KWIC (Capas)...");
        kwicService.runKwicProcess(request);
        System.out.println("Proceso KWIC (Capas) finalizado con éxito.");
    }
}