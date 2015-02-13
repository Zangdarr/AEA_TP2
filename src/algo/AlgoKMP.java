package algo;

import java.util.ArrayList;
import java.util.List;

import adn.Motif;
import adn.MotifBio;

/**
 * Algorithme de recherche de motif KMP
 * 
 * @author Quentin Baert & Alexandre Verkyndt
 */
public class AlgoKMP implements Algo {
	
	///////////////
	// ATTRIBUTS //
	///////////////
	
	private static AlgoKMP instance = new AlgoKMP();

	//////////////
	// METHODES //
	//////////////
	
	// Constructeur prive
	private AlgoKMP() {
	}
	
	/**
	 * Donne acces a l'unique instance de la classe AlhoKMP
	 * 
	 * @return l'unique instance de la classe AlgoKMP
	 */
	public static AlgoKMP getInstance() {
		return instance;
	}
	
	/**
	 * Pre-traitement du motif
	 * 
	 * Pour cet algorithme le bord d'un mot peut etre le mot vide mais pas le mot lui-meme
	 * 
	 * L'algorithme consiste a parcourir le motif et a determiner pour chacune des positions i du motif
	 * la taille du plus long mot u tel que :
	 * 	- u est un bord de motif(0 .. i - 1)
	 * 	- la chaine (u + motif(i)) ne soit pas un prefixe du motif
	 * 
	 * Si un tel mot existe alors res[i] = taille(u), sinon res[i] = -1
	 * 
	 * Le tableau resultat contient taille(motif) + 1 cases, dans la case excedente, on entre la taille
	 * du bord le plus grand du motif
	 * 
	 * @param motif
	 * 			motif a pre-traiter
	 * @return tableau de pre-traitement du motif
	 */
	public int[] preProcessing(String motif) {
		int length = motif.length();
		int[] res = new int[length + 1];
		
		/*
		 * La premiere case du tableau vaut forcement -1
		 * 
		 * Le plus petit bord d'un mot de taille 1 est forcement le mot vide, et le mot
		 * vide concatene a la premiere lettre du motif est forcement un prefixe de ce dernier
		 */
		res[0] = -1;
		
		// Completion du reste du tableau resultat
		for (int i = 1; i < length + 1; i++) {
			int max = -1;
			
			// motif(0 .. i - 1)
			Motif u = new Motif(motif.substring(0, i));
			
			/*
			 * Recherche du long bord de u
			 * 
			 * Il n'est pas necessaire d'aller plus loin que la moitie de u
			 * pour y trouver un bord
			 */
			for (int j = 0; j < (i / 2) + 1; j++) {
				// Bord potentiel
				String edge = motif.substring(0, j);
				int l = -1;
				
				// Si le bord est le mot vide ou que c'est effectivement un bord de u
				if (edge.isEmpty() || u.hasEdge(edge)) {
					// Si on ne rempli pas la derniere case du tableau resultat ...
					if (i < length) {
						char mi = motif.charAt(i);
						
						// ... il faut encore verifier que (u + motif(i)) n'est pas un prefix de motif
						if (!u.hasPrefix(edge + mi))
							l = edge.length();
					}
					// Si on rempli la derniere case du tableau resultat ...
					else {
						// ... aucune verification de necessaire
						l = edge.length();
					}
				}
				
				if (l > max)
					max = l;
			}
			
			res[i] = max;
		}
		
		return res;
	}
	
	// Donne les pre-traitements des differentes formes du motif en fonction de l'entree
	private List<Couple> getPreProcessFor(Entree entree, MotifBio motif) {
		List<Couple> res = new ArrayList<Couple>();
		
		res.add(new Couple(
				motif.getMotif(),
				this.preProcessing(motif.getMotif())));
		
		if (entree.takeReverse())
			res.add(new Couple(
					motif.getReverse(),
					this.preProcessing(motif.getReverse())));
		
		if (entree.takeCompl())
			res.add(new Couple(
					motif.getCompl(),
					this.preProcessing(motif.getCompl())));
		
		if (entree.takeRevCompl())
			res.add(new Couple(
					motif.getRevCompl(),
					this.preProcessing(motif.getRevCompl())));

		return res;
	}
	
	// Determine si l'algo doit s'arreter ou non
	private boolean isFinished(List<Integer> l, int length) {
		for (Integer i : l) {
			if (i <= length)
				return false;
		}
		
		return true;
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
		
		// Ces deux listes permettent de gerer toutes les formes du motif en un seul parcours de sequence
		List<Couple> preProcesses = this.getPreProcessFor(entree, motif);
		List<Integer> indexes = new ArrayList<Integer>();
		
		// Les indexes de chaques fenetres commencent a 0
		for (int i = 0; i < preProcesses.size(); i++)
			indexes.add(0);
		
		// Au dela de cette position, une fenetre sortirai de la sequence
		int maxLen = sequence.length() - motifLen;
		
		// Tant que tous les indexes de toutes les fenetres ne sont pas arrives au bout de la sequence
		while (!this.isFinished(indexes, sequence.length() - motifLen)) {
			// Le corps de l'algo est execute sur chacune des fenetre
			for (int preProcessNb = 0; preProcessNb < preProcesses.size(); preProcessNb++) {
				Couple couple = preProcesses.get(preProcessNb);
				// Index de la fenetre courante
				int index = indexes.get(preProcessNb);

				// Si l'indexe n'est pas arrive au bout de la sequence
				if (index <= maxLen) {
					// Motif a trouver dans la sequence (motif ou reverse ou complement ou ...)
					String motifToFind = couple.getMotif();
					// Tableau de pre-traitement du motif
					int[] preProcess = couple.getPreProcess();
					// Fenetre
					String window = sequence.substring(index, index + motifLen);

					// Indexe qui servira a determiner l'indice de la fenetre ou l'erreur est apparue
					int indexError = 0;
					boolean stop = false;
					
					// On cherche la prochaine erreur (nucleotide qui ne correspond pas au motif)
					while ((indexError <= motifLen) && !stop) {
						if (indexError == motifLen)
							stop = true;
						else if (window.charAt(indexError) != motifToFind.charAt(indexError))
							stop = true;
						else
							indexError++;
					}
				
					// Si toutes la fenetre a ete parcourue sans erreur ...
					if (indexError == motifLen)
						// ... le motif a ete trouve et sa position et ajoute a la sortie
						sortie.addPosition(index);
				
					/*
					 * Le prochain indice de la sequence a considerer pour ce motif est calcule
					 * a partir de l'indice d'erreur et du tableau de pre-traitement
					 */
					indexes.set(preProcessNb, index + (indexError - preProcess[indexError]));
				}
			}
		}
		
		return sortie;
	}
	
	////////////////////
	// CLASSE INTERNE //
	////////////////////

	private class Couple {

		private String motif;
		private int[] preProcess;
		
		public Couple(String motif, int[] preProcess) {
			this.motif = motif;
			this.preProcess = preProcess;
		}
		
		public String getMotif() {
			return this.motif;
		}
		
		public int[] getPreProcess() {
			return this.preProcess;
		}

	}

}
