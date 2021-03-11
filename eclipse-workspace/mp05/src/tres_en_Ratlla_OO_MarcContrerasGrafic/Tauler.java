package tres_en_Ratlla_OO_MarcContrerasGrafic;

public class Tauler {
	private String[][] tauler;
	
	/*** CONSTRUCTORS ***/
	/**
	 * Tauler buït
	 */
	public Tauler() {
		neteja();
	}
	
	/**
	 * Construïm tauler a partir d'una array String (tauler anterior)
	 * @param tauler
	 */
	public Tauler(String[][] tauler) {
		this.tauler = new String[3][3];
		for(int i = 0; i < tauler.length; i++) {
			for(int j = 0; j< tauler.length; j++) {
				this.tauler[i][j] = tauler[i][j]; 
			}
		}
	}
	
	/**
	 * Torna un tauler
	 * @return
	 */
	public String[][] getTauler(){
		return this.tauler;
	}
	
	/**
	 * Modifica substitueïx el contigut del tauler pel contigut de l'array tauler
	 * @param tauler
	 */
	public void setTauler(String[][] tauler) {
		this.tauler = tauler;
	}
	
	/*** MÈTODES ***/
	public void neteja() {
		this.tauler = new String[3][3];
		for(int i = 0; i < tauler.length; i++) {
			for(int j = 0; j< tauler.length; j++) {
				this.tauler[i][j] = "*";
			}
		}
	}
	
	public void verificarGuanyador(Jugador jugador) {
		if(this.tauler[0][0] == jugador.getFitxa() && this.tauler[0][1] == jugador.getFitxa() && this.tauler[0][2] == jugador.getFitxa())
			jugador.setWinner(true);
		if(this.tauler[1][0] == jugador.getFitxa() && this.tauler[1][1] == jugador.getFitxa() && this.tauler[1][2] == jugador.getFitxa())
			jugador.setWinner(true);
		if(this.tauler[2][0] == jugador.getFitxa() && this.tauler[2][1] == jugador.getFitxa() && this.tauler[2][2] == jugador.getFitxa())
			jugador.setWinner(true);
		if(this.tauler[0][0] == jugador.getFitxa() && this.tauler[0][1] == jugador.getFitxa() && this.tauler[0][2] == jugador.getFitxa())
			jugador.setWinner(true);
		if(this.tauler[0][1] == jugador.getFitxa() && this.tauler[1][1] == jugador.getFitxa() && this.tauler[2][1] == jugador.getFitxa())
			jugador.setWinner(true);
		if(this.tauler[0][2] == jugador.getFitxa() && this.tauler[1][2] == jugador.getFitxa() && this.tauler[2][2] == jugador.getFitxa())
			jugador.setWinner(true);
		if(this.tauler[0][0] == jugador.getFitxa() && this.tauler[1][1] == jugador.getFitxa() && this.tauler[2][2] == jugador.getFitxa())
			jugador.setWinner(true);
		if(this.tauler[2][0] == jugador.getFitxa() && this.tauler[1][1] == jugador.getFitxa() && this.tauler[0][2] == jugador.getFitxa())
			jugador.setWinner(true);
		
			
	}
	
	public boolean casellaRepetida(Jugador jugador, int x, int y) {
		return this.getTauler()[x][y].equals(jugador.getFitxa());
	}
	
	public void entraCasella(int x, int y) {
		
	}
	
	
	public void posarFitxa(Jugador jugador, int x, int y ) {
		this.tauler[x][y]= jugador.getFitxa();
	}
	
	public void mostrarTauler() {
		for (int i=0;i<this.tauler.length;i++) {
			for (int j=0;j<this.tauler.length;j++) {
				System.out.print(this.tauler[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	
	
}
