package com.devstack.pos.view.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccessPointTm {
    private Long propertyId;
    private String pointName;
    private String operation; // iza miss aahi enakku illa tti sir ikku iza column a naan illatti sir
    // maaththi poattu (Access point/ privilages) so theawallatti delete pannu
    private Button delBtn;
    private Button modBtn;

    // Unknown entity: com.devstack.pos.entity.AccessPoint -> hibernate util la entityya add pannu
}
