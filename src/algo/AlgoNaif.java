package algo;

import adn.MotifBio;

/**
 * Algorithme de recherche de motif naif
 * 
 * @author Quentin Baert & Alexandre Verkyndt
 */
public class AlgoNaif implements Algo {
	
	///////////////
	// ATTRIBUTS //
	///////////////
	
	private static AlgoNaif instance = new AlgoNaif();
	
	//////////////
	// METHODES //
	//////////////
	
	// Contructeur prive
	private AlgoNaif() {
	}
	
	/**
	 * Accesseur vers l'unique instance de la classe AlgoNaif
	 * 
	 * @return unique instance de la classe AlgoNaif
	 */
	public static AlgoNaif getInstance() {
		return instance;
	}
	
	// Determine si un mot est accepte par l'algorithme en fonction d'une entree
	private boolean acceptFor(String word, Entree entree, MotifBio motif) {
		if (word.equals(motif.getMotif()))
			return true;
		else if (entree.takeCompl() && word.equals(motif.getCompl()))
			return true;
		else if (entree.takeReverse() && word.equals(motif.getReverse()))
			return true;
		else if (entree.takeRevCompl() && word.equals(motif.getRevCompl()))
			return true;
		else
			return false;
	}

	@Override
	public Sortie apply(Entree entree, MotifBio motif) {
		// Motif
		String motifStr = motif.getMotif();
		int motifLen = motifStr.length();
		
		// Sequence
		String sequence = entree.getSequence();
		
		// Sortie
		Sortie sortie = new Sortie(entree);

		for (int i = 0; i <= sequence.length() - motifLen; i++) {
			String window = sequence.substring(i, i + motifLen);
			if (this.acceptFor(window, entree, motif))
				sortie.addPosition(i);
		}
		
		return sortie;
	}

}
