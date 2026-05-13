package ipplanmanager;

import java.util.HashMap;

public class GestionnaireVLAN {
    private HashMap<String, VLAN> tableVlans = new HashMap<>();
    private int prochainId = 10;

    public VLAN genererVLAN(String nomService) {
        if (!tableVlans.containsKey(nomService)) {
            VLAN nouveau = new VLAN(prochainId, "VLAN_" + nomService, "Segmentation " + nomService);
            tableVlans.put(nomService, nouveau);
            prochainId += 10; // On incrémente de 10 pour chaque nouveau service
        }
        return tableVlans.get(nomService);
    }
}