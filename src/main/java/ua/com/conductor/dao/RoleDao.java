package ua.com.conductor.dao;

import ua.com.conductor.model.Role;

public interface RoleDao {
    void add(Role role);

    Role getRoleByName(String roleName);
}
