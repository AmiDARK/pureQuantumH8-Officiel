# Gestion des secrets et sécurité

- Ne stockez pas de credentials dans le dépôt (ni en clair, ni chiffrés).
- Utilisez des **variables d’environnement** locales et des **GitHub Secrets** pour la CI.
- `.gitignore` : excluez `*.apk`, `*.aab`, `*.ipa`, `*.keystore`, `*.p12`.
- Limitez les logs CI pour éviter d’exposer des URLs signées ou des tokens.
