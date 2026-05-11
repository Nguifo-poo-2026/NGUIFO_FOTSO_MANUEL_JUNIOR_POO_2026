package ipplanmanager;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager: TP1 =====");
        System.out.println("Découverte des premières classes du projet");
        System.out.println("===============================\n");

        // --- 1. CRÉATION DES RÉSEAUX ---
        // Réseau de base [cite: 404, 406]
        ReseauIP reseauPrincipal = new ReseauIP("192.168.1.0", 24, "Réseau principal du laboratoire IRT");
        
        // DEUXIÈME RÉSEAU (Question 13) 
        ReseauIP reseauSecondaire = new ReseauIP("172.16.0.0", 16, "Réseau invité WiFi");

        System.out.println("----- Réseaux créés -----");
        reseauPrincipal.afficher();
        System.out.println();
        reseauSecondaire.afficher();
        System.out.println();

        // --- 2. CRÉATION DES ADRESSES IP ---
        AdresseIP ipRouteur = new AdresseIP("192.168.1.1"); 
        AdresseIP ipServeur = new AdresseIP("192.168.1.10"); 
        AdresseIP ipClient1 = new AdresseIP("192.168.1.50"); 
        AdresseIP ipClient2 = new AdresseIP("172.16.0.20"); // Pour le poste supplémentaire 
        AdresseIP ipInactive = new AdresseIP("10.0.0.5"); // Pour l'interface inactive 

        // --- 3. CRÉATION DES INTERFACES ---
        InterfaceReseau interfaceRouteur = new InterfaceReseau("eth0", ipRouteur); 
        InterfaceReseau interfaceServeur = new InterfaceReseau("eth0", ipServeur); 
        InterfaceReseau interfaceClient1 = new InterfaceReseau("wlan0", ipClient1); 
        
        // ÉLÉMENTS SPÉCIFIQUES (Question 13)
        // Interface pour le client supplémentaire 
        InterfaceReseau interfaceClient2 = new InterfaceReseau("eth0", ipClient2); 
        
        // Interface inactive 
        InterfaceReseau interfaceInactive = new InterfaceReseau("eth2", ipInactive); 
        
        // Interface sans adresse IP 
        InterfaceReseau interfaceSansIP = new InterfaceReseau("eth1", null); 

        // Activation des interfaces nécessaires [cite: 380, 381]
        interfaceRouteur.activer();
        interfaceServeur.activer();
        interfaceClient1.activer();
        interfaceClient2.activer();
        // interfaceInactive et interfaceSansIP restent désactivées par défaut [cite: 227]

        // --- 4. CRÉATION DES ÉQUIPEMENTS ---
        Equipement routeur = new Equipement("R1 EDGE", "Routeur", interfaceRouteur); 
        Equipement serveur = new Equipement("SRV DNS", "Serveur", interfaceServeur); 
        Equipement client1 = new Equipement("PC_ADMIN", "Poste client", interfaceClient1); 

        // ÉLÉMENTS SPÉCIFIQUES (Question 13)
        // Un Switch 
        Equipement switchPrincipal = new Equipement("SW-CORE", "Switch", interfaceSansIP); 
        
        // Un point d'accès WiFi 
        Equipement apWifi = new Equipement("AP-LABO", "Point d'accès WiFi", null); 
        
        // Un poste client supplémentaire 
        Equipement client2 = new Equipement("PC_RECHERCHE", "Poste client", interfaceClient2);

        // --- 5. AFFICHAGE DES ÉQUIPEMENTS ---
        System.out.println("----- Équipements créés -----");
        routeur.afficher(); 
        System.out.println();
        serveur.afficher(); 
        System.out.println();
        client1.afficher(); 
        System.out.println();
        client2.afficher(); // [cite: 486]
        System.out.println();
        switchPrincipal.afficher(); // Affiche l'interface sans IP [cite: 486]
        System.out.println();
        apWifi.afficher(); // Affiche "Aucune interface réseau configurée" [cite: 318, 486]
        System.out.println();
        
        System.out.println("--- Test Interface Inactive ---");
        interfaceInactive.afficher(); // [cite: 486]
    }
}