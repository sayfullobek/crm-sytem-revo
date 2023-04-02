package it.revo.revoservice.projection;

import it.revo.revoservice.entity.WhereGo;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = WhereGo.class, name = "customWhereGo")
public interface CustomWhereGo {
    Integer getId();

    String getName();
}
