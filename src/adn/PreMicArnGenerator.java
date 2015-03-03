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
    





}
