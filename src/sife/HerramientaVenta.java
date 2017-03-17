package sife;

import java.io.Serializable;

/**
 *
 * @author Alan Yoset García C
 */
public final class HerramientaVenta implements Serializable{
  private String clave;
  private String nombre;
  private String precio;
  private String cantidad; 
  private String subtotal; 

  public HerramientaVenta(){
    clave = "";
    nombre = "";
    precio = "";
    cantidad = "";
    subtotal = "";
  }

  public HerramientaVenta(String clave, String nombre, String precio, String cantidad, String subtotal) {
    this.clave = clave;
    this.nombre = nombre;
    this.precio = precio;
    this.cantidad = cantidad;
    this.subtotal = subtotal;
  }

  public String getClave() {
    return clave;
  }

  public String getNombre() {
    return nombre;
  }

  public String getPrecio() {
    return precio;
  }

  public String getCantidad() {
    return cantidad;
  }

  public String getSubtotal() {
    return subtotal;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setPrecio(String precio) {
    this.precio = precio;
  }

  public void setCantidad(String cantidad) {
    this.cantidad = cantidad;
  }

  public void setSubtotal(String subtotal) {
    this.subtotal = subtotal;
  }

  @Override
  public String toString() {
    return nombre + " | $" + precio + " | Cantidad: " + cantidad + " | Sub: $" + subtotal;
  }
  
  
}
