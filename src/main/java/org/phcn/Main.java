package org.phcn;

import org.phcn.application.services.ContaService;
import org.phcn.domain.repository.ContaRepository;
import org.phcn.infrastructure.persistency.ContaRespositoryImpl;
import org.phcn.presentation.cli.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        ContaRepository repo = new ContaRespositoryImpl();
        //ContaService service = new ContaService(repo);

        //MenuPrincipal menu = new MenuPrincipal(service);
        //menu.MenuPrincipal();
    }
}