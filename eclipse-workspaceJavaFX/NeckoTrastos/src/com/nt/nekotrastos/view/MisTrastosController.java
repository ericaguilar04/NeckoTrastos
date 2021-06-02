package com.nt.nekotrastos.view;

import java.sql.SQLException;
import java.util.ArrayList;

import com.nt.nekotrastos.MainApp;
import com.nt.nekotrastos.model.TrastoDAO;
import com.nt.nekotrastos.model.TrastoVO;
import com.nt.nekotrastos.model.UsuarioVO;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    
    @FXML
    public void añadirTrasto() {
    	 TrastoVO tempTrasto = new TrastoVO();
         boolean okClicked = mainApp.showTrastoEditDialog(tempTrasto);
         if (okClicked) {
        	 System.out.println("Dentro de añadirTrasto");
             mainApp.getTrastoData().add(tempTrasto);
         }
    }
    
}
