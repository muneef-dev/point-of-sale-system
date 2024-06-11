package com.devstack.pos.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
        private Long propertyId; // izu workbench la int so maaththiko
        private String userName;
        private String displayName;
        private String password;
        private boolean activeState;
        private UserRoleDto userRoleDto; // userrole da ellathem access seyya
}
