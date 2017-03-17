package sife;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Alan Yoset García C
 */
public class SIFE extends Application {
  
  @Override
  public void start(Stage stage) throws IOException {
    //Cargar el contenido del archivo ventana.fxml, procesarlo y crear el
    //contenido a partor del mismo.
    Parent root = FXMLLoader.load(getClass().getResource("ViewLogin.fxml"));

    //Asiganar las UI creadas desde .fxml a una Scene
    Scene scene = new Scene(root);

    //Asignar la scene a la ventana principal o stage y mostrarla.
    stage.setTitle("Iniciar sesión");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
