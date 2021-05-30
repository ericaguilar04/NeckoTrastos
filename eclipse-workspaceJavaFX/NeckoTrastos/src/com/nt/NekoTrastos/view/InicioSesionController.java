package com.nt.NekoTrastos.view;

import java.sql.SQLException;
import java.util.ArrayList;

import com.nt.NekoTrastos.MainApp;
import com.nt.NekoTrastos.model.TrastoDAO;
import com.nt.NekoTrastos.model.TrastoVO;
import com.nt.NekoTrastos.model.UsuarioVO;
import com.nt.NekoTrastos.model.UsuarioDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */


public class InicioSesionController {
	
    public  int hola;
	@FXML
    private TextField loginUsuario;
    @FXML
    private PasswordField contraseña;
    @FXML
    private Label errorLogin;

    private Stage dialogStage;
    private UsuarioVO usuario;
    private UsuarioDAO usuarioDAO;
    private boolean usuarioExiste = false;
    private MainApp mainApp;
    
    @FXML
    public boolean aceptarClicked() throws SQLException {
    	
		
    	//String getLoginUsuario = loginUsuario.getText();
    	//String getContraseña = contraseña.getText();
    	try {
    		//System.out.println(contraseña.getText());
    		usuarioDAO = new UsuarioDAO();
    		//usuariosList = usuarioDAO.comprovacionUsuario(getLoginUsuario, getContraseña);
    		/*if (loginUsuario.getText().isEmpty()) {
    			
    		}*/
    		
    			if (usuarioDAO.comprovacionUsuario(loginUsuario.getText(), contraseña.getText()) ) {
        			System.out.println(loginUsuario.getText() + contraseña.getText());
        			dialogStage.close();
        			usuarioExiste = true;
    			}
    			else {
    				errorLogin.setText("Usuario o Contraseña Incorrectas");
    			}
    			System.out.println(loginUsuario.getText() + contraseña.getText());
        		usuarioDAO.comprovacionUsuario(loginUsuario.getText(), contraseña.getText());
        		
    	}catch(SQLException e){
    		System.err.println("buttonDelete ::  ERROR" + e.getMessage());
    		 // Nothing selected.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("");
            alert.setHeaderText("No Usuario Selected");
            alert.setContentText("Please select a Usuario in the table.");
            
            alert.showAndWait();
    		
    	}
    	System.out.println("USUARIO EXISTE " + usuarioExiste);
		return usuarioExiste;
    	
    	

    }
    
    public boolean usuarioExiste() throws SQLException {
    	usuarioDAO = new UsuarioDAO();
    	if (usuarioDAO.comprovacionUsuario(loginUsuario.getText(), contraseña.getText()) )	usuarioExiste = true;
    	else usuarioExiste = false;
    	
    	return usuarioExiste;
    }
    public InicioSesionController() {	
    }
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
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
     * Devuelve true si el usuario está en la base de datos
     * 
     * @return
     * @throws SQLException 
     */
    /*public boolean isAceptarClicked() throws SQLException {
    	usuarioDAO = new UsuarioDAO();
    	
    	if (usuarioDAO.comprovacionUsuario(loginUsuario.getText(), contraseña.getText()) ) {
    		aceptarClicked = true;
    	}
    	else aceptarClicked = false;
        return aceptarClicked;
    }*/

    /**
     * Called when the user clicks ok.
     */
   /* @FXML
    private void handleAceptar() {
        if (is Aceptar()) {
            empleat.setEmpno(String.valueOf(empnoField.getText()));
            empleat.setEname(enameField.getText());


            okClicked = true;
            dialogStage.close();
        }
    }*/

    /**
     * Called when the user clicks cancel.
     */
    //@FXML
    /*private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    /*private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n"; 
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
        	Dialogs.create()
		        .title("Invalid Fields")
		        .masthead("Please correct invalid fields")
		        .message(errorMessage)
		        .showError();
            return false;
        }
    }*/
}