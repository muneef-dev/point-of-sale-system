package com.devstack.pos.view.tm;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccessPrivilegesTm {
    private long id;
    private String accessPointName;
    private CheckBox operation;
    //private Button deleteBtn; // delete a illamaa ikku aza checkbox tick a illamaakkina delete aahavei
}
