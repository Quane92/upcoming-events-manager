package com.zawartkawoj.upcomingevents.repository;

import com.zawartkawoj.upcomingevents.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByName(String name);
}
