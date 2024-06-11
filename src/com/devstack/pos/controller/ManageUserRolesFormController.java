package com.devstack.pos.controller;

import com.devstack.pos.bo.BoFactory;
import com.devstack.pos.bo.custom.UserRoleBo;
import com.devstack.pos.dto.UserRoleDto;
import com.devstack.pos.util.KeyGenerator;
import com.devstack.pos.view.tm.UserRoleTM;
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

public class ManageUserRolesFormController {
    public AnchorPane manageUserRolesContext;
    public JFXTextField txtUserRoleName;
    public JFXTextField txtDescription;
    public TextField txtSearch;
    public TableView<UserRoleTM> tblUserRoles;
    public TableColumn colId;
    public TableColumn colRoleName;
    public TableColumn colDescription;
    public TableColumn colDelete;
    public TableColumn colModify;

    UserRoleBo userRoleBo = BoFactory.getBo(BoFactory.BoType.USER_ROLES);
    ObservableList<UserRoleTM> observableList = FXCollections.observableArrayList();
    private String searchText = "";

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("propertyId"));
        colRoleName.setCellValueFactory(new PropertyValueFactory<>("roleName"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("roleDescription"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));
        colModify.setCellValueFactory(new PropertyValueFactory<>("modifyBtn"));

        loadAllUserRoles();

        txtSearch.textProperty().addListener((observable,newValue,oldValue) -> {
            searchText = newValue;
            loadAllUserRoles();
        });
    }

    private void loadAllUserRoles() {
        observableList.clear();
        for (UserRoleDto userRoleDto: userRoleBo.loadAllUserRoles(searchText)
             ) {
            Button del = new Button("Delete");
            Button mod = new Button("Modify");
            UserRoleTM userRoleTM = new UserRoleTM(userRoleDto.getPropertyId(), userRoleDto.getRoleName(),
                    userRoleDto.getRoleDescription(),del,mod);
            observableList.add(userRoleTM);
        }
        tblUserRoles.setItems(observableList);
    }

    public void submitDataOnAction(ActionEvent event) {
        if (userRoleBo.createNewUserRole(new UserRoleDto(KeyGenerator.generateId(),
                txtUserRoleName.getText().trim().toUpperCase(), txtDescription.getText()))) {
            new Alert(Alert.AlertType.INFORMATION,"created").show();
            loadAllUserRoles();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"user not created enter valid details").show();
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
