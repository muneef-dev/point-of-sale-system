package com.devstack.pos.dao.custom.impl;

import com.devstack.pos.dao.custom.UserRoleDao;
import com.devstack.pos.db.HibernateUtil;
import com.devstack.pos.entity.User;
import com.devstack.pos.entity.UserRole;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserRoleDaoImpl implements UserRoleDao {
    @Override
    public boolean create(UserRole userRole) {
        try (Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(userRole);
            transaction.commit();
        }
        return true;
    }

    @Override
    public UserRole find(Long id) {
        return null;
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public boolean modify(UserRole userRole) {
        return false;
    }

    @Override
    public List<UserRole> loadAll() {
        try (Session session = HibernateUtil.getSession()){
            return session.createQuery("FROM userrole", UserRole.class).list();
        }
    }

    @Override
    public boolean isExists() { // user roles eeratha check seithal
        try (Session session = HibernateUtil.getSession()){
            // m-1 izula sinna korapaadu eera createCriteria deprecated aahum
            /*Criteria criteria = session.createCriteria(UserRole.class); // criteria -> alavukolhal, kattalai vithihal
            // user role da count a eduththal setProjection(Projections.rawCount()) method moolam raw count a edukkalaam
            criteria.setProjection(Projections.rowCount());   // ihina Projections class a edu not the interface
            Long count = (Long) criteria.uniqueResult();// uniqueResult() s convert seyyanum long ikku
            return count>0;*/

            // m-2
            Query query = session.createQuery("SELECT COUNT(*) FROM userrole");
            Long count = (Long) query.getSingleResult();//getSingleResult() izu object onda tharum so aza long ikku cast sei
            return count>0;
        }
    }

    @Override
    public List<UserRole> loadAllUserRoles(String searchText) {
        try(Session session = HibernateUtil.getSession()){
            Query<UserRole> userRoleQuery = session.createQuery("SELECT u FROM userrole u WHERE u.roleName LIKE :searchText", UserRole.class);
            return userRoleQuery.setParameter("searchText","%"+searchText+"%").list();
        }
    }
}
