package ipplanmanager;

public class ResultatVLSM {
    private String nomBesoin;
    private String adresseReseau;
    private int cidr;
    private String masqueDecimal;
    private int capacite;
    private String premiereIp;
    private String derniereIp;
    // --- AJOUT Q14 : Nouvel attribut pour le VLAN ---
    private VLAN vlanAssocie; 

    // --- MISE À JOUR Q14 : Le constructeur accepte maintenant un VLAN à la fin ---
    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr, String masqueDecimal, 
                        int capacite, String premiereIp, String derniereIp, VLAN vlan) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masqueDecimal = masqueDecimal;
        this.capacite = capacite;
        this.premiereIp = premiereIp;
        this.derniereIp = derniereIp;
        this.vlanAssocie = vlan; // On enregistre le VLAN
    }

    // --- MISE À JOUR Q14 : Affichage de l'ID du VLAN ---
    public void afficher() {
        String idVlan = (vlanAssocie != null) ? String.valueOf(vlanAssocie.getVlanId()) : "N/A";
        
        System.out.println(String.format("VLAN: %-3s | Service: %-12s | Reseau: %-15s/%-2d | Plage: %s - %s", 
                idVlan, nomBesoin, adresseReseau, cidr, premiereIp, derniereIp));
    }
}