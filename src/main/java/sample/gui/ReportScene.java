package sample.gui;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.logic.entities.Persona;
import sample.logic.entities.Report;
import sample.logic.services.IReportServices;
import sample.logic.services.impl.ReportServices;

import java.util.List;
import java.util.Map;

public class ReportScene {


    // Visual Properties
    private Stage stage;
    private Label label;
    private Scene scene;
    private TableView reportTable;
    private Label Rol1;
    private Label Rol2;
    private Label Rol3;
    private Label Rol4;
    private Label Rol5;


    // Logic properties
    private IReportServices reportServices;

    public ReportScene() {
        stage = new Stage();

        setUp();
        behavior(stage);

        stage.setTitle("Report");
        stage.setScene(scene);
        stage.show();
    }

    private void setUp() {

        setUpTableReport();
        BackgroundFill background_fill = new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY);

        Background background = new Background(background_fill);
        HBox hBox = new HBox();

        VBox layout = new VBox(10);
        layout.getChildren().add(reportTable);
        layout.setBackground(background);

        scene = new Scene(layout, 200, 200);
    }

    private void behavior(Stage stage) {

        this.reportServices = new ReportServices();
        reportTable.setItems((ObservableList<Report>) this.reportServices.getReportPersonasByRole());

    }
    private void setText(){
        BackgroundFill background_fill = new BackgroundFill(Color.GOLD,
                CornerRadii.EMPTY, Insets.EMPTY);

        Background background1 = new Background(background_fill);

        BackgroundFill background_fill1 = new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY);

        Background background11 = new Background(background_fill1);

        Rol1 = new Label("Protestante-Civil");
        Rol1.setFont(new Font("Impact",30));
        Rol1.setBackground(background1);
        Rol1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Rol2 = new Label("Protestante-Activista");
        Rol2.setFont(new Font("Impact",30));
        Rol2.setBackground(background1);
        Rol2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Rol3 = new Label("Politico");
        Rol3.setFont(new Font("Impact",30));
        Rol3.setBackground(background1);
        Rol3.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Rol4 = new Label("Agentes de la ley");
        Rol4.setFont(new Font("Impact",30));
        Rol4.setBackground(background1);
        Rol4.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Rol5 = new Label("Comunista");
        Rol5.setFont(new Font("Impact",30));
        Rol5.setBackground(background1);
        Rol5.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
    }

    private void setUpTableReport() {

        // Role Name

        TableColumn<Report, String> RoleNameColumn = new TableColumn<>("Rol");
        RoleNameColumn.setMaxWidth(200);
        RoleNameColumn.setCellValueFactory(new PropertyValueFactory<>("criteria"));

        // Count
        TableColumn<Report, Integer> countColumn = new TableColumn<>("Count");
        countColumn.setMaxWidth(200);
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));


        reportTable = new TableView<>();
        reportTable.getColumns().addAll(RoleNameColumn, countColumn);

    }
}