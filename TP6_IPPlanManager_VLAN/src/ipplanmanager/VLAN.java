package ipplanmanager;

public class VLAN {
    private int vlanId;
    private String nom;
    private String description;

    public VLAN(int vlanId, String nom, String description) {
        this.vlanId = vlanId;
        this.nom = nom;
        this.description = description;
    }

    public void afficher() {
        System.out.println("VLAN ID : " + vlanId + " | Nom : " + nom + " | Service : " + description);
    }

    public int getVlanId() { return vlanId; }
    public String getNom() { return nom; }
}