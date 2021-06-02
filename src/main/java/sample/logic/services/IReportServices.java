package sample.logic.services;

import sample.logic.entities.*;

import java.util.List;
import java.util.Map;

public interface IReportServices {

    public List<Report> getReportPersonasByRole();
    public List<Report> getReportPersonasByRole(List<Persona> personas);

    /*public Report getCountOfVictims();*/

}