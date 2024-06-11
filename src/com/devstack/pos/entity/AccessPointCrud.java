package com.devstack.pos.entity;

import com.devstack.pos.entity.enums.Crud;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccessPointCrud {
    @Id
    private long propertyId;
    @Enumerated(EnumType.STRING) // enum type a sellanum
    private Crud crud;

    @ManyToOne
    @JoinColumn(name = "access_point_id")
    private AccessPoint accessPoint;
}
