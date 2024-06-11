package com.devstack.pos;

import com.devstack.pos.bo.BoFactory;
import com.devstack.pos.bo.custom.UserBo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        initializeData();

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/LoginForm.fxml"))));
        primaryStage.setTitle("Point Of Sale");
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

    private void initializeData(){
        UserBo userBo = BoFactory.getBo(BoFactory.BoType.USER);
        userBo.initializeSystem();
    }
}
