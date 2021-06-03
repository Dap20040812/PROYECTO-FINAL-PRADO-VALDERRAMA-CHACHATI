package sample.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.logic.PersonaException;
import sample.logic.entities.Persona;
import sample.logic.entities.Roles;
import sample.logic.services.IPersonaServices;
import sample.logic.services.IReportServices;
import sample.logic.services.impl.PersonaService;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static javafx.scene.control.cell.ComboBoxTableCell.forTableColumn;

public class BasicScene extends Application {

    // Visual Properties
    private Scene scene;
    private TableView<Persona> personasTable;
    private TableView<Persona> personasIntro;
    private TextField nameInput;
    private TextField lastNameInput;
    private TextField ageInput;
    private TextField professionInput;
    private ComboBox<String> roleInput;
    private ComboBox<String> statusInput;
    private TextField descriptionInput;
    private Text name;
    private Text lastname;
    private Text age;
    private Text profession;
    private Text role;
    private Text status;
    private Text description;








    private Button addPersona;
    private Button deletePersona;
    private Button openReport;
    private Button Edit;
    private Button apply;
    private Group group;

    // Menu
    private MenuBar menuBar;
    private Map<String, MenuItem> fileMenuItems;

    // Logic Properties
    public static IPersonaServices personaServices;

    public IPersonaServices getPersonaServices() {
        return personaServices;
    }

    //CRUD -
    @Override
    public void start(Stage primaryStage) throws Exception {

        setUp();
        behavior(primaryStage);

        primaryStage.setTitle("No se por el momento");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void behavior(Stage stage) {
        this.personaServices = new PersonaService();

        personasTable.setItems((ObservableList<Persona>) this.personaServices.getAll());

        try {

            Persona pe = new Persona("m","c","23","d", Roles.ROLE1_PROTESTANTE,"Muerto","dafa");
            personaServices.insert(pe);

        } catch (PersonaException personaException) {
            personaException.printStackTrace();
        }

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

        Edit.setOnAction(e -> {

            personasTable.setEditable(true);
            apply.setOpacity(100);
            addPersona.setOpacity(0);
            deletePersona.setOpacity(0);


        });
        apply.setOnAction(e -> {

            personasTable.setEditable(false);
            apply.setOpacity(0);
            addPersona.setOpacity(100);
            deletePersona.setOpacity(100);


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
        setupText();
        BackgroundFill background_fill = new BackgroundFill(Color.GOLD,
                CornerRadii.EMPTY, Insets.EMPTY);

        Background background = new Background(background_fill);


        VBox hBox = new VBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput,lastNameInput, ageInput,professionInput,roleInput,statusInput,descriptionInput);

        HBox hBix = new HBox();
        hBix.setPadding(new Insets(10, 100, 10, 10));
        hBix.setSpacing(10);
        hBix.getChildren().addAll(addPersona,deletePersona,Edit,apply);

        VBox hBex = new VBox();
        hBex.setPadding(new Insets(10, 10, 10, 10));
        hBex.setSpacing(10);
        hBex.getChildren().addAll(name,lastname,age,profession,role,status,description);

        HBox hBbx = new HBox();
        hBbx.setPadding(new Insets(0, 0, 0, 0));
        hBbx.setSpacing(10);
        hBbx.getChildren().addAll(hBex,hBox);

        VBox hBkx = new VBox();
        hBkx.setPadding(new Insets(0, 0, 0, 0));
        hBkx.setSpacing(10);
        hBkx.getChildren().addAll(hBbx,hBix);




        //Layout
        HBox layout = new HBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(hBex,hBkx,personasTable);
        layout.setBackground(background);

        BorderPane layout2 = new BorderPane();
        layout2.setTop(menuBar);
        layout2.setCenter(layout);

        //Scene
        scene = new Scene(layout2, 400, 400);
    }

    private void setUpCrud() {

        BackgroundFill background_fill = new BackgroundFill(Color.GREENYELLOW,
                CornerRadii.EMPTY, Insets.EMPTY);

        Background background1 = new Background(background_fill);
        addPersona = new Button("Add");
        addPersona.setMinWidth(50);
        addPersona.setMinHeight(30);
        addPersona.setBackground(background1);
        addPersona.setFont(new Font("Impact",20));

        deletePersona = new Button("Delete");
        deletePersona.setMinWidth(50);
        deletePersona.setMinHeight(30);
        deletePersona.setBackground(background1);
        deletePersona.setFont(new Font("Impact",20));

        Edit = new Button("Edit");
        Edit.setMinWidth(50);
        Edit.setMinHeight(30);
        Edit.setBackground(background1);
        Edit.setFont(new Font("Impact",20));

        apply = new Button("Apply");
        apply.setMinWidth(50);
        apply.setMinHeight(30);
        apply.setBackground(background1);
        apply.setFont(new Font("Impact",20));
        apply.setOpacity(0);



        openReport = new Button("Open Report");
        openReport.setMinWidth(90);
    }

    private void setupText(){

        name = new Text("Name");
        name.setFont(new Font("Impact",20));

        lastname = new Text("Lastname");
        lastname.setFont(new Font("Impact",20));

        age = new Text("Age");
        age.setFont(new Font("Impact",20));

        profession = new Text("Profession");
        profession.setFont(new Font("Impact",20));

        role = new Text("Role");
        role.setFont(new Font("Impact",20));

        status = new Text("Status");
        status.setFont(new Font("Impact",20));

        description = new Text("Description");
        description.setFont(new Font("Impact",20));



    }
    private void setupInputs() {
        nameInput = new TextField();
        nameInput.setPromptText("name");
        nameInput.setMinWidth(10);


        lastNameInput = new TextField();
        lastNameInput.setPromptText("last name");
        lastNameInput.setMinWidth(10);

        ageInput = new TextField();
        ageInput.setPromptText("age");
        ageInput.setMinWidth(10);

        professionInput = new TextField();
        professionInput.setPromptText("profession");
        professionInput.setMinWidth(10);

        roleInput = new ComboBox<String>();
        roleInput.setPromptText("role");
        roleInput.getItems().addAll("Protestante-Civil","Protestante-Activista","Politico","Agentes de la ley","Comunista");

        statusInput = new ComboBox<String>();
        statusInput.setPromptText("status");
        statusInput.getItems().addAll("Muerto","Vivo","Herido","Invalido como miguel");

        descriptionInput = new TextField();
        descriptionInput.setPromptText("description");
        descriptionInput.setMinWidth(30);
        descriptionInput.setMinHeight(100);


    }

    private void setupTable() {

        //Name column
        TableColumn<Persona, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMaxWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        //Name column
        TableColumn<Persona, String> lastNameColumn = new TableColumn<>("LastName");
        lastNameColumn.setMaxWidth(200);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        //Name column
        TableColumn<Persona, String> ageColumn = new TableColumn<>("Age");
        ageColumn.setMaxWidth(200);
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        TableColumn<Persona, String> professionColumn = new TableColumn<>("Profession");
        professionColumn.setMaxWidth(700);
        professionColumn.setCellValueFactory(new PropertyValueFactory<>("profession"));
        professionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Persona, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMaxWidth(700);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Protestante-Civil","Protestante-Activista","Politico","Agentes de la ley","Comunista"));

        TableColumn<Persona, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMaxWidth(700);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Muerto","Vivo","Herido","Invalido como miguel"));

        TableColumn<Persona, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMaxWidth(700);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        //Table
        personasTable = new TableView<>();
        personasTable.setStyle("-fx-background-color: blue");
        personasTable.getColumns().addAll(nameColumn, lastNameColumn, ageColumn,professionColumn,roleColumn,statusColumn,descriptionColumn);

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
