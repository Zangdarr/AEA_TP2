package algo;

import java.util.Set;
import java.util.TreeSet;

import adn.MotifADN;

/**
 * Sortie d'un algorithme de recherche de motif.
 * 
 * @author Quentin Baert & Alexandre Verkyndt
 */
public class Sortie {
	
	///////////////
	// ATTRIBUTS //
	///////////////
	
	/**
	 * Entree a partir de laquelle a ete generee la sortie
	 */
	private Entree entree;
	
	/**
	 * Motif qui a ete cherche par l'algorithme
	 */
	private MotifADN motif;
	
	/**
	 * Liste des position auxquelles le motif a ete trouve par l'algorithme
	 */
	private Set<Integer> positions;
	
	//////////////
	// METHODES //
	//////////////
	
	/**
	 * Constructeur
	 * 
	 * @param name
	 * 			nom de la sequence d'ADN de la sortie
	 * @param sequence
	 * 			sequence dans laquelle le motif a ete cherche
	 * @param motif
	 * 			motif recherche par l'algorithme
	 */
	public Sortie(Entree entree) {
		this.entree = entree;
		this.positions = new TreeSet<Integer>();
	}

	/**
	 * Donne le motif de la sortie
	 * 
	 * @return motif de la sortie
	 */
	public MotifADN getMotif() {
		return this.motif;
	}
	
	/**
	 * Donne les positions du motif dans la sequence
	 * 
	 * @return positions du motif dans la sequence
	 */
	public Set<Integer> getPositions() {
		return this.positions;
	}
	
	/**
	 * Ajoute une position a la liste des positions du motif
	 * 
	 * @param position
	 * 			position a ajouter a la liste des positions du motif
	 */
	public void addPosition(int position) {
		this.positions.add(position);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.entree + "\n");
		sb.append("Position du motif : \n");
        
        for (Integer position : this.positions) {
        	sb.append(position + "\n");
        }
        
        return sb.toString();
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entree == null) ? 0 : entree.hashCode());
		result = prime * result + ((motif == null) ? 0 : motif.hashCode());
		result = prime * result
				+ ((positions == null) ? 0 : positions.hashCode());
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
		Sortie other = (Sortie) obj;
		if (entree == null) {
			if (other.entree != null)
				return false;
		} else if (!entree.equals(other.entree))
			return false;
		if (motif == null) {
			if (other.motif != null)
				return false;
		} else if (!motif.equals(other.motif))
			return false;
		if (positions == null) {
			if (other.positions != null)
				return false;
		} else if (!positions.equals(other.positions))
			return false;
		return true;
	}

}
