package sample.logic.persistence.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.logic.entities.Exportable;
import sample.logic.entities.Persona;
import sample.logic.persistence.IPersonaPersistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaPersistence implements IPersonaPersistence {

    private static final String PERSONAS_FILE_PATH = "BaseDeDatos.MJD";
    private static final String PERSONAS_FILE_EXTENSION = "MJD";

    public PersonaPersistence() throws IOException {
        File file = new File(PERSONAS_FILE_PATH);
        if (file.createNewFile()) {
            System.out.println("The file " + file.getName() + " was created");
        }
    }

    @Override
    public void save(Persona persona) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(PERSONAS_FILE_PATH, true);
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        out.writeObject(persona);
        out.close();
    }

    @Override
    public List<Persona> read(String path) throws IOException, ClassNotFoundException {


        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fis);
        return readPersonasWithMJDExtension(in);
    }


    @Override
    public List<String> importPersonas(File file) throws Exception {


        List<String> personas = new ArrayList<>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        br.readLine(); // ignore header
        String line = br.readLine();
        while (line != null) {
            personas.add(line);
            line = br.readLine();
        }

        br.close();
        return personas;
    }

    private List<Persona> readPersonasWithMJDExtension(ObjectInputStream in) throws IOException, ClassNotFoundException {

        List<Persona> result = FXCollections.observableArrayList();

        try {
            while (true) {
                result.add((Persona) in.readObject());
            }
        } catch (EOFException | NullPointerException e) {
            System.out.println("Reached end of file");
        } finally {
            in.close();
        }

        return result;
    }
}
