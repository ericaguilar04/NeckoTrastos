package com.nt.nekotrastos.view;

import java.sql.SQLException;
import java.util.ArrayList;

import com.nt.nekotrastos.MainApp;
import com.nt.nekotrastos.model.TrastoDAO;
import com.nt.nekotrastos.model.TrastoVO;
import com.nt.nekotrastos.model.UsuarioVO;
import com.nt.nekotrastos.model.UsuarioDAO;

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
 * @author 
 */


public class InicioSesionController {
	
   
	@FXML
    private TextField loginUsuario;
    @FXML
    private PasswordField contraseña;
    @FXML
    private Label errorLogin;

    private Stage dialogStage;
    private UsuarioDAO usuarioDAO;
    private UsuarioVO usuario;
    private boolean usuarioExiste;
    
    private MainApp mainApp;
    private boolean okClicked = false;
  
    
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	System.out.println(" Iniciando InicioSesionController initialize ...");       
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
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public UsuarioVO getUsuarioVO() {
        return usuario;
    }
    
    @FXML
    public UsuarioVO aceptarClicked() throws SQLException {
    	System.out.println(" Iniciando aceptarClicked ...");
	    try {
		    		usuarioDAO = new UsuarioDAO();
		    		
		    		System.out.println(" aceptarClicked login - password " + loginUsuario.getText() + " " + contraseña.getText());
		    		usuario = usuarioDAO.comprovacionUsuario(loginUsuario.getText(), contraseña.getText());
		    		System.out.println(" Iniciando aceptarClicked usuario existe " + usuarioExiste);
		    		// esto funciona, pero no entiendo porque not peta el programa al encontrar null
		    		// if(usuarioDAO.confirmacionUsuario(loginUsuario.getText(), contraseña.getText()))
		    		if(usuario != null) {
		    			 okClicked = true;
		    	         dialogStage.close();
		    	         System.out.println(usuarioDAO.confirmacionUsuario(loginUsuario.getText(), contraseña.getText()));
		    		}else  {
		    			
		    			errorLogin.setText("Usuario o Contraseña Incorrectas");
		    		}
		    		
		    		
		    		
	        		
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
	    	
    	
    	//if(dialogStage != null)dialogStage.close();
    	return usuario;
    	
    }
    
    /*
    
    public boolean usuarioExiste() throws SQLException {
    	usuarioDAO = new UsuarioDAO();
    	if (usuarioDAO.comprovacionUsuario(loginUsuario.getText(), contraseña.getText()) )	usuarioExiste = true;
    	else usuarioExiste = false;
    	
    	return usuarioExiste;
    }
    */
    
    public InicioSesionController() {	
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