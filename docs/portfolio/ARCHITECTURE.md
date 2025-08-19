# Architecture — Vue pédagogique

```mermaid
flowchart LR
  A[Config JSON] --> B[Validator/Mapper]
  B --> C[DriverFactory]
  C -->|local| D[WebDriver]
  C -->|grid| E[Selenium Grid]
  C -->|sauce| F[Sauce Labs]
  C -->|browserstack| G[BrowserStack]
  C -->|kobiton| H[Kobiton]

  subgraph Tests
    T1[Test Web]
    T2[Test Mobile]
    T3[Test API]
  end
  D --> Tests
  E --> Tests
  F --> Tests
  G --> Tests
  H --> Tests
```
- **Config JSON** : source de vérité (environnements, capabilities).  
- **Validator/Mapper** : détecte les erreurs tôt, normalise les clés.  
- **DriverFactory** : instancie le bon provider en masquant la complexité.  
- **Tests** : consomment une interface simple et réutilisable.
