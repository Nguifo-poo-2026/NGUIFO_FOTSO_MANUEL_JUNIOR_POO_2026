# TP2 : Encapsulation et Validation des données

## Description
Ce projet introduit les concepts fondamentaux de la Programmation Orientée Objet (POO) pour améliorer la robustesse du gestionnaire IPPlan-Manager.

## Notions étudiées
* [cite_start]**private** : Protection des attributs[cite: 545].
* [cite_start]**getters & setters** : Accès et modification contrôlés[cite: 548, 550].
* [cite_start]**validation** : Vérification de la cohérence des données (ex: masques, adresses non vides)[cite: 549].
* [cite_start]**this** : Référence à l'instance courante[cite: 552].

## [cite_start]Réponses aux questions de compréhension [cite: 560]
1. **Usage de private** : Pour protéger les données et respecter l'encapsulation.
2. **Public vs Privé** : Public est accessible partout, Privé uniquement dans la classe.
3. **Getters/Setters** : Pour lire et modifier les données avec contrôle.
4. **Validations réseau** : Éviter les pannes logiques et erreurs de configuration.
5. **Mot-clé this** : Désigne l'objet actuel.
6. **Constructeur et Setters** : Pour valider les données dès l'instanciation.
7. **Masque CIDR** : Doit être entre 0 et 32 pour être valide.
8. **Sécurité** : Garantit que les données ne sont pas modifiées de manière incohérente.

## [cite_start]Tests réalisés [cite: 553]
* [cite_start]Tentative de création d'une adresse IP vide (remplacée par 0.0.0.0)[cite: 109].
* [cite_start]Test d'un masque CIDR invalide comme 45 (remplacé par 24)[cite: 231, 232].
* [cite_start]Validation des noms d'équipements et types (remplacés par "inconnu" si vides)[cite: 364, 387].

## Difficultés rencontrées
* [cite_start][repondre au question
1-Pourquoi utilise-t-on private dans les classes ?
On utilise private pour appliquer le principe d'encapsulation. Cela empêche l'accès direct et non contrôlé aux données d'un objet depuis l'extérieur de sa propre classe.  
2-Quelle différence existe entre un attribut public et un attribut privé ?
Un attribut public est accessible et modifiable par n'importe quelle autre classe du programme. Un attribut privé est invisible à l'extérieur de sa classe ; on ne peut interagir avec lui que via des méthodes spécifiques (getters et setters). 
3- Pourquoi utilise-t-on des getters et setters ?
Les getters permettent de lire la valeur d'un attribut privé en toute sécurité. Les setters permettent de modifier cette valeur tout en y intégrant des mécanismes de contrôle et de validation.  
4-Pourquoi les validations sont-elles importantes dans un logiciel réseau ?
Elles évitent les erreurs de configuration, les incohérences (comme un masque CIDR de 45), les conflits réseau et les pannes logiques qui pourraient rendre l'application imprévisible.  Quel est le rôle du mot-clé this ?this est utilisé pour lever l'ambiguïté entre un attribut de la classe et un paramètre de méthode portant le même nom. Il désigne l'instance actuelle de l'objet. 
5- Pourquoi le constructeur appelle-t-il les setters ?
Cela permet de réutiliser la logique de validation déjà écrite dans les setters dès la création de l'objet, garantissant ainsi que l'objet est valide dès sa naissance. 
6- Pourquoi la validation du masque CIDR est-elle importante ?
Un masque CIDR doit être compris entre 0 et 32 pour être techniquement valide en IPv4. Sans validation, une valeur erronée fausserait tous les calculs réseau ultérieurs.
7-  Pourquoi l'encapsulation améliore-t-elle la sécurité logicielle ?
Elle protège l'intégrité des données en empêchant toute modification accidentelle ou malveillante qui pourrait corrompre l'état interne de l'application.  