package sife;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Alan Yoset García C
 */
public class SIFE extends Application {

  private Stage stagePrincipal;
  private AnchorPane rootPane;

  @Override
  public void start(Stage stagePrincipal) throws Exception {
    this.stagePrincipal = stagePrincipal;
    mostrarVentanaPrincipal();

  }

  /*
     * cargamos la ventana principal
   */
  public void mostrarVentanaPrincipal() {
    try {
      FXMLLoader loader = new FXMLLoader(SIFE.class.getResource("ViewLogin.fxml"));
      rootPane = (AnchorPane) loader.load();
      Scene scene = new Scene(rootPane);
      stagePrincipal.setTitle("Ventana Principal");
      stagePrincipal.setScene(scene);
      ViewLoginController controller = loader.getController();
      controller.setProgramaPrincipal(this);
      stagePrincipal.show();
    } catch (IOException e) {
      //tratar la excepción.
    }
  }
  
  public void cargarPrincipal() {
    try {
      FXMLLoader loader = new FXMLLoader(SIFE.class.getResource("principal.fxml"));
      AnchorPane ventanaDos = (AnchorPane) loader.load();
      Stage ventana = new Stage();
      ventana.setTitle("Sistema ferreteria");
      ventana.initOwner(stagePrincipal);
      ventana.initModality(Modality.APPLICATION_MODAL);
      Scene scene = new Scene(ventanaDos);
      ventana.setScene(scene);
      PrincipalController controller = loader.getController();
      controller.setStagePrincipal(ventana);
      controller.setEditable(true);
      ventana.showAndWait();

    } catch (Exception e) {
      //tratar la excepción
    }
  }
  
  public void cargarPrincipalObservable() {
    try {
      FXMLLoader loader = new FXMLLoader(SIFE.class.getResource("principal.fxml"));
      AnchorPane ventanaDos = (AnchorPane) loader.load();
      Stage ventana = new Stage();
      ventana.setTitle("Sistema ferreteria - observador");
      ventana.initOwner(stagePrincipal);
      ventana.initModality(Modality.APPLICATION_MODAL);
      Scene scene = new Scene(ventanaDos);
      ventana.setScene(scene);
      PrincipalController controller = loader.getController();
      controller.setStagePrincipal(ventana);
      controller.setEditable(false);
      ventana.showAndWait();

    } catch (Exception e) {
      //tratar la excepción
    }
  }
  
  public void cargarInventario() {
    try {
      FXMLLoader loader = new FXMLLoader(SIFE.class.getResource("ViewInventario.fxml"));
      AnchorPane ventanaDos = (AnchorPane) loader.load();
      Stage ventana = new Stage();
      ventana.setTitle("Inventario - administrador");
      ventana.initOwner(stagePrincipal);
      ventana.initModality(Modality.APPLICATION_MODAL);
      Scene scene = new Scene(ventanaDos);
      ventana.setScene(scene);
      ViewInventarioController controller = loader.getController();
      controller.setStagePrincipal(ventana);
      controller.setEditable(true);
      ventana.showAndWait();

    } catch (Exception e) {
      //tratar la excepción
    }
  }
  
  public void cargarInventarioObservable() {
    try {
      FXMLLoader loader = new FXMLLoader(SIFE.class.getResource("ViewInventario.fxml"));
      AnchorPane ventanaDos = (AnchorPane) loader.load();
      Stage ventana = new Stage();
      ventana.setTitle("Inventario - observador");
      ventana.initOwner(stagePrincipal);
      ventana.initModality(Modality.APPLICATION_MODAL);
      Scene scene = new Scene(ventanaDos);
      ventana.setScene(scene);
      ViewInventarioController controller = loader.getController();
      controller.setStagePrincipal(ventana);
      controller.setEditable(false);
      ventana.showAndWait();

    } catch (Exception e) {
      //tratar la excepción
    }
  }
  
  public void cargarVentas() {
    try {
      FXMLLoader loader = new FXMLLoader(SIFE.class.getResource("VistaVentas.fxml"));
      AnchorPane ventanaDos = (AnchorPane) loader.load();
      Stage ventana = new Stage();
      ventana.setTitle("Ventas - administrador");
      ventana.initOwner(stagePrincipal);
      ventana.initModality(Modality.APPLICATION_MODAL);
      Scene scene = new Scene(ventanaDos);
      ventana.setScene(scene);
      VistaVentasController controller = loader.getController();
      controller.setStagePrincipal(ventana);
      controller.setEditable(true);
      ventana.showAndWait();

    } catch (Exception e) {
      //tratar la excepción
    }
  }
  
  public void cargarVentasObservable() {
    try {
      FXMLLoader loader = new FXMLLoader(SIFE.class.getResource("VistaVentas.fxml"));
      AnchorPane ventanaDos = (AnchorPane) loader.load();
      Stage ventana = new Stage();
      ventana.setTitle("Ventas - observable");
      ventana.initOwner(stagePrincipal);
      ventana.initModality(Modality.APPLICATION_MODAL);
      Scene scene = new Scene(ventanaDos);
      ventana.setScene(scene);
      VistaVentasController controller = loader.getController();
      controller.setStagePrincipal(ventana);
      controller.setEditable(false);
      ventana.showAndWait();

    } catch (Exception e) {
      //tratar la excepción
    }
  }
  
  public void cargarReportes() {
    try {
      FXMLLoader loader = new FXMLLoader(SIFE.class.getResource("VistaDetalleVenta.fxml"));
      AnchorPane ventanaDos = (AnchorPane) loader.load();
      Stage ventana = new Stage();
      ventana.setTitle("Reportes - administrador");
      ventana.initOwner(stagePrincipal);
      ventana.initModality(Modality.APPLICATION_MODAL);
      Scene scene = new Scene(ventanaDos);
      ventana.setScene(scene);
      VistaDetalleVentaController controller = loader.getController();
      controller.setStagePrincipal(ventana);
      controller.setEditable(true);
      ventana.showAndWait();

    } catch (Exception e) {
      //tratar la excepción
    }
  }
  
  public void cargarReportesObservable() {
    try {
      FXMLLoader loader = new FXMLLoader(SIFE.class.getResource("VistaDetalleVenta.fxml"));
      AnchorPane ventanaDos = (AnchorPane) loader.load();
      Stage ventana = new Stage();
      ventana.setTitle("Reportes - observable");
      ventana.initOwner(stagePrincipal);
      ventana.initModality(Modality.APPLICATION_MODAL);
      Scene scene = new Scene(ventanaDos);
      ventana.setScene(scene);
      VistaDetalleVentaController controller = loader.getController();
      controller.setStagePrincipal(ventana);
      controller.setEditable(false);
      ventana.showAndWait();

    } catch (Exception e) {
      //tratar la excepción
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

}
