package sife;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Alan Yoset García C
 */
public class Venta implements Serializable{
  private String idNota;
  private String fecha;
  private double subtotal;
  private double iva;
  private double total; 
  private ArrayList<HerramientaVenta> articulos = new ArrayList<>(); 

  public String getIdNota() {
    return idNota;
  }

  public void setIdNota(String idNota) {
    this.idNota = idNota;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(double subtotal) {
    this.subtotal = subtotal;
  }

  public double getIva() {
    return iva;
  }

  public void setIva(double iva) {
    this.iva = iva;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public ArrayList<HerramientaVenta> getArticulos() {
    return articulos;
  }

  public void setArticulos(ArrayList<HerramientaVenta> articulos) {
    this.articulos = articulos;
  }
  
  public void addHerramienta(HerramientaVenta venta){
    articulos.add(venta);
  }

  @Override
  public String toString() {
    return "Folio: "+idNota+" | "+fecha+" | "+total;
  }
  
}
