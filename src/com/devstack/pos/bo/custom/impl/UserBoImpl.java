package com.devstack.pos.bo.custom.impl;

import com.devstack.pos.bo.custom.UserBo;
import com.devstack.pos.dao.DaoFactory;
import com.devstack.pos.dao.custom.UserDao;
import com.devstack.pos.dao.custom.UserRoleDao;
import com.devstack.pos.db.HibernateUtil;
import com.devstack.pos.dto.UserDto;
import com.devstack.pos.dto.UserRoleDto;
import com.devstack.pos.entity.User;
import com.devstack.pos.entity.UserRole;
import com.devstack.pos.util.KeyGenerator;
import com.devstack.pos.util.PasswordGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    private UserRoleDao userRoleDao = DaoFactory.getDao(DaoFactory.DaoType.USER_ROLE);
    private UserDao userDao = DaoFactory.getDao(DaoFactory.DaoType.USER);
    @Override
    public void initializeSystem() {
        if (!userRoleDao.isExists()){
            // user role illatti izukkulla varum (record illanda)
            // user role onda save pannanum


            try (Session session = HibernateUtil.getSession()) { // izu illatti database create aahazu
                // and pos?createDatabaseIfNotExist izu true aaeekkanum aahazu (session ondu uruvaahama database uruvaahazu)
                // pos?createDatabaseIfNotExist=true

                Transaction transaction = session.beginTransaction();

                // iza pos system run aahum poze user ondu (host or admin) eerandu check pannum user eenndha run seyyum
                // illatti user a create/save pannittu run seyyum
                // and ihina issue ondeera eppodme project run aaha kola user eeraandu check pannum
                // (izu real world la ippidi seyrulla vearaya admin panel onda vechchikondu manually
                // thaan oru user a add panni manage seira)

                UserRole adminRole = new UserRole();
                UserRole userRole = new UserRole();

                adminRole.setPropertyId(KeyGenerator.generateId());
                adminRole.setRoleName("ADMIN");
                adminRole.setRoleDescription("Only for the Admin");

                userRole.setPropertyId(KeyGenerator.generateId());
                userRole.setRoleName("USER");
                userRole.setRoleDescription("Only for the User");

                // so user eam poattukolanum
                User systemAdmin = new User(
                        KeyGenerator.generateId(),"admin@gmail.com",
                        "Admin", PasswordGenerator.passwordGen(6),
                        true,adminRole);

                session.save(adminRole);
                session.save(userRole);
                session.save(systemAdmin);
                transaction.commit();

                // iza maai role set pandrazu stand alone app sutti (izula login vara mundhiye roles and user initialize
                // aahi server ikku data pohum so direct aa login aahalaam)
                // app a centralize pannina system ippidi alla veara maai maarum (izukku sign up form um varum)

            }
        }
    }

    @Override
    public List<UserDto> loadAllUsers(String searchText) {
        List<UserDto> dtos = new ArrayList<>();
        for (User userList : userDao.loadAllUsers(searchText)
             ) {
            dtos.add(new UserDto(userList.getPropertyId(), userList.getUserName(),
                    userList.getDisplayName(), userList.getPassword(),
                    userList.isActiveState(),
                    new UserRoleDto(userList.getUserRole().getPropertyId(),
                            userList.getUserRole().getRoleName(),
                            userList.getUserRole().getRoleDescription())));
        }
        return dtos;
    }

    @Override
    public void createNewSystemUser(Long userRoleId, String displayName, String email) {
        userDao.createNewSystemUser(userRoleId, displayName, email);
    }

    @Override
    public void updateSystemUser(Long userRoleId, String displayName, String email, Long userId) {
        userDao.updateSystemUser(userRoleId, displayName, email, userId);
    }

    @Override
    public boolean dropUser(Long id) {
        return userDao.remove(id);
    }
}
