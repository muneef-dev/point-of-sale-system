package com.devstack.pos.bo.custom.impl;

import com.devstack.pos.bo.custom.AccessPointBo;
import com.devstack.pos.dao.DaoFactory;
import com.devstack.pos.dao.custom.AccessPointDao;
import com.devstack.pos.dto.AccessPointCrudDto;
import com.devstack.pos.dto.AccessPointDto;
import com.devstack.pos.entity.AccessPoint;
import com.devstack.pos.entity.AccessPointCrud;

import java.util.ArrayList;
import java.util.List;

public class AccessPointBoImpl implements AccessPointBo {
    private AccessPointDao accessPointDao = DaoFactory.getDao(DaoFactory.DaoType.ACCESS_POINT);
    @Override
    public boolean createAccessPoint(AccessPointDto accessPointDto) {
        // return accessPointDao.create(new AccessPoint(accessPointDto.getPropertyId(), accessPointDto.getPointName(),null));
        AccessPoint accessPoint = new AccessPoint();
        accessPoint.setPropertyId(accessPointDto.getPropertyId());
        accessPoint.setPointName(accessPointDto.getPointName());
        // iza ippidim object thorandhum seyya ealum meala eera maai null um pass seyya ealum
        // null pass seira izukkahum private List<AccessPointCrud> accessPointCruds; izu thewa database table relationship ikkahum
        return accessPointDao.create(accessPoint);

    }

    @Override
    public boolean setPrivileges(long privilegeId, boolean create, boolean read, boolean update, boolean delete) {
        return accessPointDao.setPrivileges(privilegeId,create,read,update,delete);
    }

    @Override
    public List<AccessPointDto> loadAllAccessPoints(String searchText) {
        List<AccessPointDto> accessPointDtoList = new ArrayList<>();
        for (AccessPoint accessPoint : accessPointDao.loadAllAccessPoints(searchText)
             ) {
            AccessPointDto accessPointDto = new AccessPointDto();
            accessPointDto.setPropertyId(accessPoint.getPropertyId());
            accessPointDto.setPointName(accessPoint.getPointName());
            List<AccessPointCrudDto> accessPointCrudDtoList = new ArrayList<>();
            for (AccessPointCrud accessPointCrud : accessPoint.getAccessPointCruds()
                 ) {
                accessPointCrudDtoList.add(new AccessPointCrudDto(accessPointCrud.getPropertyId(),
                        accessPointCrud.getCrud()));
            }
            accessPointDto.setAccessPointCrudDtos(accessPointCrudDtoList);
            accessPointDtoList.add(accessPointDto);
            // accessPointDtoList.add(new AccessPointDto(accessPoint.getPropertyId(), accessPoint.getPointName(),null));
            // null um anuppalaam? eala poala ena
        }
        return accessPointDtoList; // day 6  time 1.25.00
    }

    @Override
    public boolean deleteCrud(Long id) {
        return accessPointDao.remove(id);
    }
}
