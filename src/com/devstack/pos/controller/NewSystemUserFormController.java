package com.devstack.pos.controller;

import com.devstack.pos.bo.BoFactory;
import com.devstack.pos.bo.custom.UserBo;
import com.devstack.pos.bo.custom.UserRoleBo;
import com.devstack.pos.dto.UserDto;
import com.devstack.pos.dto.UserRoleDto;
import com.devstack.pos.view.tm.SystemUserTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import java.util.Optional;

public class NewSystemUserFormController {
    public AnchorPane newSystemUserContext;
    public JFXTextField txtUserName;
    public JFXComboBox<String> cmbUserRole;
    public JFXTextField txtDisplayName;
    public TextField txtSearch;
    public JFXButton btnSubmit;
    public TableView<SystemUserTM> tblUser;
    public TableColumn<SystemUserTM,Long> colId;
    public TableColumn<SystemUserTM,String> colUserRole;
    public TableColumn<SystemUserTM,String> colStatus;
    public TableColumn<SystemUserTM,String> colDisplayName;
    public TableColumn<SystemUserTM,String> colEmail;
    public TableColumn<SystemUserTM,Button> colDelete;
    public TableColumn<SystemUserTM,Button> colModify;
    public JFXButton btnNewSystemUser;


    UserRoleBo userRoleBo = BoFactory.getBo(BoFactory.BoType.USER_ROLES);
    UserBo userBo = BoFactory.getBo(BoFactory.BoType.USER);
    private List<UserRoleDto> userRoleDtoList = new ArrayList<>(); // USER, ADMIN
    ObservableList<String> observableList = FXCollections.observableArrayList();
    ObservableList<SystemUserTM> systemUserTMObservableList = FXCollections.observableArrayList();
    private String searchText = "";

    private Long selectedUserId;

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("propertyId"));
        colUserRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("activeState"));
        colDisplayName.setCellValueFactory(new PropertyValueFactory<>("displayName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));
        colModify.setCellValueFactory(new PropertyValueFactory<>("modifyBtn"));

        loadAllUserRoles();
        loadAllSystemUsers();

        txtSearch.textProperty().addListener((observable,newValue,oldValue) -> {
            searchText = newValue;
            loadAllSystemUsers();
        });
    }

    private void loadAllUserRoles() {
        userRoleDtoList = userRoleBo.loadAllUserRoles();
        for (UserRoleDto dto: userRoleDtoList
             ) {
            observableList.add(dto.getRoleName());
        }
        cmbUserRole.setItems(observableList);
    }

    private void loadAllSystemUsers(){
        systemUserTMObservableList.clear(); // iza clear() seyyatti already load aaheera user or admin duplicate aahum
        // because azu global variable aala data already store aahra thoda submit button a click senjonna maruwa
        // new data oda old data sendu table a show aahum
        for (UserDto userDto : userBo.loadAllUsers(searchText)
                ) {
            Button delBtn = new Button("Delete");
            Button modBtn = new Button("Modify");
            SystemUserTM systemUserTM = new SystemUserTM(userDto.getPropertyId(), userDto.getUserRoleDto().getRoleName(),
                    userDto.isActiveState() ? "Active" : "Disabled",
                    userDto.getDisplayName(), userDto.getUserName(), delBtn, modBtn);
            systemUserTMObservableList.add(systemUserTM);

            delBtn.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.YES)){
                    if (userBo.dropUser(systemUserTM.getPropertyId())){
                        loadAllSystemUsers();
                    }
                }
            });

            modBtn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.WARNING, "do you need to update this data", ButtonType.APPLY, ButtonType.CANCEL);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.APPLY)){
                    cmbUserRole.setValue(systemUserTM.getUserRole());
                    txtUserName.setText(systemUserTM.getEmail());
                    txtDisplayName.setText(systemUserTM.getDisplayName());
                    btnSubmit.setText("Update Data");
                    // btnSubmit.setStyle("-fx-background-color: rgba(39, 174, 96,1.0)");
                    btnSubmit.setStyle("-fx-border-color: rgba(39, 174, 96,1.0)");
                    selectedUserId = systemUserTM.getPropertyId();
                }


            });
        }
        tblUser.setItems(systemUserTMObservableList);
        tblUser.refresh();
    }

    public void backToHomeOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("UserManagementForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) newSystemUserContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

    public void submitDataOnAction(ActionEvent event) {
        String userRole = cmbUserRole.getValue();
        Optional<UserRoleDto> selectedUserRoleDto =
                userRoleDtoList.stream().filter(e -> e.getRoleName().equals(userRole)).findFirst();
        String displayName = txtDisplayName.getText();
        String userName = txtUserName.getText();
        if(btnSubmit.getText().equalsIgnoreCase("Submit Data")){
            userBo.createNewSystemUser(selectedUserRoleDto.get().getPropertyId(), displayName, userName);
            new Alert(Alert.AlertType.INFORMATION, "Created").show();
        }else {
            if (selectedUserId!=null){
                userBo.updateSystemUser(selectedUserRoleDto.get().getPropertyId(), displayName,
                        userName, selectedUserId);
                new Alert(Alert.AlertType.INFORMATION, "Updated").show();
                addNewSystemUserOnAction(null); // even methods eam call panna ealum just pass null to call it
                selectedUserId=null; // update aahinonna izula vealilla (aza kuripputta userId la veala illa)
            }else {
                /*clearAll();
                btnSubmit.setText("Submit Data");
                btnSubmit.setStyle("-fx-border-color: #FF500A");
                btnSubmit.setStyle("-fx-background-color: #F9EBEA");*/ // izukku pazil addNewSystemUserOnAction() iza call pannu
                addNewSystemUserOnAction(null);
            }
        }
        loadAllSystemUsers();
        clearAll();
        System.out.println("hi done");
    }

    private void clearAll() {
        cmbUserRole.setValue(null);
        txtUserName.clear();
        txtDisplayName.clear();
    }

    public void addNewSystemUserOnAction(ActionEvent event) {
        clearAll();
        btnSubmit.setText("Submit Data");
        btnSubmit.setStyle("-fx-border-color: #FF500A");
        btnSubmit.setStyle("-fx-background-color: #F9EBEA");
    }
}
