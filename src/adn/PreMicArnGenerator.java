package adn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import exception.GeneratorException;

public class PreMicArnGenerator {

    /**
     * Contrainte de la generation
     */
    private StructTigeBoucle contrainte;


    /**
     * Constructeur par defaut : les contraintes sont aléatoires borné
     */
    public PreMicArnGenerator() {
        super();
        contrainte = new StructTigeBoucle();
    }

    public PreMicArnGenerator(StructTigeBoucle contrainte) {
        super();
        this.contrainte = contrainte;
    }

    
    public String generate(){
             
        return null;
    }
    


    private String genPaireNonApparieNucleotide() {
        Random rnd = new Random(System.currentTimeMillis());
        int paire_choisie = rnd.nextInt(3);
        int ordre_choisi  = rnd.nextInt(2);
        String result = "";
        switch(paire_choisie){
        case 0 : 
            result = (ordre_choisi == 0)?"AU" : "UA";
        case 1 :
            result = (ordre_choisi == 0)?"GC" : "CG";
        case 2 :
            result = (ordre_choisi == 0)?"GU" : "UG";
        }
        return result;
    }

    /**
     * Génère une paire de nucléotides apparieable aléatoirement
     * @return
     */
    private String genPaireApparieNucleotide() {
        Random rnd = new Random(System.currentTimeMillis());
        int paire_choisie = rnd.nextInt(3);
        int ordre_choisi  = rnd.nextInt(2);
        String result = "";
        switch(paire_choisie){
        case 0 : 
            result = (ordre_choisi == 0)?"AU" : "UA";
        case 1 :
            result = (ordre_choisi == 0)?"GC" : "CG";
        case 2 :
            result = (ordre_choisi == 0)?"GU" : "UG";
        }
        return result;
    }


}
