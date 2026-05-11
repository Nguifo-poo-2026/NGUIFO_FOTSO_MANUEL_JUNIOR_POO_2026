package ipplanmanager; // Le package doit être en haut [cite: 104, 339]

public class Main { // La classe Main [cite: 337, 340]

    public static void main(String[] args) { // La méthode principale [cite: 342, 345]
        
        // --- PARTIE 1 : CODE DÉJÀ PRÉSENT (Section 11.3 du TP) ---
        System.out.println("===== IPPlan-Manager: TP1 ====="); 
        // ... (vos premiers objets : ipRouteur, interfaceRouteur, routeur, etc.)
        
        
        // --- PARTIE 2 : ZONE OÙ VOUS DEVEZ AJOUTER LE CODE (Question 13) ---
        // Ajoutez vos nouveaux objets juste ici, avant la dernière accolade :

        // 1. Un deuxième réseau
        ReseauIP reseauSecondaire = new ReseauIP("192.168.2.0", 24, "Réseau WiFi Invités");
        reseauSecondaire.afficher();

        // 2. Un switch
        InterfaceReseau intSwitch = new InterfaceReseau("port1", new AdresseIP("192.168.1.2"));
        intSwitch.activer();
        Equipement monSwitch = new Equipement("SW-LABO", "Switch", intSwitch);
        monSwitch.afficher();

        // 3. Un point d'accès WiFi
        InterfaceReseau intAP = new InterfaceReseau("wlan0", new AdresseIP("192.168.1.5"));
        intAP.activer();
        Equipement monAP = new Equipement("AP-SALLE-A", "Point d'accès WiFi", intAP);
        monAP.afficher();

        // 4. Un poste client supplémentaire
        InterfaceReseau intClient2 = new InterfaceReseau("eth0", new AdresseIP("192.168.1.51"));
        intClient2.activer();
        Equipement pcCompta = new Equipement("PC-COMPTA", "Poste client", intClient2);
        pcCompta.afficher();

        // 5. Une interface inactive (on ne l'active pas)
        InterfaceReseau interfaceMorte = new InterfaceReseau("eth1", new AdresseIP("10.0.0.1"));
        interfaceMorte.afficher();

        // 6. Une interface sans adresse IP (on passe null)
        InterfaceReseau interfaceVide = new InterfaceReseau("eth2", null);
        interfaceVide.afficher();

    } // Fin de la méthode main [cite: 371]
} // Fin de la classe Main [cite: 372]