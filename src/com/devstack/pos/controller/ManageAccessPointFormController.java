package com.devstack.pos.controller;

import com.devstack.pos.bo.BoFactory;
import com.devstack.pos.bo.custom.AccessPointBo;
import com.devstack.pos.dto.AccessPointCrudDto;
import com.devstack.pos.dto.AccessPointDto;
import com.devstack.pos.util.KeyGenerator;
import com.devstack.pos.view.tm.AccessPointTm;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManageAccessPointFormController {
    public AnchorPane manageUserRolesContext;
    public JFXTextField txtAccessPointName;
    public TextField txtSearch;
    public TableView<AccessPointTm> tblAccessPoint;
    public TableColumn colId;
    public TableColumn colDisplayName;
    public TableColumn colDelete;
    public TableColumn colModify;
    public TableColumn colOperation;

    private AccessPointBo accessPointBo = BoFactory.getBo(BoFactory.BoType.ACCESS_POINT);

    private ObservableList<AccessPointTm> observableList = FXCollections.observableArrayList();

    private String searchText = "";

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("propertyId"));
        colDisplayName.setCellValueFactory(new PropertyValueFactory<>("pointName"));
        colOperation.setCellValueFactory(new PropertyValueFactory<>("operation"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delBtn"));
        colModify.setCellValueFactory(new PropertyValueFactory<>("modBtn"));

        loadAllAccessPoints();

        txtSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
            searchText = newValue;
            loadAllAccessPoints();
        }));
    }

    private void loadAllAccessPoints() {
        observableList.clear();
        for (AccessPointDto dto: accessPointBo.loadAllAccessPoints(searchText)
             ) {
            Button del = new Button("Delete");
            Button mod = new Button("Modify");

            // operations a eduththal
            ArrayList<String> arrayList = new ArrayList<>();
            for (AccessPointCrudDto accessPointCrudDto: dto.getAccessPointCrudDtos()
                 ) {
                arrayList.add(accessPointCrudDto.getCrud().name());
            }
            System.out.println(arrayList+" : here is the list");

            observableList.add(new AccessPointTm(dto.getPropertyId(), dto.getPointName(),
                    arrayList.toString(), del,mod));
        }
        tblAccessPoint.setItems(observableList);
    }

    public void submitDataOnAction(ActionEvent event) {
        if (accessPointBo.createAccessPoint(new AccessPointDto(KeyGenerator.generateId(), txtAccessPointName.getText(),null))){
            new Alert(Alert.AlertType.INFORMATION,"access point saved").show();
            loadAllAccessPoints();
            txtAccessPointName.clear();
        }
    }

    public void backToHomeOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("UserRolesAndAuthoritiesForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) manageUserRolesContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
