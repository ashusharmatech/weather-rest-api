package com.app.api.configuration.setupDataLoaders;

import com.app.api.model.Role;
import com.app.api.model.RoleName;
import com.app.api.model.User;
import com.app.api.service.RoleService;
import com.app.api.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Ashutosh Sharma
 */
@Component
public class UsersRolesLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private RoleService roleService;
    private UserService userService;

    public UsersRolesLoader(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {

        if (alreadySetup) return;

        //region Creating roles
        //================================================================================
        List<Role> adminRoles = Collections.singletonList(createRoleIfNotFound(RoleName.ROLE_ADMIN));
        List<Role> userRoles = Collections.singletonList(createRoleIfNotFound(RoleName.ROLE_USER));
        //================================================================================
        //endregion


        //region Creating users
        //================================================================================
        createUserIfNotFound("admin", "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi",
                "admin", "admin", "admin@admin.com",
                true, new Date(1514764800000L), adminRoles);
        //================================================================================
        //endregion

        alreadySetup = true;
    }

    @org.springframework.transaction.annotation.Transactional
    Role createRoleIfNotFound(RoleName name) {
        boolean existsRoleByName = roleService.existsRoleByName(name);
        if (!existsRoleByName) {
            Role role = new Role(name);
            roleService.save(role);
            return role;
        }
        return null;
    }

    @org.springframework.transaction.annotation.Transactional
    void createUserIfNotFound(String username, String password, String firstName,
                              String lastName, String email, boolean enabled,
                              Date lastPasswordResetDate, List<Role> roles) {
        boolean existsUsersByEmail = userService.existUserByEmail(email);
        if (!existsUsersByEmail) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setEmail(email);
            user.setEnabled(enabled);
            user.setLastPasswordResetDate(lastPasswordResetDate);
            user.setRoles(roles);
            userService.save(user);
        }
    }

}

