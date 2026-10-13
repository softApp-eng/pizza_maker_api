# Pizza Maker - Backend (Spring Boot)

API REST pour la gestion d'un restaurant de pizzas : consultation du menu,
création de pizzas personnalisées, recommandations d'ingrédients et commandes.

## Stack technique

- Java 17+
- Spring Boot 3.3.4 (Web, Data JPA, Validation, Actuator)
- H2 (base en mémoire, profil dev) / PostgreSQL (profil prod)
- Lombok
- springdoc-openapi (Swagger UI)
- Maven

## Architecture

```
src/main/java/com/pizzamaker/
├── PizzaMakerApplication.java
├── config/          # Configuration (CORS, ...)
├── controllers/     # Endpoints REST
├── dtos/            # Objets d'entrée/sortie de l'API (Request/Response)
├── entities/        # Entités JPA
│   └── enums/       # Énumérations métier (taille, pâte, catégorie, statut)
├── exceptions/      # Exceptions métier + gestionnaire global
├── mappers/         # Conversion entités -> DTOs
├── repositories/    # Accès aux données (Spring Data JPA)
├── services/        # Interfaces de la logique métier
│   └── impl/        # Implémentations
└── utils/           # Utilitaires (calcul de prix)
```

## Modèle métier

- **Pizza** : pizzas prédéfinies du menu (`isCustom = false`), composées de `PizzaIngredient`.
- **Ingredient** : catalogue complet (catégories : `DOUGH`, `SAUCE`, `CHEESE`, `MEAT`, `VEGGIE`, `TOPPING`) avec prix et quantité maximale.
- **CustomPizza** / **CustomPizzaIngredient** : pizza créée par le client (taille + pâte + ingrédients choisis).
- **Order** / **OrderItem** : commande pouvant mélanger pizzas prédéfinies et pizzas personnalisées.
- **Recommendation** : règles de recommandation (ingrédients complémentaires, populaires).

### Calcul du prix d'une pizza personnalisée

```
prix = base(taille) + supplément(pâte) + Σ (prix_ingredient × quantité)
```

- Taille : `SMALL` = 5 €, `MEDIUM` = 7 €, `LARGE` = 9 €
- Pâte : `THIN`/`CLASSIC` = 0 €, `THICK` = +1 €, `STUFFED` = +2 €

## Endpoints REST

| Méthode | Endpoint | Description |
|---|---|---|
| `GET` | `/api/pizzas` | Liste des pizzas du menu |
| `GET` | `/api/pizzas/{id}` | Détail d'une pizza (avec ingrédients) |
| `GET` | `/api/ingredients` | Liste des ingrédients disponibles (filtre `?category=`) |
| `POST` | `/api/custom-pizzas` | Créer une pizza personnalisée |
| `GET` | `/api/custom-pizzas/{id}` | Récupérer une pizza personnalisée |
| `POST` | `/api/orders` | Créer une commande |
| `GET` | `/api/orders/{id}` | Suivre une commande |
| `POST` | `/api/recommendations` | Recommandations selon les ingrédients sélectionnés |
| `GET` | `/api/recommendations/popular` | Recommandations populaires |

Documentation interactive : http://localhost:8080/swagger-ui.html
Console H2 : http://localhost:8080/h2-console (JDBC URL : `jdbc:h2:mem:pizzadb`)

## Lancer le projet

```bash
cd pizza_maker_backend
mvn spring-boot:run
```

L'API démarre sur http://localhost:8080. Les données de démonstration
(pizzas + ingrédients + recommandations) sont chargées depuis `src/main/resources/data.sql`.

## Exemples d'appels

Créer une pizza personnalisée :

```bash
curl -X POST http://localhost:8080/api/custom-pizzas \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Ma pizza",
    "size": "LARGE",
    "crust": "STUFFED",
    "ingredients": [ {"ingredientId": 6, "quantity": 2}, {"ingredientId": 11, "quantity": 1} ]
  }'
```

Obtenir des recommandations :

```bash
curl -X POST http://localhost:8080/api/recommendations \
  -H "Content-Type: application/json" \
  -d '{ "ingredientIds": [8] }'
```

## Passage en PostgreSQL (prod)

Adaptez `src/main/resources/application.yml` (ou utilisez un profil `prod`) :

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/pizzadb
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
```
