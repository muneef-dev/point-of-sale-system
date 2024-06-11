package com.devstack.pos.bo.custom;

import com.devstack.pos.bo.SuperBo;
import com.devstack.pos.dto.AccessPointDto;

import java.util.List;

public interface AccessPointBo extends SuperBo {
    public boolean createAccessPoint(AccessPointDto accessPointDto);
    public boolean setPrivileges(long privilegeId, boolean create, boolean read, boolean update, boolean delete);
    public List<AccessPointDto> loadAllAccessPoints(String searchText);
    public boolean deleteCrud(Long id);
}
