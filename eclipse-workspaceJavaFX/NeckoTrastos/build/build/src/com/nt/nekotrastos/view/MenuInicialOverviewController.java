package com.nt.nekotrastos.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nt.nekotrastos.MainApp;
import com.nt.nekotrastos.model.TrastoDAO;
import com.nt.nekotrastos.model.TrastoVO;
import com.nt.nekotrastos.model.UsuarioDAO;
import com.nt.nekotrastos.model.UsuarioVO;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class MenuInicialOverviewController {
	
	/*** VARIABLES GLOABLES ***/
	
    @FXML
    private TableView<TrastoVO> trastosTable;
    @FXML
    private TableColumn<TrastoVO, String> nombreTrastoColumn;
    @FXML
    private TableColumn<TrastoVO, String> descripcionColumn;  
    @FXML
    private TableColumn<TrastoVO, String> precioColumn;
    @FXML
    private TableColumn<TrastoVO, String> id_propietarioColumn;
    @FXML
    private TableColumn<TrastoVO, String> telefonoColumn;
    @FXML
    private Button onIniciSessionButton;
    @FXML
	private TextField barraBuscadorra;
    @FXML
    private Button misTrastosButton;
    @FXML
    private Button misChatsButton;
    @FXML
    private Button cerrarSesionButton;
    @FXML
    private Label avatarLabel;
    @FXML
    private ImageView avatarImagen;
 

    /*** VARIABLES GLOBALES DE CONTROLADORES Y INSTANCIAS ***/
    private MainApp mainApp;
    private TrastoDAO trastoDAO;
    private UsuarioDAO usuarioDAO;
    private InicioSesionController controllerCamuflajeBotones;
    private Stage dialogStage;
    private UsuarioVO usuarioSesionIniciada;	// Guardamos los datos del usuario logeado
    private boolean prueba;
    private Stage window;
    
    
    
    /**
     * Habilita el botón per buscar el texto introducido de la barra de búsqueda
     */
    @FXML
    public void buscarClicked() throws SQLException {
    		
    		
    		ArrayList<TrastoVO> trastosList;
    		
        	
        	try {
        	
        		trastoDAO = new TrastoDAO();
        		
            	trastosList = trastoDAO.buscarTrasto(barraBuscadorra.getText());
            	
            	
            	//Netejar la llista d'empleats
            	trastosTable.getItems().clear();
            	
            	//carregarlos a empleatable
            	for(int i=0; i<trastosList.size();i++) {
            		trastosTable.getItems().add(trastosList.get(i));
        		}
            	
          
            
        	}catch(SQLException e){
        		System.err.println("buttonDelete ::  ERROR" + e.getMessage());
        		 // Nothing selected.
                Alert alert = new Alert(AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("");
                alert.setHeaderText("No Person Selected");
                alert.setContentText("Please select a person in the table.");
                
                alert.showAndWait();
        		
        	}
        
    
    }
    /*
     * Metodo que acciona el botón para acceder los trastos del usuario
     */
    @FXML
    public void entrarMisTrastos() throws SQLException {	
    	ArrayList<TrastoVO> trastosList;
    	
    	try {
    	
    		trastoDAO = new TrastoDAO();
        	trastosList = trastoDAO.obtenerTrastosDeMisTrastos(mainApp.getUsuarioLogin().getId_Usuario());
        	
        	//Netejar la llista d'empleats
        	trastosTable.getItems().clear();
        	
        	//carregarlos a empleatable
        	for(int i=0; i<trastosList.size();i++) {
        		trastosTable.getItems().add(trastosList.get(i));
    		}
        	
      
        
    	}catch(SQLException e){
    		System.err.println("buttonDelete ::  ERROR" + e.getMessage());
    		 // Nothing selected.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            
            alert.showAndWait();
    		
    	}
    
    	
    	this.mainApp.showMisTrastos();
   
    }
    /**
     * Metodo que te lleva a la ventana mis chats (no implementado)
     * @throws SQLException
     */
    @FXML
    public void entrarMisChats() throws SQLException {	
    	
   
    }

    /**
     * Contructor del controlador
     */
    public MenuInicialOverviewController() {
    }

    /**
     * Al iniciar el controlador se ejecutara éste método
     */
    @FXML
    private void initialize() {
    	System.out.println("MenuInicialController.initialize iniciando...");
        // Initialize the trasto table with the 5 columns.
    	nombreTrastoColumn.setCellValueFactory(
                cellData -> new  SimpleStringProperty(cellData.getValue().getNombreTrasto()));
    	descripcionColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
    	precioColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
    	id_propietarioColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getID_Propietario()));
    	telefonoColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTelefono())));

    }

    /**
     * Carga los datos principales de la mainApp
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        trastosTable.setItems(mainApp.getTrastoData());
        
    }
    /**
     * Controlador de botón que nos autoriza abrir la ventana de iniciar sesión
     * @throws SQLException
     * @throws IOException
     */
   @FXML 
   public void onIniciSession() throws SQLException, IOException {
	   System.out.println("Iniciado onIniciSession .... ");
	   
	   FXMLLoader loader = new FXMLLoader();
	   loader.setLocation(getClass().getResource("IniciarSesion.fxml"));
	   loader.load();
	   controllerCamuflajeBotones = loader.getController();
    	this.mainApp.iniSession();
    	visibilidadBotonesSesion(this.mainApp.getUsuarioLogin());

    	   System.out.println("Fin onIniciSession. ");
    	   
    	
    }
   /**
    * Controlador de botón que nos autoriza cerrar sesión del usuario 
    */
   @FXML
   public void onCerrarSession() {
	   System.out.println("Iniciando onCerrarSession");
	   visibilidadBotonesSesion(null);
   }
   
   /**
    * Muestra o oculta los botones del menú inicial
    * @param usuarioLogin
    */
   public void visibilidadBotonesSesion(UsuarioVO usuarioLogin) {
   
	   if(usuarioLogin!= null) {
			System.out.println("Iniciado MenuInicialController.isibilidadBotonesSesion sesionLogin ok ");
			mostrarBoton(misTrastosButton);
			mostrarBoton(misChatsButton);
			ocultarBoton(onIniciSessionButton);
			mostrarBoton(cerrarSesionButton);
			avatarLabel.setText(this.mainApp.getUsuarioLogin().getId_Usuario()); //Te muestra por pantalla el login del usuario logeado.
		    avatarImagen.setVisible(true);
		}
		else {
			System.out.println("Iniciado MenuInicialController..visibilidadBotonesSesion sesionLogin false ");
			ocultarBoton(misTrastosButton);
			ocultarBoton(misChatsButton);
			mostrarBoton(onIniciSessionButton);
			ocultarBoton(cerrarSesionButton);
			avatarLabel.setText(""); //Te muestra por pantalla el login del usuario logeado.
		    avatarImagen.setVisible(false);
		}
   }
   
   /**
    * Método de ocultar botón
    * @param boton
    */
   private void ocultarBoton (Control boton) {
	   boton.setVisible(false);
	   boton.setManaged(true);
	   
   }
   
   /**
    * Método de mostrar botón
    * @param boton
    */
   private void mostrarBoton (Control boton) {
	   boton.setVisible(true);
	   boton.setManaged(true);
   }

}