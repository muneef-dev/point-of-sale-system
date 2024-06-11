package com.devstack.pos.dao.custom;

import com.devstack.pos.dao.CrudDao;
import com.devstack.pos.entity.AccessPoint;

import java.util.List;

public interface AccessPointDao extends CrudDao<AccessPoint,Long> {
    public List<AccessPoint> loadAllAccessPoints(String searchText);
    public boolean setPrivileges(long privilegeId, boolean create, boolean read, boolean update, boolean delete);
}
