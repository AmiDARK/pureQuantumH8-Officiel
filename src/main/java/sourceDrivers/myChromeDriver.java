package sourceDrivers;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.zip.ZipException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.CapabilityType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fileTypes.aFiles;
import fileTypes.raw;
import fileTypes.zip;
import fileTypes.raw.rawFileProfile;
import fileTypes.xml.xmlProfile;
import pureQuantumH8.pureConfig;
import pureQuantumH8.pureCore;
import pureQuantumH8.pureConfig.pureCapability;
import pureQuantumH8.pureDrivers.pureDriverDetails;
import pureQuantumH8.pureErrorHandler;

public class myChromeDriver{

	// Move driver to System.getProperty("user.home") + "\pureQuantumH8\drivers\"
	private static boolean currentlyUnderUpdate = false;
	private static boolean doNotUpdate = false;
	
	// Links for Update
	private static String chromeURL = "https://chromedriver.storage.googleapis.com/";
	private static String chromeXMLDefinition = "SystemQH8/temp/chrome_def.xml";
	// private static String outputFile = "SystemQH8/temp/Chrome.ver";
	// Fichier finaux.
	private static String loadedVersion = "SystemQH8/WebDrivers/Chrome.ver";
	private static String tempdriverfile = "SystemQH8/temp/chromedriver_win32.zip";
	private static String finaldriverpath = "SystemQH8/WebDrivers/";

	
	
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
		WebDriver newDriver = null;
		System.out.println( "[pureQuantumH8] StartDriver : Chrome Web Driver" );
		ChromeOptions coptions = null;
		// ****************************************** if Capabilities are requested
		if ( myDriverDetails.driverCaps != null && myDriverDetails.driverCaps.size() > 0 ) {
			coptions = new ChromeOptions();
			HashMap<String, Object> chromePrefs = null;
			// We check all the capabilities that were entered
			
			logPrefs = new LoggingPreferences();
			logPrefs.enable( LogType.PERFORMANCE, Level.ALL );
			coptions.setCapability( CapabilityType.LOGGING_PREFS, logPrefs );
			
			for (int cLoop = 0; cLoop < myDriverDetails.driverCaps.size(); cLoop++ ) {
				pureCapability currentCaps = myDriverDetails.driverCaps.get( cLoop );
				System.out.println( "[pureQuantumH8] myChromeDriver.capabilities('" + currentCaps.capName + "','" + currentCaps.capValue + "')" );
				switch( currentCaps.capName ) {
					// ******************************************************************************************************* argument(s)
					case "argument":
						coptions.addArguments( currentCaps.capValue );
						break;
					// ******************************************************************************************************* extension(s)
					case "extension":
						if( aFiles.getPathExist( currentCaps.capValue ) == true ) {
							coptions.addExtensions( new File( currentCaps.capValue ) );
						}else {
							pureErrorHandler.castError( pureErrorHandler.Type.fileDoesNotExistException, "myChromeDriver.startDriver", currentCaps.capValue, null );						
						}
						break;
					// ******************************************************************************************************* allowInsecuresCertificates
					case "allowInsecuresCertificates":
						if ( !currentCaps.capValue.toLowerCase().equals( "true" ) && !currentCaps.capValue.toLowerCase().equals( "false" ) ) {
							pureErrorHandler.castError( pureErrorHandler.Type.ValueIsNotABooleanException, "myChromeDriver.startDriver", currentCaps.capValue, null );
						}else {
							coptions.setAcceptInsecureCerts( Boolean.parseBoolean( currentCaps.capValue ) );
						}
						break;
					// ******************************************************************************************************* uses Proxy
					case "proxy":
						myDriverDetails.proxy = new Proxy();
						myDriverDetails.proxy.setHttpProxy( currentCaps.capValue );
						coptions.setCapability( "proxy", myDriverDetails.proxy );
					// ******************************************************************************************************* Set Binary 
					case "setBinary":
						coptions.setBinary( currentCaps.capValue );
						break;
					// ******************************************************************************************************* setHeadless mode
					case "setHeadless":
						if ( !currentCaps.capValue.toLowerCase().equals( "true" ) && !currentCaps.capValue.toLowerCase().equals( "false" ) ) {
							pureErrorHandler.castError( pureErrorHandler.Type.ValueIsNotABooleanException, "myChromeDriver.startDriver", currentCaps.capValue, null );
						}else {
							coptions.setHeadless( Boolean.parseBoolean( currentCaps.capValue ) );
						}
						break;
					// ******************************************************************************************************* Accept SSL certificates
					case "ACCEPT_SSL_CERTS":
						if ( !currentCaps.capValue.toLowerCase().equals( "true" ) && !currentCaps.capValue.toLowerCase().equals( "false" ) ) {
							pureErrorHandler.castError( pureErrorHandler.Type.ValueIsNotABooleanException, "myChromeDriver.startDriver", currentCaps.capValue, null );
						}else {
							coptions.setCapability( CapabilityType.ACCEPT_SSL_CERTS, Boolean.parseBoolean( currentCaps.capValue ) );
						}
						break;
					// ******************************************************************************************************* insert Chrome Prefs
					case "chromePrefs":
						if ( aFiles.getFileExist( currentCaps.capValue ) == true ) {
							chromePrefs = loadChromePrefs( currentCaps.capValue );
							coptions.setExperimentalOption( "prefs", chromePrefs );
						}
						break;
					// ******************************************************************************************************* to avoid to get errors when reading this. 	
					case "forceVersion":
						break;
					// ******************************************************************************************************* Unknown capablity 	
					default:
						pureErrorHandler.castError( pureErrorHandler.Type.invalidCapabilityException, "myChromeDriver.startDriver",	currentCaps.capName, currentCaps.capValue );
				
				}
			}
		}
		// ****************************************** Now, we start the driver
		System.setProperty( "webdriver.chrome.driver", "SystemQH8/WebDrivers/chromedriver.exe" ); 
		if ( coptions != null ) {
			newDriver = new ChromeDriver( coptions );
		}else {
			newDriver = new ChromeDriver();
		}
		if ( newDriver != null ) {
			myDriverDetails.Started = true;
			myDriverDetails.mainDriver = newDriver;
		}

	}

	// ********************************************************************************
	/** 
	 * Description : quitDriver( pureDriverDetails ) close the specified driver
	 * 
	 * @param myDriver : the driver structure to use
	 * 
	 * @return -
	 */
	public void quitDriver( pureDriverDetails myDriverDetails ) {
		System.out.println( "quitDriver Chrome" );
		if ( myDriverDetails != null ) {
			myDriverDetails.Started = false;
			((ChromeDriver)myDriverDetails.mainDriver).quit();	
			myDriverDetails.mainDriver = null;
		}else {
			pureErrorHandler.castError( pureErrorHandler.Type.NullPointerException, "myChromeDriver.quitDriver" , "pureDriverDetails", null );
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
		if ( currentlyUnderUpdate == false ) {
			currentlyUnderUpdate = true;
		}else {
			System.out.println( "ChromeDriver is already in an update process from another Driver thread (instance)" );
			while ( currentlyUnderUpdate == true ) {
				pureCore.sleep( 250 );
			}
		}
		System.out.println( "updateDriver Chrome" );
		if ( doNotUpdate == false ) {
			try {
				// Upload the information about latest version.
				System.out.println( "************************************************ sourceDrivers System startup" );
				System.out.println( "Chrome Driver - Self updater ver 1.2" );
				String ForceVersion = pureConfig.findCapabilityByName( "forceVersion", myDriverDetails.driverCaps );
				// Open the storage current file version information
				rawFileProfile loadverFile = new raw.rawFileProfile( loadedVersion );
				Long loadedVersioninf = -1L; // fix a negative version if no version is available
				String loadedVersionSTR = null;
				if ( loadverFile.exist() == true ) {
					loadverFile.OpenToRead();
					loadedVersionSTR = loadverFile.GetString();
					loadverFile.Closefile();
				}
				if ( loadedVersionSTR != null ) {
					System.out.println( "- Current Installed Version is : " + loadedVersionSTR );
				}else {
					System.out.println( "- There is no version currently installed." );
				}
				// Télécharge le fichier XML de définition permettant de récupérer le numéro de dernià¨re version.
				FileUtils.copyURLToFile( new URL( chromeURL ), new File( chromeXMLDefinition ) );
				// Ouvre le fichier XML télécharger
				xmlProfile myXml = new xmlProfile( chromeXMLDefinition );  // Crée le profil de gestion du fichier.
				myXml.load();                                              // Charge le fichier xml en mémoire
				int size = myXml.getItemAmount( "Contents" );
				boolean forceUpdate = false;
				String versionSTR = "";
				String filePathAndName = null;
				if ( size > 0 ) {
					NodeList myItems = myXml.getItems( "Contents" );
					for ( int iLoop = myItems.getLength()-1; iLoop > 0 ; iLoop-- ) {
						Node currentNode = myItems.item( iLoop );
						if ( currentNode.hasChildNodes() == true ) {
							NodeList cNodeItems = currentNode.getChildNodes();
							if ( cNodeItems.getLength() > 0 ) {
								for ( int cLoop = 0; cLoop < cNodeItems.getLength(); cLoop++ ) {
									Node cNodeItem =  cNodeItems.item( cLoop );
									if ( cNodeItem.getNodeName().equals( "Key" ) ) {
										filePathAndName = cNodeItem.getTextContent();
										// System.out.println( "Current Node Key =" + filePathAndName );
										if ( filePathAndName.contains( "/chromedriver_win32.zip" ) ) {
											versionSTR = filePathAndName.substring( 0,  filePathAndName.indexOf( "/" ) );
											if ( loadedVersionSTR.equals( versionSTR ) == false ) {
												if ( ForceVersion!= null && versionSTR.equals( ForceVersion ) && loadedVersionSTR.equals( ForceVersion ) == false ) {
													System.out.println( "Force Update !" );
													forceUpdate = true;
													// iLoop = 0; // Force loop to finish/
													break;
												}
											}
										}
									}
								}
								if ( forceUpdate == true ) { break; }
							}
						}
					}
					// si le forceUpdate est activé on procède alors à  la mise à  jour.
					System.out.println( "- Latest detected Release is Version " + versionSTR );
					if ( forceUpdate == true ) {
						System.out.println( "- Downloading latest Chrome WebDriver release." );
						FileUtils.copyURLToFile( new URL( chromeURL + filePathAndName ), new File( tempdriverfile ) );
						System.out.println( "- Unzipping latest chrome Webdriver to install it." );
						zip.unZip( tempdriverfile, finaldriverpath );
						System.out.println( "- Update version information file." );
						rawFileProfile versionFile = new rawFileProfile( loadedVersion );
						aFiles.DeleteFile( loadedVersion );
						versionFile.OpenToWrite();
						versionFile.PutString( versionSTR );
						versionFile.Closefile();
						System.out.println( "- Clean up temporary folder." );
						FileUtils.deleteQuietly( new File( tempdriverfile ) );
						System.out.println( "Chrome webDriver update completed successfully");
					}else {
						System.out.println("- Driver is already up to date." );
					}
				}
			}catch( ZipException e){
				System.out.println( "Chrome webDriver - Self updater ERROR : Problem occured when trying to unzip chromedriver_win32.zip file.");
			}catch( Exception e ) {
				System.out.println( "Chrome webDriver - Self updater ERROR : Problem occured during Chrome Driver update : " + e.getMessage() + " : " + e.getCause().getLocalizedMessage() );
			}
			System.out.println( " " );
			doNotUpdate = true;
			currentlyUnderUpdate = false;
		}
	}

	// ********************************************************************************
	/** 
	 * Description : quitDriver( pureDriverDetails ) close the specified driver
	 * 
	 * @param myDriver : the driver structure to use
	 * 
	 * @return -
	 */

	
	
	// ********************************************************************************
	/** 
	 * Description : loadChromePrefs( pureDriverDetails ) close the specified driver
	 * 
	 * @param myDriver : the driver structure to use
	 * 
	 * @return -
	 */
	public static HashMap<String, Object> loadChromePrefs( String FileIO ){
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		List<pureCapability> prefsLoaded = pureConfig.loadJsonDataFromFile( FileIO, "chromePrefs" );
		if ( prefsLoaded.size() > 0 ) {
			for( int pLoop = 0; pLoop < prefsLoaded.size(); pLoop++ ) {
				pureCapability newPrefs = prefsLoaded.get( pLoop );
				System.out.println( "[pureQuantumH8] myChromeDriver.chromePrefs('" + newPrefs.capName + "','" + newPrefs.capValue + "')" );
				chromePrefs.put( newPrefs.capName, newPrefs.capValue );
			}
		}
		
		return chromePrefs;
	}

	public static Rectangle GetDisplayArea( pureDriverDetails myDriver )
	  {
	  	JavascriptExecutor executor = (JavascriptExecutor)myDriver.mainDriver;
	    int xmin = Math.toIntExact( (Long)executor.executeScript("return window.pageXOffset;") );
	    int ymin = Math.toIntExact( (Long)executor.executeScript("return window.pageYOffset;") );

	  	// JavascriptExecutor executorBis = (JavascriptExecutor)myDriver.mainDriver;
	  	Point myViewSize = new Point( Math.toIntExact( (long) executor.executeScript("return window.innerWidth;" ) ),
	                                  Math.toIntExact( (long) executor.executeScript("return window.innerHeight;" ) ) );
	    Rectangle myRectangle = new Rectangle( xmin, ymin, myViewSize.x, myViewSize.y );
	    return myRectangle;
	   }

	// Return the Web browser view area
/*	public Rectangle GetDisplayArea( pureDriverDetails myDriver ){
		@SuppressWarnings("rawtypes")
		Dimension adSize = ((AndroidDriver)myDriver.mainDriver).manage().window().getSize();
	  	Rectangle wdRectangle = new Rectangle( (int)0, (int)0, (int)adSize.getWidth(), (int)adSize.getHeight() );
	  	return wdRectangle;
   } */

	private String pageDetails = null;           // the Page DOM
	private int pageSize = -1;                   // The page size in bytes
	private Long LastLoadedTime = 0L;            // Last time (clock) the page was updated
	
	public boolean isPageUpdated( pureDriverDetails myDriver ) {
		boolean isUpdated = false;
		Logs newLogs = ( (ChromeDriver)myDriver.mainDriver ).manage().logs();
		// System.out.println( newLogs.getAvailableLogTypes() );
		List<LogEntry> allNewLogs = newLogs.get( LogType.DRIVER ).getAll();
		if ( allNewLogs.size() > 0 ) {
			for( int nlLoop = 0; nlLoop < allNewLogs.size(); nlLoop++ ) {
				System.out.println( "Log#" + String.valueOf( nlLoop ) + " : " + allNewLogs.get( nlLoop ) );
			}
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



