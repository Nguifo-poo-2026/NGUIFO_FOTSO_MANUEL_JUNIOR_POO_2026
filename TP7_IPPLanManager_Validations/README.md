###### **TP7 – Validations avancées et détection des conflits**



**Objectif**

&#x20;Ajouter des validations avancées pour détecter les incohérences dans un plan d’adressage : adresses IP invalides, chevauchements de sous-réseaux, conflits  d’identifiants VLAN.



**Notions étudiées**

&#x20;- Exceptions personnalisées (héritage de `Exception`)

&#x20;- try/catch et throw

&#x20;- Validation réseau (adresse IP, plages)

&#x20;- Détection de chevauchement par conversion en entiers

&#x20;- Séparation de la logique de validation (`ValidateurPlanAdressage`)



**Scénarios testés**

&#x20;1. Plan normal : besoins (ADMIN 50, TECH 120, WIFI 80, SERVEURS 20) sur 192.168.1.0 – validation réussie.

&#x20;2. Adresse de départ invalide : test avec 192.168.300.0 → exception AdresseIPInvalideException.

&#x20;3. Chevauchement volontaire : création manuelle de 192.168.1.0/25 et 192.168.1.64/26 → détection par verifierChevauchements().

&#x20;4. Conflit VLAN : ajout de deux VLAN avec l’ID 10 → exception ConflitVLANException.



**Résultats obtenus**

&#x20;- Validation réussie pour le plan normal.

&#x20;- Détection correcte des adresses IP invalides.

&#x20;- Détection des chevauchements réseau.

&#x20;- Blocage de l’ajout d’un VLAN en double.



**Difficultés rencontrées**

&#x20;- Calcul précis des bornes de réseau avec conversion IP ↔ entier.

&#x20;- Gestion des exceptions multiples dans un même catch (syntaxe `|`).

&#x20;- Positionnement de la validation avant la génération des VLANs pour éviter des erreurs en cascade.



**Réponses aux questions**



&#x20;1. Pourquoi les validations avancées sont-elles indispensables dans un outil IPAM ?  

&#x20;  Un IPAM (IP Address Management) doit garantir la cohérence du plan d’adressage. Des erreurs (chevauchement, conflit VLAN) peuvent causer des pannes réseau réelles. Les validations avancées évitent ces risques.



&#x20;2. Différence entre une erreur simple et une exception en Java ?  

&#x20;  Une erreur simple n’interrompt pas le flux et peut être ignorée. Une exception est un objet qui signale un problème et peut être propagée, attrapée et traitée de manière structurée.



&#x20;3. Pourquoi crée t on des exceptions personnalisées ?  

&#x20;  Pour rendre les erreurs plus explicites métier (AdresseIPInvalideException au lieu de Exception générique) et permettre un traitement différencié selon le type d’erreur.



&#x20;4. Rôle du bloc try/catch ?  

&#x20;  Il permet d’exécuter un code risqué (`try`) et de réagir proprement en cas d’exception (`catch`) sans que l’application ne plante.



&#x20;5. Pourquoi deux VLANs ne doivent pas avoir le même identifiant ?  

&#x20;  L’identifiant VLAN (1-4094) doit être unique sur un même commutateur pour éviter les conflits de configuration et l’appartenance multiple d’un port à plusieurs VLANs.



&#x20;6. Pourquoi deux sous-réseaux ne doivent pas se chevaucher ?  

&#x20;  Un chevauchement rend le routage ambigu : une adresse IP pourrait appartenir à deux réseaux différents, causant des incohérences de table de routage et des problèmes de communication.



&#x20;7. Pourquoi convertir les adresses IP en entiers pour comparer des plages ?  

&#x20;  Les opérations arithmétiques (comparer des bornes, ajouter un décalage) sont naturelles sur les entiers, mais impossibles directement sur des chaînes “192.168.1.0”.



&#x20;8. Pourquoi séparer ValidateurPlanAdressage du moteur VLSM ?  

&#x20;  Principe de responsabilité unique : le moteur VLSM calcule, le validateur vérifie. On peut ainsi modifier ou enrichir les règles de validation sans toucher au moteur de calcul.



