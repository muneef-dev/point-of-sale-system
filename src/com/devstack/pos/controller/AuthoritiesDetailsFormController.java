package com.devstack.pos.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AuthoritiesDetailsFormController {
    public Label lblUserEmail;
    public Label lblRole;
    public Label lblAvailability;
    public Label lblDisplayName;
    public TableView tblOfAccessPointsOfOptions;
    public TableColumn colId;
    public TableColumn colAccessPoint;
    public TableColumn colCrud;
    public TableColumn colOption;

    public void initializeData(String code){
        // authoririties controler form la eera table row onduda id a eduththu aza id ikkaana details a thaan iza
        // popup windowla kaattanum
        System.out.println(code);
    }
}
