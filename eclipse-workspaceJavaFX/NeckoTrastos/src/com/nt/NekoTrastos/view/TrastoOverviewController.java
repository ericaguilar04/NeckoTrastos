package com.nt.NekoTrastos.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.SQLException;
import java.util.ArrayList;

import com.nt.NekoTrastos.MainApp;
import com.nt.NekoTrastos.model.TrastoDAO;
import com.nt.NekoTrastos.model.TrastoVO;


public class TrastoOverviewController {
	
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
	private TextField barraBuscadorra;


    /*@FXML
    private Label id_Productolabel;
    @FXML
    private Label nombreTrastolabel;
    @FXML
    private Label descripcionlabel;
    @FXML
    private Label preciolabel;
    @FXML
    private Label id_Propietariolabel;
    */

    // Reference to the main application.
    private MainApp mainApp;
    private TrastoDAO trastoDAO;
    
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

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TrastoOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	System.out.println("initialize");
        // Initialize the trasto table with the 4 columns.
    	nombreTrastoColumn.setCellValueFactory(
                cellData -> new  SimpleStringProperty(cellData.getValue().getNombreTrasto()));
    	descripcionColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
    	precioColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
    	id_propietarioColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getID_Propietario()));
    	

       // Clear person details.
        //showTrastoDetails(null);
        
        System.out.println(trastosTable == null);

        // Listen for selection changes and show the person details when changed.
        //trastosTable.getSelectionModel().selectedItemProperty().addListener(
                //(observable, oldValue, newValue) -> showTrastoDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        trastosTable.setItems(mainApp.getTrastoData());
    }
    
   public void onIniciSession() {
    	this.mainApp.iniSession();
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    /*private void showTrastoDetails(TrastoVO trasto) {
        if (trasto != null) {
            // Fill the labels with info from the person object.
        	id_Productolabel.setText(String.valueOf(trasto.getID_Producto()));
        	nombreTrastolabel.setText(trasto.getNombreTrasto());
        	descripcionlabel.setText(trasto.getDescripcion());
        	preciolabel.setText(String.valueOf(trasto.getPrecio()));
        	id_Propietariolabel.setText(trasto.getID_Propietario());

        } else {
            // Person is null, remove all the text.
        	id_Productolabel.setText("");
        	nombreTrastolabel.setText("");
        	descripcionlabel.setText("");
        	preciolabel.setText("");
        	id_Propietariolabel.setText("");
        }
    }
    */
    
    /**
     * Called when the user clicks on the delete button.
     * @throws SQLException 
     */
  /*  @FXML
private void handleDeletePerson() {
    	
    	TrastoVO empVO;
    	int selectedIndex;
    	ArrayList<TrastoVO> llistaEmpleats;
    	
    	try {
	        
    		selectedIndex = trastosTable.getSelectionModel().getSelectedIndex();
	        
    		if (selectedIndex >= 0) {
    			
    			trastoDAO = new TrastoDAO();
    			//obtenim l'empleat a esborrar
	        	empVO = trastosTable.getItems().get(selectedIndex);
	        	//Borrem l'empleat a bbdd
	        	trastoDAO.deleteTrasto(empVO.getID_Producto());
	        	//obtenim totes els empleats
	        	llistaEmpleats = trastoDAO.obtenirTotsTrastos();
	        	
	        	//esborrem totes els dades
	        	trastosTable.getItems().clear();
	        	//carreguem  la llista de'mepleats actuatlizada
	        	for(int i=0; i< llistaEmpleats.size();i++)
	        		trastosTable.getItems().add(llistaEmpleats.get(i));
	        	
	        	
	        } else {
	            // Nothing selected.
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Information Dialog");
	    		alert.setHeaderText(null);
	    		alert.setContentText("I have a great message for you!");

	    		alert.showAndWait();
	        }
	        
    	}catch(SQLException e) {
    		System.err.println("handleDeletePerson :: " + e.getMessage());
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information Dialog");
    		alert.setHeaderText(null);
    		alert.setContentText("I have a great message for you!");

    		alert.showAndWait();
    	}
    }
    */
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
  /*  @FXML
    private void handleNewPerson() {
        TrastoVO tempPerson = new TrastoVO();
        boolean okClicked = mainApp.showTrastoEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getTrastoData().add(tempPerson);
        }
    }
    */
    

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     
   /* @FXML
    private void handleEditPerson() {
        EmpVO selectedPerson = CompanyCSVTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                .title("No Selection")
                .masthead("No Person Selected")
                .message("Please select a person in the table.")
                .showWarning();
        }
    }*/
}