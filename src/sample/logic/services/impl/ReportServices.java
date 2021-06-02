package sample.logic.services.impl;

import javafx.collections.FXCollections;
import sample.logic.entities.*;
import sample.logic.services.IPersonaServices;
import sample.logic.services.IReportServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportServices implements IReportServices {

    private IPersonaServices personasService;

    public ReportServices() {
        personasService = new PersonaService();
    }

    public List<Report> getReportPersonasByRole() {

        List<Persona> personas = personasService.getAll();
        return this.getReportPersonasByRole(personas);
    }

    public List<Report> getReportPersonasByRole(List<Persona> personas) {

        List<Report> reports = FXCollections.observableArrayList();

        reports.add(new Report(Roles.ROLE1_PROTESTANTE, 0, String.format("Report of count of personas in profession %s", Roles.ROLE1_PROTESTANTE)));
        reports.add(new Report(Roles.ROLE2_PROTESTANTE, 0, String.format("Report of count of personas in profession %s", Roles.ROLE2_PROTESTANTE)));
        reports.add(new Report(Roles.ROLE_POLITICO, 0, String.format("Report of count of personas in profession %s", Roles.ROLE_POLITICO)));
        reports.add(new Report(Roles.ROLE_AGENTES_DE_LA_LEY, 0, String.format("Report of count of personas in profession %s", Roles.ROLE_AGENTES_DE_LA_LEY)));
        reports.add(new Report(Roles.ROLE_COMUNISTA, 0, String.format("Report of count of personas in profession %s", Roles.ROLE_COMUNISTA)));

        for (Persona p : personas) {
            if(reports.get(0).getCriteria().equals(p.getRole()))
            {
                reports.get(0).incrementCount();
            }
            else if(reports.get(1).getCriteria().equals(p.getRole()))
            {
                reports.get(1).incrementCount();
            }
            else if(reports.get(2).getCriteria().equals(p.getRole()))
            {
                reports.get(2).incrementCount();
            }
            else if(reports.get(3).getCriteria().equals(p.getRole()))
            {
                reports.get(3).incrementCount();
            }
            else if(reports.get(4).getCriteria().equals(p.getRole()))
            {
                reports.get(4).incrementCount();
            }
        }


        return reports;

    }

    /*public Report getCountOfVictims() {

        List<Persona> personas = personasService.getAll();
        int numOfVictims = (int)personas.stream().filter(p -> p.isVictim()).count();

        return new Report("Victims", numOfVictims, "This is the number of victims during the strikes");
    }*/

}