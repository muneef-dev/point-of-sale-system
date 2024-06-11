package com.devstack.pos.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccessPoint implements SuperEntity{
    @Id
    @Column(name = "property_id")
    private Long propertyId;
    @Column(name = "point_name")
    private String pointName;
    //  failed to lazily initialize a collection of role: com.devstack.pos.entity.AccessPoint.accessPointCruds,
    //  could not initialize proxy - no Session
    // mealla eera error vandha azula selleera tha vaasi mapping a paaththu mappedBy poatteera eda ththukku peiththu
    // fetch type a eager kudu defalut lazy varum aza maaththu cascade poattaalum ondu padaanttim ondu azukum error ikkum no connection
    @OneToMany(mappedBy = "accessPoint",/*cascade = CascadeType.ALL,*/fetch = FetchType.EAGER) // iza access point entity da id thaan angala pora so angala eera AccessPointCrud la eera access pointda name a thaan inga map pannanum
    private List<AccessPointCrud> accessPointCruds;
    // private List<AccessPointCrud> accessPointCruds = new ArrayList<>(); // indum kudukka ealum
    // Unknown entity: com.devstack.pos.entity.AccessPoint -> hibernate util la entityya add pannu


    // cascade = CascadeType.ALL, iza meala poattonna keela eera error vandha
    // deleted object would be re-saved by cascade (remove deleted object from associations): [com.devstack.pos.entity.AccessPointCrud#1839302385862748160]
}
