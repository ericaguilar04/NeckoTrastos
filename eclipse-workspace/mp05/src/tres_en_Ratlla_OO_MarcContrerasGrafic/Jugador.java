package tres_en_Ratlla_OO_MarcContrerasGrafic;

public class Jugador extends Fitxa {
	
	private boolean winner;
	
	/*** CONSTRUCTOR ***/
	/**
	 * Construeïx l'objecte jugador i li assigna una fitxa
	 * @param fitxa
	 */
	public Jugador(Fitxa fitxa) {
		super(fitxa.getFitxa());
		this.winner = false;
		
	}
	
	/*** SETTER ***/
	public void setFitxa(Fitxa fitxa) {
		 this.setFitxa(fitxa);
	}

	public boolean isWinner() {
		return this.winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
	
	
	
	
}
