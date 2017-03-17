/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sife;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alan Yoset García C
 */
public class PrincipalController implements Initializable {

  @FXML
  private Button botonInventario;
  @FXML
  private Button botonVender;
  @FXML
  private Button botonReportes;
  @FXML
  private TextField texHerramientas;
  @FXML
  private TextField textVentas;
  @FXML
  private TextField textGanancias;
  @FXML
  private TextField textActivos;
  
  private Stage stagePrincipal;
  private boolean isEditable; 
  private SIFE ProgramaPrincipal = new SIFE();
  
  private final Archivo archInventario = new Archivo("inventario.obj");
  private ArrayList<Herramienta> inventario;  
  private final Archivo archNotas = new Archivo("notas.obj");
  private ArrayList<Venta> ventas;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    leerInventario();
    leerVentas();
    setHerramientas();
    setVentas();
    setIngresos();
    setActivos();
  }  
  @FXML
  private void inventario(ActionEvent event) {
    if (isEditable) {
      ProgramaPrincipal.cargarInventario();
    } else {
      ProgramaPrincipal.cargarInventarioObservable();
    }
  }
  @FXML
  private void vender(ActionEvent event) {
    if (isEditable) {
      ProgramaPrincipal.cargarVentas();
    }else{
      System.out.println("Lo siento, no puedes vender");
    }
  }
  @FXML
  private void reportes(ActionEvent event) {
    if (isEditable) {
      ProgramaPrincipal.cargarReportes();
    } else {
      ProgramaPrincipal.cargarReportesObservable();
    } 
  }

  void setStagePrincipal(Stage ventana) {
    this.stagePrincipal = stagePrincipal;
  }

  void setEditable(boolean b) {
    this.isEditable = b; 
  }
  
  public void setHerramientas(){
    texHerramientas.setText(inventario.size()+"");
  }
  
  public void setVentas(){
    textVentas.setText(ventas.size()+"");
  }
  
  public void setIngresos(){
    double ingresos = 0;
    for (int i = 0; i < ventas.size(); i++) {
      ingresos += (ventas.get(i).getSubtotal() - (ventas.get(i).getSubtotal()/1.5));
    }
    textGanancias.setText(ingresos+"");
  }
  
  public void setActivos(){
    double activos = 0;
    for (int i = 0; i < inventario.size(); i++) {
      activos += (inventario.get(i).getPrecioCompra() * inventario.get(i).getExistencia());
    }
    textActivos.setText(activos+"");
  }
  
  public void leerInventario(){
    if (archInventario.existencia()) {
      inventario = archInventario.entrada();
    } else {
      inventario = new ArrayList();
    }
  }
  
  public void leerVentas() {
    if (archNotas.existencia()) {
      ventas = archNotas.entrada();
    } else {
      ventas = new ArrayList();
    }
  }
  
}
