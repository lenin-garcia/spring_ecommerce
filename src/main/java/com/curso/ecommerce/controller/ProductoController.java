package com.curso.ecommerce.controller;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.services.ProductoService;
import com.curso.ecommerce.services.UploadFileService;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    //creamos para controlar lo que va haceindo la app
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    //Inyectamos el servicio que hemos creado para cargar y eliminar imagenes
    @Autowired
    private UploadFileService upload;

    //variable para acceder a los metodos que interactuan con la bbdd
    @Autowired
    private ProductoService productoService;

    /*Model lleva informacioncion del backend  hacia la vista
    *
     */
    //muestra todos los productos listados
    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos", productoService.finAll());//Al agregar el atributo "productos" al modelo del controlador, se está pasando esa lista de productos a la vista correspondiente
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    //redirect: se utiliza para redirigir al navegador a otra ruta o página
    /*Al agregar @RequestBody antes del parámetro producto, estás indicando
    *explícitamente que el objeto producto debe obtener sus valores del cuerpo 
    *de la solicitud HTTP NOTA: al agregarselo al codgo me da error por el UTF-8
    * LOGGER.info("Este el el objeto producto{}", producto); si no se le colocan las llaves, no muestra la informacion del producto
    * para mostrar la informacion del producto este debe tener el metodo toString
    *el prodcuto debe estar asociado a un usuario
    *@RequestParam("img") MultipartFile file indicamos que estamos recibiendo por parametro una archivo
    *img nos idica que vendra del formulario en el campo con nombre img, lo almacenara en la variable file
     */
    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        LOGGER.info("Este el el objeto producto{}", producto);
        Usuario user = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(user);

        //*****carga de imagen********
        //entrara en el if cuando la imagen sea cargada por primera vez
        if (producto.getId() == null) {// cuando se crea un producto por primera vez siempre su id va a ser null
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        } else {

        }

        productoService.save(producto);
        return "redirect:/productos";
    }

    /*PathVariable mapea la url para obtener la vaiable
    *
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        //almacenamos el objeto buscado por el id
        Producto producto = new Producto();

        //Creamos una variable de tipo Optional porque puede que el prodcuto este o no
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();
        LOGGER.info("Este es el prodcuto que vamos a editar{}", producto);

        //al objeto model se le agrega el atributo que contendra el producto encontrado por el id
        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    //metodo que va a actualizar el producto
    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {

        Producto p = new Producto();
        p = productoService.get(producto.getId()).get();//obtenemos la imagen que tenia antes

        //cuando se modifique un producto pero se cargue la misma imagen
        if (file.isEmpty()) { //editamos el producto pero mantenemos la misma imagen
            producto.setImagen(p.getImagen());// y pasamos esa imagen nuevamente
        } // cuando se modifique el producto y se cambie a imagen
        else {
            //eliminara cuano no sea imagen por defecto
            if (!p.getImagen().equals("default.jpg")) {
                upload.deleteImage(p.getImagen());
            }
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }

    // al eliminar un producto redirecciona a la vista cn todos los productos
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        //borrara los datos de la imagen de la bbdd pero mantendra la imagen en el servidor
        Producto p = new Producto();
        p = productoService.get(id).get();
        //eliminaremos siempre y cuando su imagen no sea por defecto
        if (!p.getImagen().equals("default.jpg")) {
            upload.deleteImage(p.getImagen());
        }

        productoService.delete(id);
        return "redirect:/productos";
    }

}
