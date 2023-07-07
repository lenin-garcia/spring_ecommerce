
package com.curso.ecommerce.services;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.repository.ProductoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *Aqui implemetentaremos todos los metodos abstractos del servicio.
 *Usamos un atributo de tipo interfaz que implemento JpaRepository para usar los crud de jpa
 */

@Service
public class ProductoServiceImpl implements ProductoService{
    
    @Autowired
    private ProductoRepository productoRepository;
    
    

    //almacena un producto en la tabla
    @Override
    public Producto save(Producto producto) {
        productoRepository.save(producto);
        return producto;
    }

    //devuelve el producto por el Id
    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepository.findById(id);
    }

    /*
    *usamos el metodo save porque automaticamente verifica si el id es null creara el prodcuto,
    *si lo psamos con un id existente lo actualizara.
    */ 
    @Override
    public void update(Producto producto) {
      productoRepository.save(producto);
    }

    //eliminara el prodcuto que le coincida con el ide que le pasemos
    @Override
    public void delete(Integer id) {
      productoRepository.deleteById(id);
    }
    
}
