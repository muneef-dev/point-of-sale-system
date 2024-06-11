package com.devstack.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierManagementFormController {
    public AnchorPane supplierManagementContext;
    public JFXTextField txtSupplierName;
    public JFXTextField txtContact;
    public TextField txtSearch;
    public TableView tblSuppliers;
    public TableColumn colId;
    public TableColumn colSupplierName;
    public TableColumn colContact;
    public TableColumn colDelete;
    public TableColumn colModify;

    public void submitDataOnAction(ActionEvent event) {
    }

    public void backToHomeOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("InventoryManagementForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) supplierManagementContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

    public void popUpOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SupplierDetailsForm.fxml"))));
        stage.centerOnScreen();
        stage.show();
    }
}
