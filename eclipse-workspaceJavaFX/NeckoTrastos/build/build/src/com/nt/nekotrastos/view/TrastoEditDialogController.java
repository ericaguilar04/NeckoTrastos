package com.nt.nekotrastos.view;

import java.sql.SQLException;
import java.util.ArrayList;

import com.nt.nekotrastos.MainApp;
import com.nt.nekotrastos.model.TrastoDAO;
import com.nt.nekotrastos.model.TrastoVO;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TrastoEditDialogController {
	/*** VARIABLES GLOBALES ***/
	
	@FXML
	private TextField nombreTrastoField;			// Text vinculat al FXML. Mostra el nom del trasto
	@FXML
	private TextField descripcioField;			// Mostra la descripció del item a afegir/editar/borrar
	@FXML
	private TextField precioField;				// Mostra el preu del item a afefir/editar/borrar
	@FXML
	private Button AgregarButton;			// Botó que confirma la acció d'afegir item
	@FXML
	private TextField ID_Propietario;			// Text vinculat al FXML. Mostra el nom del seu propietari
	
	private TableView<TrastoVO> trastosTable;
	
	/*** CONTROLADORES I INSTÁNCIIAS ***/
	private Stage dialogStage;
    private TrastoVO trasto;
    private boolean afegirCliked = false;	
    private TrastoDAO trastoDAO;
    private MainApp mainApp;
    private String nombreTrasto;
    private String descripcion;
    private String precio;
    private Stage window;
    
    
    /**
     * Carga al inicializar el controlador
     */
    @FXML
    private void initialize() {
    }
    
    /*** FASE DE EXPERIMENTACIÓN ***/
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     * @throws SQLException 
     */
   /* public void setMainApp(MainApp mainApp) throws SQLException {
        this.mainApp = mainApp;
        // Add observable list data to the table
        this.mainApp.cargarTablaMisTrastos();
        trastosTable.setItems(this.mainApp.getMisTrastosData());
        
    }*/
    /**
     * Establece el diálogo del escenario
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Establece a editar
     * 
     * @param person
     */
    public void setTrasto(TrastoVO trasto) {
        this.trasto = trasto;
        
        
        nombreTrastoField.setText(trasto.getNombreTrasto());
        descripcioField.setText(trasto.getDescripcion());
        precioField.setText(Float.toString(trasto.getPrecio()) );
        
        
    }
    /**
     * Carga los datos del mainApp
     * @param mainApp
     * @throws SQLException
     */
    public void setMainApp(MainApp mainApp) throws SQLException {
        this.mainApp = mainApp;
        // Add observable list data to the table
        this.mainApp.cargarTablaMisTrastos();
        trastosTable.setItems(this.mainApp.getMisTrastosData());
        
    }
    /**
     * Getter del trasto a editar
     * @return
     */
    public TrastoVO getTrasto() {
    	return this.trasto;
    	
    }
    
    /**
     * Returns true if the person clicked OK, false otherwise. Devuelve true si el trasto clicado no tiene problemas de null o datos incorrectos
     * 
     * @return
     */
    public boolean isAfegirCliked() {
        return afegirCliked;
    }
    
    /**
     * Método al hacer click al boton aceptar
     * @throws SQLException 
     */
    @FXML
    private void handleOk() throws SQLException {
    	
    	
    	int selectedIndex;
    	ArrayList<TrastoVO> llistaTrastos;
    	if (isInputValid()) {
        	
        	
        	try {
        		//window = mainApp.getStage();
        		trasto.setNombreTrasto(nombreTrastoField.getText());
                trasto.setDescripcion(descripcioField.getText());
                trasto.setPrecio(Float.parseFloat(precioField.getText()) );
               // trasto.setID_Producto();
                
        		System.out.println("mainaaaaaap" + mainApp == null);
    	        //trasto = mainApp.getTrastoEditar();
        		
    	        
        		
        			
        			trastoDAO = new TrastoDAO();
    	        	//Borrem l'empleat a bbdd
    	        	trastoDAO.editarTrasto(trasto.getNombreTrasto(), trasto.getDescripcion(), trasto.getPrecio(),trasto.getID_Producto());
    	        	//obtenim totes els empleats
    	        	mainApp.cargarTablaMisTrastos();
    	        	llistaTrastos = trastoDAO.obtenerTrastosDeMisTrastos(mainApp.getUsuarioLogin().getId_Usuario());
    	        	
    	        	//esborrem totes els dades
    	        	trastosTable.getItems().clear();
    	        	//carreguem  la llista de'mepleats actuatlizada
    	        	for(int i=0; i< llistaTrastos.size();i++)
    	        		trastosTable.getItems().add(llistaTrastos.get(i));
    	        	
    	        	TrastoVO newTrasto = new TrastoVO(trasto.getNombreTrasto(),trasto.getDescripcion(),trasto.getPrecio());
    	        	TrastoDAO newConnection = new TrastoDAO();
    	        	newConnection.insertTrasto(newTrasto);
    	           //empnoField.getText();
    	        	afegirCliked = true;
    	            dialogStage.close();
    	            
        	}catch(NullPointerException e) {
        		System.err.println("handleOk :: " + e.getMessage());
        		// Show the error message.
            	Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Information Dialog");
            	alert.setHeaderText("Atención");
            	alert.setContentText("Selecciona un trasto");

            	alert.showAndWait();
        	}
        }
    	
    	afegirCliked = true;
        dialogStage.close();

            
        }
    
    
    /**
     * Decide dar por buenos los parámetros entrados para editar 
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nombreTrastoField.getText() == null || nombreTrastoField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (descripcioField.getText() == null || descripcioField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (precioField.getText() == null || precioField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Information Dialog");
        	alert.setHeaderText("Atención");
        	alert.setContentText("Casillas Vacias o tipo de datos incorrectos");

        	alert.showAndWait();
            return false;
        }
    }
}

