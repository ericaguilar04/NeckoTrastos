package nt.makery.address;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import nt.makery.address.model.EmpDAO;
import nt.makery.address.model.EmpVO;

import nt.makery.address.view.EmplatOverviewController;
import nt.makery.address.view.PersonEditDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	/*** VARIABLES ***/
    private Stage primaryStage;
    private BorderPane rootLayout;
    private EmpDAO empDAO;

    // ... AFTER THE OTHER VARIABLES ...

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<EmpVO> empleatData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {
		/** Para cargar la base de datos **/
		ArrayList<EmpVO> llistaEmpleats;		
		
		try {
			empDAO = new EmpDAO();
			llistaEmpleats = empDAO.obtenirTotsEmpleats();
			
			for(int i=0; i< llistaEmpleats.size();i++) {
				empleatData.add(llistaEmpleats.get(i));
				System.out.println(llistaEmpleats.get(i).getEname());
			}
		}catch(SQLException e) {
			System.err.println("MainApp :: " + e.getMessage());
		}
		
	}
  
	/**
	 * Devuelve los datos como una lista observable de Usuarios
	 * @return
	 */
	public ObservableList<EmpVO> getPersonData() {
	
		return empleatData;
	}

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        //initRootLayout();

        showPersonOverview();
    }
    
    /**
     * Initializes the root layout.
     */
   /* public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/IniciarSesion.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            

            // Give the controller access to the main app.
            EmplatOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    /*public boolean showPersonEditDialog(EmpVO empleat) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(empleat);	// no funciona Mirar DESPRÉS

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    */
    
	/**
	 * Returns the main stage.
	 * @return
	 */
    
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
}