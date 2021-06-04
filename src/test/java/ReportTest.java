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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class ReportTest {

    private static IPersonaServices personaServices;
    private static IReportServices reportServices;


    @BeforeAll
    public static void setUp() throws IOException, ClassNotFoundException {
    }



    @Test
    @DisplayName("GIVEN a employee by salary WHEN try to register THEN success")
    public void shouldRegisterEmployee() throws PersonaException, IOException, ClassNotFoundException {

        IPersonaPersistence personaPersistence = new PersonaPersistence();

        System.out.println(personaPersistence.read("BaseDeDatos.MJD"));



    }

}

