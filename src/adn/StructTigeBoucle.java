package adn;

import java.util.Random;

public class StructTigeBoucle {
    
    /**
     * Contraintes associé à la structure
     * - toutes ces valeurs sont incluses en max et en min
     * */
    final int MAX_LONGUEUR = 100;
    final int MIN_LONGUEUR = 70;
    final int MIN_NB_APPARIEMENT = 24;
    final int MIN_TAILLE_GROUPE_APPARIEMENT = 3;
    final int MAX_TAILLE_BOUCLE_TERMINAL = 8;
    final int MAX_TAILLE_GROUPE_NON_APPARIES = 3;
    //TODO penser la représentation des correspondances des appariements. S'inspirer du TP 1 avec la generation de motif.
    
    /**
     * Parametres de la structure
     * */
    // Nombre de nucléotides que contient la structure
    int taille;
    // Nombre d'appariements que contient la structure
    int nombre_appariements;
    // Taille de la boucle terminal situé au mileu de la structure
    int taille__boucle_terminal;
    // Quantité de boucles autres que la terminal
    int nombre_autres_boucles;
    
    
    
    /**
     * Constructeur par default. Toute les valeur initiale sont aléatoire.
     */
    public StructTigeBoucle() {
        super();
        Random rnd = new Random(System.currentTimeMillis());

        this.taille = rnd.nextInt(1 + MAX_LONGUEUR);
        this.nombre_appariements = MIN_NB_APPARIEMENT + rnd.nextInt(1 +  this.taille/2 - MIN_NB_APPARIEMENT);
        this.taille__boucle_terminal = rnd.nextInt(1 + MAX_TAILLE_BOUCLE_TERMINAL);
        
        //TODO : Pour l'instant ce paramètre sera à zéro. La gestion des boucles latérales se fera plus tard.
        this.nombre_autres_boucles = 0;
    }



    /**
     * Constructeur : si une valeur négative est passé en paramètre, un nombre aléatoire borné par les MIN et MAX sera généré pour la paramètre correspondant.
     * @param taille
     * Si taille est inférieur à la longueur minimale imposée, alors la valeur minimale sera appliquée
     * @param nombre_appariements
     * Si le nombre d'appariement est inférieur au nombre minimal imposé, alors la valeur minimale sera appliquée
     * @param taille__boucle_terminal
     * Si la taille dépasse la valeur maximale, alors la valeur maximale sera appliquée
     * @param nombre_autres_boucles
     * Ce paramètre sera gérer plus tard. Pour limiter la complexité du programme pour l'instant.
     */
    public StructTigeBoucle(int taille, int nombre_appariements,
            int taille__boucle_terminal, int nombre_autres_boucles) {
        super();
        Random rnd = new Random(System.currentTimeMillis());
        
        this.taille = (taille<0)
                            ?MIN_LONGUEUR + rnd.nextInt(1 + MAX_LONGUEUR - MIN_LONGUEUR)
                            : (taille<MIN_LONGUEUR)
                                    ?MIN_LONGUEUR
                                    :taille
        ;
                                                 
        this.nombre_appariements = (nombre_appariements<0)
                                                ?MIN_NB_APPARIEMENT + rnd.nextInt((1 +  this.taille/2 - MIN_NB_APPARIEMENT))
                                                : (taille<MIN_NB_APPARIEMENT)
                                                        ? MIN_NB_APPARIEMENT
                                                        : nombre_appariements 
        ;
        
        this.taille__boucle_terminal = (taille__boucle_terminal<0)
                                                ? rnd.nextInt(1 + MAX_TAILLE_BOUCLE_TERMINAL)
                                                : (taille__boucle_terminal>MAX_TAILLE_BOUCLE_TERMINAL)
                                                        ? MAX_TAILLE_BOUCLE_TERMINAL
                                                        : taille__boucle_terminal
        ;

        //TODO : Pour l'instant ce paramètre sera à zéro. La gestion des boucles latérales se fera plus tard.
        this.nombre_autres_boucles = 0;
        ;
    }
    
    
    
    //GETTER - SETTER    
    public int getTaille() {
        return taille;
    }
    public int getNombre_appariements() {
        return nombre_appariements;
    }
    public int getTaille__boucle_terminal() {
        return taille__boucle_terminal;
    }
    public int getNombre_autres_boucles() {
        return nombre_autres_boucles;
    }
    public int getNombre_nucléotides_apparies() {
        return nombre_appariements * 2;
    }
    
}