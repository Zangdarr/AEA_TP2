import exception.GeneratorException;
import adn.PreMicArnGenerator;
import adn.StructTigeBoucle;


public class main {

    public static void main(String[] args) {

        StructTigeBoucle struct = new StructTigeBoucle();
        
        PreMicArnGenerator gen = new PreMicArnGenerator(struct);
        
        //Nombre de fois que sera généré un microARN à partir de la structure TigeBoucle "struct"
        int nombre_de_fois = 10;
        String[] tab = new String[nombre_de_fois];
        String[] premi = new String[nombre_de_fois];
        
        for (int i = 0; i< tab.length;i++) {
            try {
                tab[i] = gen.generate().toString();
                premi[i] = gen.convertBoneToPreMiC(new StringBuffer(tab[i])).toString();
                System.out.println(tab[i]);
                System.out.println(premi[i]);
            } catch (GeneratorException e) {
                System.err.println(e.getMessage());
                break;
            }
        }
        
        System.out.println("\n\n\n");
        struct.printCaracteristics();
       
    }

}
