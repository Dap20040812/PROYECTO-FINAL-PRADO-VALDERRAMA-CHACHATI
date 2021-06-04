package sample.logic.services.impl;

import javafx.collections.FXCollections;
import jdk.jshell.Snippet;
import sample.gui.BasicScene;
import sample.logic.entities.*;
import sample.logic.services.IPersonaServices;
import sample.logic.services.IReportServices;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportServices implements IReportServices{

    private IPersonaServices personasService;


    public ReportServices() {
        personasService = BasicScene.personaServices;
    }

    public List<Report> getReportPersonasByRole() {

        List<Persona> personas = personasService.getAll();
        return this.getReportPersonasByRole(personas);
    }

    public List<Report> getReportPersonasByRole(List<Persona> personas) {

        List<Report> reports = FXCollections.observableArrayList();
        Report g;
        reports.add(g = new Report(Roles.ROLE1_PROTESTANTE, 0, String.format("Report of count of personas in role %s", Roles.ROLE1_PROTESTANTE)));
        reports.add(new Report(Roles.ROLE2_PROTESTANTE, 0, String.format("Report of count of personas in role %s", Roles.ROLE2_PROTESTANTE)));
        reports.add(new Report(Roles.ROLE_POLITICO, 0, String.format("Report of count of personas in role %s", Roles.ROLE_POLITICO)));
        reports.add(new Report(Roles.ROLE_AGENTES_DE_LA_LEY, 0, String.format("Report of count of personas in role %s", Roles.ROLE_AGENTES_DE_LA_LEY)));
        reports.add(new Report(Roles.ROLE_COMUNISTA, 0, String.format("Report of count of personas in role %s", Roles.ROLE_COMUNISTA)));

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

    public List<Report> getReportPersonasByStatusDead() {

        List<Persona> personas = personasService.getAll();
        return this.getReportPersonasByStatusDead(personas);
    }

    public List<Report> getReportPersonasByStatusDead(List<Persona> personas) {

        List<Report> reports = FXCollections.observableArrayList();

        reports.add(new Report(Roles.ROLE1_PROTESTANTE, 0, String.format("Report of count of personas in role %s", Status.MUERTO)));
        reports.add(new Report(Roles.ROLE2_PROTESTANTE, 0, String.format("Report of count of personas in role %s", Status.MUERTO)));
        reports.add(new Report(Roles.ROLE_POLITICO, 0, String.format("Report of count of personas in role %s", Status.MUERTO)));
        reports.add(new Report(Roles.ROLE_AGENTES_DE_LA_LEY, 0, String.format("Report of count of personas in role %s", Status.MUERTO)));
        reports.add(new Report(Roles.ROLE_COMUNISTA, 0, String.format("Report of count of personas in role %s", Status.MUERTO)));

        for (Persona p : personas) {

            if(p.getStatus().equals(Status.MUERTO))
            {
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

        }

        return reports;
    }

    public List<Report> getReportPersonasByStatusHerido() {

        List<Persona> personas = personasService.getAll();
        return this.getReportPersonasByStatusHerido(personas);
    }

    public List<Report> getReportPersonasByStatusHerido(List<Persona> personas) {

        List<Report> reports = FXCollections.observableArrayList();

        reports.add(new Report(Roles.ROLE1_PROTESTANTE, 0, String.format("Report of count of personas in role %s", Status.HERIDO)));
        reports.add(new Report(Roles.ROLE2_PROTESTANTE, 0, String.format("Report of count of personas in role %s", Status.HERIDO)));
        reports.add(new Report(Roles.ROLE_POLITICO, 0, String.format("Report of count of personas in role %s", Status.HERIDO)));
        reports.add(new Report(Roles.ROLE_AGENTES_DE_LA_LEY, 0, String.format("Report of count of personas in role %s", Status.HERIDO)));
        reports.add(new Report(Roles.ROLE_COMUNISTA, 0, String.format("Report of count of personas in role %s", Status.HERIDO)));

        for (Persona p : personas) {

            if(p.getStatus().equals(Status.HERIDO))
            {
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
            }


        return reports;
    }

    public List<Report> getReportPersonasByAge() {

        List<Persona> personas = personasService.getAll();
        return this.getReportPersonasByAge(personas);
    }

    public List<Report> getReportPersonasByAge(List<Persona> personas) {

        List<Report> reports = FXCollections.observableArrayList();

        reports.add(new Report("Menor de edad",0,"Report of count of personas with an age under 18"));
        reports.add(new Report("18 a 30",0,"Report of count of personas with an age between 18 to 30 "));
        reports.add(new Report("31 a 60",0,"Report of count of personas with an age between 31 to 60 "));
        reports.add(new Report("Mas de 61",0,"Report of count of personas with an age greater than 61"));


        for (Persona p : personas) {

            String AgeS = p.getAge();
            AgeS.trim();
            int AgeI = Integer.parseInt(AgeS);

            System.out.println(AgeI);

            if(18 > AgeI)
            {
                reports.get(0).incrementCount();
            }
            if(18 <= AgeI && 30 >= AgeI)
            {
                reports.get(1).incrementCount();
            }
            if(31 <= AgeI && 60 >= AgeI)
            {
                reports.get(2).incrementCount();
            }
            if(61 <= AgeI )
            {
                reports.get(3).incrementCount();
            }

        }

        return reports;
    }
}