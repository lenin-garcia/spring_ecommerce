
package com.curso.ecommerce.services;

import com.curso.ecommerce.model.Usuario;
import java.util.Optional;

/**
 *
 */
public interface IUsuarioService {
    //obtener un usaurio de la bdd
     Optional<Usuario> finByID(Integer id);
    
    
    
}
