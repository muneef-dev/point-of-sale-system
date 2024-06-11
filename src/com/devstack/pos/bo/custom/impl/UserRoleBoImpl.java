package com.devstack.pos.bo.custom.impl;

import com.devstack.pos.bo.custom.UserRoleBo;
import com.devstack.pos.dao.DaoFactory;
import com.devstack.pos.dao.custom.UserRoleDao;
import com.devstack.pos.dto.UserRoleDto;
import com.devstack.pos.entity.User;
import com.devstack.pos.entity.UserRole;

import java.util.ArrayList;
import java.util.List;

public class UserRoleBoImpl implements UserRoleBo {
    private UserRoleDao userRoleDao = DaoFactory.getDao(DaoFactory.DaoType.USER_ROLE);

    @Override
    public List<UserRoleDto> loadAllUserRoles() {
        List<UserRoleDto> userRoleDtoList = new ArrayList<>();
        for (UserRole userRole: userRoleDao.loadAll()
             ) {
            userRoleDtoList.add(new UserRoleDto(
                    userRole.getPropertyId(), userRole.getRoleName(), userRole.getRoleDescription()
            ));
        }
        return userRoleDtoList;
    }

    @Override
    public List<UserRoleDto> loadAllUserRoles(String searchText) {
        List<UserRoleDto> userRoleDtoList = new ArrayList<>();
        for (UserRole userRole:userRoleDao.loadAllUserRoles(searchText)){
            userRoleDtoList.add(new UserRoleDto(userRole.getPropertyId(), userRole.getRoleName(), userRole.getRoleDescription()));
        }
        return userRoleDtoList;

    }

    @Override
    public boolean createNewUserRole(UserRoleDto userRoleDto) {
        userRoleDao.create(new UserRole(userRoleDto.getPropertyId(), userRoleDto.getRoleName(),
                userRoleDto.getRoleDescription(), null)); // users anuppa theawalla azu database a create panna thaan theawa so null anuppina sari
        return true;
    }
}
