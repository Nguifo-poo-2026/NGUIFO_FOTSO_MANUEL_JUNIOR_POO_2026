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
    private final int hotesDemandes; 
    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr, String masqueDecimal, int capacite, int hotesDemandes) { 
        this.nomBesoin = nomBesoin; 
        this.adresseReseau = adresseReseau; 
        this.cidr = cidr; 
        this.masqueDecimal = masqueDecimal; 
        this.capacite = capacite; 
        this.hotesDemandes = hotesDemandes; 
    } 
    public String getNomBesoin() { 
        return nomBesoin; 
    } 
    public String getAdresseReseau() { 
        return adresseReseau; 
    } 
    public int getCidr() { 
        return cidr; 
    } 
    public String getMasqueDecimal() { 
        return masqueDecimal; 
    } 
    public int getCapacite() { 
        return capacite; 
    } 
    public int getHotesDemandes() { 
        return hotesDemandes; 
    } 
    public int getMarge() { 
        return capacite - hotesDemandes; 
    } 
    public void afficher() { 
        System.out.println(nomBesoin + " -> " + adresseReseau + "/" + cidr 
        + " | Masque : " + masqueDecimal 
        + " | Demandés : " + hotesDemandes 
        + " | Capacité : " + capacite 
        + " | Marge : " + getMarge()); 
    } 
} 