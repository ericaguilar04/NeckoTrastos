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
	
   /*** VARIABLES GLOBALES ***/
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
     * CONSTRUCTOR DEL CONTROLADOR
     */
    public InicioSesionController() {	
    }
    
    /**
     * Inicializa el controlador de clase. Se ejecuta al cargarlo
     * 
     */
    @FXML
    private void initialize() {
    	System.out.println(" Iniciando InicioSesionController initialize ...");       
    }
    
    /**
     * Establece el escenario de este diálogo
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    
    
    /**
     * Getter que devuelve el usuario creado como instancia de la clase UsuarioVO
     * 
     * @return
     */
    public UsuarioVO getUsuarioVO() {
        return usuario;
    }
    
    /**
     * Se ejecuta cuando el usuario haga clic al boton aceptar de está ventana para iniciar sesión
     * @return
     * @throws SQLException
     */
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
	    	
    	return usuario;
    	
    }
    
    
   
   
}