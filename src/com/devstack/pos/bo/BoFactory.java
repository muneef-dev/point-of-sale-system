package com.devstack.pos.bo;

import com.devstack.pos.bo.custom.impl.AccessPointBoImpl;
import com.devstack.pos.bo.custom.impl.UserBoImpl;
import com.devstack.pos.bo.custom.impl.UserRoleBoImpl;

public class BoFactory {
/*    private BoFactory boFactory;*/
    public BoFactory(){}
    public enum BoType{
        USER,
        USER_ROLES,
        ACCESS_POINT,

    }
    public static <T> T getBo(BoType boType){
        switch (boType){
            case USER:
                return (T) new UserBoImpl();
            case USER_ROLES:
                return (T) new UserRoleBoImpl();
            case ACCESS_POINT:
                return (T) new AccessPointBoImpl();
            default:
                return null;
        }
    }
}
