package sourceDrivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.net.URL;
import java.util.logging.Level;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import fileTypes.aFiles;
import pureQuantumH8.pureDrivers.pureDriverDetails;
import pureQuantumH8.pureCore;
import pureQuantumH8.pureErrorHandler;
import pureQuantumH8.pureConfig.pureCapability;


public class myAndroidDriver {

	// Move driver to System.getProperty("user.home") + "\pureQuantumH8\drivers\"

	// The three commands sets are based on informations available on : http://appium.io/docs/en/writing-running-appium/caps/
	private static String[] directCommands = {
			"automationName", "platformName", "platformVersion", "deviceName", "app", "browserName", "language", "locale",
			"udid", "orientation", "appActivity", "appPackage", "appWaitActivity", "appWaitPackage", "androidCoverage",
			"androidCoverageEndIntent", "androidInstallPath", "remoteAdbHost", "androidDeviceSocket", "avd", "avdArgs",
			"keystorePath", "keystorePassword", "keyAlias", "keyPassword", "chromedriverExecutable", "chromedriverArgs",
			"chromedriverExecutableDir", "chromedriverChromeMappingFile", "chromedriverPorts", "intentAction", "intentCategory",
			"intentFlags", "optionalIntentArguments", "chromeOptions", "androidScreenshotPath", "networkSpeed", "localeScript",
			"unlockType", "uninstallOtherPackages",
			// UIAutomator 1 & 2 Commands :
			"otherApps",
	};
	private static String[] castToIntegerCommands = { 
			"newCommandTimeout", "appWaitDuration", "deviceReadyTimeout", "androidDeviceReadyTimeout", "androidInstallTimeout",
			"adbPort", "systemPort", "avdLaunchTimeout", "avdReadyTimeout", "autoWebviewTimeout", "chromedriverPort", "adbExecTimeout",
			"unlockKey",
			// UIAutomator 2 Commands :
			"uiautomator2ServerLaunchTimeout", "uiautomator2ServerInstallTimeout",
			// Espresso only command :
			"espressoServerLaunchTimeout"
	};
	private static String[] castToBooleanCommands = {
			"autoWebview", "noReset", "fullReset", "eventTimings", "enablePerformanceLogging", "printPageSourceOnFindFailure",
			"allowTestPackages", "useKeystore", "chromedriverUseSystemExecutable", "dontStopAppOnReset", "unicodeKeyboard",
			"resetKeyboard", "noSign", "ignoreUnimportantViews", "disableAndroidWatchers", "recreateChromeDriverSessions",
			"nativeWebScreenshot", "autoGrantPermissions", "gpsEnabled", "isHeadless", "skipDeviceInitialization",
			"chromedriverDisableBuildCheck", "skipUnlock", "autoLaunch", "skipLogcatCapture", "disableWindowAnimation",
			// UIAutomator 2 Commands :
			"skipServerInstallation"
	};
	// ********************************************************************************
	/** 
	 * Description : startDriver( pureDriverDetails ) open the driver configured inside pureDriver
	 * 
	 * @param myDriver : the driver structure to use to open new driver
	 * 
	 * @return -
	 */
	static LoggingPreferences logPrefs = null;
	public static void startDriver( pureDriverDetails myDriverDetails ) {
		if( myDriverDetails != null ) {
			// Create Android Driver & Appium Driver Capabilities
			boolean commandFound = false;
			DesiredCapabilities androidCaps = null;
			DesiredCapabilities appiumCaps = null;
			if ( myDriverDetails.driverCaps != null && myDriverDetails.driverCaps.size() > 0 ) {
				androidCaps = new DesiredCapabilities();
				appiumCaps = new DesiredCapabilities();
				logPrefs = new LoggingPreferences();
				logPrefs.enable( LogType.PERFORMANCE, Level.ALL );
				androidCaps.setCapability( CapabilityType.LOGGING_PREFS, logPrefs );
				for (int cLoop = 0; cLoop < myDriverDetails.driverCaps.size(); cLoop++ ) {
					pureCapability currentCaps = myDriverDetails.driverCaps.get( cLoop );
					System.out.println( "[pureQuantumH8] myAndroidDriver.capabilities('" + currentCaps.capName + "'='" + currentCaps.capValue + "')" );
					commandFound = false;
					// Check for direct commands
					for( int dcLoop = 0; dcLoop < directCommands.length; dcLoop++ ) {
						if ( currentCaps.capName.equals( directCommands[ dcLoop ] ) ) {
							androidCaps.setCapability( currentCaps.capName, currentCaps.capValue );
							appiumCaps.setCapability( currentCaps.capName, currentCaps.capValue );
							commandFound = true; break;
						}
					}
					// Check for String->Integer value commands
					if ( commandFound == false ) {
						for( int ctiLoop = 0; ctiLoop < castToIntegerCommands.length; ctiLoop++ ) {
							if( currentCaps.capName.equals( castToIntegerCommands[ ctiLoop ] ) ) {
								androidCaps.setCapability( currentCaps.capName, Integer.parseInt( currentCaps.capValue ) );
								appiumCaps.setCapability( currentCaps.capName, Integer.parseInt( currentCaps.capValue ) );
								commandFound = true; break;
								
							}
						}
					}
					// Check for String->boolean value commands
					if ( commandFound == false ) {
						for( int ctiLoop = 0; ctiLoop < castToBooleanCommands.length; ctiLoop++ ) {
							if( currentCaps.capName.equals( castToBooleanCommands[ ctiLoop ] ) ) {
								androidCaps.setCapability( currentCaps.capName, currentCaps.capValue.toLowerCase().equals( "true" ) );
								appiumCaps.setCapability( currentCaps.capName, currentCaps.capValue.toLowerCase().equals( "true" ) );
								commandFound = true; break;
							}
						}
					}	
				}				
			}
			// Now we start the Appium Server
			startAppium( myDriverDetails, appiumCaps );
			String driverURL = "http://" + myDriverDetails.appiumIPAdress + ":" + String.valueOf( myDriverDetails.appiumPort ) + "/wd/hub";
			try {
				myDriverDetails.mainDriver = new AndroidDriver<>( new URL( driverURL ), androidCaps );
				myDriverDetails.Started = true;
			}catch( Exception except ) {
				pureErrorHandler.castError( pureErrorHandler.Type.MalformedURLException, null, driverURL, null );
			}
		}else {
			pureErrorHandler.castError( pureErrorHandler.Type.NullPointerException, null, "pureDriverDetails", null );
		}
	}
	
	private static void startAppium( pureDriverDetails myDriverDetails, DesiredCapabilities appiumCaps ) {
		String nodeMainJS = null;
		if ( SystemUtils.IS_OS_WINDOWS ) {
			if ( aFiles.getFileExist( System.getProperty("user.home") + "/node_modules/appium/build/lib/main.js" ) == true ) {
				nodeMainJS = System.getProperty("user.home") + "/node_modules/appium/build/lib/main.js";
			}else if( aFiles.getFileExist( System.getProperty("user.home") + "/AppData/Roaming/npm/node_modules/appium/build/lib/main.js" ) == true ) {
				nodeMainJS = System.getProperty("user.home") + "/AppData/Roaming/npm/node_modules/appium/build/lib/main.js";
			}
		} else if( SystemUtils.IS_OS_MAC_OSX ) {
			nodeMainJS = "/usr/local/lib/node_modules/appium/build/lib/main.js";				// MAC OS
		} else { // Should works on JENKINS/LINUX
			if ( aFiles.getFileExist( System.getProperty("user.home") + "/node_modules/appium/build/lib/main.js" ) == true ) {
				nodeMainJS = System.getProperty("user.home") + "/node_modules/appium/build/lib/main.js";
			}else if( aFiles.getFileExist( System.getProperty("user.home") + "/AppData/Roaming/npm/node_modules/appium/build/lib/main.js" ) == true ) {
				nodeMainJS = System.getProperty("user.home") + "/AppData/Roaming/npm/node_modules/appium/build/lib/main.js";
			}
		}
		if ( nodeMainJS != null ) {
			myDriverDetails.appiumBuilder = new AppiumServiceBuilder().withAppiumJS( new File( nodeMainJS ) );
			myDriverDetails.appiumBuilder.withCapabilities( appiumCaps );
			myDriverDetails.appiumBuilder.withIPAddress( myDriverDetails.appiumIPAdress );
			myDriverDetails.appiumBuilder.usingPort( myDriverDetails.appiumPort );
			if ( myDriverDetails.appiumOverrideSession == true ) {
				myDriverDetails.appiumBuilder.withArgument( GeneralServerFlag.SESSION_OVERRIDE );
			}
			myDriverDetails.appiumBuilder.withArgument( GeneralServerFlag.LOG_LEVEL, myDriverDetails.appiumLogLevel );
		}
		// Now we start the Appium Service
		myDriverDetails.appiumService = AppiumDriverLocalService.buildService( myDriverDetails.appiumBuilder );
		myDriverDetails.appiumService.start();
	}
	
	// ********************************************************************************
	/** 
	 * Description : quitDriver( pureDriverDetails ) close the specified driver
	 * 
	 * @param myDriver : the driver structure to use
	 * 
	 * @return -
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void quitDriver( pureDriverDetails myDriverDetails ) {
		System.out.println( "quitDriver Android" );
		if ( myDriverDetails != null ) {
			AndroidDriver<AndroidElement> myDriver = (AndroidDriver<AndroidElement>)myDriverDetails.mainDriver;
			myDriver.closeApp();
			myDriver.removeApp( (String) myDriver.getCapabilities().getCapability( "appPackage" ) );
			( ( AndroidDriver ) myDriverDetails.mainDriver ).quit();	
			myDriverDetails.mainDriver = null;
			myDriverDetails.Started = false;
			// Once the driver was closed, we stop the service if available
			if( myDriverDetails.appiumService != null ) {
				myDriverDetails.appiumService.stop();
				while( myDriverDetails.appiumService.isRunning() == true ) {
					pureCore.sleep( 250 );
				}
				myDriverDetails.appiumService = null;
			}
		}else {
			pureErrorHandler.castError( pureErrorHandler.Type.NullPointerException, "myAndroidDriver.quitDriver" , "pureDriverDetails", null );
		}
	}

	// ********************************************************************************
	/** 
	 * Description : updateDriver( pureDriverDetails ) try to see if a newer version of the driver is available (or not).
	 * 
	 * @param myDriver : the driver structure to use
	 * 
	 * @return -
	 */
	 public static void updateDriver( pureDriverDetails myDriverDetails ) {
		 // AndroidDriver does not need update.
	 }

	// Return the Web browser view area
	public Rectangle GetDisplayArea( pureDriverDetails myDriver ){
		@SuppressWarnings("rawtypes")
		Dimension adSize = ((AndroidDriver)myDriver.mainDriver).manage().window().getSize();
	  	Rectangle wdRectangle = new Rectangle( (int)0, (int)0, (int)adSize.getWidth(), (int)adSize.getHeight() );
	  	return wdRectangle;
   }

	private String pageDetails = null;           // the Page DOM
	private int pageSize = -1;                   // The page size in bytes
	private Long LastLoadedTime = 0L;            // Last time (clock) the page was updated
	
	@SuppressWarnings("rawtypes") // Because AndroidDriver is a RAW Type object.
	public boolean isPageUpdated( pureDriverDetails myDriver ) {
		boolean isUpdated = false;
		String newPageDetails = ((AndroidDriver)myDriver.mainDriver).getPageSource();
		int newPageSize = newPageDetails.length();
		if ( pageSize != newPageSize ) {
			isUpdated = true;
			pageSize = newPageSize;
			pageDetails = newPageDetails;
			LastLoadedTime = System.currentTimeMillis();
		}
		return isUpdated;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public Long getLastLoadingTime() {
		return LastLoadedTime;
	}

	public String getLastDOMDetails() {
		return pageDetails;
	}
	
}
