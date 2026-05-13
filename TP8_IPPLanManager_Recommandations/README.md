###### **TP8 – Moteur de recommandations intelligentes**



**Objectif**

&#x20;Ajouter un moteur de recommandations capable d’analyser un plan VLAN et de proposer des conseils techniques (WiFi, serveurs, grands VLANs, administration).



**Notions étudiées**

&#x20;- Interfaces Java (RegleRecommandation)

&#x20;- Polymorphisme (exécution de règles différentes via une interface commune)

&#x20;- Séparation des responsabilités (une règle par classe)

&#x20;- Extensibilité : ajouter une nouvelle règle sans modifier le moteur



**Scénarios testés**

&#x20;1. Scénario université : ETUDIANTS(500), WIFI\_INVITES(200), ENSEIGNANTS(120), LABOS(60), SERVEURS(30) – recommandations WiFi, serveurs, grand VLAN.

&#x20;2. Scénario avec administration : ADMINISTRATION(50), WIFI\_INVITES(120), SERVEURS(20), CAMERAS(80), VOIP(60) – inclut la nouvelle règle RecommandationAdministration.

&#x20;3. Test de la marge (travail supplémentaire) : détection des sous-réseaux avec marge insuffisante.



**Difficultés rencontrées**

&#x20;- Comprendre l’intérêt de l’interface : le moteur n’a pas besoin de connaître les règles concrètes.

&#x20;- Gestion du null retourné par analyser() quand aucune recommandation n’est nécessaire.

&#x20;- Ajout de la méthode getVlans() dans GestionnaireVLAN pour permettre l’analyse.



**Réponses aux questions**



&#x20;1. Rôle d’un moteur de recommandations dans un outil IPAM ?  

&#x20;  Il aide l’administrateur à appliquer les bonnes pratiques (sécurité, performance, évolutivité) au-delà de la simple correction technique.



&#x20;2. Pourquoi utiliser une interface pour les règles ?  

&#x20;  Pour définir un contrat commun (`analyser(VLAN)`) et permettre au moteur de manipuler toutes les règles de manière uniforme, sans dépendre des implémentations concrètes.



&#x20;3. Différence entre classe concrète et interface ?  

&#x20;  Une classe concrète fournit une implémentation complète (attributs + méthodes). Une interface ne définit que des signatures de méthodes – elle sert de contrat.



&#x20;4. Pourquoi `analyser()` peut retourner `null` ?  

&#x20;  Toutes les règles ne s’appliquent pas à tous les VLANs. Retourner null signifie “pas de recommandation pour ce VLAN”. Cela évite de créer des objets vides.



&#x20;5. Pourquoi le moteur de recommandations illustre-t-il le polymorphisme ?  

&#x20;  Parce que la même instruction regle.analyser(vlan) exécute des comportements différents selon la règle réelle (`RecommandationWifiInvite`, RecommandationServeurs, etc.).



&#x20;6. Pourquoi créer une classe par règle plutôt que tout mettre dans `Main` ?  

&#x20;  Pour respecter le principe ouvert/fermé : le système est ouvert à l’ajout de nouvelles règles (classe supplémentaire) mais fermé à la modification du moteur existant.



&#x20;7. Pourquoi un VLAN WiFi invité doit-il être isolé des réseaux internes ?  

&#x20;  Par mesure de sécurité : empêcher un utilisateur WiFi compromis d’accéder aux serveurs ou aux postes de l’administration. L’isolation se fait généralement par un pare‑feu ou une ACL.



&#x20;8. Pourquoi les VLANs de grande taille doivent-ils être surveillés ?  

&#x20;  Un grand VLAN (beaucoup d’hôtes) génère beaucoup de trafic de broadcast, ce qui peut dégrader les performances et rendre la supervision plus difficile.



