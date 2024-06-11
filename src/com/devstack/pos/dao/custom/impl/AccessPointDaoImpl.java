package com.devstack.pos.dao.custom.impl;

import com.devstack.pos.dao.custom.AccessPointDao;
import com.devstack.pos.db.HibernateUtil;
import com.devstack.pos.entity.AccessPoint;
import com.devstack.pos.entity.AccessPointCrud;
import com.devstack.pos.entity.enums.Crud;
import com.devstack.pos.util.KeyGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AccessPointDaoImpl implements AccessPointDao {
    @Override
    public boolean create(AccessPoint accessPoint) {
        try(Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(accessPoint);
            transaction.commit();
            return true;
        }
    }

    @Override
    public AccessPoint find(Long aLong) {
        return null;
    }

    @Override
    public boolean remove(Long aLong) {
        try(Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            Query<AccessPointCrud> accessPointCrudQuery = session.createQuery("FROM AccessPointCrud u WHERE u.propertyId=:id", AccessPointCrud.class);
            accessPointCrudQuery.setParameter("id",aLong);
            AccessPointCrud accessPointCrud = accessPointCrudQuery.uniqueResult();
            if (accessPointCrud!=null){
                session.remove(accessPointCrud);
                transaction.commit();
                return true;
            } else {
                throw new RuntimeException("Access point crud is not found");
            }
        }
    }

    @Override
    public boolean modify(AccessPoint accessPoint) {
        return false;
    }

    @Override
    public List<AccessPoint> loadAll() {
        return null;
    }

    @Override
    public List<AccessPoint> loadAllAccessPoints(String searchText) {
        try (Session session = HibernateUtil.getSession()) {
            Query<AccessPoint> accessPointQuery = session.createQuery("SELECT u FROM AccessPoint u WHERE u.pointName LIKE :searchText", AccessPoint.class);
            return accessPointQuery.setParameter("searchText","%"+searchText+"%").list();
        }
    }

    @Override
    public boolean setPrivileges(long privilegeId, boolean create, boolean read, boolean update, boolean delete) {
        try(Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            Query<AccessPoint> accessPointQuery = session.createQuery("FROM AccessPoint u WHERE u.propertyId=:id", AccessPoint.class);
            accessPointQuery.setParameter("id",privilegeId);
            AccessPoint accessPoint = accessPointQuery.uniqueResult();
            if (accessPoint!=null) {
                if (create){
                    AccessPointCrud accessPointCrud = new AccessPointCrud(KeyGenerator.generateId(),
                            Crud.CREATE,accessPoint);
                    session.save(accessPointCrud);
                }
                if (update){
                    AccessPointCrud accessPointCrud = new AccessPointCrud(KeyGenerator.generateId(),
                            Crud.UPDATE,accessPoint);
                    session.save(accessPointCrud);
                }
                if (delete){
                    AccessPointCrud accessPointCrud = new AccessPointCrud(KeyGenerator.generateId(),
                            Crud.DELETE,accessPoint);
                    session.save(accessPointCrud);
                }
                if (read){
                    AccessPointCrud accessPointCrud = new AccessPointCrud(KeyGenerator.generateId(),
                            Crud.READ,accessPoint);
                    session.save(accessPointCrud);
                }
                transaction.commit();
                return true;
            }else {
                throw new RuntimeException("Access point not found");
            }
        }
    }
}
