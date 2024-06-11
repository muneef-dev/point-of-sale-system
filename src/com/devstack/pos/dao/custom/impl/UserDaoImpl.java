package com.devstack.pos.dao.custom.impl;

import com.devstack.pos.dao.custom.UserDao;
import com.devstack.pos.db.HibernateUtil;
import com.devstack.pos.entity.User;
import com.devstack.pos.entity.UserRole;
import com.devstack.pos.util.KeyGenerator;
import com.devstack.pos.util.PasswordGenerator;
import com.devstack.pos.util.ResponseData;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean create(User user) {
        // mysql da vealaya seira hibernate sutti try resource a use sei
        // User ondu inhinekki (user reference ikku) build aahi vandhu mudinchii so try resource mattum thaan poda vendum (ennamo vealaggalla)

        try (Session session = HibernateUtil.getSession()){
            session.save(user);
            session.close();
        }
        return true;
    }

    @Override
    public User find(Long id) {
        return null;
    }

    @Override
    public boolean remove(Long id) {
        try(Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            Query<User> userQuery = session.createQuery("FROM User u WHERE u.propertyId=:id", User.class);
            userQuery.setParameter("id",id);
            User user = userQuery.uniqueResult();
            if (user!=null){
                session.remove(user);
                transaction.commit();
                return true;
            }else {
                throw new RuntimeException("User not found");
            }
        }
    }

    @Override
    public boolean modify(User user) {
        return false;
    }

    @Override
    public List<User> loadAll() {
        try (Session session = HibernateUtil.getSession()){
            return  session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public ResponseData login(String username, String password) {
        try (Session session = HibernateUtil.getSession()){
            Query<User> query = session.createQuery("FROM User WHERE username=:username", User.class);// method la vaara parameter a hql la poda kola use ":" colon
            // kattayam query ikku parameter set pannanum appa thaan azu method la vaara user name thaan database la eera column name
            // "username column" la eela records ikku equal aandu paakkum
            query.setParameter("username",username);
            // username eanum placeholder ikku thaan method la vaara username a anuppanum
            // so uppa unique result a edukkanum query.uniqueResult(); izu User a tharum
            User user = query.uniqueResult();
            if (user!=null && user.isActiveState()){
                if (PasswordGenerator.checkPassword(password,user.getPassword())){
                    return new ResponseData(true,"login success!!");
                }else {
                    return new ResponseData(false,"password is wrong!!!!!!!");
                }
                // true inda access seyya ealum
            }else {
                return new ResponseData(false, "something went wrong with the username or the active state, please contact the admin");
                // false in access seyya ela
            }
        }
    }

    @Override
    public List<User> loadAllUsers(String searchText) {
        /*try (Session session = HibernateUtil.getSession()){
                return  session.createQuery("FROM User", User.class).list();
        }*/

        /*try (Session session = HibernateUtil.getSession()){
            Query<User> userQuery = session.createQuery("SELECT u FROM User u WHERE u.displayName=:name", User.class);
            userQuery.setParameter("name",searchText);
            return userQuery.getResultList();
        }*/ // iza query ikku display name fulla type panni nonna azu mattum thaan varum so like query a adichchanum

        try (Session session = HibernateUtil.getSession()){
            Query<User> userQuery = session.createQuery("SELECT u FROM User u WHERE u.displayName LIKE :name", User.class);
            userQuery.setParameter("name","%"+searchText+"%");
            // like query adichchakola searchText ikku munnukkum pinnumkkum % podanum (concat)
            return userQuery.getResultList();
        }
    }

    @Override
    public void createNewSystemUser(Long userRoleId, String displayName, String email) {
        try (Session session = HibernateUtil.getSession()){
            // check user da email,role eera? then save
            // user email
            Query<User> userQuery = session.createQuery("FROM User WHERE username=:username", User.class);
            userQuery.setParameter("username", email);
            User user = userQuery.uniqueResult();
            if (user!=null){
                throw new RuntimeException("User already exist!");
            }

            // user role
            Query<UserRole> userRoleQuery = session.createQuery("FROM userrole WHERE property_id=:userroleId", UserRole.class);
            userRoleQuery.setParameter("userroleId",userRoleId);
            UserRole userRole = userRoleQuery.uniqueResult();
            if (userRole==null){
                throw new RuntimeException("User-role not found!");
            }

            // save
            Transaction transaction = session.beginTransaction();
            /*session.save(new User(KeyGenerator.generateId(), email, displayName,
                            PasswordGenerator.passwordGen(6), true, userRole));*/
            User createdUser = new User(KeyGenerator.generateId(), email, displayName,
                    PasswordGenerator.passwordGen(6), true, userRole);
            session.save(createdUser);
            transaction.commit();
            System.out.println(createdUser.getPassword());
        }
    }

    @Override
    public void updateSystemUser(Long userRoleId, String displayName, String email, Long userId) {
        try(Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            Query<User> userQuery = session.createQuery("FROM User u WHERE u.propertyId=:id", User.class);
            userQuery.setParameter("id",userId);
            User user = userQuery.uniqueResult();

            Query<UserRole> userRoleQuery = session.createQuery("FROM userrole u WHERE u.propertyId=:id", UserRole.class);
            userRoleQuery.setParameter("id",userRoleId);
            UserRole userRole = userRoleQuery.uniqueResult();

            if (userRole==null){
                throw new RuntimeException("user role not found");
            }

            user.setUserName(email);
            user.setUserRole(userRole);
            user.setDisplayName(displayName);

            if (user!=null){
                session.update(user);
                transaction.commit();
            }else {
                throw new RuntimeException("user not found");
            }
        }
    }
}
