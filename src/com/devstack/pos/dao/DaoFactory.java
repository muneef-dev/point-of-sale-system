package com.devstack.pos.dao;

import com.devstack.pos.dao.custom.impl.AccessPointDaoImpl;
import com.devstack.pos.dao.custom.impl.UserDaoImpl;
import com.devstack.pos.dao.custom.impl.UserRoleDaoImpl;

public class DaoFactory { // iza class vandhazu instance a manage seyya // 121212
    // UserDao userDao = new UserDaoImpl(); login form controller la ippidi meala object senjeera iza maain nearaya seyya
    // vendi aara so aza leassaakka vandhazu than daofactory

    private DaoFactory(){}
    public enum DaoType{
        USER,
        USER_ROLE,
        ACCESS_POINT
    }
    public static <T> T getDao(DaoType daoType){
        switch (daoType){
            case USER:
                return (T) new UserDaoImpl();
            case USER_ROLE:
                return (T) new UserRoleDaoImpl();
            case ACCESS_POINT:
                return (T) new AccessPointDaoImpl();
            default:
                return null;
        }
    }
}
