package main;

import adn.PreMicArnGenerator;
import adn.StructTigeBoucle;
import exception.GeneratorException;

/**
 * Main servant a generer une structure tige/boucle avec quelques paramètres
 * 
 * @author Quentin Baert et Alexandre Verkyndt
 */
public class GenerationTigeBoucleMain {

	public static void printInfo() {
		System.out.println("java -jar structGenerator.jar <tailleSequence> <nb> <tailleTerm>");
		System.out.println();
		System.out.println("Avec :");
		System.out.println("\t\t\t <tailleSequence> : taille de la structure à générer");
		System.out.println("\t\t\t <nb>             : nombre de boucles internes");
		System.out.println("\t\t\t <tailleTerm>     : taille de la boucle terminale");
	}
	public static void main(String[] args) throws GeneratorException {
		if (args.length < 3) {
			printInfo();
			return;
		}
		
		try {
			int tailleSequence = Integer.parseInt(args[0]);
			int nb = Integer.parseInt(args[1]);
			int tailleTerm = Integer.parseInt(args[2]);

        	StructTigeBoucle struct = new StructTigeBoucle(tailleSequence, nb, tailleTerm);
        
        	PreMicArnGenerator gen = new PreMicArnGenerator(struct);
        	String skeleton = gen.generate();
        
        	System.out.println(skeleton);
        	System.out.println(gen.convertBoneToPreMiC(new StringBuffer(skeleton)).toString());
		} catch (NumberFormatException e) {
			printInfo();
			return;
		}
	}

}
