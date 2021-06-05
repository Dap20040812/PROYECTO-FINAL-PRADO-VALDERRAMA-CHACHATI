import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.logic.PersonaException;
import sample.logic.entities.Persona;
import sample.logic.entities.Roles;
import sample.logic.persistence.IPersonaPersistence;
import sample.logic.persistence.impl.PersonaPersistence;
import sample.logic.services.IPersonaServices;
import sample.logic.services.IReportServices;
import sample.logic.services.impl.PersonaService;
import sample.logic.services.impl.ReportServices;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class ReportTest {

    private static IPersonaServices personaServices;private static Persona p1;
    private static Persona p2;

    @BeforeAll
    public static void setUp() throws IOException, ClassNotFoundException, PersonaException {

        personaServices = new PersonaService();
        p1 = new Persona("Miguel","Chachati","28","No tiene","Comunista","Invalido como Miguel","Increible","/2.png");
        p2 = new Persona("Miguel","Chachati","24","No tiene","Comunista","Invalido como Miguel","Increible","/2.png");

    }


    @Test
    public void shouldAddPersona() {

        personaServices.insert(p1);
        personaServices.insert(p2);
        assertTrue(personaServices.getAll().contains(p1));
        assertTrue(personaServices.getAll().contains(p2));

    }


    @Test
    public void shouldUpdatePersona() throws PersonaException {


        Persona p = new Persona("d","e","23","45","Politico","MUerto","d","/1.png");
        personaServices.update("d","e","23","45","Politico","MUerto","d",personaServices.getAll().get(0));
        assertEquals(p.getName(),personaServices.getAll().get(0).getName());
        assertEquals(p.getLastName(),personaServices.getAll().get(0).getLastName());
        assertEquals(p.getAge(),personaServices.getAll().get(0).getAge());
        assertEquals(p.getRole(),personaServices.getAll().get(0).getRole());


    }

    @Test
    public void shouldImportPersonas() throws Exception {


        File f = new File("directorio-2021-JUNE-4.csv");
        System.out.println(personaServices.importPersonas(f));


    }





}

