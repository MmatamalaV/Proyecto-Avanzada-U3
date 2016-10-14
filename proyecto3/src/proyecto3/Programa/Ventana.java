/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3.Programa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/** 
 *
 * @author mario
 */
public final class Ventana extends Stage implements EventHandler<ActionEvent>, Serializable
{
    TableView tableInsumos=new TableView();
    TableView tableProveedores=new TableView();
    TableView tableEmpleados=new TableView();
    Serializar serializador= new Serializar();
    Sistema sistema= new Sistema();
    ObservableList<Object> dataInsumos = FXCollections.observableArrayList();
    ObservableList<Object> dataProveedores = FXCollections.observableArrayList();
    ObservableList<Object> dataEmpleados = FXCollections.observableArrayList();
    String region="";
    String comuna="";
    ObservableList<String> comunas = FXCollections.observableArrayList();   
    ComboBox comunasBox=new ComboBox(comunas);
    int numgenerado=1;
    Empleado empleado;
    String nombreEmpleado;
    String fecha;
    ObservableList<String> insumos = FXCollections.observableArrayList();
    ArrayList<InsumoEntrega> entrega=new ArrayList();
    ArrayList<GuiaDespacho> guias = new ArrayList();
    String nombreEntrega="";
    ObservableList<InsumoEntrega> insumosTabla = FXCollections.observableArrayList();
    Eso eso;
    ObservableList<InsumoRecepcion> insumosRecepcionTabla = FXCollections.observableArrayList();
    ObservableList<String> proveedorRecepcionTabla = FXCollections.observableArrayList();
    String nombreRecepionInsumo="";
    String fechaRecepcion="";
    String insumobox="";
    String proveedorbox="";
    ArrayList<InsumoRecepcion> listaInsumoRecepcion=new ArrayList();
    String codigoInsumito="";
    
    
    public Ventana()
    {   
//        eso=serializador.abrir("eso");
        inicio();
    }
        
    @Override
    public void handle(ActionEvent event)
    {

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void inicio()
    {               
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1330, 430);
        root.setMinSize(1330,430);
        BorderPane border= new BorderPane();
        

        Image imageTop = new Image("/images/banner.png", 683, 47, false, false);
        ImageView imagenTop= new ImageView();
        imagenTop.setImage(imageTop);
        HBox top=new HBox();
        top.getStyleClass().add("custom-group-pane");
        top.getChildren().add(imagenTop);
        
        border.setTop(top); 
       
        VBox buttonOpcion=new VBox();
        
        Button button1=new Button("Nuevo Insumo");
        button1.setMinWidth(140);
        button1.setOnAction((ActionEvent event) ->
        { 
            nuevoInsumo();
        }); 
        Button button2=new Button("Nuevo Proveedor");
        button2.setMinWidth(140);
        button2.setOnAction((ActionEvent event) ->
        { 
            nuevoProveedor();
        }); 
        Button button3=new Button("Nuevo Empleado");
        button3.setMinWidth(140);
        button3.setOnAction((ActionEvent event) ->
        { 
            nuevoEmpleado();
        }); 
        Button button4=new Button("Recepción de Insumos");
        button4.setMinWidth(140);
        button4.setOnAction((ActionEvent event) ->
        { 
            recepcionInsumos();
        }); 
        Button button5=new Button("Entrega de Insumos");
        button5.setMinWidth(140);
        button5.setOnAction((ActionEvent event) ->
        { 
            entregaInsumo();
        }); 
                
        Button tableButton2=new Button("Eliminar");
        tableButton2.setMinWidth(140);
        tableButton2.setDisable(true);
                
        VBox tableOpcion=new VBox();
        tableOpcion.setSpacing(10);
        tableOpcion.getChildren().addAll(tableButton2);
                
        BorderedTitledPane opcionTable= new BorderedTitledPane("Opciones de la tabla",tableOpcion);
        
        buttonOpcion.getChildren().addAll(button1,button2,button3,button4,button5,opcionTable);
        
        buttonOpcion.setSpacing(18);
        buttonOpcion.setPadding(new Insets(0,10,0,10));
        buttonOpcion.setAlignment(Pos.CENTER);
        
        buttonOpcion.setMinWidth(150);
        
        border.setRight(buttonOpcion);
        
        tableInsumos = new TableView();
        this.tableInsumos.setItems(dataInsumos);
        
        TabPane tab=new TabPane();
     
        
            Tab insumos = new Tab();
            insumos.setClosable(false);
            insumos.setText("Insumos");
            tab.getTabs().add(insumos);

                TableColumn<Insumo,String> codigo=new TableColumn("codigo");
                codigo.setMinWidth(115);
                codigo.setMaxWidth(115);
                codigo.setStyle("-fx-alignment: CENTER");
                codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

                TableColumn<Insumo,String> insumo=new TableColumn("Insumo");
                insumo.setMinWidth(115);
                insumo.setMaxWidth(115);
                insumo.setStyle("-fx-alignment: CENTER");
                insumo.setCellValueFactory(new PropertyValueFactory<>("insumo"));

                TableColumn<Insumo,Integer> sI=new TableColumn("Stock inicial");
                sI.setMinWidth(90);
                sI.setMaxWidth(90);
                sI.setStyle("-fx-alignment: CENTER");
                sI.setCellValueFactory(new PropertyValueFactory<>("stockInicial"));
                
                TableColumn<Insumo,Integer> sM=new TableColumn("stock minimo");
                sM.setMinWidth(90);
                sM.setMaxWidth(90);
                sM.setStyle("-fx-alignment: CENTER");
                sM.setCellValueFactory(new PropertyValueFactory<>("stockMinimo"));
                
                TableColumn<Insumo,Integer> descripcion=new TableColumn("Descripcion");
                descripcion.setMinWidth(732);
                descripcion.setMaxWidth(732);
                descripcion.setStyle("-fx-alignment: CENTER");
                descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
                                
                tableInsumos.getColumns().addAll(codigo,insumo,sI,sM,descripcion);


            Tab proveedores = new Tab();
            proveedores.setClosable(false);
            proveedores.setText("Proveedores");
            tab.getTabs().add(proveedores);
            
            tableProveedores= new TableView();
            this.tableProveedores.setItems(dataProveedores);
//                
                TableColumn<Proveedor,String> rutProveedor=new TableColumn("Rut proveedor");
                rutProveedor.setMinWidth(114);
                rutProveedor.setMaxWidth(114);
                rutProveedor.setStyle("-fx-alignment: CENTER");
                rutProveedor.setCellValueFactory(new PropertyValueFactory<>("rut"));
//
                TableColumn<Proveedor,String> razonSocial=new TableColumn("Razon social");
                razonSocial.setMinWidth(114);
                razonSocial.setMaxWidth(114);
                razonSocial.setStyle("-fx-alignment: CENTER");
                razonSocial.setCellValueFactory(new PropertyValueFactory<>("razonSocial"));
//
                TableColumn<Proveedor,Integer> giro=new TableColumn("Giro");
                giro.setMinWidth(114);
                giro.setMaxWidth(114);
                giro.setStyle("-fx-alignment: CENTER");
                giro.setCellValueFactory(new PropertyValueFactory<>("giro"));
//                
                TableColumn<Proveedor,Integer> direccion=new TableColumn("Dirección");
                direccion.setMinWidth(114);
                direccion.setMaxWidth(114);
                direccion.setStyle("-fx-alignment: CENTER");
                direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
//                
                TableColumn<Proveedor,Integer> region=new TableColumn("Región");
                region.setMinWidth(114);
                region.setMaxWidth(114);
                region.setStyle("-fx-alignment: CENTER");
                region.setCellValueFactory(new PropertyValueFactory<>("Region"));
                
                TableColumn<Proveedor,Integer> comuna=new TableColumn("Comuna");
                comuna.setMinWidth(114);
                comuna.setMaxWidth(114);
                comuna.setStyle("-fx-alignment: CENTER");
                comuna.setCellValueFactory(new PropertyValueFactory<>("comuna"));
                
                TableColumn<Proveedor,Integer> ciudadLocalidad=new TableColumn("Ciudad/Localidad");
                ciudadLocalidad.setMinWidth(114);
                ciudadLocalidad.setMaxWidth(114);
                ciudadLocalidad.setStyle("-fx-alignment: CENTER");
                ciudadLocalidad.setCellValueFactory(new PropertyValueFactory<>("ciudadLocalidad"));
                
                TableColumn<Proveedor,Integer> telefonoContacto=new TableColumn("Telefono contacto");
                telefonoContacto.setMinWidth(114);
                telefonoContacto.setMaxWidth(114);
                telefonoContacto.setStyle("-fx-alignment: CENTER");
                telefonoContacto.setCellValueFactory(new PropertyValueFactory<>("telefonoContacto"));
                
                TableColumn<Proveedor,Integer> Email=new TableColumn("Email");
                Email.setMinWidth(114);
                Email.setMaxWidth(114);
                Email.setStyle("-fx-alignment: CENTER");
                Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
                
                TableColumn<Proveedor,Integer> infoAdicional=new TableColumn("Info adicional");
                infoAdicional.setMinWidth(114);
                infoAdicional.setMaxWidth(114);
                infoAdicional.setStyle("-fx-alignment: CENTER");
                infoAdicional.setCellValueFactory(new PropertyValueFactory<>("infoAdicional"));
                
//                     
            tableProveedores.getColumns().addAll(rutProveedor,razonSocial,giro,direccion,region,comuna,ciudadLocalidad,telefonoContacto,Email,infoAdicional);
            
            
            Tab empleados = new Tab();
            empleados.setClosable(false);
            empleados.setText("Empleados");
            tab.getTabs().add(empleados);
            
            tableEmpleados= new TableView();
            this.tableEmpleados.setItems(dataEmpleados);
            
                TableColumn<Empleado,String> rutEmpleado=new TableColumn("Rut");
                rutEmpleado.setMinWidth(380);
                rutEmpleado.setMaxWidth(380);
                rutEmpleado.setStyle("-fx-alignment: CENTER");
                rutEmpleado.setCellValueFactory(new PropertyValueFactory<>("rut"));
//
                TableColumn<Empleado,String> nombreEmpleado=new TableColumn("Nombre");
                nombreEmpleado.setMinWidth(380);
                nombreEmpleado.setMaxWidth(380);
                nombreEmpleado.setStyle("-fx-alignment: CENTER");
                nombreEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//
                TableColumn<Empleado,Integer> apellidoEmpleado=new TableColumn("Apellido");
                apellidoEmpleado.setMinWidth(380);
                apellidoEmpleado.setMaxWidth(380);
                apellidoEmpleado.setStyle("-fx-alignment: CENTER");
                apellidoEmpleado.setCellValueFactory(new PropertyValueFactory<>("apellido"));
                
                
            
            tableEmpleados.getColumns().addAll(rutEmpleado,nombreEmpleado,apellidoEmpleado);
               
            BorderPane center=new BorderPane();
            insumos.setContent(tableInsumos);
            proveedores.setContent(tableProveedores);
            empleados.setContent(tableEmpleados);
            BorderedTitledPane borderTab= new BorderedTitledPane("Tabla",tab);
            center.setCenter(borderTab);

            center.setPadding(new Insets(10,0,9,10));
           
        border.setCenter(center);
        
        root.getChildren().addAll(border);
        
        
        tableInsumos.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
            if (tableInsumos.getSelectionModel().getSelectedItem()!=null)
            {
                tableButton2.setDisable(false);
                
                tableButton2.setOnAction((ActionEvent event1) ->
                { 
                    boolean isEliminado=eliminar(Insumo.class);
                    if (isEliminado)
                    {
                        tableButton2.setDisable(true);
                    }
               }); 
            }
        }
        });
        
        tableEmpleados.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
            if (tableEmpleados.getSelectionModel().getSelectedItem()!=null)
            {
                tableButton2.setDisable(false);
                
                tableButton2.setOnAction((ActionEvent event1) ->
                { 
                    boolean isEliminado=eliminar(Empleado.class);
                    if (isEliminado)
                    {
                        tableButton2.setDisable(true);
                    }
               }); 
            }
        }
        });
        tableProveedores.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
            if (tableProveedores.getSelectionModel().getSelectedItem()!=null)
            {
                tableButton2.setDisable(false);
                
                tableButton2.setOnAction((ActionEvent event1) ->
                { 
                    boolean isEliminado=eliminar(Proveedor.class);
                    if (isEliminado)
                    {
                        tableButton2.setDisable(true);
                    }
               }); 
            }
        }
        });
        
        tab.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
            tableButton2.setDisable(true);          
        }
        });
        
        scene.getStylesheets().add("/css/my_style.css");
        Stage stage = new Stage();
        stage.setTitle("Sistema para Plantas de Congelado");
        stage.getIcons().add(new Image("/images/imagen.png"));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setOnCloseRequest((WindowEvent we) ->
        {
            cerrar();
        });
        stage.show();
        
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void cerrar()
    {
        serializador.guardar(eso, "eso");
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void nuevoInsumo()
    {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 650, 360);
        root.setMinSize(650,360);
        BorderPane border= new BorderPane();
        

        Image imageTop = new Image("/images/banner.png", 650, 45, false, false);
        ImageView imagenTop= new ImageView();
        imagenTop.setImage(imageTop);
        HBox top=new HBox();
        top.getStyleClass().add("custom-group-pane");
        top.getChildren().add(imagenTop);
        border.setTop(top);        
        scene.getStylesheets().add("/css/my_style.css");
        Stage stage = new Stage();
        stage.setTitle("Nuevo Insumo");
        stage.getIcons().add(new Image("/images/imagen.png"));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        
        VBox centro=new VBox();
        
            HBox datosBasicos=new HBox();

                VBox columna1=new VBox();

                    HBox columna1Fila1= new HBox();

                        GridPane grid1=new GridPane();

                            Label codigo=new Label("Código");
                            Label asterisco1=new Label("*");
                            asterisco1.setTextFill(Color.web("#ff0000"));
                            
                        grid1.add(codigo, 0, 0);
                        grid1.add(asterisco1, 1, 0);
                        
                        
                        TextField codigoReturn=new TextField();
                        codigoReturn.setMaxSize(149, 25);
                        codigoReturn.setMinSize(149, 25);
                        
                    columna1Fila1.getChildren().addAll(grid1,codigoReturn);
                    columna1Fila1.setSpacing(39);
                    columna1Fila1.setAlignment(Pos.BASELINE_LEFT);
                    
                    HBox columna1Fila2=new HBox();
                        
                        GridPane grid2=new GridPane();

                            Label stockInicial=new Label("Stock inicial");
                            Label asterisco2=new Label("*");
                            asterisco2.setTextFill(Color.web("#ff0000"));
                            
                        grid2.add(stockInicial, 0, 0);
                        grid2.add(asterisco2, 1, 0);
                        
                        Spinner stockInicialReturn = new Spinner();
                        stockInicialReturn.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000));
                        stockInicialReturn.setEditable(true);
                        stockInicialReturn.setMaxSize(149, 25);
                        stockInicialReturn.setMinSize(149, 25);
                        
                    columna1Fila2.getChildren().addAll(grid2,stockInicialReturn);
                    columna1Fila2.setSpacing(15);
                    columna1Fila2.setAlignment(Pos.BASELINE_LEFT);
                    
                columna1.getChildren().addAll(columna1Fila1,columna1Fila2);
                columna1.setSpacing(10);
                
                VBox columna2=new VBox();
                
                HBox columna2Fila1= new HBox();

                        GridPane grid3=new GridPane();

                            Label insumo=new Label("Insumo");
                            Label asterisco3=new Label("*");
                            asterisco3.setTextFill(Color.web("#ff0000"));
                            
                        grid3.add(insumo, 0, 0);
                        grid3.add(asterisco3, 1, 0);
                                                
                        TextField insumoReturn=new TextField();
                        insumoReturn.setMaxSize(273, 25);
                        insumoReturn.setMinSize(273, 25);
                        
                    columna2Fila1.getChildren().addAll(grid3,insumoReturn);
                    columna2Fila1.setSpacing(47);
                    columna2Fila1.setAlignment(Pos.BASELINE_LEFT);
                    
                    HBox columna2Fila2=new HBox();
                        
                        GridPane grid4=new GridPane();

                            Label stockMinimo=new Label("Stock mínimo");
                            Label asterisco4=new Label("*");
                            asterisco4.setTextFill(Color.web("#ff0000"));
                            
                        grid4.add(stockMinimo, 0, 0);
                        grid4.add(asterisco4, 1, 0);
                        
                        Spinner stockMinimoReturn = new Spinner();
                        stockMinimoReturn.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000));
                        stockMinimoReturn.setEditable(true);
                        stockMinimoReturn.setMaxSize(149, 25);
                        stockMinimoReturn.setMinSize(149, 25);
                        
                    columna2Fila2.getChildren().addAll(grid4,stockMinimoReturn);
                    columna2Fila2.setSpacing(15);
                    columna2Fila2.setAlignment(Pos.BASELINE_LEFT);
                    
                columna2.getChildren().addAll(columna2Fila1,columna2Fila2);
                columna2.setSpacing(10);
                
            datosBasicos.getChildren().addAll(columna1,columna2);
            datosBasicos.setSpacing(10);
            
            Label plix=new Label("Si va a ingresar el número por teclado por favor apretar enter para que se guarde.");
            plix.setTextFill(Color.web("#ff0000"));
            
            VBox datosBasicosFinal=new VBox();
            datosBasicosFinal.getChildren().addAll(datosBasicos,plix);
            datosBasicosFinal.setSpacing(10);
            datosBasicosFinal.setPadding(new Insets(10,10,0,10));
                    
            BorderedTitledPane borderDatosBasicos= new BorderedTitledPane("Datos Basicos",datosBasicosFinal);
            
            VBox description=new VBox();
            TextArea descripcion=new TextArea();
            descripcion.getStyleClass().add("custom-group-grey");
            
            descripcion.setPadding(new Insets(0,10,10,10));

            description.getChildren().add(descripcion);

            BorderedTitledPane borderDescripcion= new BorderedTitledPane("Descripción",description);
        
        centro.getChildren().addAll(borderDatosBasicos,borderDescripcion);
        
        centro.setPadding(new Insets(10,10,10,10));
        centro.setSpacing(15);
        
        border.setCenter(centro);
        
        HBox button= new HBox();
            
            VBox advertencia=new VBox();
                
                Label alerta=new Label("Campos con * son requeridos.");
            
            advertencia.getChildren().add(alerta);
            advertencia.setAlignment(Pos.BASELINE_LEFT);
            
            HBox botones=new HBox();
            
                Button aceptar=new Button("Aceptar");
                aceptar.setMinSize(139, 21);
                aceptar.setOnAction((ActionEvent event) ->
                {
                    if (Integer.parseInt(stockInicialReturn.getEditor().getText())<Integer.parseInt(stockMinimoReturn.getEditor().getText()))
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Error");
                        alert.setHeaderText("El stock inicial es menor que el stock minimo");
                        Stage stage1 = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage1.getIcons().add(new Image("/images/imagen.png"));
                        alert.showAndWait();
                    }     
                    else if (Integer.parseInt(stockInicialReturn.getEditor().getText())==0)
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Error");
                        alert.setHeaderText("El stock inicial es 0");
                        Stage stage1 = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage1.getIcons().add(new Image("/images/imagen.png"));
                        alert.showAndWait();
                    } 
                    else if (Integer.parseInt(stockMinimoReturn.getEditor().getText())==0)
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Error");
                        alert.setHeaderText("El stock minimo es 0");
                        Stage stage1 = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage1.getIcons().add(new Image("/images/imagen.png"));
                        alert.showAndWait();
                    } 
                    else if (codigoReturn.getText().equalsIgnoreCase("") || insumoReturn.getText().equalsIgnoreCase(""))
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Faltan campos");
                        alert.setHeaderText("Por favor verifique que todos los campos marcados con * estén completos");
                        Stage stage1 = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage1.getIcons().add(new Image("/images/imagen.png"));
                        alert.showAndWait();
                    }
                    else
                    {
                        String c=codigoReturn.getText();
                        String i=insumoReturn.getText();
                        int sI= Integer.parseInt(stockInicialReturn.getEditor().getText());
                        int sM= Integer.parseInt(stockMinimoReturn.getEditor().getText());
                        String d=descripcion.getText();
                        Insumo ins= new Insumo(c,i,sI,sM,d);
                        boolean isagregado=sistema.addInsumo(ins);
                       if (isagregado)
                        {
                            insumos.add(i);
                            dataInsumos.add(ins);
                            stage.close();
                        }   
                        else
                        {
                            Alert alert1 = new Alert(AlertType.ERROR);
                            alert1.setTitle("Repetido");
                            alert1.setHeaderText("Este insumo ya existe");
                            Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
                            stage1.getIcons().add(new Image("/images/imagen.png"));
                            alert1.showAndWait();
                        }
                    }
                    
                }); 
                
                Button cancelar=new Button("Cancelar");
                cancelar.setMinSize(139, 21);
                cancelar.setOnAction((ActionEvent event) ->
                {
                    stage.close();
                });
                
            botones.getChildren().addAll(aceptar, cancelar);
            botones.setAlignment(Pos.BASELINE_RIGHT);
            botones.setSpacing(10);
            
        button.getChildren().addAll(advertencia,botones);
        button.setSpacing(193);
        button.setPadding(new Insets(0,10,13,10));
        border.setBottom(button);
        
        root.getChildren().addAll(border);
        
        stage.show();
    }   
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void nuevoEmpleado()
    {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 650, 250);
        root.setMinSize(650,250);
        BorderPane border= new BorderPane();
        

        Image imageTop = new Image("/images/banner.png", 650, 45, false, false);
        ImageView imagenTop= new ImageView();
        imagenTop.setImage(imageTop);
        HBox top=new HBox();
        top.getStyleClass().add("custom-group-pane");
        top.getChildren().add(imagenTop);
        border.setTop(top);        
        scene.getStylesheets().add("/css/my_style.css");
        Stage stage = new Stage();
        stage.setTitle("Nuevo Empleado");
        stage.getIcons().add(new Image("/images/imagen.png"));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        
        VBox empleados = new VBox();
        
            HBox empleadoFila1=new HBox();
                
                GridPane grid1=new GridPane();

                    Label rut=new Label("Rut");
                    Label asterisco1=new Label("*");
                    asterisco1.setTextFill(Color.web("#ff0000"));
                            
                    grid1.add(rut, 0, 0);
                    grid1.add(asterisco1, 1, 0);
                        
                        
                TextField rutReturn=new TextField();
                rutReturn.setMaxSize(300, 25);
                rutReturn.setMinSize(300, 25);
                
            empleadoFila1.getChildren().addAll(grid1,rutReturn);
            empleadoFila1.setSpacing(35);
            empleadoFila1.setAlignment(Pos.BASELINE_CENTER);    
            
            HBox empleadoFila2=new HBox();
                
                GridPane grid2=new GridPane();

                    Label nombre=new Label("Nombre");
                    Label asterisco2=new Label("*");
                    asterisco2.setTextFill(Color.web("#ff0000"));
                            
                    grid2.add(nombre, 0, 0);
                    grid2.add(asterisco2, 1, 0);
                    
                TextField nombreReturn=new TextField();
                nombreReturn.setMaxSize(300, 25);
                nombreReturn.setMinSize(300, 25);
                
                empleadoFila2.getChildren().addAll(grid2,nombreReturn);
                empleadoFila2.setSpacing(10);
                empleadoFila2.setAlignment(Pos.BASELINE_CENTER);    
                    
                        
            HBox empleadoFila3=new HBox();
                
                GridPane grid3=new GridPane();

                    Label apellido=new Label("Apellido");
                    Label asterisco3=new Label("*");
                    asterisco3.setTextFill(Color.web("#ff0000"));
                            
                    grid3.add(apellido, 0, 0);
                    grid3.add(asterisco3, 1, 0);
                        
                        
                TextField apellodoReturn=new TextField();
                apellodoReturn.setMaxSize(300, 25);
                apellodoReturn.setMinSize(300, 25);
                
            empleadoFila3.getChildren().addAll(grid3,apellodoReturn);
            empleadoFila3.setSpacing(10);
            empleadoFila3.setAlignment(Pos.BASELINE_CENTER);            
            
        
                
        empleados.getChildren().addAll(empleadoFila1,empleadoFila2,empleadoFila3);
        empleados.setSpacing(15);
        BorderedTitledPane empleado= new BorderedTitledPane("Empleado",empleados);
        BorderPane center=new BorderPane();
        center.setPadding(new Insets(10, 10, 10, 10));
        center.setCenter(empleado);
        border.setCenter(center);
        
        HBox button= new HBox();
            
            VBox advertencia=new VBox();
                
                Label alerta=new Label("Campos con * son requeridos.");
            
            advertencia.getChildren().add(alerta);
            advertencia.setAlignment(Pos.BASELINE_LEFT);
            
            HBox botones=new HBox();
            
                Button aceptar=new Button("Aceptar");
                aceptar.setMinSize(139, 21);
                aceptar.setOnAction((ActionEvent event) ->
                {
                    if (nombreReturn.getText().equalsIgnoreCase("") || apellodoReturn.getText().equalsIgnoreCase("") || rutReturn.getText().equalsIgnoreCase(""))
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Faltan campos");
                        alert.setHeaderText("Por favor verifique que todos los campos marcados con * estén completos");
                        Stage stage1 = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage1.getIcons().add(new Image("/images/imagen.png"));
                        alert.showAndWait();
                    }
                    else
                    {
                        String r=rutReturn.getText();
                        String n=nombreReturn.getText();
                        String a=apellodoReturn.getText();
                        Empleado emp= new Empleado(r,n,a);
                        boolean isagregado=sistema.addEmpleado(emp);
                        
                        if (isagregado)
                        {
                            dataEmpleados.add(emp);
                            stage.close();
                        }
                        else
                        {
                            Alert alert1 = new Alert(AlertType.ERROR);
                            alert1.setTitle("Repetido");
                            alert1.setHeaderText("Este empleado ya existe");
                            Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
                            stage1.getIcons().add(new Image("/images/imagen.png"));
                            alert1.showAndWait();
                        }

                    }
                }); 
                
                Button cancelar=new Button("Cancelar");
                cancelar.setMinSize(139, 21);
                cancelar.setOnAction((ActionEvent event) ->
                {
                    stage.close();
                });
                
            botones.getChildren().addAll(aceptar, cancelar);
            botones.setAlignment(Pos.BASELINE_RIGHT);
            botones.setSpacing(10);
            
        button.getChildren().addAll(advertencia,botones);
        button.setSpacing(193);
        button.setPadding(new Insets(0,10,13,10));
        border.setBottom(button);
                    
        root.getChildren().addAll(border);
        stage.show();        
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
   public void nuevoProveedor()
   {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 560);
        root.setMinSize( 800, 650);
        BorderPane border= new BorderPane();

        Image imageTop = new Image("/images/banner.png", 650, 45, false, false);
        ImageView imagenTop= new ImageView();
        imagenTop.setImage(imageTop);
        HBox top=new HBox();
        top.getStyleClass().add("custom-group-pane");
        top.getChildren().add(imagenTop);
        border.setTop(top);        
        scene.getStylesheets().add("/css/my_style.css");
        Stage stage = new Stage();
        stage.setTitle("Nuevo Proveedor");
        stage.getIcons().add(new Image("/images/imagen.png"));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        
        VBox pane=new VBox();
        
            VBox datosFactura=new VBox();
                
                HBox datosFacturaFila1=new HBox();

                    HBox rutPart=new HBox();
                    
                        GridPane grid1=new GridPane();

                        Label rut=new Label("Rut");
                        Label asterisco1=new Label("*");
                        asterisco1.setTextFill(Color.web("#ff0000"));

                        grid1.add(rut, 0, 0);
                        grid1.add(asterisco1, 1, 0);
                        
                        TextField rutReturn=new TextField();
                        rutReturn.setMaxSize(200, 25);
                        rutReturn.setMinSize(200, 25);
                     
                    rutPart.setSpacing(78);
                    rutPart.setAlignment(Pos.BASELINE_CENTER);
                    rutPart.getChildren().addAll(grid1,rutReturn);
                    
                    HBox razonPart=new HBox();
                    
                        GridPane grid2=new GridPane();

                        Label razonSocial=new Label("Razón Social");
                        Label asterisco2=new Label("*");
                        asterisco2.setTextFill(Color.web("#ff0000"));

                        grid2.add(razonSocial, 0, 0);
                        grid2.add(asterisco2, 1, 0);    
                    
                        TextField razonSocialReturn=new TextField();
                        razonSocialReturn.setMaxSize(360, 25);
                        razonSocialReturn.setMinSize(360, 25);
                        
                    razonPart.setSpacing(10);
                    razonPart.setAlignment(Pos.BASELINE_CENTER);
                    razonPart.getChildren().addAll(grid2,razonSocialReturn);
                                        
                datosFacturaFila1.getChildren().addAll(rutPart,razonPart);
                datosFacturaFila1.setSpacing(10);
                datosFacturaFila1.setPadding(new Insets(0, 10, 10, 10));
                
                HBox datosFacturaFila2=new HBox();
                
                    GridPane grid3=new GridPane();

                        Label giro=new Label("Giro");

                        grid3.add(giro, 0, 0); 
                        
                    TextField giroReturn=new TextField();
                    giroReturn.setMaxSize(653, 25);
                    giroReturn.setMinSize(653, 25);
                        
                datosFacturaFila2.setSpacing(79);
                datosFacturaFila2.setAlignment(Pos.BASELINE_CENTER);
                datosFacturaFila2.setPadding(new Insets(0, 10, 10, 10));
                datosFacturaFila2.getChildren().addAll(grid3,giroReturn);
            
            datosFactura.setSpacing(10);
            datosFactura.getChildren().addAll(datosFacturaFila1,datosFacturaFila2);
            
            VBox infoContacto=new VBox();
            
                HBox direccionBox=new HBox();
                    
                    GridPane grid4=new GridPane();

                        Label direccion=new Label("Dirección");
                        Label asterisco4=new Label("*");
                        asterisco4.setTextFill(Color.web("#ff0000"));

                        grid4.add(direccion, 0, 0);
                        grid4.add(asterisco4, 1, 0);    
                    
                    TextField direccionReturn=new TextField();
                    direccionReturn.setMaxSize(653, 25);
                    direccionReturn.setMinSize(653, 25);
                        
                direccionBox.setSpacing(47);
                direccionBox.setPadding(new Insets(0, 10, 0, 10));
                direccionBox.getChildren().addAll(grid4,direccionReturn);
                
                HBox region_ComunaBox=new HBox();
                
                    HBox regionHBox=new HBox();
                        
                        GridPane grid5=new GridPane();

                        Label regionLabel=new Label("Región");
                        Label asterisco5=new Label("*");
                        asterisco5.setTextFill(Color.web("#ff0000"));

                        grid5.add(regionLabel, 0, 0);
                        grid5.add(asterisco5, 1, 0);    
                    
                    ObservableList<String> regiones = FXCollections.observableArrayList(
                        "XV Arica y Parinacota",
                        "I Tarapacá",
                        "II Antofagasta",
                        "III Atacama",
                        "IV Coquimbo",
                        "V Valparaíso",
                        "XIII (RM) Metropolitana de Santiago",
                        "VI Libertador General Bernardo O'Higgins",
                        "VII Maule",
                        "VIII Biobío",
                        "IX Araucanía",
                        "XIV Los Ríos",
                        "X Los Lagos",
                        "XI Aysén del General Carlos Ibáñez del Campo",
                        "XII Magallanes y de la Antártica Chilena"
                    );
                    ComboBox regionBox = new ComboBox(regiones);
                                     
                    
                    regionBox.valueProperty().addListener(new ChangeListener<String>()
                    {
                        @Override
                        public void changed(ObservableValue ov, String t, String t1)
                        {
                            setRegion(t1);
                            comunas.clear();
                            setComunas();
                        }
                    });                
                    
                    regionHBox.setSpacing(59);
                    regionHBox.setPadding(new Insets(0,10,0,10));
                    regionHBox.setAlignment(Pos.BASELINE_CENTER);
                    regionHBox.getChildren().addAll(grid5,regionBox);
                    
                    HBox comunaHBox=new HBox();
                    
                        GridPane grid6=new GridPane();

                        Label comunaLabel=new Label("Comuna");
                        Label asterisco6=new Label("*");
                        asterisco6.setTextFill(Color.web("#ff0000"));

                        grid6.add(comunaLabel, 0, 0);
                        grid6.add(asterisco6, 1, 0);    
                    
                    comunaHBox.setSpacing(10);
                    comunaHBox.setAlignment(Pos.BASELINE_CENTER);
                    comunasBox.setMinWidth(272);
                    comunaHBox.getChildren().addAll(grid6,comunasBox);
                    comunaHBox.setPadding(new Insets(0,10,0,10));
                    
                    comunasBox.valueProperty().addListener(new ChangeListener<String>()
                    {
                        @Override
                        public void changed(ObservableValue ov, String t, String t1)
                        {
                            comuna=t1;
                        }
                    });      
                       
            region_ComunaBox.getChildren().addAll(regionHBox,comunaHBox);
            region_ComunaBox.setSpacing(10);
            
            HBox ciudadLocalidad=new HBox();
            
                GridPane grid7=new GridPane();

                    Label ciudadLocalidadLabel=new Label("Ciudad/Localidad");
                    Label asterisco7=new Label("*");
                    asterisco7.setTextFill(Color.web("#ff0000"));

                grid7.add(ciudadLocalidadLabel, 0, 0);
                grid7.add(asterisco7, 1, 0);
                
                TextField ciudadLocalidadReturn= new TextField();
                
                ciudadLocalidadReturn.setMaxSize(653, 25);
                ciudadLocalidadReturn.setMinSize(653, 25);
                        
                ciudadLocalidad.setSpacing(4);
                ciudadLocalidad.setAlignment(Pos.BASELINE_CENTER);
                ciudadLocalidad.setPadding(new Insets(0, 10, 0, 10));
                
            ciudadLocalidad.getChildren().addAll(grid7,ciudadLocalidadReturn);
            
            HBox contactacion=new HBox();
            
                HBox fonoContactoHBox=new HBox();
                    
                    Label fonoContactoLabel=new Label("Teléfono Contacto");
                    TextField fonoContactoReturn=new TextField();  
                    fonoContactoReturn.setMaxSize(294, 25);
                    fonoContactoReturn.setMinSize(294, 25);
            
                fonoContactoHBox.setSpacing(5);
                fonoContactoHBox.setPadding(new Insets(0, 10, 0, 10));
                fonoContactoHBox.setAlignment(Pos.BASELINE_CENTER);
                fonoContactoHBox.getChildren().addAll(fonoContactoLabel,fonoContactoReturn);
                
                HBox eMailBox=new HBox();
                
                    Label eMailLabel=new Label("E-Mail");
                    TextField eMailReturn=new TextField();  
                    eMailReturn.setMaxSize(294, 25);
                    eMailReturn.setMinSize(294, 25);
                
                eMailBox.setSpacing(11);
                eMailBox.setAlignment(Pos.BASELINE_CENTER);
                eMailBox.getChildren().addAll(eMailLabel,eMailReturn);
                   
            contactacion.setSpacing(10);
            contactacion.getChildren().addAll(fonoContactoHBox,eMailBox);
            
            HBox infoAdicionalBox=new HBox();
            
                Label infoAdicionalLabel=new Label("Información \nadicional");
                TextArea infoAdicionalReturn=new TextArea();  
                infoAdicionalReturn.setMinWidth(653);
                infoAdicionalReturn.setMaxWidth(653);
//            
            infoAdicionalBox.setSpacing(35);
            infoAdicionalBox.setPadding(new Insets(0,10,10,10));
            infoAdicionalBox.setAlignment(Pos.BASELINE_CENTER);
            infoAdicionalBox.getChildren().addAll(infoAdicionalLabel,infoAdicionalReturn);
            
            infoContacto.getChildren().addAll(direccionBox,region_ComunaBox,ciudadLocalidad,contactacion,infoAdicionalBox);
            infoContacto.setSpacing(10);
             
            BorderedTitledPane datosFacturaBoeder= new BorderedTitledPane("Datos factura",datosFactura);
            BorderedTitledPane infoContactoBoeder= new BorderedTitledPane("Informacion de contacto",infoContacto);
            
            VBox pane2=new VBox();
            pane2.getChildren().add(infoContactoBoeder);
            pane2.setPadding(new Insets(10,0,10,0));
            
            HBox button= new HBox();
            
            VBox advertencia=new VBox();
                
                Label alerta=new Label("Campos con * son requeridos.");
            
            advertencia.getChildren().add(alerta);
            advertencia.setAlignment(Pos.BASELINE_LEFT);
            
            HBox botones=new HBox();
            
                Button aceptar=new Button("Aceptar");
                aceptar.setMinSize(139, 21);
                aceptar.setOnAction((ActionEvent event) ->
                {
                    if (rutReturn.getText().equalsIgnoreCase("") || razonSocialReturn.getText().equalsIgnoreCase("") || direccionReturn.getText().equalsIgnoreCase("") || comuna.equalsIgnoreCase("") || region.equalsIgnoreCase("") || ciudadLocalidadReturn.getText().equalsIgnoreCase(""))
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Faltan campos");
                        alert.setHeaderText("Por favor verifique que todos los campos marcados con * estén completos");
                        Stage stage1 = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage1.getIcons().add(new Image("/images/imagen.png"));
                        alert.showAndWait();
                    }
                    else
                    {
                        String r=rutReturn.getText();
                        String ra=razonSocialReturn.getText();
                        String g=giroReturn.getText();
                        String d=direccionReturn.getText();
                        String cl=ciudadLocalidadReturn.getText();
                        String tc=fonoContactoReturn.getText();
                        String em=eMailReturn.getText();
                        String i= infoAdicionalReturn.getText();
                        Proveedor prov= new Proveedor(r,ra,g,d,region,comuna,cl,tc,em,i);
                        boolean isagregado=sistema.addProveedor(prov);
                        proveedorRecepcionTabla.add(ra);
                        
                        if (isagregado)
                        {
                            dataProveedores.add(prov);
                            stage.close();
                        }
                        else
                        {
                            Alert alert1 = new Alert(AlertType.ERROR);
                            alert1.setTitle("Repetido");
                            alert1.setHeaderText("Este porveedor ya existe");
                            Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
                            stage1.getIcons().add(new Image("/images/imagen.png"));
                            alert1.showAndWait();
                        }

                    }
                }); 
                
                Button cancelar=new Button("Cancelar");
                cancelar.setMinSize(139, 21);
                cancelar.setOnAction((ActionEvent event) ->
                {
                    stage.close();
                });
                
            botones.getChildren().addAll(aceptar, cancelar);
            botones.setAlignment(Pos.BASELINE_RIGHT);
            botones.setSpacing(10);
            
        button.getChildren().addAll(advertencia,botones);
        button.setSpacing(344);
        button.setPadding(new Insets(0,10,13,10));
            
            pane.getChildren().addAll(datosFacturaBoeder,pane2);
            pane.setPadding(new Insets(10, 10, 10, 10));
            border.setCenter(pane);
            border.setBottom(button);
            
        root.getChildren().addAll(border);
        stage.show();
    }
   
    void setRegion(String t1)
    {
        this.region=t1;
    }

    void setComunas()
    {
        switch (region)
        {
            case "XV Arica y Parinacota":
                comunas.setAll(
                        "Arica",
                        "Camarones",
                        "General Lagos",
                        "Putre"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "I Tarapacá":
                comunas.setAll(
                        "Alto Hospicio",
                        "Iquique",
                        "Camiña",
                        "chane",
                        "Huara",
                        "Pica",
                        "Pozo Almonte"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "II Antofagasta":
                comunas.setAll(
                        "Antofagasta",
                        "Mejillones",
                        "Sierra Gorda",
                        "Taltal",
                        "Calama",
                        "Ollagüe",
                        "San Pedro de Atacama",
                        "María Elena",
                        "Tocopilla"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "III Atacama":
                comunas.setAll(
                        "Chañaral",
                        "Diego de Almagro",
                        "Caldera",
                        "Copiapó",
                        "Tierra Amarilla",
                        "Alto del Carmen",
                        "Freirina",
                        "Huasco",
                        "Vallenar"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "IV Coquimbo":
                comunas.setAll(
                        "Canela",
                        "Illapel",
                        "Los Vilos",
                        "Salamanca",
                        "Andacollo",
                        "Coquimbo",
                        "La Higuera",
                        "La Serena",
                        "Paihuano",
                        "Vicuña",
                        "Combarbalá",
                        "Monte Patria",
                        "Ovalle",
                        "Punitaqui",
                        "Río Hurtado"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "V Valparaíso":
                comunas.setAll(
                        "Isla de Pascua",
                        "Calle Larga",
                        "Los Andes",
                        "Rinconada de Los Andes",
                        "San Esteban",
                        "Limache",
                        "Olmué",
                        "Quilpué",
                        "Villa Alemana",
                        "Cabildo",
                        "La Ligua",
                        "Papudo",
                        "Petorca",
                        "Zapallar",
                        "Hijuelas",
                        "La Calera",
                        "La Cruz",
                        "Nogales",
                        "Quillota",
                        "Algarrobo",
                        "Cartagena",
                        "El Quisco",
                        "El Tabo",
                        "San Antonio",
                        "Santo Domingo",
                        "Catemu",
                        "Llaillay",
                        "Panquehue",
                        "Putaendo",
                        "San Felipe",
                        "Santa María",
                        "Casablanca",
                        "Concón",
                        "Juan Fernández",
                        "Puchuncaví",
                        "Quintero",
                        "Valparaíso",
                        "Viña del Mar"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "XIII (RM) Metropolitana de Santiago":
                comunas.setAll(
                        "Colina",
                        "Lampa",
                        "Tiltil",
                        "Pirque",
                        "Puente Alto",
                        "San José de Maipo",
                        "Buin",
                        "Calera de Tango",
                        "Paine",
                        "San Bernardo",
                        "Alhué",
                        "Curacaví",
                        "María Pinto",
                        "Melipilla",
                        "San Pedro",
                        "Cerrillos",
                        "Cerro Navia",
                        "Conchalí",
                        "El Bosque",
                        "Estación Central",
                        "Huechuraba",
                        "Independencia",
                        "La Cisterna",
                        "La Granja",
                        "La Florida",
                        "La Pintana",
                        "La Reina",
                        "Las Condes",
                        "Lo Barnechea",
                        "Lo Espejo",
                        "Lo Prado",
                        "Macul",
                        "Maipú",
                        "Ñuñoa",
                           "Pedro Aguirre Cerda",
                        "Peñalolén",
                        "Providencia",
                        "Pudahuel",
                        "Quilicura",
                        "Quinta Normal",
                        "Recoleta",
                        "Renca",
                        "San Miguel",
                        "San Joaquín",
                        "San Ramón",
                        "Santiago",
                        "Vitacura",
                        "El Monte",
                        "Isla de Maipo",
                        "Padre Hurtado",
                        "Peñaflor",
                        "Talagante"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "VI Libertador General Bernardo O'Higgins":
                comunas.setAll(
                        "Codegua",
                        "Coínco",
                        "Coltauco",
                        "Doñihue",
                        "Graneros",
                        "Las Cabras",
                        "Machalí",
                        "Malloa",
                        "Olivar",
                        "Peumo",
                        "Pichidegua",
                        "Quinta de Tilcoco",
                        "Rancagua",
                        "Requínoa",
                        "Rengo",
                        "San Francisco de Mostazal",
                        "San Vicente de Tagua Tagua",
                        "La Estrella",
                        "Litueche",
                        "Marchigüe",
                        "Navidad",
                        "Paredones",
                        "Pichilemu",
                        "Chépica",
                        "Chimbarongo",
                        "Lolol",
                        "Nancagua",
                        "Palmilla",
                        "Peralillo",
                        "Placilla",
                        "Pumanque",
                        "San Fernando",
                        "Santa Cruz"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "VII Maule":
                comunas.setAll(
                        "Cauquenes",
                        "Chanco",
                        "Pelluhue",
                        "Curicó",
                        "Hualañé",
                        "Licantén",
                        "Molina",
                        "Rauco",
                        "Romeral",
                        "Sagrada Familia",
                        "Teno",
                        "Vichuquén",
                        "Colbún",
                        "Linares",
                        "Longaví",
                        "Parral",
                        "Retiro",
                        "San Javier de Loncomilla",
                        "Villa Alegre","Yerbas Buenas",
                        "Constitución",
                        "Curepto",
                        "Empedrado",
                        "Maule",
                        "Pelarco",
                        "Pencahue",
                        "Río Claro",
                        "San Clemente",
                        "San Rafael",
                        "Talca"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "VIII Biobío":
                comunas.setAll(
                        "Alto Biobío",
                        "Antuco",
                        "Cabrero",
                        "Laja",
                        "Los Ángeles",
                        "Mulchén",
                        "Nacimiento",
                        "Negrete",
                        "Quilaco",
                        "Quilleco",
                        "San Rosendo",
                        "Santa Bárbara",
                        "Tucapel",
                        "Yumbel",
                        "Chiguayante",
                        "Concepción",
                        "Coronel",
                        "Florida",
                        "Hualpén",
                        "Hualqui",
                        "Lota",
                        "Penco",
                        "San Pedro de la Paz",
                        "Santa Juana",
                        "Talcahuano",
                        "Tomé",
                        "Bulnes",
                        "Chillán",
                        "Chillán Viejo",
                        "Cobquecura",
                        "Coelemu",
                        "Coihueco",
                        "El Carmen",
                        "Ninhue",
                        "Ñiquén",
                        "Pemuco",
                        "Pinto",
                        "Portezuelo",
                        "Quillón",
                        "Quirihue",
                        "Ránquil",
                        "San Carlos",
                        "San Fabián",
                        "San Ignacio",
                        "San Nicolás",
                        "Treguaco",
                        "Yungay"                        
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "IX Araucanía":
                comunas.setAll(
                        "Carahue",
                        "Cholchol",
                        "Cunco",
                        "Curarrehue",
                        "Freire",
                        "Galvarino",
                        "Gorbea",
                        "Lautaro",
                        "Loncoche",
                        "Melipeuco",
                        "Nueva Imperial",
                        "Padre Las Casas",
                        "Perquenco",
                        "Pitrufquén",
                        "Pucón",
                        "Saavedra",
                        "Temuco",
                        "Teodoro Schmidt",
                        "Toltén",
                        "Vilcún",
                        "Villarrica",
                        "Angol",
                        "Collipulli",
                        "Curacautín",
                        "Ercilla",
                        "Lonquimay",
                        "Los Sauces",
                        "Lumaco",
                        "Purén",
                        "Renaico",
                        "Traiguén",
                        "Victoria"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "XIV Los Ríos":
                comunas.setAll(
                        "Futrono",
                        "La Unión",
                        "Lago Ranco",
                        "Río Bueno",
                        "Corral",
                        "Lanco",
                        "Los Lagos",
                        "Máfil",
                        "Mariquina",
                        "Paillaco",
                        "Panguipulli",
                        "Valdivia"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "X Los Lagos":
                comunas.setAll(
                        "Ancud",
                        "Castro",
                        "Chonchi",
                        "Curaco de Vélez",
                        "Dalcahue",
                        "Puqueldón",
                        "Queilén",
                        "Quellón",
                        "Quemchi",
                        "Quinchao",
                        "Calbuco",
                        "Cochamó",
                        "Fresia",
                        "Frutillar",
                        "Llanquihue",
                        "Los Muermos",
                        "Maullín",
                        "Puerto Montt",
                        "Puerto Varas",
                        "Osorno",
                        "Puerto Octay",
                        "Purranque",
                        "Puyehue",
                        "Río Negro",
                        "San Pablo",
                        "San Juan de la Costa",
                        "Chaitén",
                        "Futaleufú",
                        "Hualaihué",
                        "Palena"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "XI Aysén del General Carlos Ibáñez del Campo":
                comunas.setAll(
                        "Aysén",
                        "Cisnes",
                        "Guaitecas",
                        "Cochrane",
                        "O'Higgins",
                        "Tortel",
                        "Coyhaique",
                        "Lago Verde",
                        "Chile Chico",
                        "Río Ibáñez"
                );
                comunasBox = new ComboBox(comunas);
                break;
            case "XII Magallanes y de la Antártica Chilena":
                comunas.setAll(
                        "Antártica",
                        "Cabo de Hornos",
                        "Laguna Blanca",
                        "Punta Arenas",
                        "Río Verde",
                        "San Gregorio",
                        "Porvenir",
                        "Primavera",
                        "Timaukel",
                        "Natales",
                        "Torres del Paine"
                );
                comunasBox = new ComboBox(comunas);
                break;
        }
    }
    boolean eliminar(Class objeto)
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Eliminar");
        alert.setHeaderText("¿Está seguro que quiere eliminarlo?");
        Stage stage1 = (Stage) alert.getDialogPane().getScene().getWindow();
        stage1.getIcons().add(new Image("/images/imagen.png"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            if (objeto==Insumo.class)
            {
                Insumo insumito=(Insumo)dataInsumos.get(tableInsumos.getSelectionModel().getSelectedIndex());
                insumos.remove(insumito.getInsumo());
                dataInsumos.remove(tableInsumos.getSelectionModel().getSelectedItem());
                sistema.removeInsumo(insumito.getCodigo());
                return true;
            }

            else if (objeto==Empleado.class)
            {
                Empleado insumito=(Empleado)dataEmpleados.get(tableEmpleados.getSelectionModel().getSelectedIndex());
                dataEmpleados.remove(tableEmpleados.getSelectionModel().getSelectedItem());
                sistema.removeEmpleado(insumito.getRut());
                return true;
            }
            else if (objeto==Proveedor.class)
            {
                Proveedor insumito=(Proveedor)dataProveedores.get(tableProveedores.getSelectionModel().getSelectedIndex());
                dataProveedores.remove(tableProveedores.getSelectionModel().getSelectedItem());
                sistema.removeProveedor(insumito.getRut());
                return true;
            }
        }
        else
        {
            alert.close();
        }
        return false;
    }
    
    
    public void recepcionInsumos()
    {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 900, 650);
        root.setMinSize( 800, 650);
        BorderPane border= new BorderPane();
        
        Image imageTop = new Image("/images/banner.png", 650, 45, false, false);
        ImageView imagenTop= new ImageView();
        imagenTop.setImage(imageTop);
        HBox top=new HBox();
        top.getStyleClass().add("custom-group-pane");
        top.getChildren().add(imagenTop);
        border.setTop(top);        
        scene.getStylesheets().add("/css/my_style.css");
        Stage stage = new Stage();
        stage.setTitle("Recepción de Insumos");
        stage.getIcons().add(new Image("/images/imagen.png"));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        
        codigoInsumito="";
        
        VBox centro = new VBox();
        
            HBox guiaDespacho = new HBox();
            
                VBox columna1 = new VBox();
                
                    HBox columna1Fila1 = new HBox();
        
                        GridPane grid1=new GridPane();

                            Label numGuia=new Label("N°Guía");
                            Label asterisco1=new Label("*");
                            asterisco1.setTextFill(Color.web("#ff0000"));
                        
                        grid1.add(numGuia, 0, 0);
                        grid1.add(asterisco1, 1, 0);
                        
                        TextField numGuiaReturn=new TextField();
                        numGuiaReturn.setMaxSize(200, 25);
                        numGuiaReturn.setMinSize(200, 25);
                        
                    columna1Fila1.getChildren().addAll(grid1,numGuiaReturn);
                    columna1Fila1.setSpacing(39);
                    columna1Fila1.setAlignment(Pos.BASELINE_LEFT);
                    
                
                    HBox columna1Fila2 = new HBox();
                    
                    GridPane grid2 = new GridPane();
                        Label rut=new Label("Rut");
                        Label asterisco2=new Label("*");
                        asterisco2.setTextFill(Color.web("#ff0000"));
                    
                    grid2.add(rut, 0, 0);
                    grid2.add(asterisco2, 1, 0);
                        
                    TextField rutReturn=new TextField();
                    rutReturn.setMaxSize(200, 25);
                    rutReturn.setMinSize(200, 25);
                    rutReturn.setEditable(false);
                        
                    columna1Fila2.getChildren().addAll(grid2,rutReturn);
                    columna1Fila2.setSpacing(58);
                    columna1Fila2.setAlignment(Pos.BASELINE_LEFT);
                
                    HBox columna1Fila3 = new HBox();
                
                        GridPane grid3 = new GridPane();
                    
                            Label fecha = new Label("Fecha");
                
                        grid3.add(fecha,0,0);
                    
                    
                    DatePicker calendario = new DatePicker();
                    calendario.setOnAction((ActionEvent e) ->
                    {
                        LocalDate date = calendario.getValue();
                        fechaRecepcion=date.toString();
                        
                    });
    
                    calendario.setPrefWidth(200);
                    calendario.setEditable(false);
                    
                    columna1Fila3.getChildren().addAll(grid3,calendario);
                    columna1Fila3.setSpacing(52);
                    columna1Fila3.setAlignment(Pos.BASELINE_LEFT);
                
                columna1.getChildren().addAll(columna1Fila1, columna1Fila2, columna1Fila3);
                columna1.setPadding(new Insets(0,0,10,10));
                columna1.setSpacing(8);
            
                VBox columna2 = new VBox();
            
                    HBox columna2Fila1 = new HBox();
                        
                        GridPane grid4 = new GridPane();
                            
                            Label numRecepcion = new Label("N°Recepción");
                            
                        grid4.add(numRecepcion,0,0);
                        
                        
                        TextField numRecepcionReturn = new TextField();
                        numRecepcionReturn.setMaxSize(260, 25);
                        numRecepcionReturn.setMinSize(260, 25);
                        numRecepcionReturn.setText(String.valueOf(numgenerado));
                        numRecepcionReturn.getStyleClass().add("custom-group-yellow");
                        numRecepcionReturn.setEditable(false);
//                        numRecepcionReturn.getStyleClass().add(".custom-group-yellow");
                        
                    columna2Fila1.getChildren().addAll(grid4,numRecepcionReturn);
                    columna2Fila1.setSpacing(8);
                    columna2Fila1.setAlignment(Pos.BASELINE_LEFT);
                    columna2Fila1.setPadding(new Insets(0,0,10,215));
            
                    HBox columna2Fila2 = new HBox();
                    
                        GridPane grid5 = new GridPane();
                            
                            Label proovedor = new Label("Proovedor");
                            Label asterisco3 = new Label("*");
                            asterisco3.setTextFill(Color.web("#ff0000"));
                            
                        grid5.add(proovedor, 0, 0);
                        grid5.add(asterisco3, 1, 0);
                        
                        ComboBox proveedorBox = new ComboBox(proveedorRecepcionTabla);
                        proveedorBox.setMaxSize(470, 25);
                        proveedorBox.setMinSize(470, 25);
                        proveedorBox.setPromptText("--Sleccionar Proveedor--");
                        proveedorBox.valueProperty().addListener(new ChangeListener<String>()
                        {
                        @Override
                        public void changed(ObservableValue ov, String t, String t1)
                        {
                            insumobox=t1;
                            Proveedor insumo=sistema.getProveedor(t1);
                            rutReturn.setText(insumo.getRut());
                        }
                        });
                        
                    columna2Fila2.getChildren().addAll(grid5,proveedorBox);
                    columna2Fila2.setSpacing(20);
                    columna2Fila2.setAlignment(Pos.BASELINE_LEFT);
                    
                columna2.getChildren().addAll(columna2Fila1,columna2Fila2);
                columna2.setPadding(new Insets(0,0,10,10));
                columna2.setSpacing(-2);
                
            guiaDespacho.getChildren().addAll(columna1,columna2);
            guiaDespacho.setSpacing(15);
            BorderedTitledPane borderGuiaDespacho= new BorderedTitledPane("Guia de Despacho",guiaDespacho);
            
            HBox insumos = new HBox();
            
                VBox columna3 = new VBox();
                    
                    HBox columna3Fila1 = new HBox();
                    
                        GridPane grid6 = new GridPane();
                        
                            Label codigoInsumo = new Label("Código Insumo");
                            
                        grid6.add(codigoInsumo, 0, 0);
                        
                        TextField codigoInsumoReturn = new TextField();
                        codigoInsumoReturn.setMaxSize(200, 25);
                        codigoInsumoReturn.setMinSize(200, 25);
                        codigoInsumoReturn.setEditable(false);
                        
                    columna3Fila1.getChildren().addAll(grid6, codigoInsumoReturn);
                    columna3Fila1.setSpacing(11);
                    columna3Fila1.setAlignment(Pos.BASELINE_LEFT);
                    
                    HBox columna3Fila2 = new HBox();
                        
                        GridPane grid7 = new GridPane();
                        
                            Label cantidad = new Label("Cantidad");
                            
                        grid7.add(cantidad, 0, 0);
                        
                        Spinner cantidadReturn = new Spinner();
                        cantidadReturn.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000));
                        cantidadReturn.setEditable(true);
                        cantidadReturn.setMaxSize(200, 25);
                        cantidadReturn.setMinSize(200, 25);
                        
                    columna3Fila2.getChildren().addAll(grid7, cantidadReturn);
                    columna3Fila2.setSpacing(45);
                    columna3Fila2.setAlignment(Pos.BASELINE_LEFT);
                    
                    
                columna3.getChildren().addAll(columna3Fila1,columna3Fila2);
                columna3.setSpacing(6);
                
            
                VBox columna4 = new VBox();
                
                    HBox columna4Fila1 = new HBox();
                    
                        GridPane grid8 = new GridPane();
                        
                            Label insumo = new Label("Insumo");
                            
                        grid8.add(insumo,0,0);
                        
                        ComboBox insumoBox=new ComboBox(this.insumos);
                        insumoBox.setMaxSize(470, 25);
                        insumoBox.setMinSize(470, 25);
                        insumoBox.setPromptText("--Sleccionar Insumo--");
                        
                        insumoBox.valueProperty().addListener(new ChangeListener<String>()
                        {
                            @Override
                            public void changed(ObservableValue ov, String t, String t1)
                            {
                                proveedorbox=t1;
                                Insumo a=sistema.getInsumoNombre(t1);
                                codigoInsumito=String.valueOf(a.getCodigo());
                                codigoInsumoReturn.setText(codigoInsumito);                            
                            }
                        });
                        
                        codigoInsumoReturn.setText(codigoInsumito);
                        
                    columna4Fila1.getChildren().addAll(grid8, insumoBox);  
                    columna4Fila1.setSpacing(42);
                    columna4Fila1.setAlignment(Pos.BASELINE_LEFT);
                    
                    HBox columna4Fila2 = new HBox();
                        
                        HBox pane= new HBox();
                        GridPane grid9 = new GridPane();
                            
                           Label valorUnitario = new Label("Valor unitario");
                           
                        grid9.add(valorUnitario,0,0);
                       
                        Spinner valorUnitarioReturn = new Spinner();
                        valorUnitarioReturn.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000));
                        valorUnitarioReturn.setEditable(true);
                        valorUnitarioReturn.setMaxSize(100, 25);
                        valorUnitarioReturn.setMinSize(100, 25);
                        
                        pane.getChildren().addAll(grid9,valorUnitarioReturn);
                        pane.setAlignment(Pos.BASELINE_RIGHT);
                        pane.setSpacing(10);
                        
                        VBox botonAgregar = new VBox();
                            
                            Button agregarInsumo=new Button("Agregar insumo");
                            agregarInsumo.setMinSize(139, 25);
                        
                        botonAgregar.getChildren().addAll(agregarInsumo);
                        agregarInsumo.setOnAction((ActionEvent event) ->
                        {
                            if (codigoInsumoReturn.getText().equals("")|| insumobox.equalsIgnoreCase("") || Integer.parseInt(cantidadReturn.getEditor().getText())==0 || Integer.parseInt(valorUnitarioReturn.getEditor().getText())==0)
                            {
                            
                            }
                            else
                            {
                                String c = codigoInsumoReturn.getText();
                                String i = insumobox;
                                int cant = Integer.parseInt(cantidadReturn.getEditor().getText());
                                int v = Integer.parseInt(valorUnitarioReturn.getEditor().getText());
                                
                                InsumoRecepcion nuevoInsumo = new InsumoRecepcion(c,i,cant,v);
                                listaInsumoRecepcion.add(nuevoInsumo);
                                insumosRecepcionTabla.add(nuevoInsumo);
                                codigoInsumoReturn.clear();
                                cantidadReturn.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000));
                                valorUnitarioReturn.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000));
                            }
                        }); 

                    columna4Fila2.getChildren().addAll(pane, botonAgregar);
                    columna4Fila2.setSpacing(231);
                            
                columna4.getChildren().addAll(columna4Fila1, columna4Fila2);
                columna4.setSpacing(6);    
                    
            insumos.getChildren().addAll(columna3,columna4);
            insumos.setSpacing(25);
            BorderedTitledPane borderInsumos= new BorderedTitledPane("Insumos",insumos);
            
                BorderPane detalleRecepcion = new BorderPane();

                    BorderPane detalle = new BorderPane();

                        TableView detalleRecepcionTabla = new TableView(insumosRecepcionTabla);
                        
                        TableColumn<InsumoRecepcion,String> codigoInsumoColumn=new TableColumn("Codigo Insumo");
                        codigoInsumoColumn.setMinWidth(220);
                        codigoInsumoColumn.setMaxWidth(220);
                        codigoInsumo.setStyle("-fx-alignment: CENTER");
                        codigoInsumoColumn.setCellValueFactory(new PropertyValueFactory<>("codigoInsumo"));
                        
                        TableColumn<InsumoRecepcion,String> InsumoColumn=new TableColumn("Insumo");
                        InsumoColumn.setMinWidth(220);
                        InsumoColumn.setMaxWidth(220);
                        codigoInsumo.setStyle("-fx-alignment: CENTER");
                        InsumoColumn.setCellValueFactory(new PropertyValueFactory<>("insumo"));
                        
                        TableColumn<InsumoRecepcion,Integer> cantidadEntregadaColumn=new TableColumn("Cantidad Entregada");
                        cantidadEntregadaColumn.setMinWidth(220);
                        cantidadEntregadaColumn.setMaxWidth(220);
                        codigoInsumo.setStyle("-fx-alignment: CENTER");
                        cantidadEntregadaColumn.setCellValueFactory(new PropertyValueFactory<>("cantidadEntregada"));
                        
                        TableColumn<InsumoRecepcion,Integer> valorUnitarioColumn=new TableColumn("Valor Unitario");
                        valorUnitarioColumn.setMinWidth(220);
                        valorUnitarioColumn.setMaxWidth(220);
                        codigoInsumo.setStyle("-fx-alignment: CENTER");
                        valorUnitarioColumn.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
                        
                        detalleRecepcionTabla.getColumns().addAll(codigoInsumoColumn,InsumoColumn,cantidadEntregadaColumn,valorUnitarioColumn);
                        
                    detalle.setCenter(detalleRecepcionTabla);
                    
                    HBox penultimaFila = new HBox();
                        
                        GridPane esp = new GridPane();
                        
                            Label espacio = new Label("");
                        
                        esp.add(espacio, 0, 0);
                        
                        VBox botonEliminar = new VBox();
                        Button eliminarInsumo=new Button("Eliminar insumo");
                        
                        
                        agregarInsumo.setMinSize(139, 25);

                        eliminarInsumo.setDisable(true);
                        botonEliminar.getChildren().addAll(eliminarInsumo);
                        botonEliminar.setAlignment(Pos.BASELINE_RIGHT);
                    
                        detalleRecepcionTabla.setOnMouseClicked(new EventHandler<MouseEvent>(){
                            @Override
                            public void handle(MouseEvent event) {
                                if (detalleRecepcionTabla.getSelectionModel().getSelectedItem()!=null)
                                {
                                    eliminarInsumo.setDisable(false);
                                    eliminarInsumo.setOnAction((ActionEvent event1) ->
                                    {
                                        listaInsumoRecepcion.remove(detalleRecepcionTabla.getSelectionModel().getSelectedItem());
                                        insumosRecepcionTabla.remove(detalleRecepcionTabla.getSelectionModel().getSelectedItem());
                                    });
                                }
                            }
                        });
                            
                        
                    penultimaFila.getChildren().addAll(esp, botonEliminar);
                    penultimaFila.setSpacing(100);
                    penultimaFila.setAlignment(Pos.BASELINE_RIGHT);
                    
                detalleRecepcion.setCenter(detalle);
                detalleRecepcion.setBottom(penultimaFila);
                
                
            BorderedTitledPane borderDetalleRecepcion = new BorderedTitledPane("Detalle Recepción", detalleRecepcion);
                
            HBox finalBox = new HBox();
            
                GridPane grid10 = new GridPane();
                    
                    Label requerido = new Label("Campos con * son requeridos");
                    
                grid10.add(requerido,0, 0);
                
                HBox botones = new HBox();
                
                Button aceptar=new Button("Aceptar");
                
                
                
                aceptar.setOnAction((ActionEvent event) ->
                {
                    if (numGuiaReturn.getText().equalsIgnoreCase("") || rutReturn.getText().equalsIgnoreCase("") || proveedorbox.equalsIgnoreCase(""))
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Faltan campos");
                        alert.setHeaderText("Por favor verifique que todos los campos marcados con * estén completos");
                        Stage stage1 = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage1.getIcons().add(new Image("/images/imagen.png"));
                        alert.showAndWait();
                    }
                    else
                    {
                        String r=rutReturn.getText();
                        String g=numGuiaReturn.getText();
                        String f=fechaRecepcion;
                        
                        GuiaDespacho guia = new GuiaDespacho(r,g,f,proveedorbox);
                        boolean isagregado=guias.add(guia);
                        
                        if (isagregado)
                        {
                            String ra=rutReturn.getText();
                            String ga=numGuiaReturn.getText();
                            String fa=fechaRecepcion;
                            EscribirFicheroTexto add=new EscribirFicheroTexto();
                            add.guardarGuiaRecepcion(ra, ga, fa,proveedorbox,listaInsumoRecepcion);
                            insumosRecepcionTabla.clear();
                            listaInsumoRecepcion.clear();
                            numgenerado++;
                            stage.close();
                        }
                        else
                        {
                            Alert alert1 = new Alert(AlertType.ERROR);
                            alert1.setTitle("Repetido");
                            alert1.setHeaderText("Este empleado ya existe");
                            Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
                            stage1.getIcons().add(new Image("/images/imagen.png"));
                            alert1.showAndWait();
                        }

                    }
                }); 
                Button cancelar=new Button("Cancelar");
                
                cancelar.setOnAction((ActionEvent event) ->
                {
                    stage.close();
                });
                
                botones.getChildren().addAll(aceptar,cancelar);
                botones.setSpacing(10);
                botones.setAlignment(Pos.BASELINE_RIGHT);
                
            finalBox.getChildren().addAll(grid10, botones);
            finalBox.setSpacing(600);
            finalBox.setAlignment(Pos.BASELINE_LEFT);
                
        centro.getChildren().addAll(borderGuiaDespacho,borderInsumos,borderDetalleRecepcion,finalBox);
        
        centro.setPadding(new Insets(10,10,10,10));
        centro.setSpacing(15);
        
        border.setCenter(centro);
                            
        root.getChildren().addAll(border);
        
        stage.show();
    }
    
    void entregaInsumo()
    {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 900, 650);
        root.setMinSize( 800, 650);
        BorderPane border= new BorderPane();
        
        Image imageTop = new Image("/images/banner.png", 650, 45, false, false);
        ImageView imagenTop= new ImageView();
        imagenTop.setImage(imageTop);
        HBox top=new HBox();
        top.getStyleClass().add("custom-group-pane");
        top.getChildren().add(imagenTop);
        border.setTop(top);        
        scene.getStylesheets().add("/css/my_style.css");
        Stage stage = new Stage();
        stage.setTitle("Recepción de Insumos");
        stage.getIcons().add(new Image("/images/imagen.png"));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        
        codigoInsumito="";
        
        VBox paneGeneral=new VBox();
        
            VBox solicitantePane=new VBox();
                
                HBox rutHBox=new HBox();
            
                    GridPane grid1=new GridPane();

                        Label rutLabel=new Label("Rut");
                        Label asterisco1=new Label("*");
                        asterisco1.setTextFill(Color.web("#ff0000"));

                    grid1.add(rutLabel, 0, 0);
                    grid1.add(asterisco1, 1, 0);
                    
                    HBox rutHBox2=new HBox();
                    
                        TextField rutReturn=new TextField();
                        rutReturn.setMaxWidth(300);
                        rutReturn.setMinWidth(300);

                        TextField nombreReturn=new TextField();
                        nombreReturn.setEditable(false);
                        nombreReturn.setMaxWidth(767);
                        nombreReturn.setMinWidth(767);

                        Button buscarRut=new Button("Buscar");
                        buscarRut.setOnAction((ActionEvent event) ->
                        { 
                            if (!sistema.getListaEmpleados().isEmpty())
                            {
                                String rut=rutReturn.getText();
                                empleado=sistema.getEmpleado(rut);

                                if (empleado!=null)
                                {
                                    nombreEmpleado=empleado.getNombre();
                                    nombreReturn.setText(nombreEmpleado);
                                }
                                else
                                {
                                    nombreReturn.setText("No hay empleado con ese rut");
                                }
                            }
                            else
                            {
                                nombreReturn.setText("No hay empleados registrados");
                            }
                        }); 
                    
                    rutHBox2.getChildren().addAll(rutReturn,buscarRut);
                    rutHBox2.setSpacing(10);
                    rutHBox2.setAlignment(Pos.BASELINE_LEFT);
                
                rutHBox.getChildren().addAll(grid1,rutHBox2);
                rutHBox.setAlignment(Pos.BASELINE_LEFT);
                rutHBox.setSpacing(65);
                rutHBox.setPadding(new Insets(0,10,0,10));
                
                HBox nombreHBox=new HBox();
                
                        GridPane grid2=new GridPane();

                        Label nombreLabel=new Label("Nombre");
                        Label asterisco2=new Label("*");
                        asterisco2.setTextFill(Color.web("#ff0000"));

                    grid2.add(nombreLabel, 0, 0);
                    grid2.add(asterisco2, 1, 0);
                                        
                nombreHBox.getChildren().addAll(grid2,nombreReturn);
                nombreHBox.setSpacing(40);
                nombreHBox.setAlignment(Pos.BASELINE_LEFT);
                nombreHBox.setPadding(new Insets(0,10,0,10));
                
                HBox fechaHBox=new HBox();
                
                    GridPane grid3=new GridPane();

                        Label fechaLabel=new Label("Fecha");
                    
                    grid3.add(fechaLabel, 0, 0);
                
                    DatePicker calendario = new DatePicker();
                    calendario.setOnAction((ActionEvent e) ->
                    {
                        LocalDate date = calendario.getValue();
                        fecha=date.toString();
                    });
    
                    calendario.setPrefWidth(360);
                    calendario.setEditable(false);
                                    
                fechaHBox.getChildren().addAll(grid3,calendario);
                fechaHBox.setPadding(new Insets(0,10,10,10));
                fechaHBox.setAlignment(Pos.BASELINE_LEFT);
                fechaHBox.setSpacing(59);
                
            solicitantePane.getChildren().addAll(rutHBox,nombreHBox,fechaHBox);
            solicitantePane.setSpacing(10);
            
            BorderedTitledPane solicitanteBorder=new BorderedTitledPane("Solicitante",solicitantePane);
            VBox solicitanteBorderPane=new VBox();
            solicitanteBorderPane.getChildren().add(solicitanteBorder);
            solicitanteBorderPane.setPadding(new Insets(10,10,0,10));
                        
            VBox insumosVBox=new VBox();
                
                HBox parte1=new HBox();
                    
                    HBox parte1columna1=new HBox();
                
                        Label codigoInsumoLabel=new Label("Codigo Insumo");
                        TextField codigoInsumoReturn=new TextField();
                        codigoInsumoReturn.setMaxWidth(354);
                        codigoInsumoReturn.setMinWidth(354);
                        codigoInsumoReturn.setEditable(false);
                    
                    parte1columna1.getChildren().addAll(codigoInsumoLabel,codigoInsumoReturn);
                    parte1columna1.setAlignment(Pos.BASELINE_LEFT);
                    parte1columna1.setSpacing(8);
                    
                    HBox parte1columna2=new HBox();
                        
                        Label insumoLabel=new Label("Insumo");
                        ComboBox insumosBox=new ComboBox(insumos);
                        insumosBox.setPromptText("--Seleccionar Insumo--");
                        insumosBox.setMaxWidth(327);
                        insumosBox.setMinWidth(327);
                        
                    parte1columna2.getChildren().addAll(insumoLabel,insumosBox);
                    parte1columna2.setAlignment(Pos.BASELINE_LEFT);
                    parte1columna2.setSpacing(36);
                
                parte1.getChildren().addAll(parte1columna1,parte1columna2);
                parte1.setPadding(new Insets(0, 10, 0, 10));
                parte1.setSpacing(10);
                
                HBox parte2=new HBox();
                
                    HBox parte2columna1=new HBox();
                    
                     Label stockEntregaLabel=new Label("Stock a Entregar");
                        Spinner stockEntregaReturn=new Spinner();
                        stockEntregaReturn.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,0));
                        stockEntregaReturn.setEditable(true);
                        stockEntregaReturn.setMaxWidth(354);
                        stockEntregaReturn.setMinWidth(354);
                    
                    Label stockActualLabel=new Label("Stock Actual");
                        TextField stockActualReturn=new TextField();
                        stockActualReturn.setEditable(false);
                        stockActualReturn.setMaxWidth(215);
                        stockActualReturn.setMinWidth(215);
                        insumosBox.valueProperty().addListener(new ChangeListener<String>()
                        {
                            @Override
                            public void changed(ObservableValue ov, String t, String t1)
                            {
                                nombreEntrega=t1;
                                Insumo a=sistema.getInsumoNombre(t1);
                                stockActualReturn.setText(String.valueOf(a.getStockInicial()));
                                codigoInsumito=String.valueOf(a.getCodigo());
                                stockEntregaReturn.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, a.getStockInicial()));
                                
                            }
                        });  
                        
                       codigoInsumoReturn.setText(codigoInsumito);
        
                        
                    parte2columna1.getChildren().addAll(stockEntregaLabel,stockEntregaReturn);
                    parte2columna1.setAlignment(Pos.BASELINE_LEFT);
                    parte2columna1.setSpacing(3);
                    
                    HBox parte2columna2=new HBox();
                    
                    
                    
                        Button agregarInsumo=new Button("Agregar Insumo");
                                            
                    parte2columna2.getChildren().addAll(stockActualLabel,stockActualReturn,agregarInsumo);
                    parte2columna2.setSpacing(10);
                                            
                parte2.getChildren().addAll(parte2columna1,parte2columna2);
                parte2columna2.setAlignment(Pos.BASELINE_LEFT);
                parte2.setPadding(new Insets(0, 10, 0, 10));
                parte2.setSpacing(10);
                
            insumosVBox.getChildren().addAll(parte1,parte2);
            insumosVBox.setPadding(new Insets(0,10,0,10));
            insumosVBox.setSpacing(10);
            
            BorderedTitledPane insumosBorder=new BorderedTitledPane("Insumos",insumosVBox);
            VBox insumosBorderPane=new VBox();
            insumosBorderPane.getChildren().add(insumosBorder);
            insumosBorderPane.setPadding(new Insets(0, 10, 0, 10));
        
            VBox detalleEntregaVBox=new VBox();
                
                TableView tabla=new TableView(insumosTabla);
                    
                    TableColumn<InsumoEntrega,String> codigo=new TableColumn("codigo");
                    codigo.setMinWidth(292);
                    codigo.setMaxWidth(292);
                    codigo.setStyle("-fx-alignment: CENTER");
                    codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            
                    TableColumn<InsumoEntrega,String> nombre1=new TableColumn("nombre");
                    nombre1.setMinWidth(292);
                    nombre1.setMaxWidth(292);
                    nombre1.setStyle("-fx-alignment: CENTER");
                    nombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            
                    TableColumn<InsumoEntrega,String> cantidad=new TableColumn("cantidad");
                    cantidad.setMinWidth(292);
                    cantidad.setMaxWidth(292);
                    cantidad.setStyle("-fx-alignment: CENTER");
                    cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
                    
                tabla.getColumns().addAll(codigo,nombre1,cantidad);
                
            detalleEntregaVBox.getChildren().add(tabla);
            
            
            agregarInsumo.setOnAction((ActionEvent event) ->
            { 
                if (codigoInsumoReturn.getText().equalsIgnoreCase("")|| nombreEntrega.equalsIgnoreCase(""))
                {
                }
                else
                {
                String c=codigoInsumoReturn.getText();
                String n=nombreEntrega;
                int sE=(int)stockEntregaReturn.getValue();
                
                InsumoEntrega a=new InsumoEntrega(c, n, sE);
                entrega.add(a);
                insumosTabla.add(a);
                                }
            });
            
            BorderedTitledPane detalleEntregaBorder=new BorderedTitledPane("Detalle Entrega",detalleEntregaVBox);
            VBox detalleEntregaBorderPane=new VBox();
            detalleEntregaBorderPane.getChildren().add(detalleEntregaBorder);
            detalleEntregaBorderPane.setPadding(new Insets(0, 10, 10, 10));
            
        paneGeneral.getChildren().addAll(solicitanteBorderPane,insumosBorderPane,detalleEntregaBorderPane);
        paneGeneral.setSpacing(10);
        border.setCenter(paneGeneral);
        
        HBox button= new HBox();
            
            VBox advertencia=new VBox();
                
                Label alerta=new Label("Campos con * son requeridos.");
            
            advertencia.getChildren().add(alerta);
            advertencia.setAlignment(Pos.BASELINE_LEFT);
            
            HBox botones=new HBox();
            
                Button aceptar=new Button("Aceptar");
                aceptar.setMinSize(139, 21);
                aceptar.setOnAction((ActionEvent event) ->
                {
                    if (rutReturn.getText().equals(""))
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Faltan campos");
                        alert.setHeaderText("Por favor verifique que todos los campos marcados con * estén completos");
                        Stage stage1 = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage1.getIcons().add(new Image("/images/imagen.png"));
                        alert.showAndWait();
                    }
                    else
                    {
                        EscribirFicheroTexto save=new EscribirFicheroTexto();
                        LocalDate date=calendario.getValue();
                        save.guardar(rutReturn.getText(),nombreReturn.getText(), date.toString(), entrega);
                        stage.close();
                    }
                }); 
                
                Button cancelar=new Button("Cancelar");
                cancelar.setMinSize(139, 21);
                cancelar.setOnAction((ActionEvent event) ->
                {
                    stage.close();
                });
                
            botones.getChildren().addAll(aceptar, cancelar);
            botones.setAlignment(Pos.BASELINE_RIGHT);
            botones.setSpacing(10);
            
        button.getChildren().addAll(advertencia,botones);
        button.setSpacing(443);
        button.setPadding(new Insets(0,10,13,10));
        
        border.setBottom(button);
        
        root.getChildren().addAll(border);
        
        stage.show();
    }   
}