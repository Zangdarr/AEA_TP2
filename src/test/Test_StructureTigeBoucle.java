package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import adn.StructTigeBoucle;

public class Test_StructureTigeBoucle {

    //ALL
    static StructTigeBoucle struct_param;
    static StructTigeBoucle struct_rand;

    //TAILLE
    static StructTigeBoucle struct_param_max_taille;
    static StructTigeBoucle struct_param_over_max_taille;
    static StructTigeBoucle struct_param_min_taille;
    static StructTigeBoucle struct_param_under_min_taille;

    //APPARIEMENT
    static StructTigeBoucle struct_param_max_appariement;
    static StructTigeBoucle struct_param_max_appariement_taille_impaire;
    static StructTigeBoucle struct_param_over_max_appariement;
    static StructTigeBoucle struct_param_over_max_appariement_taille_impaire;

    static StructTigeBoucle struct_param_min_appariement;
    static StructTigeBoucle struct_param_min_appariement_taille_impaire;
    static StructTigeBoucle struct_param_under_min_appariement;
    static StructTigeBoucle struct_param_under_min_appariement_taille_impaire;

    //BOUCLE TERMINAL
    static StructTigeBoucle struct_param_max_terminal;
    static StructTigeBoucle struct_param_over_max_terminal;
    static StructTigeBoucle struct_param_min_terminal;
    static StructTigeBoucle struct_param_under_min_terminal;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        //ALL
        struct_param = new StructTigeBoucle(84,//nucléotides
                33,//appariements
                5  //taille de la boucle terminal
                );
        struct_rand = new StructTigeBoucle();

        //TAILLE
        struct_param_max_taille = new StructTigeBoucle(StructTigeBoucle.MAX_LONGUEUR, -1, -1);
        struct_param_over_max_taille = new StructTigeBoucle(StructTigeBoucle.MAX_LONGUEUR + 1, -1, -1);

        struct_param_min_taille = new StructTigeBoucle(StructTigeBoucle.MIN_LONGUEUR, -1, -1);
        struct_param_under_min_taille = new StructTigeBoucle(StructTigeBoucle.MIN_LONGUEUR - 1, -1, -1);


        //APPARIEMENT
        struct_param_max_appariement = new StructTigeBoucle(70, 35 , 0);
        struct_param_max_appariement_taille_impaire = new StructTigeBoucle(71, 35 , 0);
        struct_param_over_max_appariement = new StructTigeBoucle(70, 36, 0);
        struct_param_over_max_appariement_taille_impaire = new StructTigeBoucle(71, 36, 0);

        struct_param_min_appariement = new StructTigeBoucle(70, StructTigeBoucle.MIN_NB_APPARIEMENT , 0);
        struct_param_min_appariement_taille_impaire = new StructTigeBoucle(71, StructTigeBoucle.MIN_NB_APPARIEMENT , 0);
        struct_param_under_min_appariement = new StructTigeBoucle(70, StructTigeBoucle.MIN_NB_APPARIEMENT-1, 0);
        struct_param_under_min_appariement_taille_impaire = new StructTigeBoucle(71, StructTigeBoucle.MIN_NB_APPARIEMENT-1, 0);

        //BOUCLE TERMINAL
        //TAILLE
        struct_param_max_terminal = new StructTigeBoucle(-1,-1,StructTigeBoucle.MAX_TAILLE_BOUCLE_TERMINAL);
        struct_param_over_max_terminal = new StructTigeBoucle(-1,-1,StructTigeBoucle.MAX_TAILLE_BOUCLE_TERMINAL + 1);

        struct_param_min_terminal = new StructTigeBoucle(-1, -1, 0);
        struct_param_under_min_terminal = new StructTigeBoucle(-1 , -1, -1);
    }
  
    @Test
    public void testGetTaille() {

        //normal
        assertEquals(84 ,struct_param.getTaille());

        //limite max
        assertEquals(struct_param_max_taille.getTaille(), StructTigeBoucle.MAX_LONGUEUR);

        //above the max limit
        assertNotEquals(struct_param_over_max_taille, StructTigeBoucle.MAX_LONGUEUR + 1);
        assertTrue(struct_param_over_max_taille.getTaille() <= StructTigeBoucle.MAX_LONGUEUR);
        assertTrue(struct_param_over_max_taille.getTaille() >= StructTigeBoucle.MIN_LONGUEUR);

        //limite min
        assertEquals(struct_param_min_taille.getTaille(), StructTigeBoucle.MIN_LONGUEUR);

        //under the min limit
        assertNotEquals(struct_param_under_min_taille, StructTigeBoucle.MIN_LONGUEUR -1);
        assertTrue(struct_param_under_min_taille.getTaille() <= StructTigeBoucle.MAX_LONGUEUR);
        assertTrue(struct_param_under_min_taille.getTaille() >= StructTigeBoucle.MIN_LONGUEUR);        



        //une bouteille à la mer yolooooo
        assertTrue(struct_rand.getTaille() <= StructTigeBoucle.MAX_LONGUEUR);
        assertTrue(struct_rand.getTaille() >= StructTigeBoucle.MIN_LONGUEUR);

    }

    @Test
    public void testGetNombre_appariements() {
        //normal
        assertEquals(33 ,struct_param.getNombre_appariements());

        //limite max
        assertEquals(struct_param_max_appariement.getNombre_appariements(), 35);
        assertEquals(struct_param_max_appariement_taille_impaire.getNombre_appariements(), 35);

        //above the max limit
        //paire
        assertNotEquals(struct_param_over_max_appariement, 36);
        assertTrue(struct_param_over_max_appariement.getNombre_appariements() <= struct_param_max_appariement.getTaille()/2);
        assertTrue(struct_param_over_max_appariement.getNombre_appariements() >= StructTigeBoucle.MIN_NB_APPARIEMENT);
        //impaire
        assertNotEquals(struct_param_over_max_appariement_taille_impaire, 36);
        assertTrue(struct_param_over_max_appariement_taille_impaire.getNombre_appariements() <= struct_param_max_appariement_taille_impaire.getTaille()/2);
        assertTrue(struct_param_over_max_appariement_taille_impaire.getNombre_appariements() >= StructTigeBoucle.MIN_NB_APPARIEMENT);

        //limite min
        assertEquals(struct_param_min_appariement.getNombre_appariements(), StructTigeBoucle.MIN_NB_APPARIEMENT);
        assertEquals(struct_param_min_appariement.getNombre_appariements(), StructTigeBoucle.MIN_NB_APPARIEMENT);


        //under the min limit
        //paire
        assertNotEquals(struct_param_under_min_appariement.getNombre_appariements(), StructTigeBoucle.MIN_NB_APPARIEMENT -1);
        assertTrue(struct_param_under_min_appariement.getNombre_appariements() <= struct_param_under_min_appariement.getTaille()/2);
        assertTrue(struct_param_under_min_appariement.getNombre_appariements() >= StructTigeBoucle.MIN_NB_APPARIEMENT-1);        
        //impaire
        assertNotEquals(struct_param_under_min_appariement_taille_impaire.getNombre_appariements(), StructTigeBoucle.MIN_LONGUEUR -1);
        assertTrue(struct_param_under_min_appariement_taille_impaire.getNombre_appariements() <= struct_param_under_min_appariement_taille_impaire.getTaille()/2);
        assertTrue(struct_param_under_min_appariement_taille_impaire.getNombre_appariements() >= StructTigeBoucle.MIN_NB_APPARIEMENT-1);        


        //une bouteille à la mer yolooooo
        assertTrue(struct_rand.getNombre_appariements() <= struct_rand.getTaille()/2);
        assertTrue(struct_rand.getNombre_appariements() >= StructTigeBoucle.MIN_NB_APPARIEMENT); 
    }

    @Test
    public void testGetTaille__boucle_terminal() {
        //normal
        assertEquals(5 ,struct_param.getTaille__boucle_terminal());

        //limite max
        assertEquals(struct_param_max_terminal.getTaille__boucle_terminal(), StructTigeBoucle.MAX_TAILLE_BOUCLE_TERMINAL);

        //above the max limit
        assertNotEquals(struct_param_over_max_terminal, StructTigeBoucle.MAX_TAILLE_BOUCLE_TERMINAL + 1);
        assertTrue(struct_param_over_max_terminal.getTaille__boucle_terminal() <= StructTigeBoucle.MAX_TAILLE_BOUCLE_TERMINAL);
        assertTrue(struct_param_over_max_terminal.getTaille__boucle_terminal() >= 0);

        //limite min
        assertEquals(struct_param_min_terminal.getTaille__boucle_terminal(), 0);

        //under the min limit
        assertNotEquals(struct_param_under_min_terminal, -1);
        assertTrue(struct_param_under_min_terminal.getTaille__boucle_terminal() <= StructTigeBoucle.MAX_TAILLE_BOUCLE_TERMINAL);
        assertTrue(struct_param_under_min_terminal.getTaille__boucle_terminal() >= 0);        



        //une bouteille à la mer yolooooo
        assertTrue(struct_rand.getTaille__boucle_terminal() <= StructTigeBoucle.MAX_TAILLE_BOUCLE_TERMINAL);
        assertTrue(struct_rand.getTaille__boucle_terminal() >= 0);    
    }

    @Test
    public void testGetNombre_nucléotides_apparies() {
        assertEquals(struct_param.getNombre_nucléotides_apparies(), 66);

      //limite max
        assertEquals(struct_param_max_appariement.getNombre_nucléotides_apparies(), 70);
        assertEquals(struct_param_max_appariement_taille_impaire.getNombre_nucléotides_apparies(), 70);

        //above the max limit
        //paire
        assertNotEquals(struct_param_over_max_appariement.getNombre_nucléotides_apparies(), 72);
        assertTrue(struct_param_over_max_appariement.getNombre_nucléotides_apparies() <= struct_param_max_appariement.getTaille());
        assertTrue(struct_param_over_max_appariement.getNombre_nucléotides_apparies() >= StructTigeBoucle.MIN_NB_APPARIEMENT*2);
        //impaire
        assertNotEquals(struct_param_over_max_appariement_taille_impaire, 72);
        assertTrue(struct_param_over_max_appariement_taille_impaire.getNombre_nucléotides_apparies() <= struct_param_max_appariement_taille_impaire.getTaille()-1);
        assertTrue(struct_param_over_max_appariement_taille_impaire.getNombre_nucléotides_apparies() >= StructTigeBoucle.MIN_NB_APPARIEMENT*2);

        //limite min
        assertEquals(struct_param_min_appariement.getNombre_nucléotides_apparies(), StructTigeBoucle.MIN_NB_APPARIEMENT*2);
        assertEquals(struct_param_min_appariement.getNombre_nucléotides_apparies(), StructTigeBoucle.MIN_NB_APPARIEMENT*2);


        //under the min limit
        //paire
        assertNotEquals(struct_param_under_min_appariement.getNombre_nucléotides_apparies(), StructTigeBoucle.MIN_NB_APPARIEMENT -1);
        assertTrue(struct_param_under_min_appariement.getNombre_nucléotides_apparies() <= struct_param_under_min_appariement.getTaille());
        assertTrue(struct_param_under_min_appariement.getNombre_nucléotides_apparies() >= StructTigeBoucle.MIN_NB_APPARIEMENT-1);        
        //impaire
        assertNotEquals(struct_param_under_min_appariement_taille_impaire.getNombre_nucléotides_apparies(), (StructTigeBoucle.MIN_NB_APPARIEMENT-1)*2);
        assertTrue(struct_param_under_min_appariement_taille_impaire.getNombre_nucléotides_apparies() <= struct_param_under_min_appariement_taille_impaire.getTaille()-1);
        assertTrue(struct_param_under_min_appariement_taille_impaire.getNombre_nucléotides_apparies() >= StructTigeBoucle.MIN_NB_APPARIEMENT*2-1); 

        //une bouteille à la mer yolooooo
        assertTrue(struct_rand.getNombre_nucléotides_apparies() <= struct_rand.getTaille());
        assertTrue(struct_rand.getNombre_nucléotides_apparies() >= StructTigeBoucle.MIN_NB_APPARIEMENT * 2);

    }

}
