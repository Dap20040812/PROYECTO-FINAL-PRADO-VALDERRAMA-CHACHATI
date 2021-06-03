package sample.logic.services.impl;

import javafx.collections.FXCollections;
import sample.logic.PersonaException;
import sample.logic.entities.Exportable;
import sample.logic.entities.Persona;
import sample.logic.persistence.IExport;
import sample.logic.persistence.IPersonaPersistence;
import sample.logic.persistence.impl.Export;
import sample.logic.persistence.impl.PersonaPersistence;
import sample.logic.services.IPersonaServices;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PersonaService implements IPersonaServices {

    private IPersonaPersistence personaPersistence;
    private IExport export;
    private List<Persona> personas;

    public PersonaService() {
        this.personas = FXCollections.observableArrayList();
        try {
            this.personaPersistence = new PersonaPersistence();
            this.export = new Export();
            //this.personas.addAll(this.personaPersistence.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Persona> getAll() {
        return this.personas;
    }

    @Override
    public Persona getById(UUID id) {
        return null;
    }

    @Override
    public Persona insert(Persona persona) {
        personas.add(persona);
        return persona;
    }

    @Override
    public void delete(List<Persona> personasToDelete) {

        personasToDelete.forEach(this.personas::remove);
    }

    @Override
    public void export() throws Exception {
        List<Exportable> e = new ArrayList<>();
        this.personas.stream().forEach(p -> e.add(p));
        this.export.export(e, Exportable.CSV);
    }

    @Override
    public List<Persona> importPersonas(File file) throws Exception {
        List<Persona> importedPersonas = new ArrayList<>();
        List<String> read = this.personaPersistence.importPersonas(file);

        for (String line : read) {
            String[] tokens = line.split(Exportable.CSV.toString());
            Persona persona = new Persona(tokens[0], tokens[1], tokens[2], tokens[3],tokens[4],tokens[5],tokens[6]);
            importedPersonas.add(persona);
            this.insert(persona);
        }

        return importedPersonas;
    }

    @Override
    public void update(String name , String LastName , String age, String profession , String role , String status , String description, Persona persona) throws PersonaException {

        persona.setName(name);
        persona.setProfession(profession);
        persona.setLastName(LastName);
        persona.setAge(age);
        persona.setRole(role);
        persona.setStatus(status);
        persona.setDescription(description);
    }
}
