package ipplanmanager;

import java.util.ArrayList; 

public class InfrastructureReseau {
    private String nom; 
    private ArrayList<Equipement> equipements; 
    private ArrayList<SousReseau> sousReseaux; 

    public InfrastructureReseau(String nom) {
        this.nom = nom; 
        this.equipements = new ArrayList<>(); 
        this.sousReseaux = new ArrayList<>(); 
    }

    // --- Méthodes existantes ---
    public void ajouterEquipement(Equipement equipement) {
        equipements.add(equipement); 
    }

    public void ajouterSousReseau(SousReseau sousReseau) {
        sousReseaux.add(sousReseau); 
    }

    // --- LA QUESTION 15 : Méthode de recherche ---
    public void rechercherEquipement(String nomRecherche) {
        boolean trouve = false; // Indicateur pour savoir si on a trouvé l'objet [cite: 491]

        // On parcourt la collection des équipements 
        for (Equipement e : equipements) {
            // Comparaison du nom (ignore la casse pour plus de souplesse)
            if (e.getNom().equalsIgnoreCase(nomRecherche)) {
                System.out.println("Équipement trouvé !");
                e.afficher(); // Affiche les informations si trouvé 
                trouve = true;
                break; // On quitte la boucle une fois trouvé
            }
        }

        // Si après la boucle rien n'est trouvé [cite: 491]
        if (!trouve) {
            System.out.println("Équipement " + nomRecherche + " introuvable."); 
        }
    }

    // --- Méthodes d'affichage ---
    public void afficherEquipements() {
        for (Equipement equipement : equipements) { 
            equipement.afficher(); 
            System.out.println(); 
        }
    }

    public void afficherSousReseaux() {
        for (SousReseau sousReseau : sousReseaux) { 
            sousReseau.afficher(); 
            System.out.println(); 
        }
    }

    public void afficher() {
        System.out.println("Infrastructure : " + nom); 
        System.out.println("===== SOUS-RÉSEAUX ====="); 
        afficherSousReseaux(); 
        System.out.println("===== ÉQUIPEMENTS ====="); 
        afficherEquipements(); 
    }
}