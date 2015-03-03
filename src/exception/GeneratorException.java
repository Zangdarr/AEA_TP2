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
        }

    }
}
