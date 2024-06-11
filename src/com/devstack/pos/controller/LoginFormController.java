package com.devstack.pos.controller;

import com.devstack.pos.dao.DaoFactory;
import com.devstack.pos.dao.custom.UserDao;
import com.devstack.pos.util.ResponseData;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginContext;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    // UserDao userDao = new UserDaoImpl(); // 121212
    public void btnLoginOnAction(ActionEvent event) throws IOException {
        // ResponseData responseData = userDao.login(txtEmail.getText(), txtPassword.getText()); // 121212
        UserDao userDao = DaoFactory.getDao(DaoFactory.DaoType.USER);
        ResponseData responseData = userDao.login(txtEmail.getText().trim(), txtPassword.getText().trim());
        if (responseData!= null) { // password = 861rGM , username = admin@gmail.com
            if ((boolean)responseData.getData()){
                setUi();
                //new Alert(Alert.AlertType.INFORMATION,responseData.getMessage()).show();
            }else {
                new Alert(Alert.AlertType.WARNING, responseData.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"something went wrong").show();
        }
    }
    private void setUi() throws IOException {
        Stage stage = (Stage) loginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminPortalForm.fxml"))));
        stage.setTitle("AdminPortal");
        stage.centerOnScreen();
    }
}