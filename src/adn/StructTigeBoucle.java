package adn;

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
