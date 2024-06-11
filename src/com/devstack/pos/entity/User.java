package com.devstack.pos.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User implements SuperEntity{
    @Id
    @Column(name = "property_id")
    private Long propertyId; // izu workbench la int so maaththiko
    @Column(name = "username",nullable = false)
    private String userName;
    @Column(name = "display_name",nullable = false)
    private String displayName;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "active_state")
    private boolean activeState;
    @ManyToOne
    @JoinColumn(name = "role_id") // user da side ikke user role da column (id) vaara so azukkum name kudukkalaam
    private UserRole userRole;
}
