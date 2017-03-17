package sife;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    
  }  
  
  @FXML
 private void handleButtonAction(ActionEvent event) throws IOException{
     Stage stage; 
     Parent root;
     if(event.getSource()==botonIngresar){
        //get reference to the button's stage         
        stage=(Stage) botonIngresar.getScene().getWindow();
        //load up OTHER FXML document
         root = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
      }
     else{
       stage=(Stage) botonIngresar.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
      }
     //create a new scene with root and set the stage
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
  
}
