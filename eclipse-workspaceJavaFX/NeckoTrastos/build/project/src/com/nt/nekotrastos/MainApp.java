package com.nt.nekotrastos;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nt.nekotrastos.model.TrastoDAO;
import com.nt.nekotrastos.model.TrastoVO;
import com.nt.nekotrastos.model.UsuarioDAO;
import com.nt.nekotrastos.model.UsuarioVO;
import com.nt.nekotrastos.view.InicioSesionController;
//import com.nt.NekoTrastos.view.TrastoEditDialogController;
import com.nt.nekotrastos.view.MenuInicialOverviewController;
import com.nt.nekotrastos.view.MisTrastosController;
import com.nt.nekotrastos.view.TrastoEditDialogController;

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
	/*** Variables globales ***/
    private Stage primaryStage;		// Escenario Principal, donde se cargan las escenas
    private BorderPane rootLayout;	// Establece los bordes la ventana root
    private TrastoDAO trastoDAO;	// Instancia de la classe TrastoDAO donde llamaremos los metodos correspondientes a las acciones hacia los trastos (mostrar, crear, editar, etc.)
    private UsuarioDAO usuarioDAO;	// Instancia de la classe UsuarioDAO donde llamaremos los metodos correspondientes a las acciones hacia los usuarios (mostrar, crear, editar, etc.)
    private UsuarioVO usuarioLogin;	// Instancia de los datos del usuario logueado
    private TrastoVO trastoEditar;	// Instancia del trasto sel·leccionado en el menú "mis trastos", para ser editado

    /*** LISTAS OBSERVABLES ***/

	/**
	 * Lista de todos los trastos
	 */
	private ObservableList<TrastoVO> trastoData = FXCollections.observableArrayList();
	/**
	 * Lista de los trastos que posee cada usuario
	 */
	private ObservableList<TrastoVO> trastoDataUsuario = FXCollections.observableArrayList();
	/**
	 * Lista de los datos de cada usuario
	 */
	private ObservableList<UsuarioVO> UsuarioData = FXCollections.observableArrayList();
	
	
	/**
	 * Constructor de la classe MainApp: carga todos los trastos de la base de datos
	 * @throws InvocationTargetException
	 */
	public MainApp() throws InvocationTargetException {

		
		ArrayList<UsuarioVO> listaUsuarios;		// Variable donde guardamos la lista de todos los usuarios
		
		
		cargarTrastos();						//Se cargan trastos a llistaTrastos Método void que carga los trastos a la listatrasto
		
		try {
			usuarioDAO = new UsuarioDAO();						// Instáncia de UsuarioDAO para cargar los métodos de los usuarios
			listaUsuarios = usuarioDAO.obtenirTotsUsuarios();	// Añadimos los trastos a la arrayList listaUsuarios
			for(int i=0; i< listaUsuarios.size();i++) {			// Bucle que va llenando la lista recorre los indices llamados anteriormente
				UsuarioData.add(listaUsuarios.get(i));			// Cada passada en el for llena añade un usuario
				System.out.println(listaUsuarios.get(i).getId_Usuario());	// Comprovación que llena la lista observable de datos 
				System.out.println(listaUsuarios.get(i).getContrasenya());
			}
		}
		catch(SQLException e) {									// Possible error en el MainApp
			System.err.println("MainApp :: " + e.getMessage());
		}
		
	}
		
	
  
	/**
	 * Devuelve una lista observable de todos los trastos (menú inicial). 
	 * @return
	 */
	public ObservableList<TrastoVO> getTrastoData() {
	
		return trastoData;
	}
	
	/**
	 * Devuelve una lista observable de los trastos del usuario logeado (mis trastos)
	 * @return
	 */
	public ObservableList<TrastoVO> getMisTrastosData() {

		return trastoDataUsuario;
	}
	/**
	 * Devuelve los datos de la tabla Usuario de la lista Usuarios
	 * @return
	 */
	public ObservableList<UsuarioVO> getUsuarioData() {
		
		return UsuarioData;
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
     * Carga la tabla Trastos en el menú mis trastos
     * @throws SQLException
     */
    public void cargarTablaMisTrastos() throws SQLException {
    	ArrayList<TrastoVO> llistaTrastosUsuario;
    	
    	llistaTrastosUsuario = trastoDAO.obtenerTrastosDeMisTrastos(this.getUsuarioLogin().getId_Usuario());
    	
    	for(int i=0; i< llistaTrastosUsuario.size();i++) {						// Guarda todos los trastos del usuario logueado (mis trastos)
			trastoDataUsuario.add(llistaTrastosUsuario.get(i));
			System.out.println(llistaTrastosUsuario.get(i).getID_Producto());
			System.out.println(llistaTrastosUsuario.get(i).getDescripcion());
		}
    }

    /**
     * Carga la ventana root donde estará el menú inicial y mis trastos
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
    
    	System.out.println("MainApp.shoeMenuInicial iniciando...");
    	
    	//Es netejen totes les llistes
    	trastoData.clear();
    	trastoDataUsuario.clear();
    	//tornem a carregar tots els trasots del menu incici
    	cargarTrastos();
    	
        try {
        	
        	
            // Carga el diseño de la vista de un archivo fxml (propiedades)
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MenuInicial.fxml"));
            AnchorPane menuInicialOverview = (AnchorPane) loader.load();

            // Carga la vista del menú inicial dentro de root layout (Escena)
            rootLayout.setCenter(menuInicialOverview);
            
            // Cuidao comenta estas dos linias y funciona lo que tenemos	/////////////////////////
            MenuInicialOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
            //Visibiliadad botones
            controller.visibilidadBotonesSesion(this.usuarioLogin);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la escena del menú inicial");
        }
    }
    
    
   
    /**
     * Muestra la ventana iniciar sesión
     */
    public void iniSession() {
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
            
         // Set the Usuario into the controller.
            InicioSesionController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            
         // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            usuarioLogin = controller.getUsuarioVO();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la escena del menú inicial");
        
        } 
		
    }
    /**
     * Muestra la ventana Mis Trastos
     * @throws SQLException
     */
    public void showMisTrastos() throws SQLException {
        try {
            // Carga el diseño de la vista de un archivo fxml (propiedades)
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MisTrastos.fxml"));
            AnchorPane misTrastosOverview = (AnchorPane) loader.load();

            // Carga la vista del menú inicial dentro de root layout (Escena)
            rootLayout.setCenter(misTrastosOverview);
            
            // Cuidao comenta estas dos linias y funciona lo que tenemos	/////////////////////////
            MisTrastosController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la escena del menú inicial");
        }
    }
    /**
     * Carga la ventana de carga de creación y edición de trastos
     * @param trasto: trasto a editar  
     * @return	devuelve true en caso de hacer click al confirmar la edición retornando isAfegirCliked
     * @throws SQLException
     */
    public boolean showTrastoEditDialog(TrastoVO trasto) throws SQLException {
        try {
        	trastoEditar = trasto;
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EditarTrasto.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Trasto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            TrastoEditDialogController controller = loader.getController();
            //controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            System.out.println("PROPIETARIO: "+trasto.getID_Propietario());
            System.out.println("Propietario: "+trasto);
            controller.setTrasto(trasto);	// no funciona Mirar DESPRÉS
            System.out.println(trasto.getNombreTrasto());

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isAfegirCliked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Devuelve los datos del trasto a editar
     * @return
     */
    public TrastoVO getTrastoEditar() {
    	return trastoEditar;
    }
    
  
    
    
    /**
     * Devuelve todos los datos del usuario logeado
     * 
     * @return
     */
    public UsuarioVO getUsuarioLogin() {
    	//System.out.println("USUARIO DEVUELTO: " + usuarioLogin.getId_Usuario());
        return usuarioLogin;
    }
    
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
    
    
    /**
     * Carga los datos para la lista observable
     */
    private void cargarTrastos() {
    	
    	ArrayList<TrastoVO> llistaTrastos;
		
		
		try {
			trastoDAO = new TrastoDAO();
			llistaTrastos = trastoDAO.obtenirTotsTrastos();	// lista para enviar al menú inicial
			
			
			for(int i=0; i< llistaTrastos.size();i++) {						// Guarda todos los trastos de la base de datos (menú inical)
				trastoData.add(llistaTrastos.get(i));
				System.out.println(llistaTrastos.get(i).getID_Producto());
				System.out.println(llistaTrastos.get(i).getDescripcion());
			}
			
			
		}catch(SQLException e) {
			System.err.println("MainApp :: " + e.getMessage());
		}
    }
}