package ipplanmanager;

public class ReseauIP {
    private String adresseReseau;
    private int masqueCidr;
    private String description;

    public ReseauIP(String adresse, int cidr, String desc) {
        this.adresseReseau = adresse;
        this.masqueCidr = cidr;
        this.description = desc;
    }

    public void afficher() {
        System.out.println("--- Détails du Réseau ---");
        System.out.println("IP/CIDR : " + adresseReseau + "/" + masqueCidr);
        System.out.println("Description : " + description);
        
        // Appels à la classe utilitaire
        System.out.println("Classe : " + CalculateurReseau.obtenirClasseReseau(adresseReseau));
        System.out.println("Masque décimal : " + CalculateurReseau.obtenirMasqueDecimal(masqueCidr));
        System.out.println("Hôtes max : " + CalculateurReseau.calculerNombreHotes(masqueCidr));
        
        String type = CalculateurReseau.estReseauPrive(adresseReseau) ? "Privé" : "Public";
        System.out.println("Type : " + type);
    }
}