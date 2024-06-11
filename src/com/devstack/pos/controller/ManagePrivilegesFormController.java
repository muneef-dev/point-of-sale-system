package com.devstack.pos.controller;

import com.devstack.pos.bo.BoFactory;
import com.devstack.pos.bo.custom.AccessPointBo;
import com.devstack.pos.dto.AccessPointCrudDto;
import com.devstack.pos.dto.AccessPointDto;
import com.devstack.pos.view.tm.AccessPrivilegesTm;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
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
import java.util.List;
import java.util.Optional;

public class ManagePrivilegesFormController {
    public AnchorPane privilegesContext;
    public TextField txtSearch;

    public JFXComboBox<String> cmbAccessPoint;
    public JFXCheckBox rBtnCreate;
    public JFXCheckBox rBtnRead;
    public JFXCheckBox rBtnDelete;
    public JFXCheckBox rBtnUpdate;
    public TableView<AccessPrivilegesTm> tblAccessPrivileges;
    public TableColumn colId;
    public TableColumn colAccessPoint;
    public TableColumn colOperation;
    public TableColumn colDelete;


    private AccessPointBo accessPointBo = BoFactory.getBo(BoFactory.BoType.ACCESS_POINT);

    private ObservableList<String> stringAccessPointObservableList = FXCollections.observableArrayList();

    List<AccessPointDto> accessPointDtoList;

    private String searchText = "";

    public void initialize(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAccessPoint.setCellValueFactory(new PropertyValueFactory<>("accessPointName"));
        colOperation.setCellValueFactory(new PropertyValueFactory<>("operation"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));

        loadAllAccessPoint();
        loadAllToTable();
    }

    private void loadAllAccessPoint(){
        accessPointDtoList = accessPointBo.loadAllAccessPoints(searchText);
        for (AccessPointDto dto: accessPointDtoList
             ) {
            stringAccessPointObservableList.add(dto.getPointName());
        }

        cmbAccessPoint.setItems(stringAccessPointObservableList);
    }

    private void loadAllToTable(){
        ObservableList<AccessPrivilegesTm> observableList = FXCollections.observableArrayList();
        for (AccessPointDto accessPointDto : accessPointDtoList) {
            // Cruds a edukka innamoru foreach oonum
            for (AccessPointCrudDto accessPointCrudDto : accessPointDto.getAccessPointCrudDtos()
                    ) {
                CheckBox checkBox = new CheckBox(accessPointCrudDto.getCrud().name());
                checkBox.setSelected(true);
                // delete a illamaa ikku aza checkbox tick a illamaakkina delete aahavei
                /*Button deleteBtn = new Button("Delete");*/
                /*AccessPrivilegesTm accessPrivilegesTm = new AccessPrivilegesTm(accessPointCrudDto.getPropertyId(),
                        accessPointDto.getPointName(),
                        checkBox, deleteBtn);*/
                AccessPrivilegesTm accessPrivilegesTm = new AccessPrivilegesTm(accessPointCrudDto.getPropertyId(),
                        accessPointDto.getPointName(),
                        checkBox);
                observableList.add(accessPrivilegesTm);

                checkBox.setOnAction(event -> {
                    if (!checkBox.isSelected()){
                        Alert alert = new Alert(Alert.AlertType.WARNING, "do you need to delete this access point crud", ButtonType.APPLY, ButtonType.CANCEL);
                        Optional<ButtonType> buttonType = alert.showAndWait();
                        if (buttonType.get().equals(ButtonType.APPLY)){
                            accessPointBo.deleteCrud(accessPrivilegesTm.getId());
                        }
                    }
                });
            }
        }
        tblAccessPrivileges.setItems(observableList);
    }

    public void submitDataOnAction(ActionEvent event) {
        /*for (AccessPointDto accessPointDto : accessPointDtoList) {
            for (AccessPointCrudDto crudDto: accessPointDto.getAccessPointCrudDtos()
                 ) {
                crudDto.getCrud() == se
            }
        }
*/
        Optional<AccessPointDto> accessPointDto = accessPointDtoList.stream().filter(e -> e.getPointName().equals(cmbAccessPoint.getValue())).findFirst();
        if (accessPointDto.isPresent()){
            accessPointBo.setPrivileges(
                    accessPointDto.get().getPropertyId(),rBtnCreate.isSelected(),rBtnRead.isSelected(),rBtnUpdate.isSelected(),rBtnDelete.isSelected()
            ); // isSelected() -> select andraal true pohum illatti false pohum
            loadAllToTable();
            new Alert(Alert.AlertType.INFORMATION,"Saved").show();
        }
    }

    public void backToHomeOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("UserRolesAndAuthoritiesForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) privilegesContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
