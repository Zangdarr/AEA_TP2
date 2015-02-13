package algo;

import adn.MotifBio;

/**
 * Interface representant un algorithme de recherche de motif.
 * 
 * @author Quentin Baert & Alexandre Verkyndt
 */
public interface Algo {

	/**
	 * Applique l'algorithme sur une entree et retourne une sortie
	 * 
	 * @param entree
	 * 			entree de l'algorithme
	 * @return sortie de l'algorithme
	 */
	public Sortie apply(Entree entree, MotifBio motif);

}
