package exception;

public class GeneratorException extends Exception {

    public GeneratorException(String message){
        super(message);
    }

    public GeneratorException(int id) throws GeneratorException{
        String message = ""; 
        switch(id) {
        case 0 : 
            message = "Trop de tentatives de génération on échouées. Suggestion : changer de structure TigeBoucle.";
            throw new GeneratorException(message);
        case 1 :
            message = "Mauvais nom de nucléotides passé en paramètre";
            throw new GeneratorException(message);
        default : 
            message = "Une erreur est survenue lors de la génération du Pré-Micro ARN";
            throw new GeneratorException(message);

        }

    }
}
