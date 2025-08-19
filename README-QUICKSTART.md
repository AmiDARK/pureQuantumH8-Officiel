# Quickstart — pureQuantumH8

Ce guide propose un démarrage rapide (build local + exécution minimale) sans modifier le cœur du projet.

## Prérequis
- **Java 17** (ou 11+ si votre `pom.xml` l’indique)
- **Maven 3.8+**
- Drivers locaux si nécessaires (ChromeDriver/GeckoDriver), ou une ferme de devices (Sauce Labs, BrowserStack, Kobiton).

## Cloner et builder
```bash
git clone https://github.com/AmiDARK/pureQuantumH8-Officiel.git
cd pureQuantumH8-Officiel
mvn -q -DskipTests package
```

## Lancer des tests unitaires (s’ils existent)
```bash
mvn test
```

## Exemple minimal (pseudo-code)
```java
// Exemple conceptuel — à intégrer dans vos tests ou un module demo
import java.nio.file.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class DemoStart {
    public static void main(String[] args) throws Exception {
        String json = Files.readString(Path.of("chromeSetup.json"));
        Map cfg = new ObjectMapper().readValue(json, Map.class);

        // 1) Provider (local / grid / sauce / browserstack / kobiton)
        String provider = (String) cfg.getOrDefault("provider", "local");

        // 2) Construire les capabilities à partir du JSON
        //    Appium: platformName, deviceName, app, automationName...
        //    Selenium: browserName, browserVersion, "goog:chromeOptions"...

        // 3) Initialiser le driver (exemples conceptuels)
        //    WebDriver driver = new ChromeDriver(options);
        //    AppiumDriver<?> mobile = new AndroidDriver<>(new URL(remoteUrl), caps);

        // 4) Utiliser le driver
        //    driver.get("https://example.org");
        //    driver.quit();
    }
}
```
