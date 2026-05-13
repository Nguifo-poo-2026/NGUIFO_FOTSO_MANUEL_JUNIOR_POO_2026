package IPPlanmanager;

/**
 *
 * 
 */

public class ResultatVLSM {
    private String nomBesoin;
    private String adresseReseau;
    private int cidr;
    private String masqueDecimal;
    private int capacite;
    private String premiereAdresse;
    private String derniereAdresse;

    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr,String masqueDecimal, int capacite,
           String premiereAdresse, String derniereAdresse) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masqueDecimal = masqueDecimal;
        this.capacite = capacite;
        this.premiereAdresse = premiereAdresse;
        this.derniereAdresse = derniereAdresse;
    }

    public String getNomBesoin() { return nomBesoin; }
    public String getAdresseReseau() { return adresseReseau; }
    public int getCidr() { return cidr; }
    public int getCapacite() { return capacite; }

    public void afficher() {
        System.out.println(nomBesoin + " -> " + adresseReseau + "/" + cidr +
                           " | Plage : " + premiereAdresse + " - " + derniereAdresse +
                           " | Capacite : " + capacite + " hotes");
    }
}