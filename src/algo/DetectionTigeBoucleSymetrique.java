package algo;

/**
 * Classe permettant de savoir si une sequence génomique représente une
 * structure tige boucle symetrique ou non
 * 
 * @author Quentin Baert & Alexandre Verkyndt
 */
public class DetectionTigeBoucleSymetrique {
	
	/**
	 * Sequence dont on souhaite savoir si elle est une structure tige/boucle ou non
	 */
	private String sequence;
	
	/**
	 * Constructeur
	 * 
	 * @param sequence
	 * 			sequence dont on souhaite savoir si elle est une structure tige/boucle ou non	
	 */
	public DetectionTigeBoucleSymetrique(String sequence) {
		this.sequence = sequence;
	}

	// Determine si une paire de nucleotides peuvent d'apparier
	private boolean isApp(char nucl1, char nucl2) {
		if (nucl1 == 'A') {
			return nucl2 == 'U';
		} else if (nucl1 == 'C') {
			return nucl2 == 'G';
		} else if (nucl1 == 'G') {
			return nucl2 == 'C' || nucl2 == 'U';
		} else {
			return nucl2 == 'A' || nucl2 == 'G';
		}
	}
	
	/**
	 * Detecte si la sequence passee a la construction est une structure tige/boucle symetrique ou non
	 * 
	 * @return true si la sequence est un structure tige/boucle symetrique, false sinon
	 */
	public boolean detect() {
		int pos1 = 0;
		int pos2 = this.sequence.length() - 1;
		char[] seq = this.sequence.toCharArray();
		// Nombre total d'appariemments
		int nbAppTotal = 0;
		// Nombre consecutif d'appariemments
		int nbAppCons = 0;
		// Longueur d'une boucle interne
		int lgBoucle = 0;
		// Boolean qui determine si on se trouve dans une boucle interne ou non
		boolean dsBoucle = false;
		
		// La sequence doit au moins commencer avec trois appariemments
		for (int i = 0; i < 3; i++) {
			if (!this.isApp(seq[pos1], seq[pos2]))
				return false;
			
			pos1++;
			pos2--;
			nbAppTotal++;
			nbAppCons++;
		}
	
		// Parcours de la sequence jusqu'a la potentielle boucle terminale
		while (pos1 < pos2) {
			// Si les deux nucleotides matchent
			if (this.isApp(seq[pos1], seq[pos2])) {
				// Et que l'on était dans une boucle
				if (dsBoucle)
					// Si la boucle est trop longue
					if (lgBoucle > 3)
						return false;
					// Sinon on sort de la boucle
					else {
						dsBoucle = false;
						lgBoucle = 0;
					}

				// On compte le match
				nbAppCons++;
				nbAppTotal++;
			}
			// Sinon, les deux caracteres ne matchent pas
			else {
				// Si l'on était dans un boucle
				if (dsBoucle) {
					lgBoucle++;
					// Il ne faut pas que la boucle soit trop longue
					// Sauf si c'est la boucle terminal
					if ((lgBoucle > 3) && (pos2 - pos1 > 8))
						return false;
				}
				// Sinon on vient de quitter des appariements successifs
				else {
					// Il faut que le nombre d'appariements soit supperieur 3
					if (nbAppCons < 3)
						return false;
					else {
						// On entre dans une boucle
						dsBoucle = true;
						nbAppCons = 0;
						// et on incremente la taille de la boucle
						lgBoucle++;
					}
				}
			}
			
			// On fait avancer un pointeur et reculer l'autre car on cherche un miRNA symetrique
			pos1++;
			pos2--;
		}
		
		return nbAppTotal >= 24;
	}

}
