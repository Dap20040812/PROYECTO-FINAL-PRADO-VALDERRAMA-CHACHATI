package sample.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.logic.PersonaException;
import sample.logic.entities.Persona;
import sample.logic.entities.Roles;
import sample.logic.services.IPersonaServices;
import sample.logic.services.impl.PersonaService;
import javafx.scene.image.Image;
import javafx.scene.control.Label;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.nio.file.Paths;
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
    private Label name1;
    private Label name2;
    private Label lastname1;
    private Label lastname2;
    private Label age1;
    private Label age2;
    private Label profession1;
    private Label profession2;
    private Label role1;
    private Label role2;
    private Label status1;
    private Label status2;
    private Label description1;
    private Label description2;
    private Label photo;
    private Label photo1;
    private Label photo2;
    private MediaPlayer mediaPlayer;










    private Button addPersona;
    private Button deletePersona;
    private Button openReport;
    private Button update;
    private Button Edit;
    private Button apply;
    private Button view;
    private StackPane group;
    private ImageView imageView;
    private ImageView imageView1;
    private ImageView imageView2;

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

    private void behavior(Stage stage) throws IOException, ClassNotFoundException {
        personaServices = new PersonaService();

        personasTable.setItems((ObservableList<Persona>) personaServices.getAll());

        addPersona.setOnAction(e -> {

            try {

                Persona p = new Persona(nameInput.getText(), lastNameInput.getText(), ageInput.getText(),professionInput.getText(),roleInput.getValue(),statusInput.getValue(),descriptionInput.getText());
                this.personaServices.insert(p);
                nameInput.clear();
                lastNameInput.clear();
                ageInput.clear();
                professionInput.clear();
                descriptionInput.clear();
            } catch (PersonaException personaException) {
                personaException.printStackTrace();
            }
        });

        deletePersona.setOnAction(e -> {

            this.personaServices.delete(personasTable.getSelectionModel().getSelectedItems());
        });

        view.setOnAction(e -> {


            String s = "22.mp3";
            Media h = new Media(Paths.get(s).toUri().toString());
            mediaPlayer = new MediaPlayer(h);
            mediaPlayer.stop();
            name2.setText(personasTable.getSelectionModel().getSelectedItem().getName());
            lastname2.setText(personasTable.getSelectionModel().getSelectedItem().getLastName());
            age2.setText(personasTable.getSelectionModel().getSelectedItem().getAge());
            profession2.setText(personasTable.getSelectionModel().getSelectedItem().getProfession());
            role2.setText(personasTable.getSelectionModel().getSelectedItem().getRole());
            status2.setText(personasTable.getSelectionModel().getSelectedItem().getStatus());
            description2.setText(personasTable.getSelectionModel().getSelectedItem().getDescription());
            if(personasTable.getSelectionModel().getSelectedItem().getRole().equals("Comunista"))
            {
                mediaPlayer.setVolume(1);
                mediaPlayer.play();
            }

        });

        update.setOnAction(e -> {

            nameInput.setText(personasTable.getSelectionModel().getSelectedItem().getName());
            lastNameInput.setText(personasTable.getSelectionModel().getSelectedItem().getLastName());
            ageInput.setText(personasTable.getSelectionModel().getSelectedItem().getAge());
            professionInput.setText(personasTable.getSelectionModel().getSelectedItem().getProfession());
            descriptionInput.setText(personasTable.getSelectionModel().getSelectedItem().getDescription());
            apply.setOpacity(100);
            addPersona.setOpacity(0);
            deletePersona.setOpacity(0);
            view.setOpacity(0);


        });
        apply.setOnAction(e -> {

            try {
                personaServices.update(nameInput.getText(),lastNameInput.getText(),ageInput.getText(),professionInput.getText(),roleInput.getValue(),statusInput.getValue(),descriptionInput.getText(),personasTable.getSelectionModel().getSelectedItem());
            } catch (PersonaException personaException) {
                personaException.printStackTrace();
            }
            apply.setOpacity(0);
            addPersona.setOpacity(100);
            deletePersona.setOpacity(100);
            view.setOpacity(100);
            nameInput.clear();
            lastNameInput.clear();
            ageInput.clear();
            professionInput.clear();
            descriptionInput.clear();
            personasTable.refresh();

            view.fire();


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
        setImage();
        BackgroundFill background_fill = new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY);

        Background background = new Background(background_fill);


        VBox hBox = new VBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput,lastNameInput, ageInput,professionInput,roleInput,statusInput,descriptionInput);

        VBox hBnx = new VBox();
        hBnx.setPadding(new Insets(10, 10, 10, 10));
        hBnx.setSpacing(10);
        hBnx.getChildren().addAll(name1,name2,lastname1,lastname2,age1,age2,profession1,profession2,role1,role2,status1,status2,description1,description2);

        HBox hBjx = new HBox();
        hBjx.setPadding(new Insets(10, 10, 10, 10));
        hBjx.setSpacing(10);
        hBjx.getChildren().addAll(hBnx,photo);


        HBox hBix = new HBox();
        hBix.setPadding(new Insets(10, 30, 10, 10));
        hBix.setSpacing(10);
        hBix.getChildren().addAll(addPersona,deletePersona,view,openReport,update,apply);

        VBox hBex = new VBox();
        hBex.setPadding(new Insets(10, 10, 10, 10));
        hBex.setSpacing(10);
        hBex.getChildren().addAll(name,lastname,age,profession,role,status,description);

        HBox hBqx = new HBox();
        hBqx.setPadding(new Insets(80, 0, 0, 30));
        hBqx.setSpacing(10);
        hBqx.getChildren().addAll(imageView2);

        HBox hBbx = new HBox();
        hBbx.setPadding(new Insets(0, 0, 0, 0));
        hBbx.setSpacing(10);
        hBbx.getChildren().addAll(hBex,hBox,hBqx);

        VBox hBlx = new VBox();
        hBlx.setPadding(new Insets(80, 0, 0, 0));
        hBlx.setSpacing(10);
        hBlx.getChildren().addAll(photo1);

        VBox hBkx = new VBox();
        hBkx.setPadding(new Insets(0, 0, 0, 0));
        hBkx.setSpacing(10);
        hBkx.getChildren().addAll(hBbx,hBix,hBlx);

        //Layout
        HBox layout = new HBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(hBex,hBkx,personasTable,hBjx);
        layout.setBackground(background);

        BorderPane layout2 = new BorderPane();
        layout2.setTop(menuBar);
        layout2.setCenter(layout);

        //Scene
        scene = new Scene(layout2, 400, 400);




    }

    private void setUpCrud() {

        BackgroundFill background_fill = new BackgroundFill(Color.GOLD,
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

        view = new Button("View");
        view.setMinWidth(50);
        view.setMinHeight(30);
        view.setBackground(background1);
        view.setFont(new Font("Impact",20));

        apply = new Button("Apply");
        apply.setMinWidth(50);
        apply.setMinHeight(30);
        apply.setBackground(background1);
        apply.setFont(new Font("Impact",20));
        apply.setOpacity(0);

        openReport = new Button("Open Report");
        openReport.setMinWidth(50);
        openReport.setMinHeight(30);
        openReport.setBackground(background1);
        openReport.setFont(new Font("Impact",20));

        update = new Button("Update");
        update.setMinWidth(50);
        update.setMinHeight(30);
        update.setBackground(background1);
        update.setFont(new Font("Impact",20));
    }
    private void setImage()  {


        Image image = new Image("/2.png");
        Image image1 = new Image("/3.png");
        Image image2 = new Image("/12.gif");
        imageView = new ImageView(image);
        imageView1 = new ImageView(image1);
        imageView2 = new ImageView(image2);
        photo = new Label();
        photo.setGraphic(imageView);
        photo.setBorder(new Border(new BorderStroke(Color.GOLD,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        photo1 = new Label();
        photo1.setGraphic(imageView1);









    }


    private void setupText(){

        BackgroundFill background_fill = new BackgroundFill(Color.GOLD,
                CornerRadii.EMPTY, Insets.EMPTY);

        Background background1 = new Background(background_fill);

        BackgroundFill background_fill1 = new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY);

        Background background11 = new Background(background_fill1);

        name = new Text("Name:");
        name.setFont(new Font("Impact",20));
        name.setFill(Color.GOLD);

        lastname = new Text("Lastname:");
        lastname.setFont(new Font("Impact",20));
        lastname.setFill(Color.GOLD);

        age = new Text("Age:");
        age.setFont(new Font("Impact",20));
        age.setFill(Color.GOLD);

        profession = new Text("Profession:");
        profession.setFont(new Font("Impact",20));
        profession.setFill(Color.GOLD);

        role = new Text("Role:");
        role.setFont(new Font("Impact",20));
        role.setFill(Color.GOLD);

        status = new Text("Status:");
        status.setFont(new Font("Impact",20));
        status.setFill(Color.GOLD);

        description = new Text("Description:");
        description.setFont(new Font("Impact",20));
        description.setFill(Color.GOLD);

        name1 = new Label("Name: ");
        name1.setFont(new Font("Impact",30));
        name1.setBackground(background1);
        name1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        name2 = new Label("");
        name2.setFont(new Font("Constantia",30));
        name2.setBackground(background11);
        name2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));


        lastname1 = new Label("LastName:");
        lastname1.setFont(new Font("Impact",30));
        lastname1.setBackground(background1);
        lastname1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        lastname2 = new Label("");
        lastname2.setFont(new Font("Constantia",30));
        lastname2.setBackground(background11);
        lastname2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        age1 = new Label("Age:");
        age1.setFont(new Font("Impact",30));
        age1.setBackground(background1);
        age1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        age2 = new Label("");
        age2.setFont(new Font("Constantia",30));
        age2.setBackground(background11);
        age2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        profession1 = new Label("Profession:");
        profession1.setFont(new Font("Impact",30));
        profession1.setBackground(background1);
        profession1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        profession2 = new Label("");
        profession2.setFont(new Font("Constantia",30));
        profession2.setBackground(background11);
        profession2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        role1 = new Label("Role:");
        role1.setFont(new Font("Impact",30));
        role1.setBackground(background1);
        role1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        role2 = new Label("");
        role2.setFont(new Font("Constantia",30));
        role2.setBackground(background11);
        role2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));


        status1 = new Label("Status:");
        status1.setFont(new Font("Impact",30));
        status1.setBackground(background1);
        status1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        status2 = new Label("");
        status2.setFont(new Font("Constantia",30));
        status2.setBackground(background11);
        status2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        description2 = new Label("");
        description2.setFont(new Font("Constantia",30));
        description2.setBackground(background11);
        description2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        description1 = new Label("Description");
        description1.setFont(new Font("Impact",30));
        description1.setBackground(background1);
        description1.setAlignment(Pos.CENTER);
        description1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));



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
        nameColumn.setMinWidth(100);
        nameColumn.setMaxWidth(700);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setStyle("-fx-background-color: rgba(255,255,000,0.7); -fx-border-width: 1; -fx-border-color: Black;");


        //Name column
        TableColumn<Persona, String> lastNameColumn = new TableColumn<>("LastName");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setMaxWidth(700);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setStyle("-fx-background-color: rgba(250,210,001,0.7);-fx-border-width: 1; -fx-border-color: Black;");

        //Name column
        TableColumn<Persona, String> ageColumn = new TableColumn<>("Age");
        ageColumn.setMinWidth(80);
        ageColumn.setMaxWidth(200);
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ageColumn.setStyle("-fx-background-color: rgba(255,255,000,0.7); -fx-border-width: 1; -fx-border-color: Black;");


        TableColumn<Persona, String> professionColumn = new TableColumn<>("Profession");
        professionColumn.setMaxWidth(700);
        professionColumn.setCellValueFactory(new PropertyValueFactory<>("profession"));
        professionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        professionColumn.setStyle("-fx-background-color: rgba(250,210,001,0.7);-fx-border-width: 1; -fx-border-color: Black;");

        TableColumn<Persona, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMaxWidth(700);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Protestante-Civil","Protestante-Activista","Politico","Agentes de la ley","Comunista"));
        roleColumn.setStyle("-fx-background-color: rgba(255,255,000,0.7); -fx-border-width: 1; -fx-border-color: Black;");

        TableColumn<Persona, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMaxWidth(700);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Muerto","Vivo","Herido","Invalido como miguel"));
        statusColumn.setStyle("-fx-background-color: rgba(255,255,000,0.7);-fx-border-width: 1; -fx-border-color: Black;");

        TableColumn<Persona, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMaxWidth(700);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setStyle("-fx-background-color: rgba(255,255,000,0.7); -fx-border-width: 1; -fx-border-color: Black;");


        //Table
        personasTable = new TableView<>();
        personasTable.setStyle("-fx-selection-bar: Black; -fx-selection-bar-non-focused: Gray; -fx-background-color: Gold; -fx-padding: 5;-fx-font-weight:bold; -fx-font-size:15px;" +
                "    -fx-font-size:15px;");
        personasTable.getColumns().addAll(nameColumn, lastNameColumn,ageColumn);

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
