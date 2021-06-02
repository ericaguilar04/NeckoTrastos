package com.nt.nekotrastos.view;

import java.sql.SQLException;
import java.util.ArrayList;

import org.controlsfx.dialog.Dialogs;

import com.nt.nekotrastos.MainApp;
import com.nt.nekotrastos.model.TrastoDAO;
import com.nt.nekotrastos.model.TrastoVO;
import com.nt.nekotrastos.model.UsuarioVO;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class MisTrastosController {
	
	// Variables privadas para comunicar los datos de la tabla al fxml
    @FXML
    private TableView<TrastoVO> trastosTable;
    @FXML
    private TableColumn<TrastoVO, String> nombreTrastoColumn;
    @FXML
    private TableColumn<TrastoVO, String> descripcionColumn;  
    @FXML
    private TableColumn<TrastoVO, String> precioColumn;
   
    private MainApp mainApp;		// instància para llamar los metodos de la MainApp
    private TrastoDAO trastoDAO;	// Instancia para almazenar los datos de los trastos del usuario que haya iniciado sesión
    private TrastoVO trastoVO;
    private UsuarioVO usuarioSesionIniciada;	// Guardamos los datos del usuario logeado
	/**
	 * Inicializa los datos con 3 columnas de los métodos de la clase TrastoVO
	 * @throws SQLException 
	 */
    @FXML
    private void initialize() throws SQLException {
    	ArrayList<TrastoVO> trastosList;
    	
	    System.out.println("initialize");
	    // Initialize the trasto table with the 4 columns.
	    nombreTrastoColumn.setCellValueFactory(
	    	cellData -> new  SimpleStringProperty(cellData.getValue().getNombreTrasto()));
	    descripcionColumn.setCellValueFactory(
	        cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
	    precioColumn.setCellValueFactory(
	        cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
	    
	    // Clear person details.
	    showTrastosDetails(null);

	    // Listen for selection changes and show the person details when changed.
	    trastosTable.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showTrastosDetails(newValue));
	    
	   
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     * @throws SQLException 
     */
    public void setMainApp(MainApp mainApp) throws SQLException {
        this.mainApp = mainApp;
        // Add observable list data to the table
        this.mainApp.cargarTablaMisTrastos();
        trastosTable.setItems(this.mainApp.getMisTrastosData());
        
    }
    
    /*
     * Metodo que nos devuelve al menú principal
     */
    @FXML
    public void atras() {
    	System.out.println("MisTrastosConroller.atras Iniciando...");
    	
    	//amagar botons
    	
    	this.mainApp.showMenuInicial();
    	
    	
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
    	
    	TrastoVO trastoVO;
    	int selectedIndex;
    	ArrayList<TrastoVO> llistaEmpleats;
    	
    	try {
	        
    		selectedIndex = trastosTable.getSelectionModel().getSelectedIndex();
	        
    		if (selectedIndex >= 0) {
    			
    			trastoDAO = new TrastoDAO();
    			//obtenim l'empleat a esborrar
	        	trastoVO = trastosTable.getItems().get(selectedIndex);
	        	//Borrem l'empleat a bbdd
	        	trastoDAO.deleteTrasto(trastoVO.getID_Producto());
	        	//obtenim totes els empleats
	        	llistaEmpleats = trastoDAO.obtenerTrastosDeMisTrastos(this.mainApp.getUsuarioLogin().getId_Usuario());
	        	
	        	//esborrem totes els dades
	        	trastosTable.getItems().clear();
	        	//carreguem  la llista de'mepleats actuatlizada
	        	for(int i=0; i< llistaEmpleats.size();i++)
	        		trastosTable.getItems().add(llistaEmpleats.get(i));
	        	
	        	
	        } else {
	        	// Show the error message.
	        	Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle("Information Dialog");
	        	alert.setHeaderText("Atención");
	        	alert.setContentText("Selecciona un trasto");

	        	alert.showAndWait();
	        }
	        
    	}catch(SQLException e) {
    		System.err.println("handleDeletePerson :: " + e.getMessage());
    		// Show the error message.
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Information Dialog");
        	alert.setHeaderText("Atención");
        	alert.setContentText("Selecciona un trasto");

        	alert.showAndWait();
    	}
    }
    
    @FXML
    public void añadirTrasto() {
    	 TrastoVO tempTrasto = new TrastoVO();
         boolean okClicked = mainApp.showTrastoEditDialog(tempTrasto);
         if (okClicked) {
        	 System.out.println("Dentro de añadirTrasto");
             mainApp.getTrastoData().add(tempTrasto);
         }
    }
    
    private void showTrastosDetails(TrastoVO trasto) {
        if (trasto != null) {
            // Fill the labels with info from the person object.
        	nombreTrastoColumn.setText(trasto.getNombreTrasto());
        	descripcionColumn.setText(trasto.getDescripcion());
        	precioColumn.setText(Float.toString(trasto.getPrecio()));
            

            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
        	nombreTrastoColumn.setText("");
        	descripcionColumn.setText("");
        	precioColumn.setText("");
        
        }
    }
}
