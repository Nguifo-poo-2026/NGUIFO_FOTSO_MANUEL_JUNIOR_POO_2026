package IPPlanmanager.service;

/**
 *
 * 
 */

import java.util.ArrayList; 
import IPPlanmanager.model.Recommandation;
import IPPlanmanager.model.VLAN;

public class MoteurRecommandation { 
    private ArrayList<RegleRecommandation> regles; 
    public MoteurRecommandation() { 
        regles = new ArrayList<>(); 
    }  
    public void ajouterRegle(RegleRecommandation regle) { 
        regles.add(regle); 
    } 
    public ArrayList<Recommandation> analyserVLANs(ArrayList<VLAN> vlans) { 
        ArrayList<Recommandation> recommandations = new ArrayList<>(); 
        for (VLAN vlan : vlans) { 
            for (RegleRecommandation regle : regles) { 
                Recommandation recommandation = (Recommandation) regle.analyser(vlan); 
                if (recommandation != null) { 
                    recommandations.add(recommandation); 
                } 
            } 
        } 
        return recommandations; 
    } 
    public void afficherRecommandations(ArrayList<Recommandation> recommandations) { 
        if (recommandations.isEmpty()) { 
            System.out.println("Aucune recommandation particulière."); 
            return; 
        } 
        for (Recommandation recommandation : recommandations) { 
            recommandation.afficher(); 
        } 
    } 
} 