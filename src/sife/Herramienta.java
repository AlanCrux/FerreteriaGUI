package sife;

import java.io.Serializable;

/**
 *
 * @author Alan Yoset García C
 */
public class Herramienta implements Serializable{
  private String clave; 
  private String nombre;
  private String descripcion;
  private String tipoUnidad;
  private double precioCompra;
  private double precioVenta;
  private int existencia;
  
  public Herramienta(){
    
  }

  public Herramienta(String clave, String nombre, String descripcion, String tipoUnidad, double precioCompra, double precioVenta, int existencia) {
    this.clave = clave;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.tipoUnidad = tipoUnidad;
    this.precioCompra = precioCompra;
    this.precioVenta = precioVenta;
    this.existencia = existencia;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getTipoUnidad() {
    return tipoUnidad;
  }

  public void setTipoUnidad(String tipoUnidad) {
    this.tipoUnidad = tipoUnidad;
  }

  public double getPrecioCompra() {
    return precioCompra;
  }

  public void setPrecioCompra(double precioCompra) {
    this.precioCompra = precioCompra;
  }

  public double getPrecioVenta() {
    return precioVenta;
  }

  public void setPrecioVenta(double precioVenta) {
    this.precioVenta = precioVenta;
  }

  public int getExistencia() {
    return existencia;
  }

  public void setExistencia(int existencia) {
    this.existencia = existencia;
  }
  
  @Override
  public String toString(){
    return this.clave + " | " + this.nombre + " | $"+ precioVenta+" | Ex:"+existencia; 
  }
  
}
