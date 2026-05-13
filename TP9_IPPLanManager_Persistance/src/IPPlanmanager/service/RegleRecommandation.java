
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
public interface RegleRecommandation { 
    Recommandation analyser(VLAN vlan); 
} 