package sample.logic.persistence.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.logic.entities.Exportable;
import sample.logic.entities.Persona;
import sample.logic.persistence.IPersonaPersistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class MyObjectOutputStream extends ObjectOutputStream {

    // Constructor of ths class
    // 1. Default
    MyObjectOutputStream() throws IOException
    {

        // Super keyword refers to parent class instance
        super();
    }

    // Constructor of ths class
    // 1. Parameterized constructor
    MyObjectOutputStream(OutputStream o) throws IOException
    {
        super(o);
    }

    // Method of this class
    public void writeStreamHeader() throws IOException
    {
        return;
    }
}

public class PersonaPersistence implements IPersonaPersistence {

    private static final String PERSONAS_FILE_PATH = "BaseDeDatos.MJD";
    private static final String PERSONAS_FILE_EXTENSION = "MJD";

    private static File f = new File(PERSONAS_FILE_PATH);


    public PersonaPersistence() throws IOException {
        File file = new File(PERSONAS_FILE_PATH);
        if (file.createNewFile()) {
            System.out.println("The file " + file.getName() + " was created");
        }
    }

    /**
     * Guarda a la persona en el archivo de persistencia
     * @param persona
     * @throws IOException
     */
    @Override
    public void save(Persona persona) throws IOException {

        boolean status = false;

        // If customer is not present
        if (persona != null) {
            // try block to check for exception
            try {

                // Initially assigning the object null to
                // avoid GC involvement
                FileOutputStream fos = null;

                // Creating an new FileOutputStream object
                fos = new FileOutputStream(
                        PERSONAS_FILE_PATH, true);

                // If there is nothing to be write onto file
                if (f.length() == 0) {
                    ObjectOutputStream oos
                            = new ObjectOutputStream(fos);
                    oos.writeObject(persona);
                    oos.close();
                }

                // There is content in file to be write on
                else {

                    MyObjectOutputStream oos = null;
                    oos = new MyObjectOutputStream(fos);
                    oos.writeObject(persona);

                    // Closing the FileOutputStream object
                    // to releae memory resources
                    oos.close();
                }

                // Closing the File class object to avoid
                // read-write
                fos.close();
            }

            // Catch block to handle the exceptions
            catch (Exception e) {

                // Print the exception along with the
                // display message
                System.out.println("Error Ocurred" + e);
            }

            // Change the flag status
            status = true;
        }
    }

    /**
     * lee el archivo de persistencia
     * @param path
     * @return la lista de personas del guardadas en el archivo de persistencia
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Persona> read(String path) throws IOException, ClassNotFoundException {


        boolean status = false;
        List<Persona> result = FXCollections.observableArrayList();

        // Try block to check for exceptions
        try {

            // Creating new file using File object above
            f.createNewFile();
        }

        // Catch block to handle the exception
        catch (Exception e) {
        }

        // If the file is empty
        if (f.length() != 0) {

            try {

                // If file doesn't exists
                FileInputStream fis = null;

                fis = new FileInputStream(PERSONAS_FILE_PATH);
                ObjectInputStream ois
                        = new ObjectInputStream(fis);

                Persona p = null;

                while (fis.available() != 0) {
                    p = (Persona)ois.readObject();
                    result.add(p);
                    // Print customer name and account
                    // number
                    System.out.println(p.getName());
                }

                // Closing the connection to release memory
                // resources using close() method
                ois.close();
                fis.close();

                // Once all connection are closed after the
                // desired action change the flag state
                status = true;
            }

            // Catch block to handle the exception
            catch (Exception e) {

                // Print the exception on the console
                // along with display message
                System.out.println("Error Ocurred" + e);

                // Exception encounterede line is also
                // displayed on console using the
                // printStackTrace() method
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * importa a las personas desde un archivo de persistencia
     * @param file
     * @return la lista de datos en string
     * @throws Exception
     */
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

}
