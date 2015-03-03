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
    
    /**
     * Génération aléatoir de groupes de points de taille inférieur ou égal à 3 en utilisant le nombre de point fournit en paramètre
     * @param nombre_de_points : nombre de points qui devront être utilisés
     * @return une liste avec pour contenu des chaînes de caractères constitué de 1 à 3 points
     */
    public ArrayList<String> genPointGroup(int nombre_de_points){
        Random rand = new Random(System.currentTimeMillis());

      //génération aléatoire de groupes de points
        ArrayList<String> groupes_points = new ArrayList<String>();
        while(nombre_de_points > 0 ){
            int tmp_int = rand.nextInt(4);                                
            StringBuffer tmp_str = new StringBuffer();

            if(nombre_de_points - tmp_int < 0){
                tmp_int = nombre_de_points;
            }
            addNChar(tmp_str, '.', tmp_int);
            nombre_de_points -= tmp_int;
            groupes_points.add(tmp_str.toString());
        }
        
        return groupes_points;
    }

    private StringBuffer addNChar(StringBuffer str, char c, int nb_times){
        for (int i = 0; i < nb_times; i++) 
            str.append(c);

        return str;
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
