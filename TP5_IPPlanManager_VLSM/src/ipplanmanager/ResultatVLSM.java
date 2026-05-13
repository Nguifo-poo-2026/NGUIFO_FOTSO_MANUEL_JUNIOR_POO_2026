package ipplanmanager;

public class ResultatVLSM {
    private String nomBesoin;
    private String adresseReseau;
    private int cidr;
    private String masqueDecimal;
    private int capacite;
    // Question 16
    private String premiereIp;
    private String derniereIp;

    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr, String masqueDecimal, 
                        int capacite, String premiereIp, String derniereIp) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masqueDecimal = masqueDecimal;
        this.capacite = capacite;
        this.premiereIp = premiereIp;
        this.derniereIp = derniereIp;
    }

    public void afficher() {
        System.out.println(String.format("%-15s -> %-15s/%d | Plage: %-15s à %-15s | Masque: %-15s | Capacité: %d", 
            nomBesoin, adresseReseau, cidr, premiereIp, derniereIp, masqueDecimal, capacite));
    }
}