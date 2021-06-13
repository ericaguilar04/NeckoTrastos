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
    private int selectedIndex;
	/**
	 * Inicializa los datos con 3 columnas de los métodos de la clase TrastoVO
	 * @throws SQLException 
	 */
    @FXML
    private void initialize() throws SQLException {
    	ArrayList<TrastoVO> trastosList;
    	
	    System.out.println("initialize");
	   
	    nombreTrastoColumn.setCellValueFactory(
	    	cellData -> new  SimpleStringProperty(cellData.getValue().getNombreTrasto()));
	    descripcionColumn.setCellValueFactory(
	        cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
	    precioColumn.setCellValueFactory(
	        cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
	    
	    // Borra los detalles de los trastos
	    showTrastosDetails(null);

	    
	    
	 
    }
    
    /**
     * Carga los datos principales de la mainApp
     * 
     * @param mainApp
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
     * Al hacer click al botón Borrar trasto
     */
    @FXML
    private void handleDeleteTrasto() {
    	
    	TrastoVO trastoVO;
    	
    	ArrayList<TrastoVO> llistaEmpleats;
    	
    	try {
	        
    		selectedIndex = trastosTable.getSelectionModel().getSelectedIndex();
    		
	        
    		if (selectedIndex >= 0) {
    			
    			trastoDAO = new TrastoDAO();
    			//obtenim l'empleat a esborrar
	        	trastoVO = trastosTable.getItems().get(selectedIndex);
	        	//Borrem l'empleat a bbdd
	        	System.out.println("Trasto a borrar:" + trastoVO.getTelefono());
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
    /**
     * Getter del índice del trasto seleccionado
     * @return
     */
    public int getSelectedIndex() {
    	return this.selectedIndex;
    }
    
    /**
     * Cuando el usuario hace click al boton añadir usuario, abre un diálogo para añadir los datos del nuevo trasto
     * @throws SQLException 
     * 
     */
    @FXML
    private void handleNewTrasto() throws SQLException {
    	System.out.println("HandleNewPerson");
        TrastoVO tempTrasto = new TrastoVO();
        this.mainApp.showTrastoEditDialog(tempTrasto);
        boolean okClicked = mainApp.showTrastoEditDialog(tempTrasto);
        if (okClicked) {
            mainApp.getTrastoData().add(tempTrasto);
        }
    }
    
    /**
     * Al hacer click al botón editar trasto
     * @throws SQLException 
     */
    @FXML
    private void handleEditTrasto() throws SQLException {
    	
    	TrastoVO trastoVO;
    	int selectedIndex;
    	ArrayList<TrastoVO> llistaEmpleats;
    	
    	try {
    		
    		selectedIndex = trastosTable.getSelectionModel().getSelectedIndex();
	        
    		if (selectedIndex >= 0) {
    			trastoVO = trastosTable.getItems().get(selectedIndex);
    			this.mainApp.showTrastoEditDialog(trastoVO);
    		
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
    /**
     * Carga la base de datos (no implementado)
     * @throws SQLException
     */
    private void cargaDataBase() throws SQLException {
    	TrastoDAO currEmpDAO = new TrastoDAO();
        ArrayList<TrastoVO> empleatsArrayList;
    	empleatsArrayList = currEmpDAO.obtenirTotsTrastos();
        trastosTable.getItems().clear();
        
        for(int i =0 ; i<empleatsArrayList.size(); i++) {
        	trastosTable.getItems().add(empleatsArrayList.get(i));
        }
    }
    /**
     * Muestra los detalles del trasto especificado como parámetro
     * @param trasto
     */
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
