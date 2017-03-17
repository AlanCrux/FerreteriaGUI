package sife;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alan Yoset García C
 */
public class ViewInventarioController implements Initializable {
  @FXML private ListView<Herramienta> listView = new ListView<>();; 
  @FXML private Button botonAgregar;
  @FXML private Button botonEditar;
  @FXML private Button botonEliminar;
  @FXML private TextField txClave;
  @FXML private TextField txNombre;
  @FXML private TextField txCostoCompra;
  @FXML private TextField txPrecioVenta;
  @FXML private TextField txTipoUnidad;
  @FXML private TextField txExistencia;
  @FXML private TextArea texAdescripcion;
  @FXML private TextField txBusqueda;
  @FXML private Button botonClave;
  @FXML private Button botonNombre;
  
  private boolean banderaAgregar=false;
  private boolean banderaEditar;
  private ObservableList<Herramienta> listViewData = FXCollections.observableArrayList();
  private final Archivo archInventario = new Archivo("inventario.obj");
  private ArrayList<Herramienta> inventario;  
  private Stage stagePrincipal;
  private boolean isEditable; 
  
  public ViewInventarioController(){
    leerInventario();
  }
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    listView.setItems(listViewData);
    listView.getSelectionModel().select(0);
    
    listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Herramienta>() {
      @Override
      public void changed(ObservableValue<? extends Herramienta> observable,
        Herramienta oldValue, Herramienta newValue) {
        try{
          if (listViewData.isEmpty()) {
            vaciarCuadros();
          } else {
            cargarHerramienta();
          }  
        } catch(Exception ex){
          System.out.println("No pasa nada");
        }

        if(banderaAgregar || banderaEditar){
            desprotegerCuadros();  
        } else {
          protegerCuadros();
        }
      }
    });
    
    txBusqueda.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (banderaAgregar || banderaEditar) {
          System.out.println("Primero termina la operacion actual");
          txBusqueda.setText("");
        } else {
          metodoDeBusquedaTriple();
        }
      }
    });
    
    botonAgregar.setOnAction(event -> {
      if (!isEditable) {
        System.out.println("No puedes editar");
      } else {
        if (banderaEditar) {
          System.out.println("No mientras editas");
        } else {
          if (banderaAgregar) {
            if (cuadrosVacios()) {
              System.out.println("Nope");
            } else {
              banderaAgregar = false;
              protegerCuadros();
              agregarNuevoLista();
              listViewData.remove(listViewData.size() - 1);
              leerInventario();
              listView.setItems(listViewData);
              listView.getSelectionModel().select(0);
              botonAgregar.setText("Agregar");
              listView.setMouseTransparent(false);
              listView.setFocusTraversable(true);
            }
          } else {
            banderaAgregar = true;
            addNew();
            listView.setMouseTransparent(true);
            listView.setFocusTraversable(false);
            txNombre.requestFocus();
          }
        }
      }
    });
    
    botonEditar.setOnAction(event -> {
      if (listViewData.isEmpty()) {
        System.out.println("No hay nada");
      } else {
        if (banderaAgregar) {
          System.out.println("No mientras agregas");
        } else {
          if (banderaEditar) {
            banderaEditar = false;
            editar();
            botonEditar.setText("Editar");
            protegerCuadros();
            leerInventario();
            listView.setItems(listViewData);
            listView.getSelectionModel().select(0);
            listView.setMouseTransparent( false );
            listView.setFocusTraversable( true );
          } else {
            banderaEditar = true;
            desprotegerCuadros();
            botonEditar.setText("Refresh");
            listView.setMouseTransparent( true );
            listView.setFocusTraversable( false );
          }
        }
      }
    });
    
    botonEliminar.setOnAction(event -> {
      if (banderaEditar) {
        System.out.println("Primero termina de editar");
      } else {
        if (listViewData.isEmpty()) {
          System.out.println("No hay nada");
        } else {
          if (banderaAgregar) {
            banderaAgregar = false;
            botonAgregar.setText("Agregar");
            eliminar();
            leerInventario();
            listView.setItems(listViewData);
            listView.getSelectionModel().select(0);
          } else {
            eliminar();
            leerInventario();
            desprotegerCuadros();
            listView.setItems(listViewData);
            listView.getSelectionModel().select(0);
          }
        }
      }
    });
    
    botonClave.setOnAction(event -> {
      if (banderaAgregar || banderaEditar) {
        System.out.println("Primero termina la operacion actual");
      } else {
        ordenarClave();
        botonClave.setStyle("-fx-background-color: #FFD700;");
        botonNombre.setStyle("-fx-background-color: #D3D3D3;");
      }
    });
    
    botonNombre.setOnAction(event -> {
      if (banderaAgregar || banderaEditar) {
        System.out.println("Primero termina la operacion actual");
      } else{
        ordenarNombre();
        botonNombre.setStyle("-fx-background-color: #FFD700;");
        botonClave.setStyle("-fx-background-color: #D3D3D3;");
      } 
    });
    
    txCostoCompra.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
        if (txCostoCompra.getText().equals("")) {
          txPrecioVenta.setText("");
        } else {
          double venta = Double.parseDouble(txCostoCompra.getText()); 
          venta = venta + (venta*.5);
          txPrecioVenta.setText(venta+"");
        }
      }
    });
  }
  
  /*
  *Este método carga los datos de la herramienta seleccionada en el listView en el formulario
  */
  public void cargarHerramienta(){
    Herramienta seleccion = listView.getSelectionModel().getSelectedItem();
    txClave.setText(seleccion.getClave());
    txNombre.setText(seleccion.getNombre());
    txCostoCompra.setText(seleccion.getPrecioCompra()+"");
    txPrecioVenta.setText(seleccion.getPrecioVenta()+"");
    txTipoUnidad.setText(seleccion.getTipoUnidad());
    txExistencia.setText(seleccion.getExistencia()+"");
    texAdescripcion.setText(seleccion.getDescripcion());
  }
  
  public void addNew(){
    Herramienta nueva = new Herramienta();
    nueva.setClave("S15"+(listViewData.size()+1));
    nueva.setNombre("Nueva herramienta");
    nueva.setPrecioVenta(0);
    listViewData.add(nueva);
    listView.setItems(listViewData);
    listView.getSelectionModel().select(listViewData.size()-1);
    
    txNombre.setEditable(true);
    txCostoCompra.setEditable(true);
    txTipoUnidad.setEditable(true);
    txExistencia.setEditable(true);
    texAdescripcion.setEditable(true);
    
    txClave.setText(nueva.getClave());
    txNombre.setText("");
    txCostoCompra.setText("");
    txPrecioVenta.setText("");
    txTipoUnidad.setText("");
    txExistencia.setText("");
    texAdescripcion.setText("");
    
    botonAgregar.setText("Guardar");
  }
  
  public void agregarNuevoLista(){
    Herramienta nueva = new Herramienta();
    nueva.setClave(txClave.getText());
    nueva.setNombre(txNombre.getText());
    nueva.setPrecioCompra(Double.parseDouble(txCostoCompra.getText()));
    nueva.setPrecioVenta(Double.parseDouble(txPrecioVenta.getText()));
    nueva.setTipoUnidad(txTipoUnidad.getText());
    nueva.setExistencia(Integer.parseInt(txExistencia.getText()));
    nueva.setDescripcion(texAdescripcion.getText());
    
    inventario.add(nueva);
    archInventario.salida(inventario);
  }
  
  public void editar(){
    Herramienta seleccion = listView.getSelectionModel().getSelectedItem();
    for (int i = 0; i < inventario.size(); i++) {
      if (inventario.get(i).getClave().equals(seleccion.getClave())) {
        inventario.get(i).setNombre(txNombre.getText());
        inventario.get(i).setPrecioCompra(Double.parseDouble(txCostoCompra.getText()));
        inventario.get(i).setPrecioVenta(Double.parseDouble(txPrecioVenta.getText()));
        inventario.get(i).setTipoUnidad(txTipoUnidad.getText());
        inventario.get(i).setExistencia(Integer.parseInt(txExistencia.getText()));
        inventario.get(i).setDescripcion(texAdescripcion.getText());
      }
    }
    archInventario.salida(inventario);
  }
  
  public void eliminar(){
    Herramienta seleccion = listView.getSelectionModel().getSelectedItem();
    for (int i = 0; i < inventario.size(); i++) {
      if (inventario.get(i).getClave().equals(seleccion.getClave())) {
        inventario.remove(i);
      }
    }
    archInventario.salida(inventario);
  }
  
  public void protegerCuadros(){
    txNombre.setEditable(false);
    txCostoCompra.setEditable(false);
    txTipoUnidad.setEditable(false);
    txExistencia.setEditable(false);
    texAdescripcion.setEditable(false);
    
    txNombre.setStyle("-fx-border-color: #DCDCDC;");
    txCostoCompra.setStyle("-fx-border-color: #DCDCDC;");
    txExistencia.setStyle("-fx-border-color: #DCDCDC;");
    txTipoUnidad.setStyle("-fx-border-color: #DCDCDC;");
    texAdescripcion.setStyle("-fx-border-color: #DCDCDC;");
  }
  
  public void desprotegerCuadros(){
    txNombre.setEditable(true);
    txCostoCompra.setEditable(true);
    txTipoUnidad.setEditable(true);
    txExistencia.setEditable(true);
    texAdescripcion.setEditable(true);
    
    txNombre.setStyle("-fx-border-color: #00FFFF;");
    txCostoCompra.setStyle("-fx-border-color: #00FFFF;");
    txExistencia.setStyle("-fx-border-color: #00FFFF;");
    txTipoUnidad.setStyle("-fx-border-color: #00FFFF;");
    texAdescripcion.setStyle("-fx-border-color: #00FFFF;");
  }
  
  public void leerInventario(){
    if (archInventario.existencia()) {
      inventario = archInventario.entrada();
    } else {
      inventario = new ArrayList();
    }
    listViewData.clear();
    for (int i = 0; i < inventario.size(); i++) {
      listViewData.add(inventario.get(i));
    }
  }
  
  public void metodoDeBusquedaTriple(){
    String criterio = txBusqueda.getText();
    listViewData.clear();
    for (int i = 0; i < inventario.size(); i++) {
      if (inventario.get(i).getClave().indexOf(criterio) != -1 || inventario.get(i).getNombre().indexOf(criterio) != -1 || inventario.get(i).getDescripcion().indexOf(criterio) != -1) {
        listViewData.add(inventario.get(i));
      }
    }
    listView.setItems(listViewData);
  }
  
  public void ordenarClave(){
    listViewData.clear();
    Collections.sort(inventario, new Comparator<Herramienta>() {
      @Override
      public int compare(Herramienta o1, Herramienta o2) {
        return o1.getClave().compareTo(o2.getClave());
      }
    });
    for (int i = 0; i < inventario.size(); i++) {
      listViewData.add(inventario.get(i));
    }
    listView.setItems(listViewData);
  }
  
  public void ordenarNombre(){
    listViewData.clear();
    Collections.sort(inventario, new Comparator<Herramienta>() {
      @Override
      public int compare(Herramienta o1, Herramienta o2) {
        return o1.getNombre().compareTo(o2.getNombre());
      }
    });
    for (int i = 0; i < inventario.size(); i++) {
      listViewData.add(inventario.get(i));
    }
    listView.setItems(listViewData);
  }
  
  public boolean cuadrosVacios(){
    boolean hayVacios = false;
    
    if (esVacio(txNombre)) {
      hayVacios = true;
    }
    
    if (esVacio(txCostoCompra)) {
      hayVacios = true;
    }
    
    if (esVacio(txTipoUnidad)) {
      hayVacios = true;
    }
    
    if (esVacio(txTipoUnidad)) {
      hayVacios = true;
    }
    
    if ("".equals(texAdescripcion.getText()) || texAdescripcion.getText() == null) {
      hayVacios = true;
    }
    
    return hayVacios; 
  }
  
  public boolean esVacio(TextField texto){
    boolean vacio = false;
    if ("".equals(texto.getText()) || texto.getText() == null) {
      vacio = true;
    }
    return vacio;
  }
  
  public void vaciarCuadros(){
    txNombre.setText("");
    txClave.setText("");
    txCostoCompra.setText("");
    txPrecioVenta.setText("");
    txTipoUnidad.setText("");
    texAdescripcion.setText("");
    txExistencia.setText("");
  }

  void setStagePrincipal(Stage ventana) {
    this.stagePrincipal = stagePrincipal;
  }

  void setEditable(boolean b) {
    isEditable = b; 
  }
  
}
