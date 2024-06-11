package com.devstack.pos.view.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SystemUserTM {
    private long propertyId;
    private String userRole;
    private String activeState;
    private String displayName;
    private String email;
    private Button deleteBtn;
    private Button modifyBtn;
}
