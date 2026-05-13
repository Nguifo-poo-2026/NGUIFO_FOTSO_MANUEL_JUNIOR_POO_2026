# TP4 : Calculs réseau automatiques - IPPlanManager

## Description
Ce TP introduit la logique algorithmique réseau dans l'application IPPlanManager. L'objectif est d'automatiser la détermination des masques, des classes et des capacités d'accueil des réseaux via une classe utilitaire.

## 16. Réponses aux questions de compréhension

1. Pourquoi a-t-on créé une classe utilitaire ?
   On utilise une classe utilitaire pour regrouper des fonctions techniques (calculs mathématiques, conversions) qui ne dépendent pas de l'état d'un objet spécifique. Cela permet de centraliser la logique et d'éviter la duplication de code.

2.Quel est le rôle du mot-clé `static` ?
   Le mot-clé `static` permet d'appeler une méthode directement par le nom de la classe (ex: `CalculateurReseau.calculerNombreHotes()`) sans avoir besoin de créer une instance (objet) avec le mot-clé `new`.

3. Pourquoi les calculs réseau sont-ils importants dans un outil IPAM ?
   L'automatisation des calculs garantit la fiabilité des données, évite les erreurs humaines de calcul binaire et permet une gestion optimisée et rapide des ressources IP d'une entreprise.

4. Quelle est l'utilité du CIDR ?
   Le CIDR (Classless Inter-Domain Routing) est une notation compacte (ex: /24) qui remplace avantageusement les masques décimaux longs. Il offre plus de flexibilité pour le découpage en sous-réseaux (subnetting).

5. Pourquoi le nombre d'hôtes dépend-il du masque réseau ?
   Le masque définit la séparation entre la partie "réseau" et la partie "hôte" de l'adresse IP. Plus le masque est long (ex: /30), moins il reste de bits pour adresser des machines, ce qui réduit le nombre d'hôtes possibles.

6. Pourquoi certaines adresses IP sont-elles privées ?
   Elles sont réservées pour une utilisation interne dans les réseaux locaux (LAN). Cela permet d'économiser les adresses publiques IPv4 et d'assurer une première couche de sécurité puisque ces adresses ne sont pas routables sur Internet.

7. Pourquoi la séparation entre logique métier et logique de calcul améliore-t-elle le projet ?
   Cela rend le code plus modulaire et facile à maintenir. On peut modifier l'algorithme de calcul dans la classe utilitaire sans avoir à modifier la structure de la classe `ReseauIP`.

8. Pourquoi les outils de planification réseau doivent-ils automatiser les calculs ?
   Pour assister les administrateurs réseau en fournissant des résultats instantanés et exacts, ce qui est crucial lors du déploiement de grandes infrastructures complexes.

## 17. Tests réalisés et difficultés

### Tests effectués
- **Calcul des hôtes :** Testé pour un masque /24 (résultat : 254) et /16 (résultat : 65534).
- **Identification de classe :** L'adresse `10.0.0.0` est bien reconnue en Classe A, `172.16.0.0` en Classe B et `192.168.1.0` en Classe C.
- **Masque décimal :** Vérification de la conversion du CIDR /28 en `255.255.255.240`.
- **Analyse IP Privée :** Le programme distingue correctement `192.168.x.x` (Privé) de `8.8.8.8` (Public).

### Difficultés rencontrées
- Utilisation de la méthode `Math.pow()` qui retourne un type `double`, nécessitant un transtypage (cast) en `(int)` pour correspondre aux types de données du projet.
- Manipulation des expressions régulières lors de l'utilisation de `split("\\.")` pour découper l'adresse IP, car le point est un caractère spécial en Java.

## 18. Mise à jour du dépôt Git
Les commandes suivantes ont été exécutées :
- `git add .`
- `git commit -m "Ajout du TP4 calculs réseau automatiques"`
- `git push origin main`