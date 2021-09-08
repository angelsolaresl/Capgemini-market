package com.capgemini.market.persistence.mapper;

import com.capgemini.market.domain.Category;
import com.capgemini.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//esta interfaz es un mapeadorpor lo tanto lleva @Mapper
@Mapper(componentModel  ="spring")

public interface CategoryMapper {

    @Mappings({
            //source: fuente, target: destino
            @Mapping(source = "idCategoria",target = "categoryId"),
            @Mapping(source = "descripcion",target = "category"),
            @Mapping(source = "estado",target = "active")
    })
    Category toCategory(Categoria categoria);

    //la conversion es inversa a la que se hace arriba (Mapping)
    @InheritInverseConfiguration
    @Mapping(target ="productos", ignore = true)
    Categoria toCategoria(Category category);


}
