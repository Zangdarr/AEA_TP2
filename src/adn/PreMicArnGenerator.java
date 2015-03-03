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

    /**
     * Génère un squelette de parenthèses et de points pour réprésenter un MicroARN
     * @return une chaîne de caractère réprésentant la strucutre parenthésée finale
     * @throws GeneratorException : Si la génération est impossible en un lapse de temps raisonable
     */
    public String generate() throws GeneratorException{
    }
    
    /**
     * Ajout aléatoire des groupes dans l'ensemble en respectant les contraintes de groupement des parenthèses.
     * @param result : chaîne de départ
     * @param groupes_points : groupes de points à insérer
     * @param points_remain : nombre de points contenu dans l'ensemble des groupes de points
     * @return un buffer de chaîne réprésentant la structure parenthésée finale
     * @throws GeneratorException Si la génération est impossible en un lapse de temps raisonable
     */
    public StringBuffer addAleaPoint(StringBuffer result, ArrayList<String> groupes_points, int points_remain) throws GeneratorException {
        StringBuffer tmp = new StringBuffer(result.toString()); //pour contrer les boucle infini (impossibilité de placer les point en respectant les contraintes
        ArrayList<String> list_tmp = groupes_points;
        int nombre_iteration = 0; //on va admettre que après 200 itérations on peut recommencer le placement de zéro
        int nombre_relance = 0;
        
        
        //générateur de nombre pseudo aléatoire satisfesant
        Random rand = new Random(System.currentTimeMillis());
        Collections.shuffle(groupes_points);
        int rand_position = 0;
        //boucle d'ajout des groupes de points
        while(!groupes_points.isEmpty()){
            //position potentiel
            rand_position = rand.nextInt(tmp.length()-6)+3;
            //on n'ajoute pas à côté d'un autre groupe de point
            if(tmp.charAt(rand_position-1) != '.' || tmp.charAt(rand_position+1) != '.'){
                if(tmp.subSequence(rand_position, rand_position+3).equals("(((") && tmp.subSequence(rand_position-3, rand_position).equals("(((") ){
                    tmp.insert(rand_position, groupes_points.get(0));
                    groupes_points.remove(0);
                }
                if(tmp.subSequence(rand_position, rand_position+3).equals(")))") && tmp.subSequence(rand_position-3, rand_position).equals(")))") ){
                    tmp.insert(rand_position, groupes_points.get(0));
                    groupes_points.remove(0);
                }
            }
            // Limitation des boucles infinies. Ne suffit parfois pas.
            if(++nombre_iteration> 100000){
                System.out.println("Nombre d'itération dépassé. On relance");
                //groupe de points initiaux regénérés
                groupes_points = genPointGroup(points_remain);
                Collections.shuffle(groupes_points);
                //Structure vierge de parenthèse avec la boucle terminal
                tmp = new StringBuffer(result.toString());
                //réinitialisation du compteur
                nombre_iteration = 0;

                if(++nombre_relance> 10){
                    System.out.println("COUCOU");
                    throw new GeneratorException(0);
                }
            }
            
        }
        
        return tmp;
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
