package ua.com.conductor.service;

import ua.com.conductor.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
