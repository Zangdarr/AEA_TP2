package adn;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

import exception.GeneratorException;

public class PreMicArnGenerator {

    /**
     * Contrainte de la generation
     */
    private StructTigeBoucle contrainte;

    /**
     * Constructeur par défaut : les contraintes sont aléatoires bornées
     */
    public PreMicArnGenerator() {
        super();
        contrainte = new StructTigeBoucle();
    }

    /**
     * Constructeur avec paramètre 
     * @param contrainte : contraintes que devra respecter la génération aléatoire.
     */
    public PreMicArnGenerator(StructTigeBoucle contrainte) {
        super();
        this.contrainte = contrainte;
    }

    /**
     * Convertie un squelette de parenthèses et de points en une structure de nucléotides valide.
     * @param skeleton : contrainte de génération
     * @return une chaîne de nucléotides basé sur le squelette passé en paramètre.
     * @throws GeneratorException : si un caractère autre qu'un nucléotide apparaît
     */
    public StringBuffer convertBoneToPreMiC(StringBuffer skeleton) throws GeneratorException{
        StringBuffer result = new StringBuffer(); // chaîne résultat
        int pos_deb = 0, // déplacement dans le squelette : position de départ
                pos_fin = skeleton.length()-1; //déplacmeent dans le squelette : position de fin
        
        char char_deb, // caractère du squelette à pos_deb
        char_fin; // caractère du squelette à pos_fin

        while(pos_deb <= pos_fin) { // tant qu'on a pas tout parcouru
            char_deb = skeleton.charAt(pos_deb); //récupération du caractère à pos_deb
            char_fin = skeleton.charAt(pos_fin); //récupération du caractère à pos_fin

            if(pos_deb == pos_fin) { // si la taille est impaire, ce cas survint à la fin
                //il s'agit forcément d'un point
                char tmp = genApparieNucleotide('z'); //génération d'un caractère aléatoire
                result.insert(pos_deb, tmp); //insertion du caractère
                pos_deb++; //avancée de pos_deb -> fin de la boucle
            }
            else if((char_deb == '(' && char_fin == ')') || (char_deb == '.' && char_fin == '.')){//s'il les deux char à traiter sont identiques
                
                if(char_deb == '('){//s'il s'agit d'une paire de parenthèse
                    String tmp = genPaireApparieNucleotide(); // génération d'une paire apparieable de nucléotides
                    result.insert(pos_deb, tmp.charAt(1)); //insertion du caractère pour la parenthèse fermante
                    result.insert(pos_deb, tmp.charAt(0)); //insertion du caractère pour la parenthèse ouvrante
                }
                else{
                    String tmp = genPaireNonApparieNucleotide(); // génération d'une paire non apparieable de nucléotides
                    result.insert(pos_deb, tmp.charAt(1));//insertion du caractère pour la parenthèse fermante
                    result.insert(pos_deb, tmp.charAt(0));//insertion du caractère pour la parenthèse ouvrante
                }
                pos_deb++;//avancée de pos_deb
                pos_fin--;//avancée de pos_fin
            }
            else{// les deux sont différents
                if(char_deb == '('){//si parenthèse ouvrante -> char_fin == '.'
                    char tmp = genNonApparieNucleotide('z');//génération d'un nucléotide aléatoire non apparieable
                    result.insert(pos_deb, tmp);//insertion du caractère pour le point
                    pos_fin--;//avancée de pos_fin
                }
                else{//point -> char_fin == ')'
                    char tmp = genNonApparieNucleotide('z');//génération d'un nucléotide aléatoire non apparieable
                    result.insert(pos_deb, tmp);//insertion du caractère pour le point
                    pos_deb++;//avancée de pos_deb
                }
            }
        }
        return result;//renvoie de la chaîne de nucléotides achevée
    }

    /**
     * Génère un squelette de parenthèses et de points pour réprésenter un MicroARN
     * @return une chaîne de caractères réprésentant la strucutre parenthésée finale
     * @throws GeneratorException : Si la génération est impossible en un lapse de temps raisonable
     */
    public String generate() throws GeneratorException{
        StringBuffer result;              //stockera le résultat final
        int points_remain;                //nombre de points pour les boucles latérales
        ArrayList<String> groupes_points; //liste aléatoire de groupes de points de taille 1 à 3 points

        /****************************************************************************************
         * Étape 1 : génération de la structure parenthésée avec la boucle terminal en son centre
         */
        result = new StringBuffer();

        //Structure globale : des parenthèses encadrant la boucle terminal
        addNChar(result, '(', this.contrainte.getNombre_appariements());
        addNChar(result, '.', this.contrainte.getTaille__boucle_terminal());
        addNChar(result, ')', this.contrainte.getNombre_appariements());
        
        // résult  ~= (((((((....)))))))




        /*****************************************************************************************
         * Étape 2 : génération aléatoire de groupes de points de taille variant de 1 a 3 pour respecter la contrainte du groupement des points
         */
        //points qu'il faut ajouter pour atteindre la taille voulue
        points_remain = this.contrainte.getTaille() - this.contrainte.getNombre_nucléotides_apparies() - this.contrainte.getTaille__boucle_terminal();

        //génération de la liste de groupes de points aléatoires
        groupes_points = genPointGroup(points_remain);
        
        // groupe_points ~= {"...",".","..","."}



        /*****************************************************************************************
         * Étape 3 : Ajout aléatoire des groupes de points dans la structure parenthésée
         */

        //ajout aléatoire des groupes dans l'ensemble en respectant les contraintes de groupement des parenthèses
        result = addAleaPoint(result, groupes_points, points_remain);
        
        //result ~= ((((.(((..((((.(((.(((...((((..(((...(((((.......)))))...)))))..)))...)))))...))))..)))))))

        
        
        /*****************************************************************************************
         * Étape 4 : Renvoie de la structure achevée respectant les contraintes données par la structure TigeBoucle
         */
        return result.toString();
    
    }

    /**
     * Ajout aléatoire des groupes dans l'ensemble en respectant les contraintes de groupement des parenthèses.
     * @param result : chaîne de départ
     * @param groupes_points : groupes de points à insérer
     * @param points_remain : nombre de points contenus dans l'ensemble des groupes de points
     * @return un buffer de chaîne réprésentant la structure parenthésée finale
     * @throws GeneratorException Si la génération est impossible en un lapse de temps raisonable
     */
    public StringBuffer addAleaPoint(StringBuffer result, ArrayList<String> groupes_points, int points_remain) throws GeneratorException {
        StringBuffer tmp = new StringBuffer(result.toString()); //pour contrer les boucles infinies (impossibilité de placer les points en respectant les contraintes
        int nombre_iteration = 0; //on va admettre qu'après 100000 itérations on peut recommencer le placement de zéro
        int nombre_relance = 0;   //on va admettre qu'après 10 relance on peut abandonner 
        SecureRandom rand = new SecureRandom(); //générateur de nombre pseudo aléatoire satisfesant


        Collections.shuffle(groupes_points); //on mélange la liste
        
        int rand_position = 0; //position choisie aléatoirement 
        
        while(!groupes_points.isEmpty()){ //boucle d'ajout des groupes de points

            rand_position = rand.nextInt(tmp.length()-6)+3; //position potentiel à tester
            
            if(tmp.charAt(rand_position-1) != '.' || tmp.charAt(rand_position+1) != '.'){ //on n'ajoute pas à côté d'un autre groupe de point
                //on ajoute si la position est encerclée par trois parenthèses gauches de chaque côtés
                if(tmp.subSequence(rand_position, rand_position+3).equals("(((") && tmp.subSequence(rand_position-3, rand_position).equals("(((") ){
                    tmp.insert(rand_position, groupes_points.get(0));//insertion du groupe de points à la rand_position
                    groupes_points.remove(0);//retrait du groupe à la liste
                }
                //on ajoute si la position est encerclée par trois parenthèses droites de chaque côtés
                if(tmp.subSequence(rand_position, rand_position+3).equals(")))") && tmp.subSequence(rand_position-3, rand_position).equals(")))") ){
                    tmp.insert(rand_position, groupes_points.get(0));//insertion du groupe de points à la rand_position
                    groupes_points.remove(0);//retrait du groupe à liste
                }
            }
            /**
             * Limitation des boucles infinies.
             */
            if(++nombre_iteration> 100000){ //si plus de 100K itérations
                System.out.println("Nombre d'itération dépassé. On relance");
                
                groupes_points = genPointGroup(points_remain); //groupe de points initiaux regénérés
                Collections.shuffle(groupes_points); // mélange de la liste
                
                tmp = new StringBuffer(result.toString()); //Structure vierge de parenthèses avec la boucle terminal
                
                nombre_iteration = 0;//réinitialisation du compteur

                if(++nombre_relance> 10){ //si plus de 10 relances
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
        SecureRandom rand = new SecureRandom();

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


    /**
     * Génère un caractère non apparieable à c parmi A,U,C et G en respectant la règle de complémentarité faible. 
     * Si 'z' est passé en paramètre alors un caractère parmi les quatres sera renvoyé au hasard.
     * @param c
     * @return un caractère non complémentaire à c ou un au hasard
     * @throws GeneratorException
     */
    private char genNonApparieNucleotide(char c) throws GeneratorException {
        SecureRandom rnd = new SecureRandom();
        int choice;
        char result = ' ';

        switch(c){
        case 'A' : 
            choice = rnd.nextInt(3);
            result =  (choice == 0)?'C' : (choice == 1)?'G' : 'A';
            break;
        case 'C' :
            choice = rnd.nextInt(3);
            result = (choice == 0)?'A' : (choice == 1)?'U':'C';
            break;
        case 'U' :
            choice = rnd.nextInt(2);
            result = (choice == 0)?'C':'U';
            break;
        case 'G' :
            choice = rnd.nextInt(2);
            result = (choice == 0)?'A':'G';
            break;
        case 'z' :
            choice = rnd.nextInt(4);
            result = (choice == 0)?'A': (choice == 1)?'U': (choice == 2)?'G' : 'C' ;
            break;
        default : 
            throw new GeneratorException(1);

        }
        return result;
    }

    /**
     * Génère un caractère apparieable à c parmi A,U,C et G en respectant la règle de complémentarité faible. 
     * Si 'z' est passé en paramètre alors un caractère parmi les quatres sera renvoyé au hasard.
     * @param c
     * @return un caractère complémentaire à c ou un au hasard
     * @throws GeneratorException
     */
    private char genApparieNucleotide(char c) throws GeneratorException {

        SecureRandom rnd = new SecureRandom();
        int choice;
        char result = ' ';

        switch(c){
        case 'A' : 
            result =  'U';
            break;
        case 'C' :
            result = 'G';
            break;
        case 'U' :
            choice = rnd.nextInt(2);
            result = (choice == 0)?'A':'G';
            break;
        case 'G' :
            choice = rnd.nextInt(2);
            result = (choice == 0)?'C':'U';
            break;
        case 'z' :
            choice = rnd.nextInt(4);
            result = (choice == 0)?'A': (choice == 1)?'U': (choice == 2)?'G' : 'C' ;
            break;
        default : 
            throw new GeneratorException(1);

        }
        return result;
    }

    /**
     * Génère une paire de nucléotides apparieables aléatoirement
     * @return deux caractères non apparieables au hasard
     * @throws GeneratorException 
     */
    private String genPaireNonApparieNucleotide() throws GeneratorException {
        SecureRandom rand = new SecureRandom();
        
        char c1 = genNonApparieNucleotide('z'),// un caractère au hasard
             c2 = genNonApparieNucleotide(c1); // un caractère non apparieable à c
        String result = "" + c1 + c2;
        
        return result;
    }

    /**
     * Génère une paire de nucléotides apparieables aléatoirement
     * @return deux caractères apparieables au hasard
     * @throws GeneratorException 
     */
    private String genPaireApparieNucleotide() throws GeneratorException {
        SecureRandom rand = new SecureRandom();
        
        char c1 = genApparieNucleotide('z'),// un caractère au hasard
             c2 = genApparieNucleotide(c1); // un caractère apparieable à c
        String result = "" + c1 + c2;
        
        return result;
    }


}
