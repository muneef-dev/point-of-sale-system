package com.devstack.pos.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccessPointDto {
    private Long propertyId;
    private String pointName;
    private List<AccessPointCrudDto> AccessPointCrudDtos;
}
