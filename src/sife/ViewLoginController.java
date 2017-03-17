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
  @FXML private TextField textUser;
  @FXML private PasswordField textClave;

  private SIFE ProgramaPrincipal;

  @FXML
  private void inventarioSuper(ActionEvent event) {
    if (textUser.getText().equals(user)) {
      if (textClave.getText().equals(clave)) {
        ProgramaPrincipal.cargarPrincipal();
        textClave.clear();
        textUser.clear();
      } else{
        System.out.println("Error de clave");
      }
    } else {
      System.out.println("Error de usuario");
    }
  }
  @FXML
  private void inventarioObservable(ActionEvent event) {
    ProgramaPrincipal.cargarPrincipalObservable();
  }

  public void setProgramaPrincipal(SIFE ProgramaPrincipal) {
    this.ProgramaPrincipal = ProgramaPrincipal;
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

}
