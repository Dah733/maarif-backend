package com.maarif.maarifbackend.inscription.repositories;

import com.maarif.maarifbackend.inscription.entities.TempStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin( origins = "*")
public interface TempStudentsRepository extends JpaRepository<TempStudent, Long> {
}
