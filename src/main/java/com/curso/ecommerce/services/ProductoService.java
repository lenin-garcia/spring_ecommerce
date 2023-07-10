package com.curso.ecommerce.services;

import com.curso.ecommerce.model.Producto;
import java.util.List;
import java.util.Optional;

/**
 * Seran los CRUD mas especificos sobre nuestra entity
 */
public interface ProductoService {

    public Producto save(Producto producto);

    //nos permite validar si el objeto que mandamos a llamar de la BBDD existe o no.
    public Optional<Producto> get(Integer id);

    public void update(Producto producto);

    public void delete(Integer id);

    public List<Producto> finAll();
}
