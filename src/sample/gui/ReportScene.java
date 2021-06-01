package sample.gui;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    // Logic properties
    private IReportServices reportServices;

    public ReportScene() {
        stage = new Stage();

        setUp();
        //behavior(stage);

        stage.setTitle("Report");
        stage.setScene(scene);
        stage.show();
    }

    private void setUp() {

        setUpTableReport();
        VBox layout = new VBox(10);
        layout.getChildren().add(reportTable);
        scene = new Scene(layout, 200, 200);
    }

    /*private void behavior(Stage stage) {

        this.reportServices = new ReportServices();
        Map<String, Report> reportPersonasByProfession = this.reportServices.getReportPersonasByProfession();
        Report victims = this.reportServices.getCountOfVictims();

        reportTable.setItems((ObservableList<Report>) reportPersonasByProfession.values());

    }*/

    private void setUpTableReport() {

        // Profession Name
        TableColumn<Report, String> professionNameColumn = new TableColumn<>();
        professionNameColumn.setMaxWidth(200);
        professionNameColumn.setCellValueFactory(new PropertyValueFactory("criteria"));

        // Count
        TableColumn<Report, Integer> countColumn = new TableColumn<>();
        countColumn.setMaxWidth(200);
        countColumn.setCellValueFactory(new PropertyValueFactory("count"));

        reportTable = new TableView<>();
        reportTable.getColumns().addAll(professionNameColumn, countColumn);

    }
}