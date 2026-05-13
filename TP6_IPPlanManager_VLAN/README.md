README - TP6 : Gestion des VLANs et Segmentation Logique
1. Objectifs du TP
L'objectif principal de ce TP était d'enrichir le projet IPPlan-Manager en lui permettant de gérer la segmentation logique d'un réseau. Plus précisément :

Modéliser des VLANs en Java avec des attributs spécifiques (ID, nom, description).

Créer un gestionnaire métier (GestionnaireVLAN) pour centraliser la manipulation des réseaux virtuels.

Associer automatiquement les sous-réseaux générés par le moteur VLSM à des VLANs cohérents.

Automatiser l'attribution des IDs pour simuler une configuration réseau professionnelle.

2. Notions Étudiées
Segmentation logique : Utilisation des VLANs pour isoler les flux de communication sur une même infrastructure physique.

Collections avancées : Utilisation de ArrayList pour stocker les objets VLAN et de HashMap (selon nos échanges) pour garantir l'unicité des services.

Architecture logicielle métier : Séparation des responsabilités entre le moteur de calcul (MoteurVLSM) et le gestionnaire de ressources (GestionnaireVLAN).

Encapsulation et validation : Contrôle des données, notamment pour respecter les limites d'IDs VLAN (1 à 4094).

3. Scénarios Testés
Scénario Entreprise (Initial) : Test avec les services TECHNIQUE (120 hôtes), WIFI (80 hôtes), ADMINISTRATION (50 hôtes) et SERVEURS (20 hôtes).

Scénario Université (Supplémentaire) : Test de grande envergure incluant ETUDIANTS (500 hôtes), ENSEIGNANTS (120 hôtes), LABORATOIRES (60 hôtes), WIFI PUBLIC (200 hôtes) et SERVEURS (30 hôtes).

Test de Recherche : Vérification de la capacité du système à retrouver un VLAN spécifique par son ID (ex: ID 20).

Test de Doublons : Validation de la logique permettant d'attribuer le même VLAN à deux besoins différents appartenant au même service.

4. Résultats Obtenus
Génération automatique : Le programme crée des VLANs avec des IDs incrémentés de 10 en 10.

Affichage complet : Chaque VLAN affiche son ID, son nom, sa description et les détails complets du sous-réseau associé (Masque, Capacité, etc.).

Détection de VLANs Critiques : Identification réussie des segments dépassant 100 hôtes grâce à la méthode afficherVLANsCritiques().

5. Difficultés Rencontrées
Liaison d'objets : Faire collaborer la classe VLAN avec l'objet ResultatVLSM pour que l'affichage soit cohérent.

Incrémentation des IDs : Mise en place d'une logique de numérotation automatique qui ne crée pas de conflits lors de l'ajout de nouveaux besoins.

Formatage de l'affichage : Assurer que les informations détaillées du réseau (calculées au TP5) remontent correctement dans l'affichage du VLAN.

6. Réponses aux Questions de Compréhension (Question 17)
1. Pourquoi les VLANs sont-ils importants dans les réseaux modernes ?
Ils permettent de découper logiquement un réseau physique pour améliorer la sécurité, réduire la congestion (domaines de broadcast) et organiser les flux par département.

2. Pourquoi un VLAN est-il souvent associé à un sous-réseau spécifique ?
Pour que la séparation logique soit efficace, chaque groupe d'utilisateurs (VLAN) doit avoir son propre plan d'adressage IP afin de permettre le routage et le filtrage entre les services.

3. Pourquoi la séparation logique améliore-t-elle la sécurité ?
Elle empêche une machine d'un département (ex: WiFi Invité) d'accéder directement aux ressources d'un autre (ex: Serveurs) sans passer par un équipement de contrôle (pare-feu/routeur).

4. Quel est le rôle de la classe GestionnaireVLAN ?
Elle sert de couche métier pour centraliser la gestion des VLANs : ajout, recherche par ID, affichage global et statistiques (nombre de VLANs).

5. Pourquoi la classe VLAN contient-elle un objet ResultatVLSM ?
Cela modélise l'association directe entre un segment logique (VLAN) et sa réalité technique (le sous-réseau calculé par le moteur).

6. Pourquoi utilise-t-on encore ArrayList dans ce TP ?
Pour stocker de manière dynamique et ordonnée la liste des VLANs créés, dont le nombre n'est pas connu à l'avance.

7. Pourquoi les responsabilités des classes doivent-elles être séparées ?
Pour rendre le code plus maintenable, évolutif et professionnel. Chaque classe s'occupe d'une seule tâche (Principe de responsabilité unique).

8. Pourquoi le projet commence-t-il à ressembler à une application professionnelle ?
Grâce à l'utilisation de gestionnaires métier, de structures de données dynamiques et d'une architecture qui reflète les besoins réels des infrastructures réseau d'entreprise.