package IPPlanmanager.model;

/**
 *
 * 
 */


public class VLAN {
    private int id;
    private String nom;
    private ResultatVLSM reseauAssocie;
    private String description;

    public VLAN(int id, String nom, ResultatVLSM reseauAssocie, String description) {
        setId(id);
        setNom(nom);
        this.reseauAssocie = reseauAssocie;
        setDescription(description);
    }

    public int getId() { return id; }
    public void setId(int id) {
        // Validation : id entre 1 et 4094
        if (id < 1 || id > 4094) {
            System.out.println("ID VLAN invalide, mis a 1 par defaut");
            this.id = 1;
        } else {
            this.id = id;
        }
    }

    public String getNom() { return nom; }
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) this.nom = "VLAN_INCONNU";
        else this.nom = nom;
    }

    public ResultatVLSM getReseauAssocie() { return reseauAssocie; }
    public void setReseauAssocie(ResultatVLSM ra) { this.reseauAssocie = ra; }

    public String getDescription() { return description; }
    public void setDescription(String desc) {
        if (desc == null || desc.isEmpty()) this.description = "Aucune description";
        else this.description = desc;
    }

    public void afficher() {
        System.out.println("VLAN ID : " + id);
        System.out.println("Nom : " + nom);
        System.out.println("Description : " + description);
        if (reseauAssocie != null) {
            reseauAssocie.afficher();
        }
    }
}