package tres_en_Ratlla_OO_MarcContrerasGrafic;

public class Fitxa {
	
	private String fitxa;
	
	/*** CONSTRUCTOR ***/
	
	/**
	 * Construïm l'objecte entrant la fitxa
	 * @param fitxa
	 */
	public Fitxa(String fitxa) {
		this.fitxa = fitxa;
	}
	
	/*** GETTER ***/
	/**
	 * Ens mostra el valor de la fitxa
	 * @return
	 */
	public String getFitxa(){
		return this.fitxa;
	}
	
	/*** SETTER ***/
	/**
	 * Modifiquem el valor de la fitxa
	 * @param fitxa
	 */
	public void setFitxa(String fitxa) {
		this.fitxa = fitxa;
	}
	
	
}
