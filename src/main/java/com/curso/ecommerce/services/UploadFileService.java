
package com.curso.ecommerce.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *Permitira subir imagen y eliminarla
 */
@Service
public class UploadFileService {
   
    /*tendra la ubicacion del proyecto en donde se cargaran la imagenes
    *estas imagenes las tendre en el proyecto, no el la bbdd 
    *almacenaremos el nombre de la imagen en la bbdd para optimizar recursos
    */ 
    private String folder= "images//"; //carpeta donde se guardaran las imagenes
    
   /*Se usa para recibir archivos de formularios
    *encapsula inf relacioanada con el archivo, nombre, tamaño, tipo de contenido y bytes reales del archivo.
    *Spring vicula de manera automatica el archivo al controlador
    */ 
    
    public String saveImage(MultipartFile file) throws IOException{
    
        // si el formulario tiene imagen entrara aqui..
        if (!file.isEmpty()) {
         byte []cantidadDeBytes = file.getBytes(); //obtenemos los bytes como parte de la transferencai de datos
         Path path = Paths.get(folder + file.getOriginalFilename());// Path lo usamos cuando vayamos a trabajar con rutas 
         Files.write(path, cantidadDeBytes);//Guarda la imagen en bytes en la ruta que le estamos indicando 
         return file.getOriginalFilename();//devolvera el nombre dde la imagen que he subido
        }
        
        //si la imagen esta vacia, mandaremos una imagen por default
        return "default.jpg";
    }
    
    //borrara la imagen
    public void deleteImage(String nombre){
        String ruta = "images//";
        File file = new File(ruta+nombre);
        file.delete();
    }
}
