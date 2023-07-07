
package com.curso.ecommerce.repository;

import com.curso.ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*@Repository no es necesario agregarlo aqui porque ya lo hereda de la superclase
*JpaRepository
**/
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}
