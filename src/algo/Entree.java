package algo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Entree d'un algorithme de recherche de motif.
 * 
 * @author Quentin Baert & Alexandre Verkyndt
 */
public class Entree {
	
	///////////////
	// ATTRIBUTS //
	///////////////

	/**
	 * Nom de la sequence d'ADN de l'entree
	 */
	private String name;
	
	/**
	 * Sequence de nucleotides representant un brin d'ADN dans lequel rechercher un motif
	 */
	private String sequence;
	
	/**
	 * Determine si l'algo doit considerer le reverse du motif
	 */
	private boolean takeReverse;
	
	/**
	 * Determine si l'algo doit considerer le complement du motif
	 */
	private boolean takeCompl;
	
	/**
	 * Determine si l'algo doit considerer le reverse-complement du motif
	 */
	private boolean takeRevCompl;
	
	//////////////
	// METHODES //
	//////////////
	
	/**
	 * Constructeur
	 * 
	 * @param name
	 * 			nom de la sequence d'ADN de l'entree 
	 * @param sequence
	 * 			sequence de l'entree dans laquelle chercher le motif
	 * @param motif
	 * 			motif a chercher dans le sequence de l'entree
	 */
	public Entree(String name, String sequence, boolean takeReverse, boolean takeCompl, boolean takeRevCompl) {
		this.name = name;
		this.sequence = sequence;
		this.takeCompl = takeCompl;
		this.takeReverse = takeReverse;
		this.takeRevCompl = takeRevCompl;
	}
	
	/**
	 * Constructeur
	 * 
     * Récupère depuis un fichier fasta le nom de la sequence et la sequence ADN
     * 
     * @param chemin
     * @throws IOException
     */
    public Entree(String path, boolean takeReverse, boolean takeCompl, boolean takeRevCompl) throws IOException {

        // br sert à lire le fichier ligne par ligne
        BufferedReader br = new BufferedReader(new  FileReader(path));

        String ligne;

        // on cherche la ligne correspondant au nom de la sequance
        while((ligne = br.readLine()) != null)
            if( ! ligne.startsWith(">"))
                continue;
            else {
                this.name = ligne;
                break;
            }
        //si on a pas trouve le nom, alors le fichier n'est pas valide
        if(ligne == null) {
            br.close();
            throw new IOException("Le fichier fasta ne possède pas le bon format");
        }

        StringBuilder sb = new StringBuilder();
        // on recupere la sequence adn
        while((ligne = br.readLine()) != null)
        	sb.append(ligne.substring(0, ligne.length() - 1));
        
        br.close();

        this.sequence = sb.toString();
        this.takeReverse = takeReverse;
        this.takeCompl = takeCompl;
        this.takeRevCompl = takeRevCompl;
    }
    
	/**
	 * Donne le nom de la sequence d'ADN de l'entree
	 * 
	 * @return nom de la sequence d'ADN de l'entree
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Donne la sequence de l'entree
	 * 
	 * @return sequence de l'entree
	 */
	public String getSequence() {
		return this.sequence;
	}
	
	/**
	 * Determine si l'algo doit considerer le reverse du motif
	 * 
	 * @return true si l'algo doit considerer le reverse du motif, false sinon
	 */
	public boolean takeReverse() {
		return this.takeReverse;
	}
	
	/**
	 * Determine si l'algo doit considerer le complement du motif
	 * 
	 * @return true si l'algo doit considerer le complement du motif, false sinon
	 */
	public boolean takeCompl() {
		return this.takeCompl;
	}
	
	/**
	 * Determine si l'algo doit considerer le reverse-complement du motif
	 * 
	 * @return true si l'algo doit considerer le complement du motif, false sinon
	 */
	public boolean takeRevCompl() {
		return this.takeRevCompl;
	}
	
	/**
	 * Determine si l'entree contient une sequence d'ADN
	 * 
	 * @return true si l'entree contient une sequence d'ADN, false sinon
	 */
	public boolean containsADNSequence() {
		for (int i = 0; i < this.sequence.length(); i++) {
			if (sequence.charAt(i) == 'U')
				return false;
			else if (sequence.charAt(i) == 'T')
				return true;
		}

		return true;
	}
	
	/**
	 * Determine si l'entree contient une sequence d'ARN
	 * 
	 * @return true si l'entree contient une sequence d'ARN, false sinon
	 */
	public boolean containsARNSequence() {
		return !this.containsADNSequence();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
        sb.append("Nom de la séquence : " +
                  this.name               +
                  "\n"                    +
                  "Sequence : "           +
                  this.sequence);
        
        return sb.toString();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((sequence == null) ? 0 : sequence.hashCode());
		result = prime * result + (takeCompl ? 1231 : 1237);
		result = prime * result + (takeRevCompl ? 1231 : 1237);
		result = prime * result + (takeReverse ? 1231 : 1237);
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
		Entree other = (Entree) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sequence == null) {
			if (other.sequence != null)
				return false;
		} else if (!sequence.equals(other.sequence))
			return false;
		if (takeCompl != other.takeCompl)
			return false;
		if (takeRevCompl != other.takeRevCompl)
			return false;
		if (takeReverse != other.takeReverse)
			return false;
		return true;
	}

}
