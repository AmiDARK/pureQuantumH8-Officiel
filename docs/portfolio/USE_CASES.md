# Use Cases — Démonstrations

## 1) Web UI — Smoke Test
**But :** vérifier l’accès à une page et une assertion simple.  
**JSON :** `{"provider":"local","browserName":"chrome"}`  
**Étapes :** démarrer le driver, `get("https://example.org")`, vérifier `<h1>` contient "Example".

## 2) Mobile Cloud — Android (BrowserStack / Sauce / Kobiton)
**But :** lancer une app Android et vérifier une activité.  
**JSON (extrait générique) :**
```json
{
  "provider": "browserstack",
  "platformName": "Android",
  "deviceName": "Google Pixel 7",
  "app": "bs://<app-id>",
  "bstack:options": { "projectName":"pqH8", "buildName":"demo" }
}
```
**Étapes :** charger le JSON, compléter les secrets via variables d’environnement, initialiser `AndroidDriver`, vérifier une vue.

## 3) API (optionnel)
**But :** appeler une route REST et asserter le JSON.  
**Stack possible :** Rest-Assured / HttpClient + Jackson.
