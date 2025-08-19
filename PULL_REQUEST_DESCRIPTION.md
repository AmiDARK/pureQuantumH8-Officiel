# CI + Docs : Quickstart, mapping config, sécurité

## Résumé
- Ajoute une **CI GitHub Actions Maven** (`.github/workflows/maven.yml`).
- Ajoute **README-QUICKSTART.md** (build local + exemple minimal).
- Ajoute **docs/configuration.md** (mapping JSON → capabilities).
- Ajoute **docs/SECURITY.md** (gestion des secrets).
- Ajoute **CONTRIBUTING.md** (adapté à la licence BY-NC-ND).

## Pourquoi
- Faciliter l’onboarding (build + tests).
- Documenter l’usage des fichiers JSON (Android/iOS/Web, Sauce/BS/Kobiton).
- Encadrer les contributions sans remettre en cause la ligne directrice.

## Tests
- CI: build Maven + tests (`mvn test`) si présents.
- Aucun changement dans le cœur du code.
