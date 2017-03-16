package sife;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Alan Yoset García C
 */
public class ViewLoginController implements Initializable {
  @FXML private Button botonIngresar;
  @FXML private TextField textUser;
  @FXML private TextField textPassword;   
  @FXML private Label mensajeIngreso; 
 
  Usuario administrador = new Usuario("administrador","12345");
  Usuario observador = new Usuario("observador","54321"); 
  @FXML
  private Label labelTitulo;
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    mensajeIngreso.setText("");
    botonIngresar.setOnAction(event -> {
      checkUser(textUser.getText(), textPassword.getText());
    });
  }  
  
  public void checkUser(String user, String password){
    if (user.equals("administrador")) {
      checkPassword(administrador, password);
    } else if (user.equals("observador")) {
      checkPassword(observador, password);
    } else {
      mensajeIngreso.setText("Ese usuario no existe");
      mensajeIngreso.setTextFill(Color.RED);
    } 
  }
  
  public void checkPassword(Usuario usuario, String password){
    if (usuario.getPassword().equals(password)) {
      mensajeIngreso.setText("Usuario correcto");
      mensajeIngreso.setTextFill(Color.GREEN);
    } else {
      mensajeIngreso.setText("Error de password");
      mensajeIngreso.setTextFill(Color.RED);
    }
  }
  
}
