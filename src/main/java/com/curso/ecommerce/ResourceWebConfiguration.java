
package com.curso.ecommerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*El método addResourceHandlers es un método de la interfaz WebMvcConfigurer en Spring MVC que se utiliza para configurar el manejo de recursos estáticos, como imágenes, archivos CSS o JavaScript.
*Cuando se recibe una solicitud que comienza con /images/, Spring MVC buscará los recursos correspondientes en la ubicación física file:images/
 *El doble asteris indicara que traera todo lo que se tenga en ese directorio **
 */
@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/images/**").addResourceLocations("file:images/");// ruta donde buscara las iamgenes
    }
   
    
    
}
