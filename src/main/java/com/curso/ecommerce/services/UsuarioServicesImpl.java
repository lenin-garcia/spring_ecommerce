
package com.curso.ecommerce.services;

import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.repository.IUsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicesImpl implements IUsuarioService{

    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    //obtenemos el usuario para 
    @Autowired
    private IUsuarioService usuarioService;
 
    @Override
    public Optional<Usuario> finByID(Integer id) {
        return usuarioRepository.findById(id); 
    }

    
    
    
  
}
