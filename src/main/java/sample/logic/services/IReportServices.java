package sample.logic.services;

import sample.logic.entities.*;

import java.util.List;
import java.util.Map;

public interface IReportServices {

    List<Report> getReportPersonasByRole();
    List<Report> getReportPersonasByRole(List<Persona> personas);

    List<Report> getReportPersonasByStatusDead();
    List<Report> getReportPersonasByStatusDead(List<Persona> personas);

    List<Report> getReportPersonasByStatusHerido();
    List<Report> getReportPersonasByStatusHerido(List<Persona> personas);

    List<Report> getReportPersonasByAge();
    List<Report> getReportPersonasByAge(List<Persona> personas);



}