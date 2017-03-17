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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Alan Yoset García C
 */
public class VistaVentasController implements Initializable  {

  @FXML
  private ListView<Herramienta> listaProductos;
  @FXML
  private Button botonEliminar;
  @FXML
  private Button botonFinalizar;
  @FXML
  private Button botonAgregar;
  @FXML
  private Button botonCancelar;
  @FXML
  private TextField texBusqueda;
  @FXML
  private TextField texSubtotal;
  @FXML
  private TextField texIva;
  @FXML
  private TextField textTotal;
  @FXML
  private TableView<HerramientaVenta> tabla = new TableView<HerramientaVenta>();
  @FXML private TextField texCantidad; 
  
  private ObservableList<Herramienta> listViewData = FXCollections.observableArrayList();
  private final ObservableList<HerramientaVenta> dataCarrito = FXCollections.observableArrayList();
  private final Archivo archInventario = new Archivo("inventario.obj");
  private ArrayList<Herramienta> inventario;
  
  private final Archivo archNotas = new Archivo("notas.obj");
  private ArrayList<Venta> ventas;
  
  private double subtotal = 0;
  private double iva = 0;
  private double total = 0;
  boolean enlista;
  
  public VistaVentasController() {
    leerInventario();
    leerVentas();
  }
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    listaProductos.setItems(listViewData);
    listaProductos.getSelectionModel().select(0);
    tabla.setEditable(true);
    TableColumn claveCol = new TableColumn("Clave");
    claveCol.setCellValueFactory(new PropertyValueFactory<HerramientaVenta,String>("clave"));
    
    TableColumn nombreCol = new TableColumn("Nombre");
    nombreCol.setCellValueFactory(new PropertyValueFactory<HerramientaVenta,String>("nombre"));

    TableColumn precioCol = new TableColumn("Precio");
    precioCol.setCellValueFactory(new PropertyValueFactory<HerramientaVenta,String>("precio"));
    
    TableColumn cantidadCol = new TableColumn("Cantidad");
    cantidadCol.setCellValueFactory(new PropertyValueFactory<HerramientaVenta,String>("cantidad"));
    
    TableColumn subtotalCol = new TableColumn("Subtotal");
    subtotalCol.setCellValueFactory(new PropertyValueFactory<HerramientaVenta,String>("subtotal"));

    tabla.setItems(dataCarrito);
    tabla.getColumns().addAll(claveCol,nombreCol,precioCol, cantidadCol, subtotalCol);
    
    botonAgregar.setOnAction(event -> {
      addCarrito();
      calculaSubtotal();
      texSubtotal.setText(subtotal+"");
    });
    
    botonEliminar.setOnAction(event -> {
      deleteCarrito();
    });
    
    botonFinalizar.setOnAction(event -> {
      descontarInventario();
      generarVenta();
    });
    
    texSubtotal.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        calculaIva();
        texIva.setText(iva+"");
      }
    });
    
    texIva.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        calculaTotal();
        textTotal.setText(total+"");
      }
    });
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
  
  public void leerVentas(){
    if (archNotas.existencia()) {
      ventas = archNotas.entrada();
    } else {
      ventas = new ArrayList();
    }
  }
  
  public void addCarrito() {
    //ESTE METODO AGREGA A LA LISTA DEL CARRITO UN ITEM 
    Herramienta htemp = listaProductos.getSelectionModel().getSelectedItem();
    if (recienAgregado()) {
      System.out.println("Ya has añadido este producto");
    } else {
      if (Integer.parseInt(texCantidad.getText()) > htemp.getExistencia()) {
        System.out.println("Existencias insuficientes\nsolo hay: " + htemp.getExistencia());
      } else {
        double subtotal = Integer.parseInt(texCantidad.getText()) * htemp.getPrecioVenta();
        dataCarrito.add(new HerramientaVenta(htemp.getClave(), htemp.getNombre(),
            htemp.getPrecioVenta() + "", texCantidad.getText(), subtotal + ""));
        texCantidad.clear();
      }
    }
  }
  
  public boolean recienAgregado(){
    Herramienta htemp = listaProductos.getSelectionModel().getSelectedItem();
    enlista = false; 
    tabla.getItems().forEach(HerramientaVenta -> {
      if (htemp.getClave() == HerramientaVenta.getClave()) {
        enlista = true;
      }
    });
    return enlista; 
  }
  
  public void deleteCarrito(){
    HerramientaVenta selectedItem = tabla.getSelectionModel().getSelectedItem();
    tabla.getItems().remove(selectedItem);
  }
  
  public void calculaSubtotal(){
    subtotal = 0;
    tabla.getItems().forEach(HerramientaVenta -> {
      subtotal += Double.parseDouble(HerramientaVenta.getSubtotal());
    });
  }
  
  public void calculaIva(){
    iva = 0;
    iva += (subtotal*.16);
  }
  
  public void calculaTotal(){
    total = 0;
    total = subtotal + iva; 
  }
  
  public void descontarInventario(){
    tabla.getItems().forEach(HerramientaVenta -> {
      for (int i = 0; i < inventario.size(); i++) {
        if (inventario.get(i).getClave() == HerramientaVenta.getClave()) {
          inventario.get(i).setExistencia(inventario.get(i).getExistencia()-Integer.parseInt(HerramientaVenta.getCantidad()));
        }
      }
    });
    archInventario.salida(inventario);
  }
  
  public void generarVenta(){
    HerramientaVenta temp = new HerramientaVenta();
    Venta ventaAnonima = new Venta();
    ventaAnonima.setFecha("Hoy");
    ventaAnonima.setIdNota("Y"+ventas.size());
    ventaAnonima.setIva(iva);
    ventaAnonima.setSubtotal(subtotal);
    ventaAnonima.setTotal(total);
    tabla.getItems().forEach(HerramientaVenta -> {
      temp.setClave(HerramientaVenta.getClave());
      temp.setNombre(HerramientaVenta.getNombre());
      temp.setCantidad(HerramientaVenta.getCantidad());
      temp.setPrecio(HerramientaVenta.getPrecio());
      temp.setSubtotal(HerramientaVenta.getSubtotal());
      System.out.println(temp);
      ventaAnonima.addHerramienta(temp);
    });
    ventas.add(ventaAnonima);
    archNotas.salida(ventas);
  }
  
}
