package com.devstack.pos.bo.custom;

import com.devstack.pos.bo.SuperBo;
import com.devstack.pos.dto.UserRoleDto;
import com.devstack.pos.entity.UserRole;

import java.util.List;

public interface UserRoleBo extends SuperBo {
    public List<UserRoleDto> loadAllUserRoles();
    public List<UserRoleDto> loadAllUserRoles(String searchText);
    public boolean createNewUserRole(UserRoleDto userRoleDto);
}
