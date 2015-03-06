package test;

import static org.junit.Assert.*;

import org.junit.Test;

import adn.PreMicArnGenerator;
import adn.StructTigeBoucle;
import algo.DetectionTigeBoucleSymetrique;
import exception.GeneratorException;

public class TestDetectionTigeBoucleSymetrique {

	@Test
	public void testDetectionTigeBoucleSansBoucleInterneOuTerminale() throws GeneratorException {
		// Génération d'une structure tige/boucle uniquement compose d'appariements
		StructTigeBoucle stb = new StructTigeBoucle(100, 50, 0);
		PreMicArnGenerator generator = new PreMicArnGenerator(stb);
		String skeleton = generator.generate();
		String sequence = generator.convertBoneToPreMiC(new StringBuffer(skeleton)).toString();
		
		// Detecteur de structure tige/boucle symetrique
		DetectionTigeBoucleSymetrique dtbs = new DetectionTigeBoucleSymetrique(sequence);
		
		assertTrue(dtbs.detect());
	}
	
	@Test
	public void testDetectionTigeBoucleAvecBoucleTerminale() throws GeneratorException {
		// Génération d'une structure tige/boucle avec uniquement une boucle terminale
		StructTigeBoucle stb = new StructTigeBoucle(100, 46, 8);
		PreMicArnGenerator generator = new PreMicArnGenerator(stb);
		String skeleton = generator.generate();
		String sequence = generator.convertBoneToPreMiC(new StringBuffer(skeleton)).toString();
		
		// Detecteur de structure tige/boucle symetrique
		DetectionTigeBoucleSymetrique dtbs = new DetectionTigeBoucleSymetrique(sequence);
		
		assertTrue(dtbs.detect());
	}
	
	@Test
	public void testDetectionTigeBoucleAvecBoucleTerminaleEtBouclesInternesSymetriques() throws GeneratorException {
		// Génération d'une structure tige/boucle avec uniquement une boucle terminale
		StructTigeBoucle stb = new StructTigeBoucle(100, 46, 8);
		PreMicArnGenerator generator = new PreMicArnGenerator(stb);
		String skeleton = generator.generate();
		
		// Création artificielles de boucle internes symetriques
		char[] skeletonPrime = skeleton.toCharArray();
		// Une boucle interne de taille 2 et sa symetrique
		skeletonPrime[3] = '.';
		skeletonPrime[skeleton.length() - 1 - 3] = '.';
		skeletonPrime[4] = '.';
		skeletonPrime[skeleton.length() - 1 - 4] = '.';
		// Une boucle interne de taille 3 et sa symetrique
		skeletonPrime[9] = '.';
		skeletonPrime[skeleton.length() - 1 - 9] = '.';
		skeletonPrime[10] = '.';
		skeletonPrime[skeleton.length() - 1 - 10] = '.';
		skeletonPrime[11] = '.';
		skeletonPrime[skeleton.length() - 1 - 11] = '.';
		
		// Generation de la nouvelle sequence a partir de la structure tige/boucle modifiée
		String sequence = generator.convertBoneToPreMiC(new StringBuffer(new String(skeletonPrime))).toString();
		
		// Detecteur de structure tige/boucle symetrique
		DetectionTigeBoucleSymetrique dtbs = new DetectionTigeBoucleSymetrique(sequence);
		
		assertTrue(dtbs.detect());
	}
	
	@Test
	public void testNonDetectionTigeBoucleAvecBouclesInternesDeTaille4() throws GeneratorException {
		// Génération d'une structure tige/boucle avec uniquement une boucle terminale
		StructTigeBoucle stb = new StructTigeBoucle(100, 46, 8);
		PreMicArnGenerator generator = new PreMicArnGenerator(stb);
		String skeleton = generator.generate();
		
		// Création artificielles de boucle internes symetriques
		char[] skeletonPrime = skeleton.toCharArray();
		// Une boucle interne de taille 4 et sa symetrique
		skeletonPrime[3] = '.';
		skeletonPrime[skeleton.length() - 1 - 3] = '.';
		skeletonPrime[4] = '.';
		skeletonPrime[skeleton.length() - 1 - 4] = '.';
		skeletonPrime[5] = '.';
		skeletonPrime[skeleton.length() - 1 - 5] = '.';
		skeletonPrime[6] = '.';
		skeletonPrime[skeleton.length() - 1 - 6] = '.';
		
		// Generation de la nouvelle sequence a partir de la structure tige/boucle modifiée
		String sequence = generator.convertBoneToPreMiC(new StringBuffer(new String(skeletonPrime))).toString();
		
		// Detecteur de structure tige/boucle symetrique
		DetectionTigeBoucleSymetrique dtbs = new DetectionTigeBoucleSymetrique(sequence);
		
		assertFalse(dtbs.detect());
	}
	
	@Test
	public void testNonDetectionTigeBoucleAvecAppariementsDeTailleInferieurA3AuDebut() throws GeneratorException {
		// Génération d'une structure tige/boucle avec uniquement une boucle terminale
		StructTigeBoucle stb = new StructTigeBoucle(100, 46, 8);
		PreMicArnGenerator generator = new PreMicArnGenerator(stb);
		String skeleton = generator.generate();
		
		// Création artificielles d'appariements de taille < 3 en debut de sequence
		char[] skeletonPrime = skeleton.toCharArray();
		skeletonPrime[2] = '.';
		skeletonPrime[skeleton.length() - 1 - 2] = '.';
		
		// Generation de la nouvelle sequence a partir de la structure tige/boucle modifiée
		String sequence = generator.convertBoneToPreMiC(new StringBuffer(new String(skeletonPrime))).toString();
		
		// Detecteur de structure tige/boucle symetrique
		DetectionTigeBoucleSymetrique dtbs = new DetectionTigeBoucleSymetrique(sequence);
		
		assertFalse(dtbs.detect());
	}
	
	@Test
	public void testNonDetectionTigeBoucleAvecAppariementsDeTailleInferieurA3() throws GeneratorException {
		// Génération d'une structure tige/boucle avec uniquement une boucle terminale
		StructTigeBoucle stb = new StructTigeBoucle(100, 46, 8);
		PreMicArnGenerator generator = new PreMicArnGenerator(stb);
		String skeleton = generator.generate();
		
		// Création artificielles d'appariements de taille < 3 en cours de sequence
		char[] skeletonPrime = skeleton.toCharArray();
		skeletonPrime[3] = '.';
		skeletonPrime[skeleton.length() - 1 - 3] = '.';
		skeletonPrime[5] = '.';
		skeletonPrime[skeleton.length() - 1 - 5] = '.';
		
		// Generation de la nouvelle sequence a partir de la structure tige/boucle modifiée
		String sequence = generator.convertBoneToPreMiC(new StringBuffer(new String(skeletonPrime))).toString();
		
		// Detecteur de structure tige/boucle symetrique
		DetectionTigeBoucleSymetrique dtbs = new DetectionTigeBoucleSymetrique(sequence);
		
		assertFalse(dtbs.detect());
	}
}
