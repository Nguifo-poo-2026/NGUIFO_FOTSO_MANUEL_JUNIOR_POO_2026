
package IPPlanmanager.service;

/**
 *
 * 
 */
import IPPlanmanager.exception.ConflitVLANException;
import IPPlanmanager.model.VLAN;

import java.util.ArrayList;

public class GestionnaireVLAN {
    private final ArrayList<VLAN> vlans;
    
    public ArrayList<VLAN> getVlans() { 
        return vlans; 
    } 

    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }

    public void ajouterVLAN(VLAN vlan) throws ConflitVLANException { 
        if (vlan == null) { 
            return; 
        } 
        for (VLAN v : vlans) { 
            if (v.getId() == vlan.getId()) { 
                throw new ConflitVLANException("Conflit VLAN : l'identifiant" + vlan.getId() + " est deja utilise."); 
            } 
        } 
        vlans.add(vlan); 
    } 

    public void afficherTousLesVLANs() {
        for (VLAN v : vlans) {
            v.afficher();
            System.out.println();
        }
    }

    public VLAN rechercherVLAN(int id) {
        for (VLAN v : vlans) {
            if (v.getId() == id) return v;
        }
        return null;
    }

    public int obtenirNombreVLANs() {
        return vlans.size();
    }

    // Travail supplémentaire : VLANs critiques (>100 hôtes)
    public void afficherVLANsCritiques() {
        System.out.println("--- VLANs critiques (capacite > 100 hotes) ---");
        boolean found = false;
        for (VLAN v : vlans) {
            if (v.getReseauAssocie() != null && v.getReseauAssocie().getCapacite() > 100) {
                System.out.println("VLAN critique detecte : VLAN " + v.getId() +
                                   " - " + v.getNom() +
                                   " - " + v.getReseauAssocie().getCapacite() + " hotes");
                found = true;
            }
        }
        if (!found) System.out.println("Aucun VLAN critique.");
    }

    // Travail supplémentaire : VLAN avec la plus grande capacité
    public void afficherVLANMaxCapacite() {
        VLAN maxVlan = null;
        int maxCap = -1;
        for (VLAN v : vlans) {
            if (v.getReseauAssocie() != null && v.getReseauAssocie().getCapacite() > maxCap) {
                maxCap = v.getReseauAssocie().getCapacite();
                maxVlan = v;
            }
        }
        if (maxVlan != null) {
            System.out.println("VLAN avec la plus grande capacite : VLAN " + maxVlan.getId() +
                               " - " + maxVlan.getNom() +
                               " - " + maxCap + " hotes");
        } else {
            System.out.println("Aucun VLAN trouve.");
        }
    }
}