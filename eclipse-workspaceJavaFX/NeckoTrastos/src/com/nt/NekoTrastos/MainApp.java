package com.nt.NekoTrastos;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nt.NekoTrastos.model.TrastoDAO;
import com.nt.NekoTrastos.model.TrastoVO;
import com.nt.NekoTrastos.view.InicioSesionController;
//import com.nt.NekoTrastos.view.TrastoEditDialogController;
import com.nt.NekoTrastos.view.TrastoOverviewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
//import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private TrastoDAO trastoDAO;

    // ... AFTER THE OTHER VARIABLES ...

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<TrastoVO> TrastoData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {
		ArrayList<TrastoVO> llistaEmpleats;
		
		try {
			trastoDAO = new TrastoDAO();
			llistaEmpleats = trastoDAO.obtenirTotsTrastos();
			
			for(int i=0; i< llistaEmpleats.size();i++) {
				TrastoData.add(llistaEmpleats.get(i));
				System.out.println(llistaEmpleats.get(i).getID_Producto());
				System.out.println(llistaEmpleats.get(i).getDescripcion());
			}
		}catch(SQLException e) {
			System.err.println("MainApp :: " + e.getMessage());
		}
		
	}
  
	/**
	 * Returns the data as an observable list of Persons. 
	 * @return
	 */
	public ObservableList<TrastoVO> getTrastoData() {
	
		return TrastoData;
	}

    @Override
    /**
     * Método constructor que inicia la aplicación
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;			// Establece el escenario introducido como parámetro
        this.primaryStage.setTitle("Menú Inicial");	// Cabecera de la página
        initRootLayout();							// Carga la escena de los bordes de la aplicación

        showMenuInicial();						// Carga los elementos de la ventana inicializada
        
    }

    /**
     * Se inicializan los bordes efefefe
     */
    public void initRootLayout() {
        try {
            // Carga el diseño raiz de una archivo fxml (propiedades)
            FXMLLoader loader = new FXMLLoader();									// Instáncia para cargar escenas al escenario a partir de un FXML
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));	// Establecemos la ruta del archivo FXML
            rootLayout = (BorderPane) loader.load();								// Le damos las propiedades del borde assignado

            // Muestra el contenido de la escena configurado en las propiedades
            Scene scene = new Scene(rootLayout);			// Instáncia de la escena con las propiedades de rootLayout
            primaryStage.setScene(scene);					// Establecemos la
            primaryStage.show();							// Muestra la escena
            
        } catch (IOException e) {							// Error al configurar la escena  
            e.printStackTrace();
            System.err.println("Error al cargar la escena");
        }
    }

    /**
     * Muestra la vista del menú inicial dentro del root layout
     */
    public void showMenuInicial() {
        try {
            // Carga el diseño de la vista de un archivo fxml (propiedades)
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MenuInicial.fxml"));
            AnchorPane menuInicialOverview = (AnchorPane) loader.load();

            // Carga la vista del menú inicial dentro de root layout (Escena)
            rootLayout.setCenter(menuInicialOverview);
            
            // Cuidao comenta estas dos linias y funciona lo que tenemos	/////////////////////////
            TrastoOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la escena del menú inicial");
        }
    }
    
    public boolean iniSession() {
    	// Carga el diseño de la vista de un archivo fxml (propiedades)
    	try {
            // Carga el diseño de la vista de un archivo fxml (propiedades)
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/IniciarSesion.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

         // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Inicar Session");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
         // Set the person into the controller.
            InicioSesionController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            //controller.setTrasto(trasto);	// no funciona Mirar DESPRÉS
            
         // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la escena del menú inicial");
            return false;
        }
		
    }
    

    
    /*public boolean showTrastoEditDialog(TrastoVO trasto) {
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
            TrastoEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(trasto);	// no funciona Mirar DESPRÉS

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }*/
    /**
     * Retorna el escenario principal
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    /*
     * Ejecuta la aplicación
     */
    public static void main(String[] args) {
        launch(args);
    }
}