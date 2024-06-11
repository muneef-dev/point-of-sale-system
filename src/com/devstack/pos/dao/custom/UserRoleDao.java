package com.devstack.pos.dao.custom;

import com.devstack.pos.dao.CrudDao;
import com.devstack.pos.entity.User;
import com.devstack.pos.entity.UserRole;

import java.util.List;

public interface UserRoleDao extends CrudDao<UserRole,Long> {
    // UserRoleDao la UserRoleDao ikku mattum thewayaana (CrudDao pozuvaana method ea eera) method eekkum (exceptional cases)

    // UserRole initialize seira user role illatti mattum aahum (user role eendha initialize seirulla)
    // so use role eerandu oru method a poattu paaththu kolanum

    public boolean isExists(); // UserRole already eeka? like isUserRoleExist
    public List<UserRole> loadAllUserRoles(String searchText);
}
