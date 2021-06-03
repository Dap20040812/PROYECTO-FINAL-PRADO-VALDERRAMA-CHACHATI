import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.logic.PersonaException;
import sample.logic.entities.Persona;
import sample.logic.entities.Roles;
import sample.logic.services.IPersonaServices;
import sample.logic.services.IReportServices;
import sample.logic.services.impl.PersonaService;
import sample.logic.services.impl.ReportServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class ReportTest {

    private static IPersonaServices personaServices;
    private static IReportServices reportServices;


    @BeforeAll
    public static void setUp() {
        personaServices = new PersonaService();
        try {

            Persona pe = new Persona("m", "c", "23", "d", Roles.ROLE1_PROTESTANTE, "Muerto", "dafa");
            personaServices.insert(pe);

        } catch (PersonaException personaException) {
            personaException.printStackTrace();
        }
        reportServices = new ReportServices();
    }



    @Test
    @DisplayName("GIVEN a employee by salary WHEN try to register THEN success")
    public void shouldRegisterEmployee() {

        assertEquals(1, personaServices.getAll().size());
        assertEquals(1,reportServices.getReportPersonasByRole().get(0).getCount());

    }

}

