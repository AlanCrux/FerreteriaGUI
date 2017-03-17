/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sife;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alan Yoset García C
 */
public class VistaDetalleVentaController implements Initializable {

  @FXML
  private ListView<Venta> listaNotas;
  @FXML
  private TextField textBusqueda;
  @FXML
  private Button botonCargar;
  @FXML
  private TextField texFolio;
  @FXML
  private TextField texFecha;
  @FXML
  private ListView<HerramientaVenta> listaProductos;
  @FXML
  private TextField textSubtotal;
  @FXML
  private TextField texIva;
  @FXML
  private TextField textTotal;
  
  private final Archivo archNotas = new Archivo("notas.obj");
  private ArrayList<Venta> ventas;
  
  private ObservableList<Venta> listViewData = FXCollections.observableArrayList();
  private ObservableList<HerramientaVenta> dataHerramientas = FXCollections.observableArrayList();
  private Stage stagePrincipal;
  private boolean isEditable; 

  /**
   * Initializes the controller class.
   */
  
  
  public VistaDetalleVentaController() {
    leerVentas();
  }  

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    listaNotas.setItems(listViewData);
    listaNotas.getSelectionModel().select(0);
    
    listaProductos.setItems(dataHerramientas);
    
    botonCargar.setOnAction(event -> {
      cargarNota();
    });
    
    textBusqueda.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        metodoDeBusquedaDoble();
      }
    });
  }
  
  public void leerVentas(){
    if (archNotas.existencia()) {
      ventas = archNotas.entrada();
    } else {
      ventas = new ArrayList();
    }
    listViewData.clear();
    System.out.println(ventas.size());
   for (int i = 0; i < ventas.size(); i++) {
     listViewData.add(ventas.get(i));
      //System.out.println(ventas.get(i));
      //EL PROBLEMA ESTA AQUI
    }
  }
  
  public void metodoDeBusquedaDoble(){
    String criterio = textBusqueda.getText();
    listViewData.clear();
    for (int i = 0; i < listViewData.size(); i++) {
      if (listViewData.get(i).getIdNota().indexOf(criterio) != -1 || listViewData.get(i).getFecha().indexOf(criterio) != -1 ) {
        listViewData.add(listViewData.get(i));
      }
    }
    listaNotas.setItems(listViewData);
  }
  
  public void cargarNota(){
    Venta seleccion = listaNotas.getSelectionModel().getSelectedItem();
    texFolio.setText(seleccion.getIdNota());
    texFecha.setText(seleccion.getFecha());
    textSubtotal.setText(seleccion.getSubtotal()+"");
    texIva.setText(seleccion.getIva()+"");
    textTotal.setText(seleccion.getTotal()+"");
    ArrayList<HerramientaVenta> temp = seleccion.getArticulos();
    dataHerramientas.clear();
    for (int i = 0; i < seleccion.getArticulos().size(); i++) {
      dataHerramientas.add(temp.get(i));
      System.out.println(temp.get(i));
    }
  }

  void setStagePrincipal(Stage ventana) {
    this.stagePrincipal = ventana; 
  }

  void setEditable(boolean b) {
    this.isEditable = b; 
  }
}
