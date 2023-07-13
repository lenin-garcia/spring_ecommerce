
package com.curso.ecommerce.controller;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.services.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    //inyectamos para acceder al la bbdd
    @Autowired
    private ProductoService productoService;
    
    //AL entrar al home mostrara el lisatdo de productos
    @GetMapping
    public String home(Model model){
        List<Producto> productos = productoService.finAll() ;// traemos el listado de productos
        model.addAttribute("productos",productos);//manda el listado de productos a la vista home
        
    return "administrador/home";
    }
    
}
