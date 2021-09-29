package com.maarif.maarifbackend.authentification.repositories;

import java.util.Optional;

import com.maarif.maarifbackend.authentification.entities.ERole;
import com.maarif.maarifbackend.authentification.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
