package nt.makery.address.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

import java.sql.SQLException;
import java.util.ArrayList;


import nt.makery.address.MainApp;
import nt.makery.address.model.EmpDAO;
import nt.makery.address.model.EmpVO;


public class EmplatOverviewController {
	
    @FXML
    private TableView<EmpVO> companyCSVTable;
    @FXML
    private TableColumn<EmpVO, String> empnoColumn;
    @FXML
    private TableColumn<EmpVO, String> enameColumn;  

    @FXML
    private Label empnoLabel;
    @FXML
    private Label enamelabel;
    @FXML
    private Label joblabel;
    @FXML
    private Label mgrlabel;
    @FXML
    private Label hiredatelabel;
    @FXML
    private Label salarylabel;
    @FXML
    private Label commlabel;
    @FXML
    private Label deptnolabel;

    // Reference to the main application.
    private MainApp mainApp;
    private EmpDAO empDAO;
    
    

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public EmplatOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	empnoColumn.setCellValueFactory(
                cellData -> new  SimpleStringProperty(String.valueOf(cellData.getValue().getEmpno())));
    	enameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getEname()));
    	

       // Clear person details.
        showPersonDetails(null);
        
        System.out.println(companyCSVTable == null);

        // Listen for selection changes and show the person details when changed.
        companyCSVTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        companyCSVTable.setItems(mainApp.getPersonData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(EmpVO empleat) {
        if (empleat != null) {
            // Fill the labels with info from the person object.
        	empnoLabel.setText(String.valueOf(empleat.getEmpno()));
        	enamelabel.setText(empleat.getEname());
        	joblabel.setText(empleat.getJob());
        	mgrlabel.setText(empleat.getMgr());
        	hiredatelabel.setText(empleat.getHiredate());
        	salarylabel.setText(empleat.getSalary());
        	commlabel.setText(empleat.getComm());
        	deptnolabel.setText(empleat.getDeptno());

        } else {
            // Person is null, remove all the text.
        	empnoLabel.setText("");
        	enamelabel.setText("");
        	joblabel.setText("");
        	mgrlabel.setText("");
        	hiredatelabel.setText("");
        	salarylabel.setText("");
        	commlabel.setText("");
        	deptnolabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     * @throws SQLException 
     */
    
    /*  @FXML
	private void handleDeletePerson() {
    	
    	EmpVO empVO;
    	int selectedIndex;
    	ArrayList<EmpVO> llistaEmpleats;
    	
    	try {
	        
    		selectedIndex = companyCSVTable.getSelectionModel().getSelectedIndex();
	        
    		if (selectedIndex >= 0) {
    			
    			empDAO = new EmpDAO();
    			//obtenim l'empleat a esborrar
	        	empVO = companyCSVTable.getItems().get(selectedIndex);
	        	//Borrem l'empleat a bbdd
	        	empDAO.deleteEMP(empVO.getEmpno());
	        	//obtenim totes els empleats
	        	llistaEmpleats = empDAO.obtenirTotsEmpleats();
	        	
	        	//esborrem totes els dades
	        	companyCSVTable.getItems().clear();
	        	//carreguem  la llista de'mepleats actuatlizada
	        	for(int i=0; i< llistaEmpleats.size();i++)
	        		companyCSVTable.getItems().add(llistaEmpleats.get(i));
	        	
	        	
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
    
    /*
    @FXML
    private void handleNewPerson() {
        EmpVO tempPerson = new EmpVO();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
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