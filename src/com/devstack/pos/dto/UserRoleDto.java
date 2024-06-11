package com.devstack.pos.dto;

import lombok.*;

@NoArgsConstructor  // iza annotation ellam lombok jar aala vandhazu izu leasi
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRoleDto {
        // getters setters ikku pazila innamoru dependency eera that is "lombok" so iza poatta getters setters (methods) poda theawalla
        // getters setters ikku pazila annotation mattum use seyyalaam
        private Long propertyId;
        private String roleName;
        private String roleDescription;

}
