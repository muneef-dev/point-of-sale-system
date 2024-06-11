package com.devstack.pos.dao.custom;

import com.devstack.pos.dao.CrudDao;
import com.devstack.pos.dto.UserRoleDto;
import com.devstack.pos.entity.User;
import com.devstack.pos.entity.UserRole;
import com.devstack.pos.util.ResponseData;

import java.util.List;

public interface UserDao extends CrudDao<User,Long> { // T -> User type , ID -> User da id -> Integer type
    // iza class la veala seita user udan enbazaal Type -> User and Id -> Integer ena type safe seyyanum
    // CrudDao la create seyya vendiya type a keakkum
    // but UserDao ikku varum pozu T convert aahum User ikku ID convet aahum specific type aana integer ikku (sela endaththula String aahalaam)

    public ResponseData login(String username, String password);
    public List<User> loadAllUsers(String searchText);
    public void createNewSystemUser(Long userRoleId, String displayName, String email); // dto use pandra bo layerla
    public void updateSystemUser(Long userRoleId,String displayName,String email,Long userId);
}
