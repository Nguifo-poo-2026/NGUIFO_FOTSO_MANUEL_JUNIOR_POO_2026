package IPPlanmanager.service;

/**
 *
 * 
 */
//import IPPlanmanager.exception.ConflitVLANException;
//import IPPlanmanager.model.BesoinReseau;
import IPPlanmanager.model.Recommandation;
//import IPPlanmanager.model.ResultatVLSM;
import IPPlanmanager.model.VLAN;
//import IPPlanmanager.repository.BesoinRepository;
//import IPPlanmanager.repository.FichierPlanRepository;
public class RecommandationWifiInvite implements RegleRecommandation { 
    @Override 
    public Recommandation analyser(VLAN vlan) { 
        if (vlan.getNom().toUpperCase().contains("WIFI")) { 
            return new Recommandation("Isolation du WiFi", "ELEVEE", "Le VLAN " + vlan.getNom() + " doit etre isole des VLANs internes sensibles."); 
        } 
        return null; 
    } 
}
