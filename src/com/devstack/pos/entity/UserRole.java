package com.devstack.pos.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "userrole")
@NoArgsConstructor  // iza annotation ellam lombok jar aala vandhazu izu leasi
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRole implements SuperEntity{
    // getters setters ikku pazila innamoru dependency eera that is "lombok" so iza poatta getters setters (methods) poda theawalla
    // getters setters ikku pazila annotation mattum use seyyalaam
    @Id
    @Column(name = "property_id") // endha name la table database la build aahanum indu kudukura
    private Long propertyId;
    @Column(name = "role_name", nullable = false) // null poda mudiyazu
    private String roleName;
    @Column(name = "role_description", nullable = false)
    private String roleDescription;
    @OneToMany(mappedBy = "userRole") // id uruvaahonum user side la
    private Set<User> users; // also can use List

}
