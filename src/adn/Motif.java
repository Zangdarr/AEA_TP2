package adn;

/**
 * Represente un motif de texte
 * 
 * @author Quentin Baert & Alexandre Verkyndt
 */
public class Motif {

	///////////////
	// ATTRIBUTS //
	///////////////

	/**
	 * Motif
	 */
	protected String motif;
	
	//////////////
	// METHODES //
	//////////////
	
	/**
	 * Constructeur.
	 *
	 * @param motif
	 *			chaine de caracteres representant le motif
	 */
	public Motif(String motif) {
		this.motif = motif;
	}
	
	/**
	 * Donne la chaine de caracteres representant le motif
	 * 
	 * @return chaine de caracteres representant le motif
	 */
	public String getMotif() {
		return this.motif;
	}
	
	/**
	 * Determine si une chaine de caracteres est un prefixe de ce motif
	 * 
	 * @param s
	 * 			chaine de caracteres dont on souhaite savoir si elle est prefixe du motif
	 * @return true si la chaine est un prefixe de ce motif, false sinon
	 */
	public boolean hasPrefix(String s) {
		return this.motif.startsWith(s);
	}
	
	/**
	 * Determine si une chaine de caracteres est un suffixe de ce motif
	 * 
	 * @param s
	 * 			chaine de caractere dont on souhaite savoir si elle est suffixe du motif
	 * @return true si la chaine est un suffixe de ce motif, false sinon
	 */
	public boolean hasSuffix(String s) {
		return this.motif.endsWith(s);
	}
	
	/**
	 * Determine si une chaine de caracteres est un bord de ce motif
	 * 
	 * @param s
	 * 			chaine de caracteres dont on souhaite savoir si elle est un bord du motif
	 * @return true si la chaine est un bord de ce motif, false sinon
	 */
	public boolean hasEdge(String s) {
		return !s.isEmpty() &&
			   !s.equals(this.motif) &&
			   this.hasPrefix(s) &&
			   this.hasSuffix(s);
	}

}
