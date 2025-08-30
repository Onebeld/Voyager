package com.onebeld.voyager.initializers;

import com.onebeld.voyager.entities.Role;
import com.onebeld.voyager.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseRoleInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public DatabaseRoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeDatabase();
    }

    private void initializeDatabase() {
        if (roleRepository.count() != 0)
            return;

        List<Role> roles = List.of(
                new Role("ADMIN"),
                new Role("EMPLOYEE"),
                new Role("USER")
        );

        roleRepository.saveAll(roles);
    }
}
