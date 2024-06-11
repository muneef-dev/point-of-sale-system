package com.devstack.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPortalFormController {
    public AnchorPane adminPortalContext;

    public void userManagementOnAction(ActionEvent event) throws IOException {
        setUi("UserManagementForm");
    }

    public void userRoleAndAuthoritiesOnAction(ActionEvent event) throws IOException {
        setUi("UserRolesAndAuthoritiesForm");
    }

    public void incomeAndReportOnAction(ActionEvent event) throws IOException {
        setUi("");
    }

    public void manageInventoryOnAction(ActionEvent event) throws IOException {
        setUi("InventoryManagementForm");
    }

    public void customerManagementOnAction(ActionEvent event) throws IOException {
        setUi("");
    }

    public void dealsAndDiscountOnAction(ActionEvent event) throws IOException {
        setUi("");
    }

    public void reportAndStatisticsOnAction(ActionEvent event) throws IOException {
        setUi("");
    }

    public void userActivityOnAction(ActionEvent event) throws IOException {
        setUi("");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) adminPortalContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
