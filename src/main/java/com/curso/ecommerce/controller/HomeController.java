package com.curso.ecommerce.controller;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.repository.ProductoRepository;
import com.curso.ecommerce.services.ProductoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    
    //traera los productos desde la bbdd
    @Autowired
    private ProductoService productoService;
    
    private Logger log = LoggerFactory.getLogger(HomeController.class);

    //enviara los prodcutos a la vista desde la bbdd con el objeto Model
    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("productos", productoService.finAll());// con esto enviamos a la vista desde el cont
        return "usuario/home";
    }
    
    
    //nos llevara del producto hasta la vista del detalle del producto
    @GetMapping("/productohome/{id}")
    public String productoHome (@PathVariable Integer id ){
        log.info("Id enviado para mostrar detalle de producto{}",id);
        return "usuario/productohome";
    }

}
