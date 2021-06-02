package com.nt.nekotrastos.view;

import java.sql.SQLException;

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
	/*** Variables ***/
	
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
	
	private Stage dialogStage;
    private TrastoVO trasto;
    private boolean okClicked = false;	
    private TrastoDAO trastoDAO;
    private MainApp mainApp;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
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
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setTrasto(TrastoVO trasto) {
        this.trasto = trasto;

        
        
    }
    
    /**
     * Returns true if the person clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isAfegirCliked() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks ok.
     * @throws SQLException 
     */
    @FXML
    private void handleOk() throws SQLException {
        if (isInputValid()) {
            trasto.setNombreTrasto(nombreTrastoField.getText());
            trasto.setDescripcion(descripcioField.getText());
            trasto.setPrecio(Float.parseFloat(precioField.getText()));
           
            //trasto.setID_Propietario(mainApp.getUsuarioLogin());
            trastoDAO.insertTrasto(nombreTrastoField.getText(), descripcioField.getText());
            //trastosTable.

            okClicked = true;
            dialogStage.close();
        }
    }
    
    /**
     * Validates the user input in the text fields.
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

