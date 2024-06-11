package com.devstack.pos.view.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRoleTM {
    private Long propertyId;
    private String roleName;
    private String roleDescription;
    private Button deleteBtn;
    private Button modifyBtn;
}
