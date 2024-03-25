import java.io.Serializable;

public class Radio implements Serializable {
    private int produit_no;
    private String produit_fabriquant;
    private String produit_modele;
    private String produit_option;
    private float produit_prix;

    public Radio(int produit_no, String produit_fabriquant, String produit_modele, String produit_option, float produit_prix){
        this.produit_no = produit_no;
        this.produit_fabriquant = produit_fabriquant;
        this.produit_modele = produit_modele;
        this.produit_option = produit_option;
        this.produit_prix = produit_prix;
    }

    public String getProduit_fabriquant() {
        return produit_fabriquant;
    }

    public String getProduit_modele() {
        return produit_modele;
    }

    public String getProduit_option() {
        return produit_option;
    }

    public float getProduit_prix() {
        return produit_prix;
    }

    public void setProduit_option(String produit_option) {
        this.produit_option = produit_option;
    }

    public String toString() {
        return this.produit_no+"\t"+
                Utilitaires.ajouterEspaces(30,this.produit_fabriquant,'F')+"\t" +
                Utilitaires.ajouterEspaces(20,this.produit_modele,'F')+"\t" +
                Utilitaires.ajouterEspaces(20,this.produit_option,'F')+"\t" +
                Utilitaires.ajouterEspaces(10, String.valueOf(this.produit_prix),'F') + "\n";
    }



    public int getProduit_no() {
        return produit_no;
    }
}
