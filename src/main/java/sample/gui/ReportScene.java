package sample.gui;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private Label nada;
    private Label NUm;
    private Label NUm1;
    private Label NUm2;
    private Label NUm3;
    private Label NUm4;
    private Label NUm5;
    private Label Dead;
    private Label Dead1;
    private Label Dead2;
    private Label Dead3;
    private Label Dead4;
    private Label Dead5;
    private Label Herido;
    private Label Herido1;
    private Label Herido2;
    private Label Herido3;
    private Label Herido4;
    private Label Herido5;
    private Label Age1;
    private Label Age2;
    private Label Age3;
    private Label Age4;
    private Label Age5;
    private Label Age6;
    private Label Age7;
    private Label Age8;















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
        setText();
        BackgroundFill background_fill = new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY);

        Background background = new Background(background_fill);


        VBox hBmx = new VBox();
        hBmx.setPadding(new Insets(0, 0, 0, 0));
        hBmx.setSpacing(10);
        hBmx.getChildren().addAll(Rol1,NUm1,Dead1,Herido1);

        VBox hBdx = new VBox();
        hBdx.setPadding(new Insets(0, 0, 0, 0));
        hBdx.setSpacing(10);
        hBdx.getChildren().addAll(Rol2,NUm2,Dead2,Herido2);

        VBox hBvx = new VBox();
        hBvx.setPadding(new Insets(0, 0, 0, 0));
        hBvx.setSpacing(10);
        hBvx.getChildren().addAll(Rol3,NUm3,Dead3,Herido3);

        VBox hBux = new VBox();
        hBux.setPadding(new Insets(0, 0, 0, 0));
        hBux.setSpacing(10);
        hBux.getChildren().addAll(Rol4,NUm4,Dead4,Herido4);

        VBox hBwx = new VBox();
        hBwx.setPadding(new Insets(0, 0, 0, 0));
        hBwx.setSpacing(10);
        hBwx.getChildren().addAll(Rol5,NUm5,Dead5,Herido5);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(hBmx,hBdx,hBvx,hBux,hBwx);


        VBox hBkx = new VBox();
        hBkx.setPadding(new Insets(50, 10, 10, 0));
        hBkx.setSpacing(10);
        hBkx.getChildren().addAll(Dead);

        VBox hBex = new VBox();
        hBex.setPadding(new Insets(50, 10, 10, 0));
        hBex.setSpacing(10);
        hBex.getChildren().addAll(Herido);

        VBox hBnx = new VBox();
        hBnx.setPadding(new Insets(50, 10, 10, 10));
        hBnx.setSpacing(10);
        hBnx.getChildren().addAll(nada,NUm,hBkx,hBex);

        HBox hBgx = new HBox();
        hBgx.setPadding(new Insets(10, 10, 10, 10));
        hBgx.setSpacing(10);
        hBgx.getChildren().addAll(hBnx,hBox);

        VBox hBqx = new VBox();
        hBqx.setPadding(new Insets(10, 10, 10, 10));
        hBqx.setSpacing(10);
        hBqx.getChildren().addAll(Age1,Age5);

        VBox hBjx = new VBox();
        hBjx.setPadding(new Insets(10, 10, 10, 10));
        hBjx.setSpacing(10);
        hBjx.getChildren().addAll(Age2,Age6);

        VBox hBpx = new VBox();
        hBpx.setPadding(new Insets(10, 10, 10, 10));
        hBpx.setSpacing(10);
        hBpx.getChildren().addAll(Age3,Age7);

        VBox hBzx = new VBox();
        hBzx.setPadding(new Insets(10, 10, 10, 10));
        hBzx.setSpacing(10);
        hBzx.getChildren().addAll(Age4,Age8);

        HBox hBfx = new HBox();
        hBfx.setPadding(new Insets(10, 10, 10, 10));
        hBfx.setSpacing(10);
        hBfx.getChildren().addAll(hBqx,hBjx,hBpx,hBzx);

        VBox hByx = new VBox();
        hByx.setPadding(new Insets(10, 10, 10, 10));
        hByx.setSpacing(10);
        hByx.getChildren().addAll(hBgx,hBfx);


        VBox layout = new VBox(10);
        layout.getChildren().add(hByx);
        layout.setBackground(background);

        scene = new Scene(layout, 200, 200);
    }

    private void behavior(Stage stage) {

        this.reportServices = new ReportServices();

        this.NUm1.setText(""+this.reportServices.getReportPersonasByRole().get(0).getCount());
        this.NUm2.setText(""+this.reportServices.getReportPersonasByRole().get(1).getCount());
        this.NUm3.setText(""+this.reportServices.getReportPersonasByRole().get(2).getCount());
        this.NUm4.setText(""+this.reportServices.getReportPersonasByRole().get(3).getCount());
        this.NUm5.setText(""+this.reportServices.getReportPersonasByRole().get(4).getCount());

        this.Dead1.setText(""+this.reportServices.getReportPersonasByStatusDead().get(0).getCount());
        this.Dead2.setText(""+this.reportServices.getReportPersonasByStatusDead().get(1).getCount());
        this.Dead3.setText(""+this.reportServices.getReportPersonasByStatusDead().get(2).getCount());
        this.Dead4.setText(""+this.reportServices.getReportPersonasByStatusDead().get(3).getCount());
        this.Dead5.setText(""+this.reportServices.getReportPersonasByStatusDead().get(4).getCount());

        this.Herido1.setText(""+this.reportServices.getReportPersonasByStatusHerido().get(0).getCount());
        this.Herido2.setText(""+this.reportServices.getReportPersonasByStatusHerido().get(1).getCount());
        this.Herido3.setText(""+this.reportServices.getReportPersonasByStatusHerido().get(2).getCount());
        this.Herido4.setText(""+this.reportServices.getReportPersonasByStatusHerido().get(3).getCount());
        this.Herido5.setText(""+this.reportServices.getReportPersonasByStatusHerido().get(4).getCount());

        this.Age5.setText(""+this.reportServices.getReportPersonasByAge().get(0).getCount());
        this.Age6.setText(""+this.reportServices.getReportPersonasByAge().get(1).getCount());
        this.Age7.setText(""+this.reportServices.getReportPersonasByAge().get(2).getCount());
        this.Age8.setText(""+this.reportServices.getReportPersonasByAge().get(3).getCount());


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

        nada = new Label();

        NUm = new Label(" # Por Roles");
        NUm.setFont(new Font("Impact",30));
        NUm.setBackground(background1);
        NUm.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        NUm1 = new Label("");
        NUm1.setFont(new Font("Constantia",80));
        NUm1.setBackground(background11);
        NUm1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        NUm1.setAlignment(Pos.CENTER);

        NUm2 = new Label("");
        NUm2.setFont(new Font("Constantia",80));
        NUm2.setBackground(background11);
        NUm2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        NUm3 = new Label("");
        NUm3.setFont(new Font("Constantia",80));
        NUm3.setBackground(background11);
        NUm3.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        NUm4 = new Label("");
        NUm4.setFont(new Font("Constantia",80));
        NUm4.setBackground(background11);
        NUm4.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        NUm5 = new Label("");
        NUm5.setFont(new Font("Constantia",80));
        NUm5.setBackground(background11);
        NUm5.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Dead = new Label("# De Muertos");
        Dead.setFont(new Font("Impact",30));
        Dead.setBackground(background1);
        Dead.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Dead1 = new Label("");
        Dead1.setFont(new Font("Constantia",80));
        Dead1.setBackground(background11);
        Dead1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Dead2 = new Label("");
        Dead2.setFont(new Font("Constantia",80));
        Dead2.setBackground(background11);
        Dead2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Dead2 = new Label("");
        Dead2.setFont(new Font("Constantia",80));
        Dead2.setBackground(background11);
        Dead2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Dead3 = new Label("");
        Dead3.setFont(new Font("Constantia",80));
        Dead3.setBackground(background11);
        Dead3.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Dead4 = new Label("");
        Dead4.setFont(new Font("Constantia",80));
        Dead4.setBackground(background11);
        Dead4.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Dead5 = new Label("");
        Dead5.setFont(new Font("Constantia",80));
        Dead5.setBackground(background11);
        Dead5.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Herido = new Label("# De Heridos");
        Herido.setFont(new Font("Impact",30));
        Herido.setBackground(background1);
        Herido.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Herido1 = new Label("");
        Herido1.setFont(new Font("Constantia",80));
        Herido1.setBackground(background11);
        Herido1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        Herido2 = new Label("");
        Herido2.setFont(new Font("Constantia",80));
        Herido2.setBackground(background11);
        Herido2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        Herido3 = new Label("");
        Herido3.setFont(new Font("Constantia",80));
        Herido3.setBackground(background11);
        Herido3.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        Herido4 = new Label("");
        Herido4.setFont(new Font("Constantia",80));
        Herido4.setBackground(background11);
        Herido4.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        Herido5 = new Label("");
        Herido5.setFont(new Font("Constantia",80));
        Herido5.setBackground(background11);
        Herido5.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Age1 = new Label("# De Menores de edad(menos-18)");
        Age1.setFont(new Font("Impact",30));
        Age1.setBackground(background1);
        Age1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Age2 = new Label("# De Adultos(18-30)");
        Age2.setFont(new Font("Impact",30));
        Age2.setBackground(background1);
        Age2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Age3 = new Label("# De Adultos mayores(31-60)");
        Age3.setFont(new Font("Impact",30));
        Age3.setBackground(background1);
        Age3.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Age4 = new Label("# De Ancianos(61-más)");
        Age4.setFont(new Font("Impact",30));
        Age4.setBackground(background1);
        Age4.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Age5 = new Label("");
        Age5.setFont(new Font("Constantia",80));
        Age5.setBackground(background11);
        Age5.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Age6 = new Label("");
        Age6.setFont(new Font("Constantia",80));
        Age6.setBackground(background11);
        Age6.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Age7 = new Label("");
        Age7.setFont(new Font("Constantia",80));
        Age7.setBackground(background11);
        Age7.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Age8 = new Label("");
        Age8.setFont(new Font("Constantia",80));
        Age8.setBackground(background11);
        Age8.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
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