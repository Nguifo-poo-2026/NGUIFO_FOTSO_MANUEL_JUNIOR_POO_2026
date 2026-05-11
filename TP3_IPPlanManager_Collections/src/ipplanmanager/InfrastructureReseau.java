package ipplanmanager; 
 
import java.util.ArrayList; 
 
public class InfrastructureReseau { 
 
    private String nom; 
    private ArrayList<Equipement> equipements;     private ArrayList<SousReseau> sousReseaux; 
 
    public InfrastructureReseau(String nom) {         this.nom = nom; 
        equipements = new ArrayList<>();         sousReseaux = new ArrayList<>(); 
    } 
    public void ajouterEquipement(Equipement equipement) { 
        equipements.add(equipement); 
    } 
    public void ajouterSousReseau(SousReseau sousReseau) { 
        sousReseaux.add(sousReseau); 
    } 
    public void afficherEquipements() {         for (Equipement equipement : equipements) { 
            equipement.afficher();             System.out.println(); 
        } 
    } 
    public void afficherSousReseaux() {         for (SousReseau sousReseau : sousReseaux) { 
            sousReseau.afficher();             System.out.println(); 
        } 
    } 
    public void afficher() { 
        System.out.println("Infrastructure : " + nom); 
 
        System.out.println(); 
        System.out.println("===== SOUS-RÉSEAUX =====");         afficherSousReseaux(); 
 
        System.out.println(); 
        System.out.println("===== ÉQUIPEMENTS =====");         afficherEquipements(); 
    } 
} 
