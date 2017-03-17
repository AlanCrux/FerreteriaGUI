package sife;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alan Yoset García C
 */
public class ViewLoginController implements Initializable {

  
  private final String user = "super";
  private final String clave = "&super";
  @FXML private Button botonIngresar;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    botonIngresar.setOnAction((event) -> {
      Node node = (Node) event.getSource();
      Stage stage = (Stage) node.getScene().getWindow();
      Parent root;
      try {
        root = FXMLLoader.load(getClass().getResource("ViewInventario.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventario");
        stage.centerOnScreen();
        stage.show();
      } catch (IOException ex) {
        Logger.getLogger(ViewInventarioController.class.getName()).log(Level.SEVERE, null, ex);
      }
    });
  }
  
 
  
}
