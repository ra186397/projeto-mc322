package game;

public enum Color {

    RED,
    BLUE,
    NONE;

    @Override
    public String toString(){
        switch (this){
            case RED:
            return "vermelho";

            case BLUE:
            return "azul";

            default:
            return "nada";
        }
    }
    
}