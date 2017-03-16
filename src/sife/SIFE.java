/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sife;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Alan Yoset García C
 */
public class SIFE extends Application {
  
  @Override
  public void start(Stage primaryStage) {
    try {
      FXMLLoader login = new FXMLLoader(getClass().getResource("ViewLogin.fxml"));
      FXMLLoader inventario = new FXMLLoader(getClass().getResource("ViewInventario.fxml"));
      FXMLLoader ventas = new FXMLLoader(getClass().getResource("VistaVentas.fxml"));
      FXMLLoader detalleventas = new FXMLLoader(getClass().getResource("VistaDetalleVenta.fxml"));
      
      Parent page = login.load();
      Parent vistainventario = inventario.load();
      Parent vistaVentas = ventas.load();
      Parent vistaDetalleVentas = detalleventas.load();

      Scene scene = new Scene(vistaDetalleVentas); 
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
    }
    
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}
