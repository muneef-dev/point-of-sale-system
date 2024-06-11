package com.devstack.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductManagementFormController {
    public AnchorPane productManagementContext;
    public JFXTextField txtDescription;
    public JFXComboBox cmbCategory;
    public JFXTextField txtBrand;
    public TextField txtSearch;
    public TableView tblProduct;
    public TableColumn colId;
    public TableColumn colCategory;
    public TableColumn colDescription;
    public TableColumn colBrand;
    public TableColumn colDelete;
    public TableColumn colModify;
    public ImageView txtProductImage;
    public JFXComboBox cmbSupplier;

    public void submitDataOnAction(ActionEvent event) {
    }

    public void backToHomeOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("InventoryManagementForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) productManagementContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
