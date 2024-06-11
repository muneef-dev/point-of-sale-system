package com.devstack.pos.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserRolesAndAuthoritiesFormController {
    public AnchorPane userRolesAndAuthoritiesContext;

    public void manageUserRoleOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("ManageUserRolesForm");
    }

    public void manageAccessPointOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("ManageAccessPointForm");
    }

    public void privilegesOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("ManagePrivilegesForm");
    }

    public void authoritiesOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("ManageAuthoritiesForm");
    }

    public void backToHomeOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("UserManagementForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) userRolesAndAuthoritiesContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
