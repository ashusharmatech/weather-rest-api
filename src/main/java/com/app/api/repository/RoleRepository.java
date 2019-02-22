package com.app.api.repository;

import com.app.api.model.Role;
import com.app.api.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsRoleByName(RoleName name);
    Role findByName(RoleName name);
    Role findById(Long id);

    List<Role> findAllByName(RoleName roleUser);
}
