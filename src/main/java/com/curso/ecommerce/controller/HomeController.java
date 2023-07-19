package com.curso.ecommerce.controller;

import com.curso.ecommerce.model.DetalleOrden;
import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.repository.ProductoRepository;
import com.curso.ecommerce.services.ProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    //traera los productos desde la bbdd
    @Autowired
    private ProductoService productoService;

    private Logger log = LoggerFactory.getLogger(HomeController.class);

    //listado que contendra todos los detalles
    private List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    //almacenara la orden
    private Orden orden = new Orden();

    //enviara los prodcutos a la vista desde la bbdd con el objeto Model
    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("productos", productoService.finAll());// con esto enviamos a la vista desde el cont
        return "usuario/home";
    }

    //nos llevara del producto hasta la vista del detalle del producto
    @GetMapping("/productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        log.info("Id enviado para mostrar detalle de producto{}", id);
        Producto producto = new Producto();
        Optional<Producto> produtoOptional = productoService.get(id);
        producto = produtoOptional.get(); //obtenemos realmente el producto
        model.addAttribute("producto", producto); //enviamos el prodcto a la vista
        return "usuario/productohome";
    }

    //redireccionara a la vista del carrito de compra
    //para agregar el prodctuo al carrito necesitamos la cantidad y el id
    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto(); // almacenara el producto
        double sumaTotal = 0.0; // almacenara el total de los prodcutos

        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("El prodcuto añadido es: {}", optionalProducto.get());
        log.info("Cantidad: {}", cantidad);

        //obtenemos informacion del producto
        producto = optionalProducto.get();//obtenemos el producto
        detalleOrden.setCantidad(cantidad);//seteamos la cantidad que entra como parametro
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);//guarda el id del producto

        //agregamos detallles a la lista
        detalles.add(detalleOrden);

        //sumamos el total del carrito
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();//sumara todos los totales que esten en esa lista

        //pasamos las variable a la vista para mostrarlas
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }

    //quitar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProcutoCart(@PathVariable Integer id, Model model) {

        //al eliminar el el producto tenemos que crear una lista nueva 
        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();
        // si el id no coincide lo agregara a la lista nueva
        for (DetalleOrden detalleOrden : detalles) {
            if (detalleOrden.getProducto().getId() != id) {
                ordenesNueva.add(detalleOrden);
            }
        }
        //nueva lista con los prodcutos que queden
        detalles = ordenesNueva;

        //hay que racalcular nuevamente el total y enviamos la informacion a la vista
        double sumaTotal = 0.0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }

}
