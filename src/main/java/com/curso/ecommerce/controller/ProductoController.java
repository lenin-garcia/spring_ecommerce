
package com.curso.ecommerce.controller;



import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.services.ProductoService;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    //creamos para controlar lo que va haceindo la app
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    //variable para acceder a los metodos que interactuan con la bbdd
    @Autowired
    private ProductoService productoService;
    
    
   /*Model lleva informacioncion del backend  hacia la vista
    *
    */
    //muestra todos los productos listados
    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productos", productoService.finAll());//Al agregar el atributo "productos" al modelo del controlador, se está pasando esa lista de productos a la vista correspondiente
        return "productos/show";
    }
    
    @GetMapping("/create")
    public String create(){
        return "productos/create" ;
    }
    
    
    //redirect: se utiliza para redirigir al navegador a otra ruta o página
    /*Al agregar @RequestBody antes del parámetro producto, estás indicando
    *explícitamente que el objeto producto debe obtener sus valores del cuerpo 
    *de la solicitud HTTP NOTA: al agregarselo al codgo me da error por el UTF-8
    * LOGGER.info("Este el el objeto producto{}", producto); si no se le colocan las llaves, no muestra la informacion del producto
    * para mostrar la informacion del producto este debe tener el metodo toString
    *el prodcuto debe estar asociado a un usuario
    */
   @PostMapping("/save")
    public String save( Producto producto){
        LOGGER.info("Este el el objeto producto{}", producto);
        Usuario user = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(user);
        productoService.save(producto);
        return "redirect:/productos";
    }
 
    /*PathVariable mapea la url para obtener la vaiable
    *
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
    //almacenamos el objeto buscado por el id
        Producto producto = new Producto();
    //Creamos una variable de tipo Optional porque puede que el prodcuto este o no
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();
        LOGGER.info("Este es el prodcuto que vamos a editar{}",producto);
    
    //al objeto model se le agrega el atributo que contendra el producto encontrado por el id
        model.addAttribute("producto", producto);
        return "productos/edit";
    }
    

    
    //metodo que va a actualizar el producto
    @PostMapping("/update")
    public String update(Producto producto){
        productoService.update(producto);
        return "redirect:/productos";
    }
    
    
}
