
package com.curso.ecommerce.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nombre;
    private String userName;
    private String email;
    private String direccion;
    private String telefono;
    private String tipo; // hace referecnia al tipo de usuaio Administrador o usuario normal
    private String password;
    
    @OneToMany(mappedBy = "usuario")
    private List<Producto> listadoProductos; //Un usuario tendra un listado de productos subidos.

    @OneToMany(mappedBy = "usuario")
    private List<Orden> listadoOrdendes;
    
    
    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String userName, String email, String direccion, String telefono, String tipo, String password) {
        this.id = id;
        this.nombre = nombre;
        this.userName = userName;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.password = password;

    }

  

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserNae() {
        return userName;
    }

    public void setUserNae(String userNae) {
        this.userName = userNae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", userNae=" + userName + ", email=" + email + ", direccion=" + direccion + ", telefono=" + telefono + ", tipo=" + tipo + ", password=" + password + '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Producto> getListadoProductos() {
        return listadoProductos;
    }

    public void setListadoProductos(List<Producto> listadoProductos) {
        this.listadoProductos = listadoProductos;
    }

    public List<Orden> getListadoOrdendes() {
        return listadoOrdendes;
    }

    public void setListadoOrdendes(List<Orden> listadoOrdendes) {
        this.listadoOrdendes = listadoOrdendes;
    }
    
    
    
}
