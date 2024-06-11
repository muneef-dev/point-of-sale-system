package com.devstack.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageAuthoritiesFormController {
    public AnchorPane authoritiesContext;
    public TextField txtSearch;
    public TableView tblAuthorities;
    public TableColumn colId;
    public TableColumn colUserName;
    public TableColumn colAvailability;
    public TableColumn colUserRole;
    public TableColumn colDisplayName;



    public void backToHomeOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("UserRolesAndAuthoritiesForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) authoritiesContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

    public void popUpOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/AuthorityDetailsForm.fxml"));
        Parent parent = fxmlLoader.load();
        AuthoritiesDetailsFormController controller = fxmlLoader.getController();
        controller.initializeData("code is here");
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Authority Details");
        stage.show();
    }
}
