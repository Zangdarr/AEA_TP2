package adn;

/**
 * Classe representant un motif a rechercher dans une sequence d'ADN.
 * 
 * @author Quentin Baert & Alexandre Verkyndt
 */
public class MotifADN extends Motif implements MotifBio {
	
	//////////////
	// METHODES //
	//////////////
	
	/**
	 * Constructeur.
	 *
	 * @param motif
	 *			chaine de caracteres representant le motif d'ADN
	 */
	public MotifADN(String motif) {
		super(motif);
	}

	// Renverse une chaine de caracteres
	private String reverse(String string) {
		return new StringBuilder(string).reverse().toString();
	}
	
	/**
	 * Donne la chaine de caracteres representant le motif reverse de ce motif
	 * 
	 * @return chaine de caracteres representant le motif reverse de ce motif
	 */
	public String getReverse() {
		return this.reverse(this.motif); 
	}
	
	// Donne l'inverse d'un nucleotide
	private char nucleotideCompl(char nucleotide) {
		switch (nucleotide) {
			case 'A': return 'T';
			case 'C': return 'G';
			case 'G': return 'C';
			default : return 'A';
		}
	}
	
	/**
	 * Donne la chaine de caracteres representant le motif complementaire a ce motif
	 * 
	 * @return chaine de caracteres representant le motif complementaire a ce motif
	 */
	public String getCompl() {
		StringBuilder sb = new StringBuilder();
		
		for (char nucleotide : this.motif.toCharArray()) {
			sb.append(this.nucleotideCompl(nucleotide));
		}
		
		return sb.toString();
	}
	
	/**
	 * Donne la chaine de caracteres representant le motif reverse complementaire a ce motif
	 * 
	 * @return chaine de caracteres representant le motif reverse complementaire a ce motif
	 */
	public String getRevCompl() {
		return this.reverse(this.getCompl());
	}
	
	@Override
	public String toString() {
		return this.motif;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((motif == null) ? 0 : motif.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MotifADN other = (MotifADN) obj;
		if (motif == null) {
			if (other.motif != null)
				return false;
		} else if (!motif.equals(other.motif))
			return false;
		return true;
	}

}
