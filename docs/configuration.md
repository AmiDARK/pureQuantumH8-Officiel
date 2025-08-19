# Configuration — mapping JSON → Capabilities

Mapping générique pour relier vos fichiers `.json` (ex: `AndroidSetup.json`, `chromeSetup.json`, `BrowserStackAndroidAPP.json`, `KobitonAndroidAPP.json`, `SauceLabsAndroidAPP.json`) aux **capabilities** Selenium/Appium et aux fermes de devices.

## Section commune
- `provider`: `"local" | "grid" | "sauce" | "browserstack" | "kobiton"`
- `remoteUrl`: URL du hub (Grid/Appium/Cloud) quand nécessaire
- `timeouts`: objet pour `implicit`, `pageLoad`, `script` (en ms)

## Web (Selenium)
- `browserName`: `"chrome" | "firefox" | "edge" | "safari"`
- `browserVersion`: version cible (optionnelle)
- `goog:chromeOptions` / `moz:firefoxOptions`: arguments/options spécifiques
- `acceptInsecureCerts`: `true|false`

## Mobile (Appium)
- `platformName`: `"Android" | "iOS"`
- `platformVersion`: ex. `"13"`
- `deviceName`: ex. `"Pixel 7"`
- `udid`: identifiant d’un device local (optionnel)
- `automationName`: `"UiAutomator2" | "XCUITest"`
- `app`: chemin du binaire (`.apk`, `.aab`, `.ipa`) ou identifiant de package
- `appPackage` / `appActivity` (Android), `bundleId` (iOS)
- `noReset`, `fullReset`

## Clouds spécifiques

### Sauce Labs
- `sauce:options`: `{ name, build, tags, ... }`
- `username` / `accessKey` **via variables d’environnement**

### BrowserStack
- `bstack:options`: `{ projectName, buildName, sessionName, ... }`
- `browserstack.user` / `browserstack.key` **via variables d’environnement**

### Kobiton
- `kobiton:options`: `{ sessionName, deviceGroup, deviceName, app }`
- `KOBITON_USERNAME` / `KOBITON_API_KEY` **via variables d’environnement**

## Bonnes pratiques
- Ne mettez **jamais** de clés dans le JSON versionné.
- Chargez les secrets via **variables d’environnement** ou **GitHub Secrets**.
- Validez le schéma JSON avant d’initialiser un driver (messages d’erreur lisibles).
