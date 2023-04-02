package it.revo.revoservice.repository.rest;

import it.revo.revoservice.entity.WhereGo;
import it.revo.revoservice.projection.CustomWhereGo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4000")
@RepositoryRestResource(path = "whereGo", collectionResourceRel = "list", excerptProjection = CustomWhereGo.class)
public interface WhereGoRepository extends JpaRepository<WhereGo, Integer> {

}
