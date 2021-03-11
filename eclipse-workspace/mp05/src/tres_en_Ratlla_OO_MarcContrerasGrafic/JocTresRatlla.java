package tres_en_Ratlla_OO_MarcContrerasGrafic;
import java.util.Scanner;
import java.awt.Font;
import java.util.Random;
import edu.princeton.cs.introcs.*;	
public class JocTresRatlla {
	public static void main(String[] args) {
		/*** SCANNER, RANDOM & Variables***/
		Scanner key = new Scanner(System.in);
		Random arbit = new Random();
		String imatgeW = "/tres_en_Ratlla_OO_MarcContrerasGrafic/pic.png";
		String imatgeL = "/tres_en_Ratlla_OO_MarcContrerasGrafic/loser.png";
		String imatgeE = "/tres_en_Ratlla_OO_MarcContrerasGrafic/empat.png";
		int x = 0, y = 0, count = 0;
		boolean canviJugador = true;
		/*** Creació de fitxes ***//
		Fitxa fitxa1 = new Fitxa("X");
		Jugador jugador1 = new Jugador(fitxa1);	// Jugador 
		fitxa1 = new Fitxa("O");
		Jugador jugador2 = new Jugador(fitxa1);	// Robot
		
		/*** inicialitzem tauler ***/
		Tauler tauler = new Tauler();		
		
		
		iniciGrafic(); /*** Dibuixem el tauler ***/
				
		/*** COMENCEM A JUGAR ***/
		while (!jugador1.isWinner() && !jugador2.isWinner()  && count != 9) { // Mentre que no tinguem guanyador
			System.out.println("Play: ");
			/**
			 * JUGAR AMB EL RATOLÍ
			 */
			double coordenadaX = 0;			 // Coordenada x rebuda de la funció stdDraw		
			double coordenadaY = 0;			 // Coordenada y rebuda de la funció stdDraw
			int x_1 = 0;			 // Coordenada x_1 canviada a int que omplirà la casella de la array a1
			int y_1 = 0;			 // Coordenada y_1 canviada a int que omplirà la casella de la array a1
			
			/*** Entrada per teclat ***/
			if(count == 0) {
				System.out.println("First");
			x = key.nextInt();
			y = key.nextInt();
			}
			
			/*** COMPROVAR FITXA ***/
			while(tauler.casellaRepetida(jugador1, x, y) || tauler.casellaRepetida(jugador2, x, y)) {
				System.out.println("Torna a provar");
				if(canviJugador == true) {
					x = key.nextInt();
					y = key.nextInt();
				}
				else if(canviJugador == false){
					x = arbit.nextInt(3);
					y = arbit.nextInt(3);
				}
			}
			
			if(canviJugador == true) tauler.posarFitxa(jugador1, x, y);
			else if(canviJugador == false) tauler.posarFitxa(jugador2, x, y);
	
			count++;
			canviJugador = !canviJugador;
			tauler.verificarGuanyador(jugador1);
			tauler.verificarGuanyador(jugador2);
			System.out.println(count + " " + jugador1.isWinner() + " " + jugador2.isWinner());
			
			tauler.mostrarTauler();
		
			
		}
		
		/*** Final del programa ***/
		if(jugador1.isWinner()) {
			System.out.println("Guanyador Jugador");
			dibuixFinal(imatgeW, "WINNER");
		}
		else if(jugador2.isWinner()) {
			System.out.println("Guanyador Robot");
			dibuixFinal(imatgeL, "LOSER");
		}
		else if(!jugador1.isWinner() || !jugador2.isWinner() && count == 9)	{
			System.out.println("EMPAT");
			dibuixFinal(imatgeE,"EMPATS");
		}
		
		key.close();
		
					
		
			
		
	}	
	
	/*** METODES DE JOC ***/
		
	
	/*** METODES DE PART GRAFICA ***/
	
	/**
	 * Mostra el tauler buït
	 */
		public static void iniciGrafic() {
			StdDraw.setPenRadius(0.025);			// Si volem que les línies, punts, cercles, etc., siguin més grans o més petits
			StdDraw.setPenColor(StdDraw.BLACK);		// Establim el color 
			StdDraw.line(0, 0.33, 1, 0.33);			// Coordenades per dibuixar les línies del tauler (4 en total)
			StdDraw.line(0, 0.667, 1, 0.667);
			StdDraw.line(0.33, 0, 0.33, 1);
			StdDraw.line(0.667, 0, 0.667, 1);
		}
		
		/**
		 * Espera a que l'usuari cliqui
		 */
		private static void esperaClic() {
			while (StdDraw.isMousePressed()) {			// Espera mentre que esta apretat
				
			}

			while (!StdDraw.isMousePressed()) {			// Espera mentre que no està apretat quan fasis click després d'estar en aquest while continuarà el programa i podràs interactuar amb ell
				
			}
		}
		/**
		 * Neteja pantalla i mostra imatge d'empat
		 */
		private static void dibuixFinal(String imatge, String text) {
			StdDraw.clear();						// Netegem la pantalla 
			StdDraw.picture(0.5, 0.5, imatge); // mostrem la foto del directori
			Font font = new Font("Arial", Font.BOLD, 60);	// Variable per guardar el format del text que escriurem a continuació
			StdDraw.setPenColor(StdDraw.BLACK);				// Establim el color del text
			StdDraw.setFont(font);							// Establim el format del text
			StdDraw.text(0.5, 0.5, text);				// Escrivim el text i les coordenades
		}
		
		
	
}
