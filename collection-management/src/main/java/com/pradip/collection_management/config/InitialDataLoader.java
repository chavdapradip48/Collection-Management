package com.pradip.collection_management.config;

import com.pradip.collection_management.model.Permission;
import com.pradip.collection_management.model.Role;
import com.pradip.collection_management.model.User;
import com.pradip.collection_management.repository.PermissionRepository;
import com.pradip.collection_management.repository.RoleRepository;
import com.pradip.collection_management.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class InitialDataLoader implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final List<String> resources = Arrays.asList("USER", "PERMISSION");
    private final List<String> actions = Arrays.asList("CREATE", "READ", "UPDATE", "DELETE");
    private static final Map<String, String> roles = Map.of(
            "MASTER_ADMIN_ROLE", "",
            "ADMIN_ROLE","USER_",
            "USER_ROLE", "USER_READ"
    );

    private final String MASTER_ADMIN_EMAIL = "master.admin@test.com";
    private final String MASTER_ADMIN_PASSWORD = "masteradmin123";

    private Map<String, Permission> generatePermissions() {
        return resources.stream()
                .flatMap(resource -> actions.stream()
                        .map(action -> Map.entry(resource + "_" + action, createPermission(resource, action))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    
    @Override
    public void run(String... args) throws Exception {

        Map<String, Permission> allPermissions = generatePermissions();

        Map<String, Role> allRoles = roles.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey, entry -> createRoleWithRule(allPermissions, entry.getKey(),entry.getKey())));

        if (userRepository.findByEmail(MASTER_ADMIN_EMAIL).isEmpty()) {
            User masterAdmin = new User();
            masterAdmin.setEmail(MASTER_ADMIN_EMAIL);
            masterAdmin.setPassword(MASTER_ADMIN_PASSWORD);
            masterAdmin.setName("Pradip Master");
            masterAdmin.setMobile(1231231230L);
            masterAdmin.setRoles(Set.of(allRoles.get("MASTER_ADMIN_ROLE")));
            masterAdmin = userRepository.save(masterAdmin);

            System.out.println("User with MASTER_ADMIN_ROLE has been created: " +masterAdmin);
        }
    }

    private Permission createPermission(String resource, String action) {
        return permissionRepository.findByResourceAndAction(resource, action)
                .orElseGet(() -> {
                    Permission permission = new Permission(0L, resource + "_" + action,
                            "This Permission for the "+resource+" resource and user can perform "+action+" action in this.",resource, action);
                    System.out.println("Permission has been created with: " + permission);
                    return permissionRepository.save(permission);
                });
    }

    private Role createRole(String name, Set<Permission> permissions) {
        return roleRepository.findByName(name)
                .orElseGet(() -> {
                    Role role = new Role(0L, name, name+ " role is having permission :  "+permissions.stream().map(Permission::getName).collect(Collectors.joining(", ")), permissions);
                    System.out.println("Permission has been created with: " + role);
                    return roleRepository.save(role);
                });
    }

    private HashSet<Permission> getPermissionsByStartKey(Map<String, Permission> allPermissions, String startCheckKey){
        return new HashSet<>(allPermissions.entrySet().stream().
                filter(entry -> entry.getKey().startsWith(startCheckKey)).map(Map.Entry::getValue).toList());
    }

    private Role createRoleWithRule(Map<String, Permission> allPermissions, String roleName, String extractRuleKey) {
        return createRole(roleName, getPermissionsByStartKey(allPermissions, extractRuleKey));
    }
}
