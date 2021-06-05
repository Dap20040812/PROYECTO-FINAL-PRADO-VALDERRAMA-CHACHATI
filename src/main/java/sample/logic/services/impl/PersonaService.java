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

    public PersonaService() throws IOException, ClassNotFoundException {
        this.personas = FXCollections.observableArrayList();
        personaPersistence = new PersonaPersistence();
        if(personaPersistence.read("BaseDeDatos.MJD") != null)
        {
            this.personas = personaPersistence.read("BaseDeDatos.MJD");

        }
        try {
            this.personaPersistence = new PersonaPersistence();
            this.export = new Export();
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

    /**
     * Inserta una persona en la lista de personas y la agraga en la persitencia
     * @param persona
     * @return la persona guardada
     */
    @Override
    public Persona insert(Persona persona) {
        personas.add(persona);
        try {
            this.personaPersistence.save(persona);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persona;
    }

    /**
     * Elimina la persona de la lista de personas
     * @param personasToDelete
     */
    @Override
    public void delete(List<Persona> personasToDelete) {

        personasToDelete.forEach(this.personas::remove);
    }

    /**
     * Exporta la lista de personas a un archivo csv
     * @throws Exception
     */
    @Override
    public void export() throws Exception {
        List<Exportable> e = new ArrayList<>();
        this.personas.stream().forEach(p -> e.add(p));
        this.export.export(e, Exportable.CSV);
    }

    /**
     * importa a las personas de un archivo csv
     * @param file
     * @return la lista de personas importada
     * @throws Exception
     */
    @Override
    public List<Persona> importPersonas(File file) throws Exception {
        List<Persona> importedPersonas = new ArrayList<>();
        List<String> read = this.personaPersistence.importPersonas(file);

        for (String line : read) {
            String[] tokens = line.split(Exportable.CSV.toString());
            Persona persona = new Persona(tokens[0], tokens[1], tokens[2], tokens[3],tokens[4],tokens[5],tokens[6],tokens[7]);
            importedPersonas.add(persona);
            this.insert(persona);
        }

        return importedPersonas;
    }

    /**
     * actualiza a una persona de la lista de personas
     * @param name
     * @param LastName
     * @param age
     * @param profession
     * @param role
     * @param status
     * @param description
     * @param persona
     * @throws PersonaException
     */
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
