package IPPlanmanager.model;

/**
 *
 * 
 */

public class ResultatVLSM {
    private final String nomBesoin;
    private final String adresseReseau;
    private final int cidr;
    private final String masqueDecimal;
    private final int capacite;
    private final String premiereAdresse;
    private final String derniereAdresse;

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

    public String getNomBesoin() {return nomBesoin; }
    public String getAdresseReseau() {return adresseReseau; }
    public int getCidr() { return cidr; }
    public String getmasqueDecimal(){return masqueDecimal; }
    public int getCapacite() {return capacite; }
    public String getpremiereAdresse(){return premiereAdresse; }
    public String getderniereAdresse(){return derniereAdresse; }


    public void afficher() {
        System.out.println(nomBesoin + " -> " + adresseReseau + "/" + cidr +
            " | Plage : " + premiereAdresse + " - " + derniereAdresse +
            " | Capacite : " + capacite + " hotes");
    }
}