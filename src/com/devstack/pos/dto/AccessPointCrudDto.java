package com.devstack.pos.dto;

import com.devstack.pos.entity.enums.Crud;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccessPointCrudDto {
    private long propertyId;
    private Crud crud;
}
