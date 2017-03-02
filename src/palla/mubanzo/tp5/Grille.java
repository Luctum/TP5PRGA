package palla.mubanzo.tp5;

public class Grille<T> {

    private int hauteur;
    private int largeur;
    private Object [][] tab;

    public Grille (int hauteur, int largeur){
        assert hauteur >= 0 && largeur >= 0;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.tab = new Object[this.hauteur][this.largeur];
    }

    public boolean coordCorrectes(int lig, int col){
        return ( 1 <= lig && lig <= hauteur) && (1 <= col && col <= largeur);
    }

    public T getCellule (int lig, int col){
        assert coordCorrectes(lig, col);
        return (T) this.tab[lig-1][col-1];
    }

    public void setCellule(int lig, int col, T ch) {
        assert coordCorrectes(lig, col);
        this.tab[lig-1][col-1] = ch;
    }

    @Override
    public String toString(){
        String s = "";
        for(int i = 1; i <= getHauteur(); i++ ){
            for(int j = 1; j <= getLargeur()-1; j++){
                s += i + "," + j  + "|";
            }
            s += i + "," + getLargeur() + "\n";
        }

        return s;
    }

    public int getHauteur() {
        return this.hauteur;
    }

    public int getLargeur() {
        return this.largeur;
    }


    public static void main(String[] args){
        Grille<String> maGrille = new Grille<String>(3,5);
        for(int i = 1; i <= maGrille.getHauteur(); i++){
            String textLigne = Integer.toString(1);
            for(int c = 1; c <= maGrille.getLargeur(); c++){
                maGrille.setCellule(1, c, textLigne + ',' + Integer.toString(c));
            }

        }

        System.out.println(maGrille.toString());
    }
}
