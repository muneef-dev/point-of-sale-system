package com.devstack.pos.bo.custom;

import com.devstack.pos.bo.SuperBo;
import com.devstack.pos.dto.UserDto;
import com.devstack.pos.dto.UserRoleDto;
import com.devstack.pos.entity.User;

import java.util.List;

public interface UserBo extends SuperBo {
    public void initializeSystem();
    public List<UserDto> loadAllUsers(String searchText);
    public void createNewSystemUser(Long userRoleId,String displayName,String email);
    //public void createNewSystemUser(UserRoleDto userRoleDto,String displayName,String email); // entity use pandra dao layerla
    // so inga izu (bo layer sutti) UserRoleDto userRoleDto -> dao la izu UserRole userRole
    public void updateSystemUser(Long userRoleId,String displayName,String email,Long userId);
    public boolean dropUser(Long id);
}
