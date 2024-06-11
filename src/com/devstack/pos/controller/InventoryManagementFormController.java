package com.devstack.pos.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryManagementFormController {
    public AnchorPane inventoryManagementContext;

    public void productManagementOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("ProductManagementForm");
    }

    public void supplierManagementOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("SupplierManagementForm");
    }

    public void batchManagementOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("BatchManagementForm");

    }

    public void categoryManagementMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("CategoryManagementForm");
    }

    public void backToHomeOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("AdminPortalForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) inventoryManagementContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
