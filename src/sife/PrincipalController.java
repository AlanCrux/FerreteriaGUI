/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sife;

import java.net.URL;
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

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    
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
      ProgramaPrincipal.cargarVentasObservable();
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
  
}
