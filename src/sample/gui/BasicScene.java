package sample.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.logic.PersonaException;
import sample.logic.entities.Persona;
import sample.logic.entities.ProfessionEnum;
import sample.logic.persistence.impl.PersonaPersistence;
import sample.logic.services.IPersonaServices;
import sample.logic.services.impl.PersonaService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BasicScene extends Application {

    // Visual Properties
    private Scene scene;
    private TableView<Persona> personasTable;
    private TextField nameInput;
    private TextField lastNameInput;
    private TextField ageInput;
    private TextField professionInput;
    private ComboBox<String> roleInput;
    private ComboBox<String> statusInput;
    private TextField descriptionInput;


    private Button addPersona;
    private Button deletePersona;
    private Button openReport;

    // Menu
    private MenuBar menuBar;
    private Map<String, MenuItem> fileMenuItems;

    // Logic Properties
    private IPersonaServices personaServices;

    //CRUD -
    @Override
    public void start(Stage primaryStage) throws Exception {

        setUp();
        behavior(primaryStage);

        primaryStage.setTitle("Sabana Example");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void behavior(Stage stage) {
        this.personaServices = new PersonaService();

        personasTable.setItems((ObservableList<Persona>) this.personaServices.getAll());

        addPersona.setOnAction(e -> {

            try {

                Persona p = new Persona(nameInput.getText(), lastNameInput.getText(), ageInput.getText(),professionInput.getText(),roleInput.getValue(),statusInput.getValue(),descriptionInput.getText());
                this.personaServices.insert(p);
                nameInput.clear();
                lastNameInput.clear();
                ageInput.clear();
                professionInput.clear();
            } catch (PersonaException personaException) {
                personaException.printStackTrace();
            }
        });

        deletePersona.setOnAction(e -> {

            this.personaServices.delete(personasTable.getSelectionModel().getSelectedItems());
        });

        fileMenuItems.get("Export").setOnAction(e -> {
            try {
                this.personaServices.export();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        fileMenuItems.get("Import").setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select personas file");
            File file = fileChooser.showOpenDialog(stage);
            if (file == null) {
                System.out.println("No file");
            } else {
                try {
                    this.personaServices.importPersonas(file);
                    this.personaServices.getAll().stream().forEach(p -> System.out.println(p));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        openReport.setOnAction(e -> {
            new ReportScene();
        });
    }

    private void setUp() {
        setupTable();
        setupInputs();
        setupMenu();
        setUpCrud();

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, lastNameInput, ageInput,professionInput,roleInput,statusInput,descriptionInput, addPersona, deletePersona, openReport);

        //Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(personasTable, hBox);

        BorderPane layout2 = new BorderPane();
        layout2.setTop(menuBar);
        layout2.setCenter(layout);

        //Scene
        scene = new Scene(layout2, 400, 400);
    }

    private void setUpCrud() {
        addPersona = new Button("Add");
        addPersona.setMinWidth(30);

        deletePersona = new Button("Delete");
        deletePersona.setMinWidth(30);

        openReport = new Button("Open Report");
        openReport.setMinWidth(90);
    }

    private void setupInputs() {
        nameInput = new TextField();
        nameInput.setPromptText("name");
        nameInput.setMinWidth(30);

        lastNameInput = new TextField();
        lastNameInput.setPromptText("last name");
        lastNameInput.setMinWidth(30);

        ageInput = new TextField();
        ageInput.setPromptText("age");
        ageInput.setMinWidth(30);

        professionInput = new TextField();
        professionInput.setPromptText("profession");
        professionInput.setMinWidth(30);

        roleInput = new ComboBox<String>();
        roleInput.setPromptText("role");
        roleInput.getItems().addAll("Protestante-Civil","Protestante-Activista","Politico","Agentes de la ley","Comunista");

        statusInput = new ComboBox<String>();
        statusInput.setPromptText("status");
        statusInput.getItems().addAll("Muerto","Vivo","Herido","Invalido como miguel");

        descriptionInput = new TextField();
        descriptionInput.setPromptText("description");
        descriptionInput.setMinWidth(100);


    }

    private void setupTable() {
        //Name column
        TableColumn<Persona, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMaxWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Name column
        TableColumn<Persona, String> lastNameColumn = new TableColumn<>("LastName");
        lastNameColumn.setMaxWidth(200);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        //Name column
        TableColumn<Persona, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setMaxWidth(200);
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Persona, String> professionColumn = new TableColumn<>("Profession");
        professionColumn.setMaxWidth(700);
        professionColumn.setCellValueFactory(new PropertyValueFactory<>("profession"));

        TableColumn<Persona, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMaxWidth(700);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<Persona, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMaxWidth(700);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));


        //Table
        personasTable = new TableView<>();
        personasTable.getColumns().addAll(nameColumn, lastNameColumn, ageColumn,professionColumn,roleColumn,statusColumn);
    }

    private void setupMenu() {

        Menu fileMenu = new Menu("File");

        fileMenuItems = new HashMap<>();
        fileMenuItems.put("Import", new MenuItem("Import"));
        fileMenuItems.put("Export", new MenuItem("Export"));

        fileMenu.getItems().add(fileMenuItems.get("Import"));
        fileMenu.getItems().add(fileMenuItems.get("Export"));

        menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);


    }

    public static void main(String[] args) {
        launch(args);
    }


}
