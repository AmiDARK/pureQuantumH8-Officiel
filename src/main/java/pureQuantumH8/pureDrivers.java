package pureQuantumH8;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;

import fileTypes.aFiles;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import pureQuantumH8.pureConfig.pureCapability;

//**************************************************************************************************************************
/** Description : Here is the main pureDrivers class.
*/
public class pureDrivers{
	
	//**************************************************************************************************************************
	/** Description : a main protortoz to avoid running time
	*/
	public static void main( String[] args ){
        System.out.println( "[pureQuantumH8] This file is not an executable" );
    }
	
	//**************************************************************************************************************************
	/** Description : Here are all the data used by the structure pureDriverDetails
	*/
	
	// ******************************************************* Datas for the default original Selenium WebDrivers (chrome/firefox/ie/edge/opera)
	private static String WebDriverChromeClass                = "org.openqa.selenium.chrome.ChromeDriver";
	private static String WebDriverFirefoxClass               = "org.openqa.selenium.firefox.FirefoxDriver";
	private static String WebDriverIEClass                    = "org.openqa.selenium.ie.InternetExplorerDriver";
	private static String WebDriverEdgeClass                  = "org.openqa.selenium.edge.EdgeDriver";
	private static String WebDriverOperaClass                 = "org.openqa.selenium.opera.OperaDriver";
	// ******************************************************* Datas for the default original Appium mobile drivers (Android/IOS)
	private static String AndroidDriverClass                  = "io.appium.java_client.android.AndroidDriver";
//	private static String AndroidDriverBrowserStackClass      = "io.appium.java_client.android.AndroidDriver";
//	private static String AndroidDriverSauceLabsClass         = "io.appium.java_client.android.AndroidDriver";
	private static String IOSDriverClass                      = "io.appium.java_client.ios.IOSDriver";
	// ******************************************************* Datas for the default classes to setup WebDrivers (chrome/firefox/ie/edge/opera)
	private static String WebDriverSetupChromeClass           = "sourceDrivers.myChromeDriver";
	private static String WebDriverSetupFirefoxClass          = "sourceDrivers.myFirefoxDriver";
	private static String WebDriverSetupIEClass               = "sourceDrivers.myIEDriver";
	private static String WebDriverSetupEdgeClass             = "sourceDrivers.myEdgeDriver";
	private static String WebDriverSetupOperaClass            = "sourceDrivers.myOperaDriver";
	// ******************************************************* Datas for the default classes to setup drivers (Android/IOS)
	private static String AndroidDriverSetupClass             = "sourceDrivers.myAndroidDriver";
	private static String AndroidDriverSetupBrowserStackClass = "sourceDrivers.myBrowserStackAndroidDriver";
	private static String AndroidDriverSetupSauceLabsClass    = "sourceDrivers.mySauceLabsAndroidDriver";
	private static String AndroidDriverSetupKobitonClass     = "sourceDrivers.myKobitonAndroidDriver";
	private static String IOSDriverSetupClass                 = "sourceDrivers.myIOSDriver";

	// ******************************************************* Data for the default classes used by : Elements objects
	private static String WebelementClass                     = "org.openqa.selenium.WebElement";
	private static String AndroidElementClass                 = "io.appium.java_client.android.AndroidElement";
	private static String IOSElementClass                     = "io.appium.java_client.ios.IOSElement";

	// ******************************************************* List containing all the created drivers
	private static List<pureDriverDetails> allPureDrivers = new ArrayList<pureDriverDetails>();
	// ******************************************************* Ths ID of the current driver to use
	private static int currentPureDriver = -1;
	
	
	//**************************************************************************************************************************
	/** Description : CONSTRUCTOR to Create a new pureDriver and start it
	*
	* @param driverName         : ( chrome, firefox, ie, edge, opera, android, ios ) 
	* @param capabilities       : list of capabilities requested for the driver
	*
	* @return pureDriverDetails : the pureDrivers definition datas
	* 
	*/
	public static pureDriverDetails newPureDriver( String driverName, List<pureCapability> capabilities ) {
		pureDriverDetails newDriverDetails = new pureDriverDetails( driverName, capabilities, false, null );
		allPureDrivers.add( newDriverDetails );
		currentPureDriver = allPureDrivers.size() - 1;
		return newDriverDetails;
	}
	
	//**************************************************************************************************************************
	/** Description : CONSTRUCTOR to Create a new pureDriver
	*
	* @param driverName         : ( chrome, firefox, ie, edge, opera, android, ios ) 
	* @param capabilities       : list of capabilities requested for the driver
	* @param autoStart          : =true for direct start, =false for no direct start
	*
	* @return pureDriverDetails : the pureDrivers definition datas
	* 
	*/
	public static pureDriverDetails newPureDriver( String driverName, List<pureCapability> capabilities, boolean autoStart ) {
		pureDriverDetails newDriverDetails = new pureDriverDetails( driverName, capabilities, autoStart, null );
		allPureDrivers.add( newDriverDetails );
		currentPureDriver = allPureDrivers.size() - 1;
		return newDriverDetails;
	}
	
	//**************************************************************************************************************************
	/** Description : CONSTRUCTOR to Create a new pureDriver
	*
	* @param driverName         : ( chrome, firefox, ie, edge, opera, android, ios ) 
	* @param capabilities       : list of capabilities requested for the driver
	* @param autoStart          : =true for direct start, =false for no direct start
	* @param fictiveName        : give a fictive name for the driver (for example : 'driver to check emails' or 'main app view')
	*
	* @return pureDriverDetails : the pureDrivers definition datas
	* 
	*/
	public static pureDriverDetails newPureDriver( String driverName, List<pureCapability> capabilities, boolean autoStart, String fictiveName ) {
		pureDriverDetails newDriverDetails = new pureDriverDetails( driverName, capabilities, autoStart, fictiveName );
		allPureDrivers.add( newDriverDetails );
		currentPureDriver = allPureDrivers.size() - 1;
		return newDriverDetails;
	}
	
	//**************************************************************************************************************************
	/** Description : Change the current driver
	*
	* @param driverID           : the ID of the driver to user
	*                 
	*/
	public static void setCurrentDriver( int driverID ) {
		if ( driverID > -1 && driverID < allPureDrivers.size() ) {
			currentPureDriver = driverID;
		}else{
			pureErrorHandler.castError( -1, "[PureQuantumH8] setCurrentDriver cannot set driver %CONTENT1% because it does not exists", String.valueOf( driverID ), null );
		}
	}

		//**************************************************************************************************************************
		/** Description : Close all windows of the current driver and quit it.
		*/
	public static void pureDriverQuit() {
		if ( currentPureDriver > -1 ) {
			pureDriverDetails driverToQuit = allPureDrivers.get( currentPureDriver );
			driverToQuit.quit();
			currentPureDriver = -1;
		}
	}
	
	public static pureDriverDetails getDriverDetails( int iD ) {
		pureDriverDetails driverDetails = null;
		if ( iD > -1 ) {
			driverDetails = allPureDrivers.get( iD );
			if ( driverDetails.startDriverThread != null ) {
				while( driverDetails.startDriverThread.isAlive() == true ) {
					pureCore.sleep( 250 );
				}
			}
		}
		return driverDetails;
	}

	public static pureDriverDetails getCurrentDriverDetails() {
		pureDriverDetails currentDriver = null;
		if ( currentPureDriver > -1 ) {
			currentDriver = allPureDrivers.get( currentPureDriver );
			if ( currentDriver.startDriverThread != null ) {
				while( currentDriver.startDriverThread.isAlive() == true ) {
					pureCore.sleep( 250 );
				}
				currentDriver.startDriverThread = null;
			}
		}
		return currentDriver;
	}
	
	public static int getDriverDetailsID( pureDriverDetails driverToFind ) {
		int dID = -1;
		if ( allPureDrivers.size() > 0 ) {
			for( int dLoop = 0; dLoop < allPureDrivers.size(); dLoop++ ) {
				if ( allPureDrivers.get( dLoop ) == driverToFind ) {
					dID = dLoop; break;
				}
			}
		}
		return dID;
	}
	
	
	//**************************************************************************************************************************
	/** Description : get the area view of the current driver
	 * 
	 * @return : Return a rectangle composed of x,y, width, height
	*/
	public static Rectangle GetDisplayArea() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (Rectangle)pureCore.callMethod( currentDriver.driverInstance, currentDriver.driverSetupClass, "GetDisplayArea", pureDriverDetails.class, "pureDrivers.GetDisplayArea", currentDriver );
	}

	public static Boolean isPageUpdated() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (Boolean)pureCore.callMethod( currentDriver.driverInstance, currentDriver.driverSetupClass, "isPageUpdated", pureDriverDetails.class, "pureDrivers.isPageUpdated", currentDriver );
	}

	//**************************************************************************************************************************
	/** Description : Wait until a chosen driver available in the list, finished it's startup and is then available for use in tests.
	 * 
	*/
	public static void waitUntilDriverIsReady( pureDriverDetails driversDetails ) {
		if ( driversDetails.startDriverThread != null ) {
			while( driversDetails.startDriverThread.isAlive() == true ) {
				pureCore.sleep( 250 );
			}
			driversDetails.startDriverThread = null;
		}
	}
	
	//**************************************************************************************************************************
	/** Description : Wait until all the drivers available in the list, finished their startup and are available for tests.
	 * 
	*/
	public static void waitUntilDriversAreReady(  pureDriverDetails[] driverDetails ) {
		if ( driverDetails != null && driverDetails.length > 0 ) {
			boolean success = false;
			int Amount = 0;
			do{
				Amount = 0;
				for( int dLoop = 0; dLoop < allPureDrivers.size(); dLoop++ ) {
					if ( allPureDrivers.get( dLoop ).startDriverThread.isAlive() == false ) {
						Amount++;
					}
				}
				if ( Amount == allPureDrivers.size() ) {
					success = true;
				} else {
					pureCore.sleep( 250 );
				}
			}while( success == false );
		}
		
	}

	
	
	// ************************************************************************************************************************ get
	// WebDriver               [0]  = public abstract void org.openqa.selenium.WebDriver.get(java.lang.String)
	// ChromeDriver            [9]  = public void org.openqa.selenium.remote.RemoteWebDriver.get(java.lang.String)
	// FireFoxDriver           [3]  = public void org.openqa.selenium.remote.RemoteWebDriver.get(java.lang.String)
	// InternetExplorerDriver  [2]  = public void org.openqa.selenium.remote.RemoteWebDriver.get(java.lang.String)
	// EdgeDriver              [0]  = public void org.openqa.selenium.remote.RemoteWebDriver.get(java.lang.String)
	// OperaDriver             [5]  = public void org.openqa.selenium.remote.RemoteWebDriver.get(java.lang.String)
	// SafariDriver            [1]  = public void org.openqa.selenium.remote.RemoteWebDriver.get(java.lang.String)
	// AndroidDriver           [44] = public void org.openqa.selenium.remote.RemoteWebDriver.get(java.lang.String)
	public static void get( String URL ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "get", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), URL );
	}
	
	// ************************************************************************************************************************ close
	// WebDriver               [1]  = public abstract void org.openqa.selenium.WebDriver.close()
	// ChromeDriver            [11] = public void org.openqa.selenium.remote.RemoteWebDriver.close()
	// FireFoxDriver           [5]  = public void org.openqa.selenium.remote.RemoteWebDriver.close()
	// InternetExplorerDriver  [4]  = public void org.openqa.selenium.remote.RemoteWebDriver.close()
	// EdgeDriver              [2]  = public void org.openqa.selenium.remote.RemoteWebDriver.close()
	// OperaDriver             [7]  = public void org.openqa.selenium.remote.RemoteWebDriver.close()
	// SafariDriver            [3]  = public void org.openqa.selenium.remote.RemoteWebDriver.close()
	// AndroidDriver		   [45] = public void org.openqa.selenium.remote.RemoteWebDriver.close()
	public static void close() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "close", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ************************************************************************************************************************ quit
	// WebDriver               [2]  = public abstract void org.openqa.selenium.WebDriver.quit()
	// ChromeDriver            [13] = public void org.openqa.selenium.remote.RemoteWebDriver.quit()
	// FireFoxDriver           [6]  = public void org.openqa.selenium.remote.RemoteWebDriver.quit()
	// InternetExplorerDriver  [5]  = public void org.openqa.selenium.remote.RemoteWebDriver.quit()
	// EdgeDriver              [3]  = public void org.openqa.selenium.remote.RemoteWebDriver.quit()
	// OperaDriver             [8]  = public void org.openqa.selenium.remote.RemoteWebDriver.quit()
	// SafariDriver            [4]  = public void org.openqa.selenium.remote.RemoteWebDriver.quit()
	// AndroidDriver		   [46] = public void org.openqa.selenium.remote.RemoteWebDriver.quit()
	// quit not wrapped as it call the driverSetup->quit method
	public static void quit() {
		pureDriverQuit();
	}
	
	// ************************************************************************************************************************ getCurrentUrl
	// WebDriver               [3]  = public abstract java.lang.String org.openqa.selenium.WebDriver.getCurrentUrl()
	// ChromeDriver            [14] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getCurrentUrl()
	// FireFoxDriver           [7]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getCurrentUrl()
	// InternetExplorerDriver  [6]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getCurrentUrl()
	// EdgeDriver              [4]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getCurrentUrl()
	// OperaDriver             [9]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getCurrentUrl()
	// SafariDriver            [5]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getCurrentUrl()
	// AndroidDriver		   [49] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getCurrentUrl()
	public static String getCurrentUrl() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (String)pureCore.callMethod( currentDriver.mainDriver,  currentDriver.mainDriver.getClass(), "getCurrentUrl", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ************************************************************************************************************************ getTitle
	// WebDriver               [4]  = public abstract java.lang.String org.openqa.selenium.WebDriver.getTitle()
	// ChromeDriver            [15] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getTitle()
	// FireFoxDriver           [8]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getTitle()
	// InternetExplorerDriver  [7]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getTitle()
	// EdgeDriver              [5]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getTitle()
	// OperaDriver             [10] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getTitle()
	// SafariDriver            [6]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getTitle()
	// AndroidDriver		   [50] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getTitle()
	public static String getTitle() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (String)pureCore.callMethod( currentDriver.mainDriver,  currentDriver.mainDriver.getClass(), "getTitle", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ getPageSource
	// WebDriver               [5]  = public abstract java.lang.String org.openqa.selenium.WebDriver.getPageSource()
	// ChromeDriver            [18] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getPageSource()
	// FireFoxDriver           [9]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getPageSource()
	// InternetExplorerDriver  [8]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getPageSource()
	// EdgeDriver              [6]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getPageSource()
	// OperaDriver             [11] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getPageSource()
	// SafariDriver            [7]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getPageSource()
	// AndroidDriver		   [51] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getPageSource()
	public static Set<?> getPageSource() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (Set<?>)pureCore.callMethod( currentDriver.mainDriver,  currentDriver.mainDriver.getClass(), "getPageSource", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ getWindowHandles
	// WebDriver               [6]  = public abstract java.util.Set org.openqa.selenium.WebDriver.getWindowHandles()
	// ChromeDriver            [19] = public java.util.Set org.openqa.selenium.remote.RemoteWebDriver.getWindowHandles()
	// FireFoxDriver           [10] = public java.util.Set org.openqa.selenium.remote.RemoteWebDriver.getWindowHandles()
	// InternetExplorerDriver  [9]  = public java.util.Set org.openqa.selenium.remote.RemoteWebDriver.getWindowHandles()
	// EdgeDriver              [7]  = public java.util.Set org.openqa.selenium.remote.RemoteWebDriver.getWindowHandles()
	// OperaDriver             [12] = public java.util.Set org.openqa.selenium.remote.RemoteWebDriver.getWindowHandles()
	// SafariDriver            [8]  = public java.util.Set org.openqa.selenium.remote.RemoteWebDriver.getWindowHandles()
	// AndroidDriver		   [52] = public java.util.Set<java.lang.String> org.openqa.selenium.remote.RemoteWebDriver.getWindowHandles()
	public static Set<?> getWindowHandles() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (Set<?>)pureCore.callMethod( currentDriver.mainDriver,  currentDriver.mainDriver.getClass(), "getWindowHandles", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ getWindowHandle
	// WebDriver               [7]  = public abstract java.lang.String org.openqa.selenium.WebDriver.getWindowHandle()
	// ChromeDriver            [20] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getWindowHandle()
	// FireFoxDriver           [11] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getWindowHandle()
	// InternetExplorerDriver  [10] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getWindowHandle()
	// EdgeDriver              [8]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getWindowHandle()
	// OperaDriver             [13] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getWindowHandle()
	// SafariDriver            [9]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getWindowHandle()
	// AndroidDriver		   [53] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.getWindowHandle()
	public static String getWindowHandle() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (String)pureCore.callMethod( currentDriver.mainDriver,  currentDriver.mainDriver.getClass(), "getWindowHandle", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ switchTo
	// WebDriver               [8]  = public abstract org.openqa.selenium.WebDriver$TargetLocator org.openqa.selenium.WebDriver.switchTo()
	// ChromeDriver            [21] = public org.openqa.selenium.WebDriver$TargetLocator org.openqa.selenium.remote.RemoteWebDriver.switchTo()
	// FireFoxDriver           [12] = public org.openqa.selenium.WebDriver$TargetLocator org.openqa.selenium.remote.RemoteWebDriver.switchTo()
	// InternetExplorerDriver  [11] = public org.openqa.selenium.WebDriver$TargetLocator org.openqa.selenium.remote.RemoteWebDriver.switchTo()
	// EdgeDriver              [9]  = public org.openqa.selenium.WebDriver$TargetLocator org.openqa.selenium.remote.RemoteWebDriver.switchTo()
	// OperaDriver             [14] = public org.openqa.selenium.WebDriver$TargetLocator org.openqa.selenium.remote.RemoteWebDriver.switchTo()
	// SafariDriver            [10] = public org.openqa.selenium.WebDriver$TargetLocator org.openqa.selenium.remote.RemoteWebDriver.switchTo()
	// AndroidDriver		   [54] = public org.openqa.selenium.WebDriver$TargetLocator org.openqa.selenium.remote.RemoteWebDriver.switchTo()
	public static org.openqa.selenium.WebDriver.TargetLocator switchTo() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.WebDriver.TargetLocator)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "switchTo", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ************************************************************************************************************************ navigate
	// WebDriver               [9]  = public abstract org.openqa.selenium.WebDriver$Navigation org.openqa.selenium.WebDriver.navigate()
	// ChromeDriver            [22] = public org.openqa.selenium.WebDriver$Navigation org.openqa.selenium.remote.RemoteWebDriver.navigate()
	// FireFoxDriver           [13] = public org.openqa.selenium.WebDriver$Navigation org.openqa.selenium.remote.RemoteWebDriver.navigate()
	// InternetExplorerDriver  [12] = public org.openqa.selenium.WebDriver$Navigation org.openqa.selenium.remote.RemoteWebDriver.navigate()
	// EdgeDriver              [10] = public org.openqa.selenium.WebDriver$Navigation org.openqa.selenium.remote.RemoteWebDriver.navigate()
	// OperaDriver             [15] = public org.openqa.selenium.WebDriver$Navigation org.openqa.selenium.remote.RemoteWebDriver.navigate()
	// SafariDriver            [11] = public org.openqa.selenium.WebDriver$Navigation org.openqa.selenium.remote.RemoteWebDriver.navigate()
	// AndroidDriver		   [55] = public org.openqa.selenium.WebDriver$Navigation org.openqa.selenium.remote.RemoteWebDriver.navigate()
	public static org.openqa.selenium.WebDriver.Navigation navigate() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.WebDriver.Navigation)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "navigate", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ************************************************************************************************************************ manage
	// WebDriver               [10] = public abstract org.openqa.selenium.WebDriver$Options org.openqa.selenium.WebDriver.manage()
	// ChromeDriver            [23] = public org.openqa.selenium.WebDriver$Options org.openqa.selenium.remote.RemoteWebDriver.manage()
	// FireFoxDriver           [14] = public org.openqa.selenium.WebDriver$Options org.openqa.selenium.remote.RemoteWebDriver.manage()
	// InternetExplorerDriver  [13] = public org.openqa.selenium.WebDriver$Options org.openqa.selenium.remote.RemoteWebDriver.manage()
	// EdgeDriver              [11] = public org.openqa.selenium.WebDriver$Options org.openqa.selenium.remote.RemoteWebDriver.manage()
	// OperaDriver             [16] = public org.openqa.selenium.WebDriver$Options org.openqa.selenium.remote.RemoteWebDriver.manage()
	// SafariDriver            [12] = public org.openqa.selenium.WebDriver$Options org.openqa.selenium.remote.RemoteWebDriver.manage()
	// AndroidDriver		   [56] = public org.openqa.selenium.WebDriver$Options org.openqa.selenium.remote.RemoteWebDriver.manage()
	public static org.openqa.selenium.WebDriver.Options manage() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.WebDriver.Options)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "manage", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ findElement (Return pureElement Object)
	// WebDriver               [11] = public abstract org.openqa.selenium.WebElement org.openqa.selenium.WebDriver.findElement(org.openqa.selenium.By)
	// ChromeDriver            [17] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElement(org.openqa.selenium.By)
	// FireFoxDriver           [15] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElement(org.openqa.selenium.By)
	// InternetExplorerDriver  [14] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElement(org.openqa.selenium.By)
	// EdgeDriver              [12] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElement(org.openqa.selenium.By)
	// OperaDriver             [17] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElement(org.openqa.selenium.By)
	// SafariDriver            [13] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElement(org.openqa.selenium.By)
	// AndroidDriver		   [11] = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidDriver.findElement(org.openqa.selenium.By)
	public static pureElement findElement( org.openqa.selenium.By xPath ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object SourceObject = pureCore.callMethod( currentDriver.mainDriver,  currentDriver.mainDriver.getClass(), "findElement", org.openqa.selenium.By.class, currentDriver.mainDriver.getClass().toString(), xPath );
		if ( SourceObject != null ) {
			pureCore.peMethod myMethod = pureCore.peMethodFromBy( xPath.toString() );
			pureElement newPureElement = new pureElement( "The Name", "The Type", myMethod.peMethodid, myMethod.peSearch, pureCore.defRefreshMode );
			newPureElement.theElement = SourceObject;
			newPureElement.refrehCode = "0xFFFFFFFF";
			return newPureElement;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElement (Return Object for WebElement)
	// WebDriver               [11] = public abstract org.openqa.selenium.WebElement org.openqa.selenium.WebDriver.findElement(org.openqa.selenium.By)
	// ChromeDriver            [17] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElement(org.openqa.selenium.By)
	// FireFoxDriver           [15] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElement(org.openqa.selenium.By)
	// InternetExplorerDriver  [14] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElement(org.openqa.selenium.By)
	// EdgeDriver              [12] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElement(org.openqa.selenium.By)
	// OperaDriver             [17] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElement(org.openqa.selenium.By)
	// SafariDriver            [13] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElement(org.openqa.selenium.By)
	// AndroidDriver		   [11] = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidDriver.findElement(org.openqa.selenium.By)
	public static Object findElementWE( org.openqa.selenium.By xPath ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return pureCore.callMethod( currentDriver.mainDriver,  currentDriver.mainDriver.getClass(), "findElement", org.openqa.selenium.By.class, currentDriver.mainDriver.getClass().toString(), xPath );
	}


	// ************************************************************************************************************************ findElement (Return Object for WebElement)
	// AndroidDriver		   [10] = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidDriver.findElement(java.lang.String,java.lang.String)
	public static Object findElementWE( org.openqa.selenium.By xPath, pureDriverDetails driverToUse ) {
		return pureCore.callMethod( driverToUse.mainDriver,  driverToUse.mainDriver.getClass(), "findElement", org.openqa.selenium.By.class, driverToUse.mainDriver.getClass().toString(), xPath );
	}

	
	// ************************************************************************************************************************ findElements
	// WebDriver               [12] = public abstract java.util.List org.openqa.selenium.WebDriver.findElements(org.openqa.selenium.By)
	// ChromeDriver            [16] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElements(org.openqa.selenium.By)
	// FireFoxDriver           [16] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElements(org.openqa.selenium.By)
	// InternetExplorerDriver  [15] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElements(org.openqa.selenium.By)
	// EdgeDriver              [13] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElements(org.openqa.selenium.By)
	// OperaDriver             [18] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElements(org.openqa.selenium.By)
	// SafariDriver            [14] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElements(org.openqa.selenium.By)
	// AndroidDriver		   [30] = public java.util.List<T> io.appium.java_client.AppiumDriver.findElements(org.openqa.selenium.By)
	public static pureElements findElements( org.openqa.selenium.By xPath ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object feedback = pureCore.callMethod( currentDriver.mainDriver,  currentDriver.mainDriver.getClass(), "findElements", org.openqa.selenium.By.class, currentDriver.mainDriver.getClass().toString(), xPath );
		List<Object> myObjectList = pureCore.CastObjecttoList( feedback	);
		if ( myObjectList != null ) {
			pureCore.peMethod myMethod = pureCore.peMethodFromBy( xPath.toString() );
			pureElements newPureElements = new pureElements( "-", "-", myMethod.peMethodid, myMethod.peSearch, pureCore.defRefreshMode );
			newPureElements.theElements = myObjectList;
			newPureElements.refrehCode = "0xFFFFFFFF";
			return newPureElements;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ Location
	// ChromeDriver            [0]  = public org.openqa.selenium.html5.Location org.openqa.selenium.chrome.ChromeDriver.location()
	// OperaDriver             [0]  = public org.openqa.selenium.html5.Location org.openqa.selenium.opera.OperaDriver.location()
	// AndroidDriver		   [23] = public org.openqa.selenium.html5.Location io.appium.java_client.AppiumDriver.location()
	public org.openqa.selenium.html5.Location location() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.html5.Location)pureCore.callMethod( currentDriver.mainDriver,  currentDriver.mainDriver.getClass(), "location", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ setFileDetector
	// ChromeDriver            [1]  = public void org.openqa.selenium.chrome.ChromeDriver.setFileDetector(org.openqa.selenium.remote.FileDetector)
	// FireFoxDriver           [1]  = public void org.openqa.selenium.firefox.FirefoxDriver.setFileDetector(org.openqa.selenium.remote.FileDetector)
	// InternetExplorerDriver  [0]  = public void org.openqa.selenium.ie.InternetExplorerDriver.setFileDetector(org.openqa.selenium.remote.FileDetector)
	// EdgeDriver              [14] = public void org.openqa.selenium.remote.RemoteWebDriver.setFileDetector(org.openqa.selenium.remote.FileDetector)
	// OperaDriver             [2]  = public void org.openqa.selenium.opera.OperaDriver.setFileDetector(org.openqa.selenium.remote.FileDetector)
	// SafariDriver            [0]  = public void org.openqa.selenium.safari.SafariDriver.setFileDetector(org.openqa.selenium.remote.FileDetector)
	// AndroidDriver		   [57] = public void org.openqa.selenium.remote.RemoteWebDriver.setFileDetector(org.openqa.selenium.remote.FileDetector)
	public void setFileDetector( org.openqa.selenium.remote.FileDetector myFileDetector ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver,  currentDriver.mainDriver.getClass(), "setFileDetector", org.openqa.selenium.remote.FileDetector.class,
				"pureDrivers.setFileDetector",  (Object)myFileDetector );
	}
	
	// ************************************************************************************************************************ getLocalStorage
	// ChromeDriver            [2]  = public org.openqa.selenium.html5.LocalStorage org.openqa.selenium.chrome.ChromeDriver.getLocalStorage()
	// FireFoxDriver           [2]  = public org.openqa.selenium.html5.LocalStorage org.openqa.selenium.firefox.FirefoxDriver.getLocalStorage()
	// OperaDriver             [3]  = public org.openqa.selenium.html5.LocalStorage org.openqa.selenium.opera.OperaDriver.getLocalStorage()
	public org.openqa.selenium.html5.LocalStorage getLocalStorage() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.html5.LocalStorage)pureCore.callMethod( currentDriver.mainDriver,  currentDriver.mainDriver.getClass(), "location", (Class<?>)null , currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ************************************************************************************************************************ setLocation
	// ChromeDriver            [3]  = public void org.openqa.selenium.chrome.ChromeDriver.setLocation(org.openqa.selenium.html5.Location)
	// OperaDriver             [4]  = public void org.openqa.selenium.opera.OperaDriver.setLocation(org.openqa.selenium.html5.Location)
	// AndroidDriver		   [31] = public void io.appium.java_client.AppiumDriver.setLocation(org.openqa.selenium.html5.Location)
	public void setLocation( org.openqa.selenium.html5.Location myLocation ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver,  currentDriver.mainDriver.getClass(), "setLocation", org.openqa.selenium.html5.Location.class, currentDriver.mainDriver.getClass().toString(),  (Object)myLocation );
	}
	
	// ************************************************************************************************************************ getTouch
	// ChromeDriver            [4]  = public org.openqa.selenium.interactions.TouchScreen org.openqa.selenium.chrome.ChromeDriver.getTouch()
	public org.openqa.selenium.interactions.TouchScreen getTouch() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.interactions.TouchScreen)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(),  "getTouch",  (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ************************************************************************************************************************ launchApp
	// ChromeDriver            [5]  = public void org.openqa.selenium.chrome.ChromeDriver.launchApp(java.lang.String)
	// AndroidDriver		   [84] = public default void io.appium.java_client.InteractsWithApps.launchApp()
	public void launchApp( String inPut ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "launchApp" , java.lang.String.class, currentDriver.mainDriver.getClass().toString(), inPut );
	}
	
	// ************************************************************************************************************************ getSessionStorage
	// ChromeDriver            [6]  = public org.openqa.selenium.html5.SessionStorage org.openqa.selenium.chrome.ChromeDriver.getSessionStorage()
	// FireFoxDriver           [0]  = public org.openqa.selenium.html5.SessionStorage org.openqa.selenium.firefox.FirefoxDriver.getSessionStorage()
	// OperaDriver             [1]  = public org.openqa.selenium.html5.SessionStorage org.openqa.selenium.opera.OperaDriver.getSessionStorage()
	public org.openqa.selenium.html5.SessionStorage getSessionStorage() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.html5.SessionStorage)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(),"getSesionStorage", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ************************************************************************************************************************ getNetworkConnection
	// ChromeDriver            [7]  = public org.openqa.selenium.mobile.NetworkConnection$ConnectionType org.openqa.selenium.chrome.ChromeDriver.getNetworkConnection()
	public org.openqa.selenium.mobile.NetworkConnection.ConnectionType getNetworkConnection() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.mobile.NetworkConnection.ConnectionType)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getNetworkConnection", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );		
	}
	
	// ************************************************************************************************************************ setNetworkConnection
	// ChromeDriver            [8]  = public org.openqa.selenium.mobile.NetworkConnection$ConnectionType org.openqa.selenium.chrome.ChromeDriver.setNetworkConnection(org.openqa.selenium.mobile.NetworkConnection$ConnectionType)
	public org.openqa.selenium.mobile.NetworkConnection.ConnectionType setNetworkConnection( org.openqa.selenium.mobile.NetworkConnection.ConnectionType myConnection ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.mobile.NetworkConnection.ConnectionType)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getNetworkConnection", org.openqa.selenium.mobile.NetworkConnection.ConnectionType.class, currentDriver.mainDriver.getClass().toString(), myConnection );	
	}
	
	// ************************************************************************************************************************ toStringL
	// ChromeDriver            [10] = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.toString()
	// FireFoxDriver           [4]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.toString()
	// InternetExplorerDriver  [3]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.toString()
	// EdgeDriver              [1]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.toString()
	// OperaDriver             [6]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.toString()
	// SafariDriver            [2]  = public java.lang.String org.openqa.selenium.remote.RemoteWebDriver.toString()
	// AndroidDriver		   [0]  = public java.lang.String io.appium.java_client.android.AndroidDriver.toString()
		public String toStringL() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
	 	return (String)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "toString", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ getSessionId
	// ChromeDriver            [12] = public org.openqa.selenium.remote.SessionId org.openqa.selenium.remote.RemoteWebDriver.getSessionId()
	// FireFoxDriver           [17] = public org.openqa.selenium.remote.SessionId org.openqa.selenium.remote.RemoteWebDriver.getSessionId()
	// InternetExplorerDriver  [16] = public org.openqa.selenium.remote.SessionId org.openqa.selenium.remote.RemoteWebDriver.getSessionId()
	// EdgeDriver              [15] = public org.openqa.selenium.remote.SessionId org.openqa.selenium.remote.RemoteWebDriver.getSessionId()
	// OperaDriver             [19] = public org.openqa.selenium.remote.SessionId org.openqa.selenium.remote.RemoteWebDriver.getSessionId()
	// SafariDriver            [15] = public org.openqa.selenium.remote.SessionId org.openqa.selenium.remote.RemoteWebDriver.getSessionId()
	// AndroidDriver		   [58] = public org.openqa.selenium.remote.SessionId org.openqa.selenium.remote.RemoteWebDriver.getSessionId()
	public org.openqa.selenium.remote.SessionId getSessionId() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.remote.SessionId)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getSessionId", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ getErrorHandler
	// ChromeDriver            [24] = public org.openqa.selenium.remote.ErrorHandler org.openqa.selenium.remote.RemoteWebDriver.getErrorHandler()
	// FireFoxDriver           [18] = public org.openqa.selenium.remote.ErrorHandler org.openqa.selenium.remote.RemoteWebDriver.getErrorHandler()
	// InternetExplorerDriver  [17] = public org.openqa.selenium.remote.ErrorHandler org.openqa.selenium.remote.RemoteWebDriver.getErrorHandler()
	// EdgeDriver              [16] = public org.openqa.selenium.remote.ErrorHandler org.openqa.selenium.remote.RemoteWebDriver.getErrorHandler()
	// OperaDriver             [20] = public org.openqa.selenium.remote.ErrorHandler org.openqa.selenium.remote.RemoteWebDriver.getErrorHandler()
	// SafariDriver            [16] = public org.openqa.selenium.remote.ErrorHandler org.openqa.selenium.remote.RemoteWebDriver.getErrorHandler()
	// AndroidDriver		   [59] = public org.openqa.selenium.remote.ErrorHandler org.openqa.selenium.remote.RemoteWebDriver.getErrorHandler()
	public org.openqa.selenium.remote.ErrorHandler getErrorHandler() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.remote.ErrorHandler)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getErrorHandler", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ************************************************************************************************************************ setErrorHandler
	// ChromeDriver            [25] = public void org.openqa.selenium.remote.RemoteWebDriver.setErrorHandler(org.openqa.selenium.remote.ErrorHandler)
	// FireFoxDriver           [19] = public void org.openqa.selenium.remote.RemoteWebDriver.setErrorHandler(org.openqa.selenium.remote.ErrorHandler)
	// InternetExplorerDriver  [18] = public void org.openqa.selenium.remote.RemoteWebDriver.setErrorHandler(org.openqa.selenium.remote.ErrorHandler)
	// EdgeDriver              [17] = public void org.openqa.selenium.remote.RemoteWebDriver.setErrorHandler(org.openqa.selenium.remote.ErrorHandler)
	// OperaDriver             [21] = public void org.openqa.selenium.remote.RemoteWebDriver.setErrorHandler(org.openqa.selenium.remote.ErrorHandler)
	// SafariDriver            [17] = public void org.openqa.selenium.remote.RemoteWebDriver.setErrorHandler(org.openqa.selenium.remote.ErrorHandler)
	// AndroidDriver		   [60] = public void org.openqa.selenium.remote.RemoteWebDriver.setErrorHandler(org.openqa.selenium.remote.ErrorHandler)
	public void setErrorHandler( org.openqa.selenium.remote.ErrorHandler theError ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "setErrorHandler", org.openqa.selenium.remote.ErrorHandler.class, currentDriver.mainDriver.getClass().toString(), theError );
	}

	// ************************************************************************************************************************ getCapabilities
	// ChromeDriver            [26] = public org.openqa.selenium.Capabilities org.openqa.selenium.remote.RemoteWebDriver.getCapabilities()
	// FireFoxDriver           [20] = public org.openqa.selenium.Capabilities org.openqa.selenium.remote.RemoteWebDriver.getCapabilities()
	// InternetExplorerDriver  [19] = public org.openqa.selenium.Capabilities org.openqa.selenium.remote.RemoteWebDriver.getCapabilities()
	// EdgeDriver              [18] = public org.openqa.selenium.Capabilities org.openqa.selenium.remote.RemoteWebDriver.getCapabilities()
	// OperaDriver             [22] = public org.openqa.selenium.Capabilities org.openqa.selenium.remote.RemoteWebDriver.getCapabilities()
	// SafariDriver            [18] = public org.openqa.selenium.Capabilities org.openqa.selenium.remote.RemoteWebDriver.getCapabilities()
	// AndroidDriver		   [12] = public org.openqa.selenium.Capabilities io.appium.java_client.android.AndroidDriver.getCapabilities()
	public org.openqa.selenium.Capabilities getCapabilities() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.Capabilities)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getCapabilities", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ getScreenshotAs
	// ChromeDriver            [27] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.getScreenshotAs(org.openqa.selenium.OutputType) throws org.openqa.selenium.WebDriverException
	// FireFoxDriver           [21] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.getScreenshotAs(org.openqa.selenium.OutputType) throws org.openqa.selenium.WebDriverException
	// InternetExplorerDriver  [1]  = public java.lang.Object org.openqa.selenium.ie.InternetExplorerDriver.getScreenshotAs(org.openqa.selenium.OutputType)
	// EdgeDriver              [19] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.getScreenshotAs(org.openqa.selenium.OutputType) throws org.openqa.selenium.WebDriverException
	// OperaDriver             [23] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.getScreenshotAs(org.openqa.selenium.OutputType) throws org.openqa.selenium.WebDriverException
	// SafariDriver            [19] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.getScreenshotAs(org.openqa.selenium.OutputType) throws org.openqa.selenium.WebDriverException
	// AndroidDriver		   [61] = public <X> X org.openqa.selenium.remote.RemoteWebDriver.getScreenshotAs(org.openqa.selenium.OutputType<X>) throws org.openqa.selenium.WebDriverException
	public Object getScreenshotAs( org.openqa.selenium.OutputType<?> file ) throws WebDriverException{
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getScreenshotAs", org.openqa.selenium.OutputType.class, currentDriver.mainDriver.getClass().toString(), file );
	}

	// ************************************************************************************************************************ executeScript
	// ChromeDriver            [30] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeScript(java.lang.String,java.lang.Object[])
	// FireFoxDriver           [22] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeScript(java.lang.String,java.lang.Object[])
	// InternetExplorerDriver  [20] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeScript(java.lang.String,java.lang.Object[])
	// EdgeDriver              [20] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeScript(java.lang.String,java.lang.Object[])
	// OperaDriver             [24] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeScript(java.lang.String,java.lang.Object[])
	// SafariDriver            [20] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeScript(java.lang.String,java.lang.Object[])
	// AndroidDriver		   [62] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeScript(java.lang.String,java.lang.Object...)
	public Object executeScript( String Str1, Object[] myObjectList ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		//
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = java.lang.String.class;
		myClasses[ 1 ] = java.lang.Object[].class;
		//
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)Str1;
		myTrueParam[ 1 ] = myObjectList;
		//
		return pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "executeScript", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}
	
	// ************************************************************************************************************************ setLogLevel
	// ChromeDriver            [31] = public void org.openqa.selenium.remote.RemoteWebDriver.setLogLevel(java.util.logging.Level)
	// FireFoxDriver           [23] = public void org.openqa.selenium.remote.RemoteWebDriver.setLogLevel(java.util.logging.Level)
	// InternetExplorerDriver  [21] = public void org.openqa.selenium.remote.RemoteWebDriver.setLogLevel(java.util.logging.Level)
	// EdgeDriver              [21] = public void org.openqa.selenium.remote.RemoteWebDriver.setLogLevel(java.util.logging.Level)
	// OperaDriver             [25] = public void org.openqa.selenium.remote.RemoteWebDriver.setLogLevel(java.util.logging.Level)
	// SafariDriver            [21] = public void org.openqa.selenium.remote.RemoteWebDriver.setLogLevel(java.util.logging.Level)
	// AndroidDriver		   [63] = public void org.openqa.selenium.remote.RemoteWebDriver.setLogLevel(java.util.logging.Level)
	public void setLogLevel( java.util.logging.Level logLevel ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "setLogLevel", java.util.logging.Level.class , currentDriver.mainDriver.getClass().toString(), logLevel );
	}

	// ************************************************************************************************************************ perform
	// ChromeDriver            [32] = public void org.openqa.selenium.remote.RemoteWebDriver.perform(java.util.Collection)
	// FireFoxDriver           [24] = public void org.openqa.selenium.remote.RemoteWebDriver.perform(java.util.Collection)
	// InternetExplorerDriver  [22] = public void org.openqa.selenium.remote.RemoteWebDriver.perform(java.util.Collection)
	// EdgeDriver              [22] = public void org.openqa.selenium.remote.RemoteWebDriver.perform(java.util.Collection)
	// OperaDriver             [26] = public void org.openqa.selenium.remote.RemoteWebDriver.perform(java.util.Collection)
	// SafariDriver            [22] = public void org.openqa.selenium.remote.RemoteWebDriver.perform(java.util.Collection)
	// AndroidDriver		   [64] = public void org.openqa.selenium.remote.RemoteWebDriver.perform(java.util.Collection<org.openqa.selenium.interactions.Sequence>)
	public void perform( java.util.Collection<?> mCollection ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "perform", java.util.Collection.class , currentDriver.mainDriver.getClass().toString(), mCollection );
	}
	
	// ************************************************************************************************************************ resetInputState
	// ChromeDriver            [33] = public void org.openqa.selenium.remote.RemoteWebDriver.resetInputState()
	// FireFoxDriver           [25] = public void org.openqa.selenium.remote.RemoteWebDriver.resetInputState()
	// InternetExplorerDriver  [23] = public void org.openqa.selenium.remote.RemoteWebDriver.resetInputState()
	// EdgeDriver              [23] = public void org.openqa.selenium.remote.RemoteWebDriver.resetInputState()
	// OperaDriver             [27] = public void org.openqa.selenium.remote.RemoteWebDriver.resetInputState()
	// SafariDriver            [23] = public void org.openqa.selenium.remote.RemoteWebDriver.resetInputState()
	// AndroidDriver		   [65] = public void org.openqa.selenium.remote.RemoteWebDriver.resetInputState()
	public void resetInputState() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "perform", (Class<?>)null , currentDriver.mainDriver.getClass().toString(), (Object)null );
		
	}

	// ************************************************************************************************************************ getKeyboard
	// ChromeDriver            [34] = public org.openqa.selenium.interactions.Keyboard org.openqa.selenium.remote.RemoteWebDriver.getKeyboard()
	// FireFoxDriver           [26] = public org.openqa.selenium.interactions.Keyboard org.openqa.selenium.remote.RemoteWebDriver.getKeyboard()
	// InternetExplorerDriver  [24] = public org.openqa.selenium.interactions.Keyboard org.openqa.selenium.remote.RemoteWebDriver.getKeyboard()
	// EdgeDriver              [24] = public org.openqa.selenium.interactions.Keyboard org.openqa.selenium.remote.RemoteWebDriver.getKeyboard()
	// OperaDriver             [28] = public org.openqa.selenium.interactions.Keyboard org.openqa.selenium.remote.RemoteWebDriver.getKeyboard()
	// SafariDriver            [24] = public org.openqa.selenium.interactions.Keyboard org.openqa.selenium.remote.RemoteWebDriver.getKeyboard()
	// AndroidDriver		   [66] = public org.openqa.selenium.interactions.Keyboard org.openqa.selenium.remote.RemoteWebDriver.getKeyboard()
	public org.openqa.selenium.interactions.Keyboard getKeyboard(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.interactions.Keyboard)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getKeyboard", (Class<?>)null , currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ getMouse
	// ChromeDriver            [35] = public org.openqa.selenium.interactions.Mouse org.openqa.selenium.remote.RemoteWebDriver.getMouse()
	// FireFoxDriver           [27] = public org.openqa.selenium.interactions.Mouse org.openqa.selenium.remote.RemoteWebDriver.getMouse()
	// InternetExplorerDriver  [25] = public org.openqa.selenium.interactions.Mouse org.openqa.selenium.remote.RemoteWebDriver.getMouse()
	// EdgeDriver              [25] = public org.openqa.selenium.interactions.Mouse org.openqa.selenium.remote.RemoteWebDriver.getMouse()
	// OperaDriver             [29] = public org.openqa.selenium.interactions.Mouse org.openqa.selenium.remote.RemoteWebDriver.getMouse()
	// SafariDriver            [25] = public org.openqa.selenium.interactions.Mouse org.openqa.selenium.remote.RemoteWebDriver.getMouse()
	// AndroidDriver		   [13] = public org.openqa.selenium.interactions.Mouse io.appium.java_client.android.AndroidDriver.getMouse()
	public org.openqa.selenium.interactions.Mouse getMouse(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.interactions.Mouse)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getMouse", (Class<?>)null , currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ getFileDetector
	// ChromeDriver            [36] = public org.openqa.selenium.remote.FileDetector org.openqa.selenium.remote.RemoteWebDriver.getFileDetector()
	// FireFoxDriver           [28] = public org.openqa.selenium.remote.FileDetector org.openqa.selenium.remote.RemoteWebDriver.getFileDetector()
	// InternetExplorerDriver  [26] = public org.openqa.selenium.remote.FileDetector org.openqa.selenium.remote.RemoteWebDriver.getFileDetector()
	// EdgeDriver              [26] = public org.openqa.selenium.remote.FileDetector org.openqa.selenium.remote.RemoteWebDriver.getFileDetector()
	// OperaDriver             [30] = public org.openqa.selenium.remote.FileDetector org.openqa.selenium.remote.RemoteWebDriver.getFileDetector()
	// SafariDriver            [26] = public org.openqa.selenium.remote.FileDetector org.openqa.selenium.remote.RemoteWebDriver.getFileDetector()
	// AndroidDriver		   [67] = public org.openqa.selenium.remote.FileDetector org.openqa.selenium.remote.RemoteWebDriver.getFileDetector()
	public org.openqa.selenium.remote.FileDetector getFileDetector(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.remote.FileDetector)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getFileDetector", (Class<?>)null , currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ************************************************************************************************************************ getCommandExecutor
	// ChromeDriver            [37] = public org.openqa.selenium.remote.CommandExecutor org.openqa.selenium.remote.RemoteWebDriver.getCommandExecutor()
	// FireFoxDriver           [29] = public org.openqa.selenium.remote.CommandExecutor org.openqa.selenium.remote.RemoteWebDriver.getCommandExecutor()
	// InternetExplorerDriver  [27] = public org.openqa.selenium.remote.CommandExecutor org.openqa.selenium.remote.RemoteWebDriver.getCommandExecutor()
	// EdgeDriver              [27] = public org.openqa.selenium.remote.CommandExecutor org.openqa.selenium.remote.RemoteWebDriver.getCommandExecutor()
	// OperaDriver             [31] = public org.openqa.selenium.remote.CommandExecutor org.openqa.selenium.remote.RemoteWebDriver.getCommandExecutor()
	// SafariDriver            [27] = public org.openqa.selenium.remote.CommandExecutor org.openqa.selenium.remote.RemoteWebDriver.getCommandExecutor()
	// AndroidDriver		   [47] = public org.openqa.selenium.remote.CommandExecutor org.openqa.selenium.remote.RemoteWebDriver.getCommandExecutor()
	public org.openqa.selenium.remote.CommandExecutor getCommandExecutor(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.remote.CommandExecutor)pureCore.callMethod(
				currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getCommandExecutor", (Class<?>)null , currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ************************************************************************************************************************ executeAsyncScript
	// ChromeDriver            [52] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeAsyncScript(java.lang.String,java.lang.Object[])
	// FireFoxDriver           [30] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeAsyncScript(java.lang.String,java.lang.Object[])
	// InternetExplorerDriver  [28] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeAsyncScript(java.lang.String,java.lang.Object[])
	// EdgeDriver              [28] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeAsyncScript(java.lang.String,java.lang.Object[])
	// OperaDriver             [32] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeAsyncScript(java.lang.String,java.lang.Object[])
	// SafariDriver            [28] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeAsyncScript(java.lang.String,java.lang.Object[])
	// AndroidDriver		   [48] = public java.lang.Object org.openqa.selenium.remote.RemoteWebDriver.executeAsyncScript(java.lang.String,java.lang.Object...)
	public Object executeAsyncScript( String Str1, Object[] myObjectList ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		//
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = java.lang.String.class;
		myClasses[ 1 ] = java.lang.Object[].class;
		//
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)Str1;
		myTrueParam[ 1 ] = myObjectList;
		//
		return pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "executeScript", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}

	// ************************************************************************************************************************ wait
	// ChromeDriver            [53] = public final void java.lang.Object.wait() throws java.lang.InterruptedException
	// FireFoxDriver           [47] = public final void java.lang.Object.wait() throws java.lang.InterruptedException
	// InternetExplorerDriver  [45] = public final void java.lang.Object.wait() throws java.lang.InterruptedException
	// EdgeDriver              [45] = public final void java.lang.Object.wait() throws java.lang.InterruptedException
	// OperaDriver             [49] = public final void java.lang.Object.wait() throws java.lang.InterruptedException
	// SafariDriver            [45] = public final void java.lang.Object.wait() throws java.lang.InterruptedException
	// AndroidDriver		   [68] = public final void java.lang.Object.wait() throws java.lang.InterruptedException
	public void waitD(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
	 	pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "wait", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	 }

	// ************************************************************************************************************************ waitD
	// ChromeDriver            [54] = public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
	// FireFoxDriver           [48] = public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
	// InternetExplorerDriver  [46] = public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
	// EdgeDriver              [46] = public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
	// OperaDriver             [50] = public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
	// SafariDriver            [46] = public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
	// AndroidDriver		   [69] = public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
	public void waitD( long TimeL, int TimeI ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		//
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = long.class;
		myClasses[ 1 ] = int.class;
		//
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)TimeL;
		myTrueParam[ 1 ] = (Object)TimeI;
		//
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "wait", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}
	
	// ************************************************************************************************************************ waitD
	// ChromeDriver            [55] = public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
	// FireFoxDriver           [49] = public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
	// InternetExplorerDriver  [47] = public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
	// EdgeDriver              [47] = public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
	// OperaDriver             [51] = public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
	// SafariDriver            [47] = public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
	// AndroidDriver		   [70] = public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
	public void waitD( long TimeL ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
	 	pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "wait", long.class, currentDriver.mainDriver.getClass().toString(), (Object)TimeL );
	 }

	// ************************************************************************************************************************ equalsL
	// ChromeDriver            [56] = public boolean java.lang.Object.equals(java.lang.Object)
	// FireFoxDriver           [50] = public boolean java.lang.Object.equals(java.lang.Object)
	// InternetExplorerDriver  [48] = public boolean java.lang.Object.equals(java.lang.Object)
	// EdgeDriver              [48] = public boolean java.lang.Object.equals(java.lang.Object)
	// OperaDriver             [52] = public boolean java.lang.Object.equals(java.lang.Object)
	// SafariDriver            [48] = public boolean java.lang.Object.equals(java.lang.Object)
	// AndroidDriver		   [71] = public boolean java.lang.Object.equals(java.lang.Object)
	public boolean equalsL( Object ObjectToCompare ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (boolean)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "equals", Object.class, currentDriver.mainDriver.getClass().toString(), ObjectToCompare );
	}
	
	// ************************************************************************************************************************ hashCodeD
	// ChromeDriver            [57] = public native int java.lang.Object.hashCode()
	// FireFoxDriver           [51] = public native int java.lang.Object.hashCode()
	// InternetExplorerDriver  [49] = public native int java.lang.Object.hashCode()
	// EdgeDriver              [49] = public native int java.lang.Object.hashCode()
	// OperaDriver             [53] = public native int java.lang.Object.hashCode()
	// SafariDriver            [49] = public native int java.lang.Object.hashCode()
	// AndroidDriver		   [72] = public native int java.lang.Object.hashCode()
	public int hashCodeD(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (int)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "hashCode", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ getClassD
	// ChromeDriver            [58] = public final native java.lang.Class java.lang.Object.getClass()
	// FireFoxDriver           [52] = public final native java.lang.Class java.lang.Object.getClass()
	// InternetExplorerDriver  [50] = public final native java.lang.Class java.lang.Object.getClass()
	// EdgeDriver              [50] = public final native java.lang.Class java.lang.Object.getClass()
	// OperaDriver             [54] = public final native java.lang.Class java.lang.Object.getClass()
	// SafariDriver            [50] = public final native java.lang.Class java.lang.Object.getClass()
	// AndroidDriver		   [73] = public final native java.lang.Class<?> java.lang.Object.getClass()
	public Class<?> getClassD(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (Class<?>)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getClass", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ************************************************************************************************************************ notifyD
	// ChromeDriver            [59] = public final native void java.lang.Object.notify()
	// FireFoxDriver           [53] = public final native void java.lang.Object.notify()
	// InternetExplorerDriver  [51] = public final native void java.lang.Object.notify()
	// EdgeDriver              [51] = public final native void java.lang.Object.notify()
	// OperaDriver             [55] = public final native void java.lang.Object.notify()
	// SafariDriver            [51] = public final native void java.lang.Object.notify()
	// AndroidDriver		   [74] = public final native void java.lang.Object.notify()
	public void notifyD(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "notify", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ************************************************************************************************************************ notifyAllD
	// ChromeDriver            [60] = public final native void java.lang.Object.notifyAll()
	// FireFoxDriver           [54] = public final native void java.lang.Object.notifyAll()	public boolean equalsL( Object ObjectToCompare ) {
	// InternetExplorerDriver  [52] = public final native void java.lang.Object.notifyAll()
	// EdgeDriver              [52] = public final native void java.lang.Object.notifyAll()
	// OperaDriver             [56] = public final native void java.lang.Object.notifyAll()
	// SafariDriver            [52] = public final native void java.lang.Object.notifyAll()
	// AndroidDriver		   [75] = public final native void java.lang.Object.notifyAll()
	public void notifyAllD(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "notifyAll", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ************************************************************************************************************************ findElementById
	// ChromeDriver            [28] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementById(java.lang.String)
	// FireFoxDriver           [45] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementById(java.lang.String)
	// InternetExplorerDriver  [43] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementById(java.lang.String)
	// EdgeDriver              [43] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementById(java.lang.String)
	// OperaDriver             [47] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementById(java.lang.String)
	// SafariDriver            [43] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementById(java.lang.String)
	// AndroidDriver		   [14] = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidDriver.findElementById(java.lang.String)
	public pureElement findElementById( String objectID ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object SourceObject = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementById", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), objectID );
		if ( SourceObject != null ) {
			pureElement newPureElement = new pureElement( "-", "-", pureCore.elementMethod.id, objectID, pureCore.defRefreshMode );
			newPureElement.theElement = SourceObject;
			newPureElement.refrehCode = "0xFFFFFFFF";
			return newPureElement;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElementByLinkText
	// ChromeDriver            [38] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByLinkText(java.lang.String)
	// FireFoxDriver           [31] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByLinkText(java.lang.String)
	// InternetExplorerDriver  [29] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByLinkText(java.lang.String)
	// EdgeDriver              [29] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByLinkText(java.lang.String)
	// OperaDriver             [33] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByLinkText(java.lang.String)
	// SafariDriver            [29] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByLinkText(java.lang.String)
	// AndroidDriver		   [3]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidDriver.findElementByLinkText(java.lang.String) throws org.openqa.selenium.WebDriverException
	public pureElement findElementByLinkText( String ObjectLinkText ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object SourceObject = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementByLinkText", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), ObjectLinkText );
		if ( SourceObject != null ) {
			pureElement newPureElement = new pureElement( "-", "-", pureCore.elementMethod.linkText, ObjectLinkText, pureCore.defRefreshMode );
			newPureElement.theElement = SourceObject;
			newPureElement.refrehCode = "0xFFFFFFFF";
			return newPureElement;
		}else {
			return null;
		}
	}
	
	// ************************************************************************************************************************ findElementByPartialLinkText
	// ChromeDriver            [40] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByPartialLinkText(java.lang.String)
	// FireFoxDriver           [32] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByPartialLinkText(java.lang.String)
	// InternetExplorerDriver  [30] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByPartialLinkText(java.lang.String)
	// EdgeDriver              [30] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByPartialLinkText(java.lang.String)
	// OperaDriver             [34] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByPartialLinkText(java.lang.String)
	// SafariDriver            [30] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByPartialLinkText(java.lang.String)
	// AndroidDriver		   [4]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidDriver.findElementByPartialLinkText(java.lang.String) throws org.openqa.selenium.WebDriverException
	public pureElement findElementByPartialLinkText( String ObjectLinkText ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object SourceObject = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementByPartialLinkText", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), ObjectLinkText );
		if ( SourceObject != null ) {
			pureElement newPureElement = new pureElement( "-", "-", pureCore.elementMethod.partialLinkText, ObjectLinkText, pureCore.defRefreshMode );
			newPureElement.theElement = SourceObject;
			newPureElement.refrehCode = "0xFFFFFFFF";
			return newPureElement;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElementByTagName
	// ChromeDriver            [42] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByTagName(java.lang.String)
	// FireFoxDriver           [33] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByTagName(java.lang.String)
	// InternetExplorerDriver  [31] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByTagName(java.lang.String)
	// EdgeDriver              [31] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByTagName(java.lang.String)
	// OperaDriver             [35] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByTagName(java.lang.String)
	// SafariDriver            [31] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByTagName(java.lang.String)
	// AndroidDriver		   [5]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidDriver.findElementByTagName(java.lang.String)
	public pureElement findElementByTagName( String TagName ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object SourceObject = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementByTagName", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), TagName );
		if ( SourceObject != null ) {
			pureElement newPureElement = new pureElement( "-", "-", pureCore.elementMethod.tagNamei, TagName, pureCore.defRefreshMode );
			newPureElement.theElement = SourceObject;
			newPureElement.refrehCode = "0xFFFFFFFF";
			return newPureElement;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElementByName
	// ChromeDriver            [44] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByName(java.lang.String)
	// FireFoxDriver           [34] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByName(java.lang.String)
	// InternetExplorerDriver  [32] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByName(java.lang.String)
	// EdgeDriver              [32] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByName(java.lang.String)
	// OperaDriver             [36] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByName(java.lang.String)
	// SafariDriver            [32] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByName(java.lang.String)
	// AndroidDriver		   [6]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidDriver.findElementByName(java.lang.String)
	public pureElement findElementByName( String Name ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object SourceObject = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementByName", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), Name );
		if ( SourceObject != null ) {
			pureElement newPureElement = new pureElement( "-", "-", pureCore.elementMethod.name, Name, pureCore.defRefreshMode );
			newPureElement.theElement = SourceObject;
			newPureElement.refrehCode = "0xFFFFFFFF";
			return newPureElement;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElementByClassName
	// ChromeDriver            [46] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByClassName(java.lang.String)
	// FireFoxDriver           [35] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByClassName(java.lang.String)
	// InternetExplorerDriver  [33] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByClassName(java.lang.String)
	// EdgeDriver              [33] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByClassName(java.lang.String)
	// OperaDriver             [37] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByClassName(java.lang.String)
	// SafariDriver            [33] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByClassName(java.lang.String)
	// AndroidDriver		   [7]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidDriver.findElementByClassName(java.lang.String)
	public pureElement findElementByClassName( String ClassName ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object SourceObject = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementByClassName", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), ClassName );
		if ( SourceObject != null ) {
			pureElement newPureElement = new pureElement( "-", "-", pureCore.elementMethod.className, ClassName, pureCore.defRefreshMode );
			newPureElement.theElement = SourceObject;
			newPureElement.refrehCode = "0xFFFFFFFF";
			return newPureElement;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElementByCssSelector
	// ChromeDriver            [48] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByCssSelector(java.lang.String)
	// FireFoxDriver           [36] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByCssSelector(java.lang.String)
	// InternetExplorerDriver  [34] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByCssSelector(java.lang.String)
	// EdgeDriver              [34] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByCssSelector(java.lang.String)
	// OperaDriver             [38] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByCssSelector(java.lang.String)
	// SafariDriver            [34] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByCssSelector(java.lang.String)
	// AndroidDriver		   [8]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidDriver.findElementByCssSelector(java.lang.String) throws org.openqa.selenium.WebDriverException
	public pureElement findElementByCssSelector( String ObjectLinkText ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object SourceObject = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementByLinkText", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), ObjectLinkText );
		if ( SourceObject != null ) {
			pureElement newPureElement = new pureElement( "-", "-", pureCore.elementMethod.linkText, ObjectLinkText, pureCore.defRefreshMode );
			newPureElement.theElement = SourceObject;
			newPureElement.refrehCode = "0xFFFFFFFF";
			return newPureElement;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElementByXPath
	// ChromeDriver            [50] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(java.lang.String)
	// FireFoxDriver           [37] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(java.lang.String)
	// InternetExplorerDriver  [35] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(java.lang.String)
	// EdgeDriver              [35] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(java.lang.String)
	// OperaDriver             [39] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(java.lang.String)
	// SafariDriver            [35] = public org.openqa.selenium.WebElement org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(java.lang.String)
	// AndroidDriver		   [9]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidDriver.findElementByXPath(java.lang.String)
	public pureElement findElementByXPath( String xPath ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object SourceObject = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementByXPath", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), xPath );
		if ( SourceObject != null ) {
			pureElement newPureElement = new pureElement( "-", "-", pureCore.elementMethod.xpath, xPath, pureCore.defRefreshMode );
			newPureElement.theElement = SourceObject;
			newPureElement.refrehCode = "0xFFFFFFFF";
			return newPureElement;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElementsById
	// ChromeDriver            [29] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsById(java.lang.String)
	// FireFoxDriver           [46] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsById(java.lang.String)
	// InternetExplorerDriver  [44] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsById(java.lang.String)
	// EdgeDriver              [44] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsById(java.lang.String)
	// OperaDriver             [48] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsById(java.lang.String)
	// SafariDriver            [44] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsById(java.lang.String)
	// AndroidDriver		   [32] = public java.util.List<T> io.appium.java_client.AppiumDriver.findElementsById(java.lang.String)
	public pureElements findElementsById( String Search ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object feedback = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementsById", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), Search );
		List<Object> myObjectList = pureCore.CastObjecttoList( feedback	);
		if ( myObjectList != null ) {
			pureElements newPureElements = new pureElements( "-", "-", pureCore.elementMethod.id, Search, pureCore.defRefreshMode );
			newPureElements.theElements = myObjectList;
			newPureElements.refrehCode = "0xFFFFFFFF";
			return newPureElements;
		}else {
			return null;
		}
	}
	
	// ************************************************************************************************************************ findElementsByLinkText
	// ChromeDriver            [39] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByLinkText(java.lang.String)
	// FireFoxDriver           [38] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByLinkText(java.lang.String)
	// InternetExplorerDriver  [36] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByLinkText(java.lang.String)
	// EdgeDriver              [36] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByLinkText(java.lang.String)
	// OperaDriver             [40] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByLinkText(java.lang.String)
	// SafariDriver            [36] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByLinkText(java.lang.String)
	// AndroidDriver		   [26] = public java.util.List<T> io.appium.java_client.AppiumDriver.findElementsByLinkText(java.lang.String)
	public pureElements findElementsByLinkText( String Search ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object feedback = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementsByLinkText", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), Search );
		List<Object> myObjectList = pureCore.CastObjecttoList( feedback	);
		if ( myObjectList != null ) {
			pureElements newPureElements = new pureElements( "-", "-", pureCore.elementMethod.linkText, Search, pureCore.defRefreshMode );
			newPureElements.theElements = myObjectList;
			newPureElements.refrehCode = "0xFFFFFFFF";
			return newPureElements;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findelementsByPartialLinkText
	// ChromeDriver            [41] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByPartialLinkText(java.lang.String)
	// FireFoxDriver           [39] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByPartialLinkText(java.lang.String)
	// InternetExplorerDriver  [37] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByPartialLinkText(java.lang.String)
	// EdgeDriver              [37] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByPartialLinkText(java.lang.String)
	// OperaDriver             [41] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByPartialLinkText(java.lang.String)
	// SafariDriver            [37] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByPartialLinkText(java.lang.String)
	// AndroidDriver		   [27] = public java.util.List<T> io.appium.java_client.AppiumDriver.findElementsByPartialLinkText(java.lang.String)
	public pureElements findElementsByPartialLinkText( String Search ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object feedback = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementsByPartialLinkText", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), Search );
		List<Object> myObjectList = pureCore.CastObjecttoList( feedback	);
		if ( myObjectList != null ) {
			pureElements newPureElements = new pureElements( "-", "-", pureCore.elementMethod.partialLinkText, Search, pureCore.defRefreshMode );
			newPureElements.theElements = myObjectList;
			newPureElements.refrehCode = "0xFFFFFFFF";
			return newPureElements;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElementsByTagName
	// ChromeDriver            [43] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByTagName(java.lang.String)
	// FireFoxDriver           [40] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByTagName(java.lang.String)
	// InternetExplorerDriver  [38] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByTagName(java.lang.String)
	// EdgeDriver              [38] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByTagName(java.lang.String)
	// OperaDriver             [42] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByTagName(java.lang.String)
	// SafariDriver            [38] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByTagName(java.lang.String)
	// AndroidDriver		   [28] = public java.util.List<T> io.appium.java_client.AppiumDriver.findElementsByTagName(java.lang.String)
	public pureElements findElementsByTagName( String Search ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object feedback = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementsByTagName", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), Search );
		List<Object> myObjectList = pureCore.CastObjecttoList( feedback	);
		if ( myObjectList != null ) {
			pureElements newPureElements = new pureElements( "-", "-", pureCore.elementMethod.tagNamei, Search, pureCore.defRefreshMode );
			newPureElements.theElements = myObjectList;
			newPureElements.refrehCode = "0xFFFFFFFF";
			return newPureElements;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElementsByName
	// ChromeDriver            [45] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByName(java.lang.String)
	// FireFoxDriver           [41] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByName(java.lang.String)
	// InternetExplorerDriver  [39] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByName(java.lang.String)
	// EdgeDriver              [39] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByName(java.lang.String)
	// OperaDriver             [43] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByName(java.lang.String)
	// SafariDriver            [39] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByName(java.lang.String)
	// AndroidDriver		   [33] = public java.util.List<T> io.appium.java_client.AppiumDriver.findElementsByName(java.lang.String)
	public pureElements findElementsByName( String Search ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object feedback = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementsByName", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), Search );
		List<Object> myObjectList = pureCore.CastObjecttoList( feedback	);
		if ( myObjectList != null ) {
			pureElements newPureElements = new pureElements( "-", "-", pureCore.elementMethod.name, Search, pureCore.defRefreshMode );
			newPureElements.theElements = myObjectList;
			newPureElements.refrehCode = "0xFFFFFFFF";
			return newPureElements;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElementsByClassName 
	// ChromeDriver            [47] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByClassName(java.lang.String)
	// FireFoxDriver           [42] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByClassName(java.lang.String)
	// InternetExplorerDriver  [40] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByClassName(java.lang.String)
	// EdgeDriver              [40] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByClassName(java.lang.String)
	// OperaDriver             [44] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByClassName(java.lang.String)
	// SafariDriver            [40] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByClassName(java.lang.String)
	// AndroidDriver		   [34] = public java.util.List<T> io.appium.java_client.AppiumDriver.findElementsByClassName(java.lang.String)
	public pureElements findElementsByClassName( String Search ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object feedback = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementsByClassName", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), Search );
		List<Object> myObjectList = pureCore.CastObjecttoList( feedback	);
		if ( myObjectList != null ) {
			pureElements newPureElements = new pureElements( "-", "-", pureCore.elementMethod.className, Search, pureCore.defRefreshMode );
			newPureElements.theElements = myObjectList;
			newPureElements.refrehCode = "0xFFFFFFFF";
			return newPureElements;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElementsByCssSelector
	// ChromeDriver            [49] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByCssSelector(java.lang.String)
	// FireFoxDriver           [43] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByCssSelector(java.lang.String)
	// InternetExplorerDriver  [41] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByCssSelector(java.lang.String)
	// EdgeDriver              [41] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByCssSelector(java.lang.String)
	// OperaDriver             [45] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByCssSelector(java.lang.String)
	// SafariDriver            [41] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByCssSelector(java.lang.String)
	// AndroidDriver		   [35] = public java.util.List<T> io.appium.java_client.AppiumDriver.findElementsByCssSelector(java.lang.String)
	public pureElements findElementsByCssSelector( String Search ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object feedback = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementsByCssSelector", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), Search );
		List<Object> myObjectList = pureCore.CastObjecttoList( feedback	);
		if ( myObjectList != null ) {
			pureElements newPureElements = new pureElements( "-", "-", pureCore.elementMethod.cssSelector, Search, pureCore.defRefreshMode );
			newPureElements.theElements = myObjectList;
			newPureElements.refrehCode = "0xFFFFFFFF";
			return newPureElements;
		}else {
			return null;
		}
	}

	// ************************************************************************************************************************ findElementsByXPath 
	// ChromeDriver            [51] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByXPath(java.lang.String)
	// FireFoxDriver           [44] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByXPath(java.lang.String)
	// InternetExplorerDriver  [42] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByXPath(java.lang.String)
	// EdgeDriver              [42] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByXPath(java.lang.String)
	// OperaDriver             [46] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByXPath(java.lang.String)
	// SafariDriver            [42] = public java.util.List org.openqa.selenium.remote.RemoteWebDriver.findElementsByXPath(java.lang.String)
	// AndroidDriver		   [36] = public java.util.List<T> io.appium.java_client.AppiumDriver.findElementsByXPath(java.lang.String)
	public pureElements findElementsByXPath( String Search ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Object feedback = pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementsByXPath", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), Search );
		List<Object> myObjectList = pureCore.CastObjecttoList( feedback	);
		if ( myObjectList != null ) {
			pureElements newPureElements = new pureElements( "-", "-", pureCore.elementMethod.xpath, Search, pureCore.defRefreshMode );
			newPureElements.theElements = myObjectList;
			newPureElements.refrehCode = "0xFFFFFFFF";
			return newPureElements;
		}else {
			return null;
		}
	}
	
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [1] = public org.openqa.selenium.remote.Response io.appium.java_client.android.AndroidDriver.execute(java.lang.String)
	public org.openqa.selenium.remote.Response execute( String executeStr ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		org.openqa.selenium.remote.Response feedback = (org.openqa.selenium.remote.Response)pureCore.callMethod( currentDriver.mainDriver,
				currentDriver.mainDriver.getClass(), "execute", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), executeStr );
		return feedback;
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [2] = public org.openqa.selenium.remote.Response io.appium.java_client.android.AndroidDriver.execute(java.lang.String,java.util.Map)
	@SuppressWarnings("rawtypes")
	public org.openqa.selenium.remote.Response execute( String Str1, java.util.Map Map1 ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		//
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = java.lang.String.class;
		myClasses[ 1 ] = java.util.Map.class;
		//
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)Str1;
		myTrueParam[ 1 ] = (Object)Map1;
		//
		org.openqa.selenium.remote.Response feedback = (org.openqa.selenium.remote.Response)pureCore.callMethod( currentDriver.mainDriver,
				currentDriver.mainDriver.getClass(), "execute", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
		return feedback;
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [15] = public void io.appium.java_client.android.AndroidDriver.endTestCoverage(java.lang.String,java.lang.String)
	public void endTestCoverage( String Str1, String Str2 ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		//
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = java.lang.String.class;
		myClasses[ 1 ] = java.lang.String.class;
		//
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)Str1;
		myTrueParam[ 1 ] = (Object)Str2;
		//
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "endTestCoverage", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [16] = public io.appium.java_client.battery.BatteryInfo io.appium.java_client.android.AndroidDriver.getBatteryInfo()
	public io.appium.java_client.battery.BatteryInfo getBatteryInfo(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (io.appium.java_client.battery.BatteryInfo)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getBatteryInfo", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [17] = public io.appium.java_client.android.AndroidBatteryInfo io.appium.java_client.android.AndroidDriver.getBatteryInfo()
	public io.appium.java_client.android.AndroidBatteryInfo getBatteryInfoA(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (io.appium.java_client.android.AndroidBatteryInfo)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getBatteryInfo", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [18] = public synchronized io.appium.java_client.ws.StringWebSocketClient io.appium.java_client.android.AndroidDriver.getLogcatClient()
	public synchronized io.appium.java_client.ws.StringWebSocketClient getLogcatClient(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (io.appium.java_client.ws.StringWebSocketClient)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getLogcatClient", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [19] = public void io.appium.java_client.android.AndroidDriver.openNotifications()
	public void openNotifications(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "openNotifications", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [20] = public void io.appium.java_client.android.AndroidDriver.toggleLocationServices()
	public void toggleLocationServices(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "toggleLocationServices", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [21] = public org.openqa.selenium.WebDriver io.appium.java_client.AppiumDriver.context(java.lang.String)
	public org.openqa.selenium.WebDriver context( String contextStr ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		org.openqa.selenium.WebDriver feedback = (org.openqa.selenium.WebDriver)pureCore.callMethod( currentDriver.mainDriver,
				currentDriver.mainDriver.getClass(), "context", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), contextStr );
		return feedback;
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [22] = public java.lang.String io.appium.java_client.AppiumDriver.getContext()
	public String getContext(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (String)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getContext", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [24] = public void io.appium.java_client.AppiumDriver.rotate(org.openqa.selenium.ScreenOrientation)
	public void rotate( org.openqa.selenium.ScreenOrientation Orientation ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "rotate", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), Orientation );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [25] = public void io.appium.java_client.AppiumDriver.rotate(org.openqa.selenium.DeviceRotation)
	public void rotate( org.openqa.selenium.DeviceRotation deviceRotation ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "rotate", java.lang.String.class, currentDriver.mainDriver.getClass().toString(), deviceRotation );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [29] = public java.util.List<T> io.appium.java_client.AppiumDriver.findElements(java.lang.String,java.lang.String)
	public void findElements( String Str1, String Str2 ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = java.lang.String.class;
		myClasses[ 1 ] = java.lang.String.class;
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)Str1;
		myTrueParam[ 1 ] = (Object)Str2;
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElements", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [37] = public org.openqa.selenium.remote.ExecuteMethod io.appium.java_client.AppiumDriver.getExecuteMethod()
	public org.openqa.selenium.remote.ExecuteMethod getExecuteMethod(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.remote.ExecuteMethod)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(),
				"getExecuteMethod", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [38] = public org.openqa.selenium.DeviceRotation io.appium.java_client.AppiumDriver.rotation()
	public org.openqa.selenium.DeviceRotation rotation(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.DeviceRotation)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(),
				"rotation", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [39] = public org.openqa.selenium.ScreenOrientation io.appium.java_client.AppiumDriver.getOrientation()
	public org.openqa.selenium.ScreenOrientation getOrientation(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (org.openqa.selenium.ScreenOrientation)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(),
				"getOrientation", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [40] = public java.net.URL io.appium.java_client.AppiumDriver.getRemoteAddress()
	public java.net.URL getRemoteAddress(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.net.URL)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getRemoteAddress", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [41] = public boolean io.appium.java_client.AppiumDriver.isBrowser()
	public boolean isBrowser(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (boolean)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "isBrowser", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [42] = public java.util.List<T> io.appium.java_client.AppiumDriver.findElementsByAccessibilityId(java.lang.String)
	// AndroidDriver		   [25] = public void io.appium.java_client.AppiumDriver.rotate(org.openqa.selenium.DeviceRotation)
	@SuppressWarnings("rawtypes")
	public java.util.List findElementsByAccessibilityId( java.lang.String AccessibilityID ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.util.List)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementsByAccessibilityId",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), AccessibilityID );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [43] = public java.util.Set<java.lang.String> io.appium.java_client.AppiumDriver.getContextHandles()
	public String getContextHandles(){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (String)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getContextHandles", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [76] = public default io.appium.java_client.TouchAction io.appium.java_client.PerformsTouchActions.performTouchAction(io.appium.java_client.TouchAction)
	@SuppressWarnings("rawtypes")
	public io.appium.java_client.TouchAction performTouchAction( io.appium.java_client.TouchAction touchAction ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (io.appium.java_client.TouchAction)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "performTouchAction",
				io.appium.java_client.TouchAction.class, currentDriver.mainDriver.getClass().toString(), touchAction );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [77] = public default void io.appium.java_client.PerformsTouchActions.performMultiTouchAction(io.appium.java_client.MultiTouchAction)
	public void performMultiTouchAction( io.appium.java_client.MultiTouchAction multitouchAction ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "performMultiTouchAction", io.appium.java_client.MultiTouchAction.class,
				currentDriver.mainDriver.getClass().toString(), multitouchAction );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [78] = public default T io.appium.java_client.FindsByAccessibilityId.findElementByAccessibilityId(java.lang.String)
	@SuppressWarnings("unchecked")
	public <T> T findElementByAccessibilityId( java.lang.String accessibilityID ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (T)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "findElementByAccessibilityId", java.lang.String.class,
				currentDriver.mainDriver.getClass().toString(), accessibilityID );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [79] = public default void io.appium.java_client.HidesKeyboard.hideKeyboard()
	public void hideKeyboard() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "hideKeyboard", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [80] = public default java.lang.String io.appium.java_client.HasDeviceTime.getDeviceTime(java.lang.String)
	public java.lang.String getDeviceTime( java.lang.String format ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.lang.String)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getDeviceTime",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), format );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [81] = public default java.lang.String io.appium.java_client.HasDeviceTime.getDeviceTime()
	public java.lang.String getDeviceTime() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.lang.String)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getDeviceTime", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [82] = public default byte[] io.appium.java_client.InteractsWithFiles.pullFile(java.lang.String)
	public byte[] pullFile( java.lang.String remotePath ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (byte[])pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "pullFile",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), remotePath );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [83] = public default byte[] io.appium.java_client.InteractsWithFiles.pullFolder(java.lang.String)
	public byte[] pullFolder( java.lang.String remotePath ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (byte[])pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "pullFolder",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), remotePath );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [85] = public default void io.appium.java_client.InteractsWithApps.installApp(java.lang.String,io.appium.java_client.appmanagement.BaseInstallApplicationOptions)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [86] = public default void io.appium.java_client.InteractsWithApps.installApp(java.lang.String)
	public void installApp( java.lang.String appPath ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "installApp",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), appPath );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [87] = public default boolean io.appium.java_client.InteractsWithApps.isAppInstalled(java.lang.String)
	public boolean isAppInstalled( java.lang.String bundleId ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (boolean)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "isAppInstalled",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), bundleId );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [88] = public default void io.appium.java_client.InteractsWithApps.resetApp()
	public void resetApp() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "resetApp", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [89] = public default boolean io.appium.java_client.InteractsWithApps.removeApp(java.lang.String,io.appium.java_client.appmanagement.BaseRemoveApplicationOptions)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [90] = public default boolean io.appium.java_client.InteractsWithApps.removeApp(java.lang.String)
	public boolean removeApp( java.lang.String bundleId ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (boolean)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "removeApp",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), bundleId );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [91] = public default void io.appium.java_client.InteractsWithApps.closeApp()
	public void closeApp() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "closeApp", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [92] = public default void io.appium.java_client.InteractsWithApps.activateApp(java.lang.String)
	public void activateApp( java.lang.String bundleId ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "activateApp",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), bundleId );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [93] = public default void io.appium.java_client.InteractsWithApps.activateApp(java.lang.String,io.appium.java_client.appmanagement.BaseActivateApplicationOptions)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [94] = public default io.appium.java_client.appmanagement.ApplicationState io.appium.java_client.InteractsWithApps.queryAppState(java.lang.String)
	public io.appium.java_client.appmanagement.ApplicationState queryAppState( java.lang.String bundleId ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (io.appium.java_client.appmanagement.ApplicationState)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "queryAppState",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), bundleId );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [95] = public default boolean io.appium.java_client.InteractsWithApps.terminateApp(java.lang.String)
	public boolean terminateApp( java.lang.String bundleId ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (boolean)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "terminateApp",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), bundleId );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [96] = public default boolean io.appium.java_client.InteractsWithApps.terminateApp(java.lang.String,io.appium.java_client.appmanagement.BaseTerminateApplicationOptions)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [97] = public default void io.appium.java_client.InteractsWithApps.runAppInBackground(java.time.Duration)
	public void runAppInBackground( java.time.Duration duration ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "runAppInBackground",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), duration );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [98] = public default java.util.Map<java.lang.String, java.lang.String> io.appium.java_client.HasAppStrings.getAppStringMap()
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [99] = public default java.util.Map<java.lang.String, java.lang.String> io.appium.java_client.HasAppStrings.getAppStringMap(java.lang.String)
	@SuppressWarnings("unchecked")
	public java.util.Map<java.lang.String, java.lang.String> getAppStringMap( java.lang.String language ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.util.Map<java.lang.String, java.lang.String>)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getAppStringMap",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), language );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [100] = public default java.util.Map<java.lang.String, java.lang.String> io.appium.java_client.HasAppStrings.getAppStringMap(java.lang.String,java.lang.String)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [101] = public default java.lang.Object io.appium.java_client.HasSessionDetails.getSessionDetail(java.lang.String)
	public java.lang.Object getSessionDetail( java.lang.String detail ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getSessionDetail",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), detail );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [102] = public default java.lang.String io.appium.java_client.HasSessionDetails.getPlatformName()
	public void getPlatformName() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getPlatformName", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [103] = public default java.util.Map<java.lang.String, java.lang.Object> io.appium.java_client.HasSessionDetails.getSessionDetails()
	@SuppressWarnings("unchecked")
	public java.util.Map<java.lang.String, java.lang.Object> getSessionDetails() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.util.Map<java.lang.String, java.lang.Object>)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getSessionDetails", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [104] = public default java.lang.String io.appium.java_client.HasSessionDetails.getAutomationName()
	public java.lang.String getAutomationName() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.lang.String)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getAutomationName", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [105] = public default io.appium.java_client.imagecomparison.FeaturesMatchingResult io.appium.java_client.ComparesImages.matchImagesFeatures(java.io.File, java.io.File, io.appium.java_client.imagecomparison.FeaturesMatchingOptions ) throws java.io.IOException
	public io.appium.java_client.imagecomparison.FeaturesMatchingResult matchImagesFeatures( java.io.File image1, java.io.File image2, io.appium.java_client.imagecomparison.FeaturesMatchingOptions fileOptions ) throws java.io.IOException {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Class<?>[] myClasses = new Class[3];
		myClasses[ 0 ] = java.io.File.class;
		myClasses[ 1 ] = java.io.File.class;
		myClasses[ 2 ] = io.appium.java_client.imagecomparison.FeaturesMatchingOptions.class;
		Object[] myTrueParam = new Object[3];
		myTrueParam[ 0 ] = (Object)image1;
		myTrueParam[ 1 ] = (Object)image2;
		myTrueParam[ 2 ] = (Object)fileOptions;
		return (io.appium.java_client.imagecomparison.FeaturesMatchingResult)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(),
				"matchImagesFeatures", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [106] = public default io.appium.java_client.imagecomparison.FeaturesMatchingResult io.appium.java_client.ComparesImages.matchImagesFeatures(java.io.File, java.io.File) throws java.io.IOException
	public io.appium.java_client.imagecomparison.FeaturesMatchingResult matchImagesFeatures( java.io.File image1, java.io.File image2 ) throws java.io.IOException {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = java.io.File.class;
		myClasses[ 1 ] = java.io.File.class;
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)image1;
		myTrueParam[ 1 ] = (Object)image2;
		return (io.appium.java_client.imagecomparison.FeaturesMatchingResult)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(),
				"matchImagesFeatures", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [107] = public default io.appium.java_client.imagecomparison.FeaturesMatchingResult io.appium.java_client.ComparesImages.matchImagesFeatures(byte[], byte[], io.appium.java_client.imagecomparison.FeaturesMatchingOptions )
	public io.appium.java_client.imagecomparison.FeaturesMatchingResult matchImagesFeatures( byte[] base64image1, byte[] base64image2, io.appium.java_client.imagecomparison.FeaturesMatchingOptions fileOptions ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Class<?>[] myClasses = new Class[3];
		myClasses[ 0 ] = byte[].class;
		myClasses[ 1 ] = byte[].class;
		myClasses[ 2 ] = io.appium.java_client.imagecomparison.FeaturesMatchingOptions.class;
		Object[] myTrueParam = new Object[3];
		myTrueParam[ 0 ] = (Object)base64image1;
		myTrueParam[ 1 ] = (Object)base64image2;
		myTrueParam[ 2 ] = (Object)fileOptions;
		return (io.appium.java_client.imagecomparison.FeaturesMatchingResult)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(),
				"matchImagesFeatures", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [108] = public default io.appium.java_client.imagecomparison.FeaturesMatchingResult io.appium.java_client.ComparesImages.matchImagesFeatures(byte[] ,byte[] )
	public io.appium.java_client.imagecomparison.FeaturesMatchingResult matchImagesFeatures( byte[] base64image1, byte[] base64image2 )  {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = byte[].class;
		myClasses[ 1 ] = byte[].class;
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)base64image1;
		myTrueParam[ 1 ] = (Object)base64image2;
		return (io.appium.java_client.imagecomparison.FeaturesMatchingResult)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(),
				"matchImagesFeatures", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [109] = public default io.appium.java_client.imagecomparison.OccurrenceMatchingResult io.appium.java_client.ComparesImages.findImageOccurrence(java.io.File ,java.io.File ) throws java.io.IOException
	public io.appium.java_client.imagecomparison.OccurrenceMatchingResult findImageOccurrence( java.io.File image1, java.io.File image2 ) throws java.io.IOException {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = java.io.File.class;
		myClasses[ 1 ] = java.io.File.class;
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)image1;
		myTrueParam[ 1 ] = (Object)image2;
		return (io.appium.java_client.imagecomparison.OccurrenceMatchingResult)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(),
				"findImageOccurrence", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [110] = public default io.appium.java_client.imagecomparison.OccurrenceMatchingResult io.appium.java_client.ComparesImages.findImageOccurrence(java.io.File, java.io.File, io.appium.java_client.imagecomparison.OccurrenceMatchingOptions ) throws java.io.IOException
	public io.appium.java_client.imagecomparison.OccurrenceMatchingResult findImageOccurrence( java.io.File image1, java.io.File image2, io.appium.java_client.imagecomparison.OccurrenceMatchingOptions fileOptions ) throws java.io.IOException {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = java.io.File.class;
		myClasses[ 1 ] = java.io.File.class;
		myClasses[ 2 ] = io.appium.java_client.imagecomparison.OccurrenceMatchingOptions.class;
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)image1;
		myTrueParam[ 1 ] = (Object)image2;
		myTrueParam[ 2 ] = (Object)fileOptions;
		return (io.appium.java_client.imagecomparison.OccurrenceMatchingResult)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(),
				"findImageOccurrence", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [111] = public default io.appium.java_client.imagecomparison.OccurrenceMatchingResult io.appium.java_client.ComparesImages.findImageOccurrence(byte[] ,byte[], io.appium.java_client.imagecomparison.OccurrenceMatchingOptions )
	public io.appium.java_client.imagecomparison.OccurrenceMatchingResult findImageOccurrence( byte[] base64image1, byte[] base64image2, io.appium.java_client.imagecomparison.OccurrenceMatchingOptions fileOptions ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Class<?>[] myClasses = new Class[3];
		myClasses[ 0 ] = byte[].class;
		myClasses[ 1 ] = byte[].class;
		myClasses[ 2 ] = io.appium.java_client.imagecomparison.OccurrenceMatchingOptions.class;
		Object[] myTrueParam = new Object[3];
		myTrueParam[ 0 ] = (Object)base64image1;
		myTrueParam[ 1 ] = (Object)base64image2;
		myTrueParam[ 2 ] = (Object)fileOptions;
		return (io.appium.java_client.imagecomparison.OccurrenceMatchingResult)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(),
				"findImageOccurrence", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [112] = public default io.appium.java_client.imagecomparison.OccurrenceMatchingResult io.appium.java_client.ComparesImages.findImageOccurrence(byte[], byte[] )
	public io.appium.java_client.imagecomparison.OccurrenceMatchingResult findImageOccurrence( byte[] base64image1, byte[] base64image2 ){
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = byte[].class;
		myClasses[ 1 ] = byte[].class;
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)base64image1;
		myTrueParam[ 1 ] = (Object)base64image2;
		return (io.appium.java_client.imagecomparison.OccurrenceMatchingResult)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(),
				"findImageOccurrence", myClasses, currentDriver.mainDriver.getClass().toString(), myTrueParam );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [113] = public default io.appium.java_client.imagecomparison.SimilarityMatchingResult io.appium.java_client.ComparesImages.getImagesSimilarity(java.io.File, java.io.File ) throws java.io.IOException
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [114] = public default io.appium.java_client.imagecomparison.SimilarityMatchingResult io.appium.java_client.ComparesImages.getImagesSimilarity(java.io.File, java.io.File, io.appium.java_client.imagecomparison.SimilarityMatchingOptions ) throws java.io.IOException
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [115] = public default io.appium.java_client.imagecomparison.SimilarityMatchingResult io.appium.java_client.ComparesImages.getImagesSimilarity(byte[], byte[], io.appium.java_client.imagecomparison.SimilarityMatchingOptions )
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [116] = public default io.appium.java_client.imagecomparison.SimilarityMatchingResult io.appium.java_client.ComparesImages.getImagesSimilarity(byte[], byte[] )
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [117] = public default void io.appium.java_client.android.nativekey.PressesKey.pressKeyCode(int)
	public void pressKeyCode( int key ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "pressKeyCode",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), key );
	}

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [118] = public default void io.appium.java_client.android.nativekey.PressesKey.pressKeyCode(int,java.lang.Integer)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [119] = public default void io.appium.java_client.android.nativekey.PressesKey.pressKey(io.appium.java_client.android.nativekey.KeyEvent)
	public void pressKey( io.appium.java_client.android.nativekey.KeyEvent keyEvent ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "pressKey",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), keyEvent );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [120] = public default void io.appium.java_client.android.nativekey.PressesKey.longPressKeyCode(int,java.lang.Integer)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [121] = public default void io.appium.java_client.android.nativekey.PressesKey.longPressKeyCode(int)
	public void longPressKeyCode( int key ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "longPressKeyCode",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), key );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [122] = public default void io.appium.java_client.android.nativekey.PressesKey.longPressKey(io.appium.java_client.android.nativekey.KeyEvent)
	public void longPressKey( io.appium.java_client.android.nativekey.KeyEvent keyEvent ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "longPressKey",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), keyEvent );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [123] = public default io.appium.java_client.android.connection.ConnectionState io.appium.java_client.android.connection.HasNetworkConnection.setConnection(io.appium.java_client.android.connection.ConnectionState )
	public io.appium.java_client.android.connection.ConnectionState setConnection( io.appium.java_client.android.connection.ConnectionState connectionState ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (io.appium.java_client.android.connection.ConnectionState)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "setConnection",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), connectionState );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [124] = public default io.appium.java_client.android.connection.ConnectionState io.appium.java_client.android.connection.HasNetworkConnection.getConnection()
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [125] = public default void io.appium.java_client.android.PushesFiles.pushFile(java.lang.String, byte[] )
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [126] = public default void io.appium.java_client.android.PushesFiles.pushFile(java.lang.String, java.io.File ) throws java.io.IOException
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [127] = public default void io.appium.java_client.android.StartsActivity.startActivity(io.appium.java_client.android.Activity )
	public void startActivity( io.appium.java_client.android.Activity activity ) {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "startActivity",
				java.lang.String.class, currentDriver.mainDriver.getClass().toString(), activity );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [128] = public default java.lang.String io.appium.java_client.android.StartsActivity.currentActivity()
	public java.lang.String currentActivity() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.lang.String)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "currentActivity", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [129] = public default java.lang.String io.appium.java_client.android.StartsActivity.getCurrentPackage()
	public java.lang.String getCurrentPackage() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.lang.String)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getCurrentPackage", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [130] = public default T io.appium.java_client.FindsByAndroidUIAutomator.findElementByAndroidUIAutomator(java.lang.String)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [131] = public default java.util.List<T> io.appium.java_client.FindsByAndroidUIAutomator.findElementsByAndroidUIAutomator(java.lang.String)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [132] = public default void io.appium.java_client.LocksDevice.lockDevice()
	public void lockDevice() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "lockDevice", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [133] = public default void io.appium.java_client.LocksDevice.lockDevice(java.time.Duration)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [134] = public default void io.appium.java_client.LocksDevice.unlockDevice()
	public void unlockDevice() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "unlockDevice", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [135] = public default boolean io.appium.java_client.LocksDevice.isDeviceLocked()
	public boolean isDeviceLocked() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (boolean)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "isDeviceLocked", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [136] = public default void io.appium.java_client.android.HasAndroidSettings.configuratorSetWaitForIdleTimeout(java.time.Duration)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [137] = public default void io.appium.java_client.android.HasAndroidSettings.configuratorSetWaitForSelectorTimeout(java.time.Duration)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [138] = public default void io.appium.java_client.android.HasAndroidSettings.configuratorSetScrollAcknowledgmentTimeout(java.time.Duration)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [139] = public default void io.appium.java_client.android.HasAndroidSettings.configuratorSetActionAcknowledgmentTimeout(java.time.Duration)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [140] = public default void io.appium.java_client.android.HasAndroidSettings.ignoreUnimportantViews(java.lang.Boolean)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [141] = public default void io.appium.java_client.android.HasAndroidSettings.configuratorSetKeyInjectionDelay(java.time.Duration)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [142] = public default void io.appium.java_client.HasSettings.setSetting(io.appium.java_client.Setting,java.lang.Object)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [143] = public default java.util.Map<java.lang.String, java.lang.Object> io.appium.java_client.HasSettings.getSettings()
	@SuppressWarnings("unchecked")
	public java.util.Map<java.lang.String, java.lang.Object> getSettings() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.util.Map<java.lang.String, java.lang.Object>)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(),
				"getSettings", (Class<?>)null, currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [144] = public default java.util.Map<java.lang.String, java.lang.String> io.appium.java_client.android.HasAndroidDeviceDetails.getSystemBars()
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [145] = public default java.lang.Long io.appium.java_client.android.HasAndroidDeviceDetails.getDisplayDensity()
	public java.lang.Long getDisplayDensity() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.lang.Long)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getDisplayDensity", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [146] = public default java.util.List<java.lang.String> io.appium.java_client.android.HasSupportedPerformanceDataType.getSupportedPerformanceDataTypes()
	@SuppressWarnings("unchecked")
	public java.util.List<java.lang.String> getSupportedPerformanceDataTypes() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.util.List<java.lang.String>)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getSupportedPerformanceDataTypes", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [147] = public default java.util.List<java.util.List<java.lang.Object>> io.appium.java_client.android.HasSupportedPerformanceDataType.getPerformanceData(java.lang.String,java.lang.String,int) throws java.lang.Exception
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [148] = public default void io.appium.java_client.android.AuthenticatesByFinger.fingerPrint(int)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [149] = public default boolean io.appium.java_client.HasOnScreenKeyboard.isKeyboardShown()
	public boolean isKeyboardShown() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (boolean)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "isKeyboardShown", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [150] = public default <T> java.lang.String io.appium.java_client.screenrecording.CanRecordScreen.startRecordingScreen(T)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [151] = public default java.lang.String io.appium.java_client.screenrecording.CanRecordScreen.startRecordingScreen()
	public java.lang.String startRecordingScreen() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.lang.String)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "startRecordingScreen", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [152] = public default java.lang.String io.appium.java_client.screenrecording.CanRecordScreen.stopRecordingScreen()
	public java.lang.String stopRecordingScreen() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		return (java.lang.String)pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "stopRecordingScreen", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [153] = public default <T> java.lang.String io.appium.java_client.screenrecording.CanRecordScreen.stopRecordingScreen(T)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [154] = public default void io.appium.java_client.android.SupportsSpecialEmulatorCommands.sendSMS(java.lang.String,java.lang.String)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [155] = public default void io.appium.java_client.android.SupportsSpecialEmulatorCommands.makeGsmCall(java.lang.String,io.appium.java_client.android.GsmCallActions)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [156] = public default void io.appium.java_client.android.SupportsSpecialEmulatorCommands.setGsmVoice(io.appium.java_client.android.GsmVoiceState)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [157] = public default void io.appium.java_client.android.SupportsSpecialEmulatorCommands.setNetworkSpeed(io.appium.java_client.android.NetworkSpeed)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [158] = public default void io.appium.java_client.android.SupportsSpecialEmulatorCommands.setPowerCapacity(int)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [159] = public default void io.appium.java_client.android.SupportsSpecialEmulatorCommands.setPowerAC(io.appium.java_client.android.PowerACState)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [160] = public default void io.appium.java_client.android.SupportsSpecialEmulatorCommands.setGsmSignalStrength(io.appium.java_client.android.GsmSignalStrength)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [161] = public default void io.appium.java_client.android.SupportsNetworkStateManagement.toggleWifi()
	public void toggleWifi() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "toggleWifi", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [162] = public default void io.appium.java_client.android.SupportsNetworkStateManagement.toggleData()
	public void toggleData() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "toggleData", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [163] = public default void io.appium.java_client.android.SupportsNetworkStateManagement.toggleAirplaneMode()
	public void toggleAirplaneMode() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "toggleAirplaneMode", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [164] = public default void io.appium.java_client.android.ListensToLogcatMessages.startLogcatBroadcast(java.lang.String,int)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [165] = public default void io.appium.java_client.android.ListensToLogcatMessages.startLogcatBroadcast(java.lang.String)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [166] = public default void io.appium.java_client.android.ListensToLogcatMessages.startLogcatBroadcast()
	public void startLogcatBroadcast() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "startLogcatBroadcast", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [167] = public default void io.appium.java_client.android.ListensToLogcatMessages.addLogcatMessagesListener(java.util.function.Consumer<java.lang.String>)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [168] = public default void io.appium.java_client.android.ListensToLogcatMessages.addLogcatErrorsListener(java.util.function.Consumer<java.lang.Throwable>)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [169] = public default void io.appium.java_client.android.ListensToLogcatMessages.addLogcatConnectionListener(java.lang.Runnable)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [170] = public default void io.appium.java_client.android.ListensToLogcatMessages.addLogcatDisconnectionListener(java.lang.Runnable)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [171] = public default void io.appium.java_client.android.ListensToLogcatMessages.removeAllLogcatListeners()
	public void removeAllLogcatListeners() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "removeAllLogcatListeners", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [172] = public default void io.appium.java_client.android.ListensToLogcatMessages.stopLogcatBroadcast()
	public void stopLogcatBroadcast() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "stopLogcatBroadcast", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [173] = public default void io.appium.java_client.android.HasAndroidClipboard.setClipboard(java.lang.String,io.appium.java_client.clipboard.ClipboardContentType,byte[])
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [174] = public default void io.appium.java_client.android.HasAndroidClipboard.setClipboardText(java.lang.String,java.lang.String)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [175] = public default void io.appium.java_client.clipboard.HasClipboard.setClipboard(io.appium.java_client.clipboard.ClipboardContentType,byte[])
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [176] = public default void io.appium.java_client.clipboard.HasClipboard.setClipboardText(java.lang.String)
	
	// ********************************************************************************************************************************************************
	// AndroidDriver		   [177] = public default java.lang.String io.appium.java_client.clipboard.HasClipboard.getClipboard(io.appium.java_client.clipboard.ClipboardContentType)

	// ********************************************************************************************************************************************************
	// AndroidDriver		   [178] = public default java.lang.String io.appium.java_client.clipboard.HasClipboard.getClipboardText()
	public void getClipboardText() {
		pureDriverDetails currentDriver = getCurrentDriverDetails();
		pureCore.callMethod( currentDriver.mainDriver, currentDriver.mainDriver.getClass(), "getClipboardText", (Class<?>)null,
				currentDriver.mainDriver.getClass().toString(), (Object)null );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//**************************************************************************************************************************
	/** Description : [Internal] The structure class for pureDriverDetails objects
	*/
	@SuppressWarnings("unused")
	public static class pureDriverDetails{

		/** *****************************************************************************************
		* Description : pureQuantumH8 pureDriverDetails datas structure
		*/
		private String              fictiveName            = null;  // A subjective name given to the Driver that may allow to find it
		//
		private Class<?>            driverClass            = null;  // Class.forName( ChromeDriverClass / FirefoxDriverClass / AndroidDriverClass / IOSDriverClass / ... );
		private Class<?>            driverSetupClass       = null;  // Class.forName( myChromeDriverSetupClass / myAndroidDriverSetupClass / ... );
		private Object              driverInstance         = null;  // driverSetupClass instance ( to call updateDriver / startDriver / quitDriver )
		// public Object            subDriverInstance      = null;
		public Object               mainDriver             = null;  // Driver itself
		public Class<?>             elementClass           = null;  // = WebElement, Androidelement, IOSElement objects
		protected String            driverName             = null;  // The driver name (chrome, firefox, ie, edge, opera, android, ios)
		protected String            driverVersion          = null;  // The driver version get from the driver itself (once started)
		protected boolean    DownloadFolderClearAtStartup  = false; // =true to clear the content of a download folder
		protected String            DownloadFolder         = null;  // specify a path for the folder where files will be downloaded
		protected String            UploadFolder           = null;  // specify a path from where files will be used to be sent to driver/device
		private Thread              startDriverThread      = null;  // The thread to use to start driver in differate mode 
		public boolean              Started                = false; // = true if driver is running, otherwise = false
		private boolean             Headless               = false; // = true if driver was set to use Headless mode (Jenkins)
		public List<pureCapability> driverCaps             = new ArrayList<pureCapability>(); // List of capabilities sent to the driver to setup it
		private String              windowHandle           = null;  // Driver window handle
		private List<String>        windowsHandles         = null;  // Driver windoww handles llist
		private Dimension           displayBox             = null;  // Position and dimensions of the driver view
		//
		public Proxy                proxy                  = null;
		// Appium datas
		public AppiumServiceBuilder appiumBuilder          = null;
		public String               appiumIPAdress         = "127.0.0.1"; // Default IP Adress if not specified
		public int                  appiumPort             = 4723;
		public boolean              appiumOverrideSession  = true;
		public String               appiumLogLevel         = "error";
		public AppiumDriverLocalService appiumService      = null;
		
		/** *****************************************************************************************
		 * Description : pureQuantumH8 pureDriverDetails Constructor
		*/
		public pureDriverDetails( String driverName, List<pureCapability> driverCaps, boolean directStart, String fictiveName ){
			this.createDriver( driverName, driverCaps, directStart, fictiveName );
		}
		
		//**************************************************************************************************************************
		/** Description : pureDriverDetails constructor to create driver setup data
		*
		* @param driverName         : ( chrome, firefox, ie, edge, opera, android, ios ) 
		* @param capabilities       : list of capabilities requested for the driver
		* @param autoStart          : =true for direct start, =false for no direct start
		* @param fictiveName        : give a fictive name for the driver (for example : 'driver to check emails' or 'main app view')
		*/
		private void createDriver( String driverName, List<pureCapability> driverCaps, boolean directStart, String fictiveName ) {
			System.out.println( "[PQ8] Setup new '" + driverName + "' Driver" );
			this.fictiveName = fictiveName;
			this.driverClass = this.setDriverClassFromName( driverName );          // example : org.openqa.selenium.chrome.ChromeDriver
			this.driverName = driverName;                                          // example : "chrome"
			this.driverCaps = driverCaps;
			this.driverSetupClass = this.setDriverSetupClassFromName( driverName );// example : sourceDrivers.myChromeDriver
			this.elementClass = this.setDriverElementClassFromName( driverName );  // example : org.openqa.selenium.WebElement
			if ( directStart == true ) { this.startDriver(); }                     // if directstart == true -> We directly start the driver		
		}
		
		//**************************************************************************************************************************
		/** Description : Start the current pureDriver defined in this. pureDriverDetail if not yet started
		*/
		public void startDriver() {
			System.out.println("start Thread");
			if ( this.startDriverThread == null && this.Started == false ) {
				this.startDriverThread = new Thread( new StartDriver( this ) );        // Create a new runnable thread to start driver
				this.startDriverThread.setDaemon( true );                              // Set it as "multi-thread"
				this.startDriverThread.start();
			}else {
				pureErrorHandler.castError( -1, "Error Occured during Driver Setup, Thread already defined", null, null );
			}
		}
		
		//**************************************************************************************************************************
		/** Description : As driver start is multi threaded, it is necessary to wait until driver correctly started
		* 
		* @return : isStarted  : = true if driver correctly started, otherwise false
		*/
		public boolean waitUntilStarted() {
			if ( this.startDriverThread != null ) {
				do {
					pureCore.sleep( 250 );
				}while( this.startDriverThread.isAlive() == true );
			}
			return this.isStarted();
		}

		//**************************************************************************************************************************
		/** Description : As driver start is multi threaded, it is necessary to wait until driver correctly started
		* 
		* @return : isStarted  : = true if driver correctly started, otherwise false
		*/
		public boolean isStarted() {
			return this.Started;
		}
		
		//**************************************************************************************************************************
		/** Description : Close all windows of the current driver and quit it. It also update pureDriverDetails datas so it can be opened again later if needed.
		*/
		public void quit() {
			if( this.Started == true ) {
				pureCore.callMethod( this.driverInstance, this.driverSetupClass, "quitDriver", pureDriverDetails.class, "pureDrivers.quitDriver()", this );
				this.startDriverThread = null;
				this.Started = false;
				this.windowHandle = null;
				this.windowsHandles = null;
				this.displayBox = null;
				this.mainDriver = null;
				this.driverInstance = null;
			}
		}
		
		/** *****************************************************************************************
		 * Description : pureQuantumH8 pureDriverDetails Set driver class name from driver Name
		*/
		private Class<?> setDriverClassFromName( String driverName ) {
			String feedback = null;
			Class<?> zeClass = null;
			if ( driverName != null ) {
				switch( driverName.toLowerCase() ) {
					case "chrome":                  feedback = WebDriverChromeClass;	       break;
					case "firefox":                 feedback = WebDriverFirefoxClass;          break;
					case "ie":                      feedback = WebDriverIEClass;               break;
					case "edge":                    feedback = WebDriverEdgeClass;             break;
					case "opera":                   feedback = WebDriverOperaClass;            break;
					case "android":                 feedback = AndroidDriverClass;             break;
					case "ios":                     feedback = IOSDriverClass;                 break;
					case "browserstackandroidapp" : feedback = AndroidDriverClass;             break;
					case "saucelabsandroidapp" :    feedback = AndroidDriverClass;             break;
					case "kobitonandroidapp" :      feedback = AndroidDriverClass;             break;		
					default:
						// ******************************************************************************************* ERREUR : unknown driver name
						break;	
				}
			}else {
				// ******************************************************************************************* ERREUR : driverName is a null pointer
			}
			try {
				zeClass = Class.forName( feedback );
			}catch( Exception e ) {
				zeClass = (Class<?>)null;
			}
			return zeClass;
		}
	
		/** *****************************************************************************************
		 * Description : pureQuantumH8 pureDriverDetails Set driver SETUP class name from driver Name
		*/
		private Class<?> setDriverSetupClassFromName( String driverName ) {
			String feedback = null;
			Class<?> zeClass = null;
			if ( driverName != null ) {
				switch( driverName.toLowerCase() ) {
					case "chrome":                  feedback = WebDriverSetupChromeClass;           break;
					case "firefox":                 feedback = WebDriverSetupFirefoxClass;          break;
					case "ie":                      feedback = WebDriverSetupIEClass;               break;
					case "edge":                    feedback = WebDriverSetupEdgeClass;             break;
					case "opera":                   feedback = WebDriverSetupOperaClass;            break;
					case "android":                 feedback = AndroidDriverSetupClass;             break;
					case "ios":                     feedback = IOSDriverSetupClass;                 break;
					case "browserstackandroidapp" : feedback = AndroidDriverSetupBrowserStackClass; break;
					case "saucelabsandroidapp" :    feedback = AndroidDriverSetupSauceLabsClass;    break;
					case "kobitonandroidapp" :      feedback = AndroidDriverSetupKobitonClass;      break;
					default:
						// ******************************************************************************************* ERREUR : unknown driver name
						break;	
				}
			}else {
				// ******************************************************************************************* ERREUR : driverName is a null pointer
			}
			try {
				zeClass = Class.forName( feedback );
			}catch( Exception e ) {
				zeClass = null;
			}
			return zeClass;
		}

		/** *****************************************************************************************
		 * Description : pureQuantumH8 pureDriverDetails Set driver Element(s) class name from driver Name
		*/
		private Class<?> setDriverElementClassFromName( String driverName ) {
			String feedback = null;
			Class<?> zeClass = null;
			if ( driverName != null ) {
				switch( driverName.toLowerCase() ) {
					case "chrome":                  feedback = WebelementClass;     break;
					case "firefox":                 feedback = WebelementClass;     break;
					case "ie":                      feedback = WebelementClass;     break;
					case "edge":                    feedback = WebelementClass;     break;
					case "opera":                   feedback = WebelementClass;     break;
					case "android":                 feedback = AndroidElementClass; break;
					case "ios":                     feedback = IOSElementClass;     break;
					case "browserstackandroidapp" : feedback = AndroidElementClass; break;
					case "saucelabsandroidapp" :    feedback = AndroidElementClass; break;
					case "kobitonandroidapp" :      feedback = AndroidElementClass; break;
					case "":
						// ******************************************************************************************* ERREUR : unknown driver name
						break;	
				}
			}else {
				// ******************************************************************************************* ERREUR : driverName is a null pointer
			}
			try {
				zeClass = Class.forName( feedback );
			}catch( Exception e ) {
				zeClass = null;
			}
			return zeClass;
		}
		
	}


	
	


	//**************************************************************************************************************************
	/** Description : Thread class to start a new pureDriver using pureDriverDetails
	*/
	private static class StartDriver implements Runnable{
		
		private pureDriverDetails currentDriver = null;
		
		//**************************************************************************************************************************
		/** Description : Setup runnable to use the correct pureDriverDetails
		* 
		* @param pureDriverDetails : The driver to start using Thread
		*/
		public StartDriver( pureDriverDetails pureDriverDetails ) {
			this.currentDriver = pureDriverDetails;
		}
		
		//**************************************************************************************************************************
		/** Description : runnable used by Thread.start(); methods
		*/
		public void run() {
			System.out.println( "[PQ8] Start new '" + this.currentDriver.driverName + "' Driver" );
			if ( this.currentDriver != null ) {
				// 1. We get the pureDriverDetails to use to start the driver
				// 2. We create an instance of the driver setup class. ( pureQuantumH8.sourceDriver.myXXXXdriver.class )
				try {
					this.currentDriver.driverInstance = this.currentDriver.driverSetupClass.newInstance();
				}catch( Exception except ) {
					pureErrorHandler.castError( pureErrorHandler.exceptions.getType( except ), null, "pureDrivers.setDriver()", "pureDrivers.constructor()" );
				}		
				// 3 We call the auto-update method
				pureCore.callMethod( this.currentDriver.driverInstance, this.currentDriver.driverSetupClass, "updateDriver", pureDriverDetails.class, "pureDrivers.updateDriver()", this.currentDriver );
				// *************************************************************************************** Set/Create Download Folder + Clear Download Folder at Startup
				if ( this.currentDriver.DownloadFolder != null && this.currentDriver.DownloadFolder.length() > 0 ) {
					if ( !aFiles.getPathExist( this.currentDriver.DownloadFolder ) ) {
						System.out.println( "[PQ8] Create default Download directory for '" + this.currentDriver.driverName + "' Driver" );
						aFiles.MakeDir( this.currentDriver.DownloadFolder );
					}else if ( this.currentDriver.DownloadFolderClearAtStartup ) {
						System.out.println( "[PQ8] Clear default Download directory content for '" + this.currentDriver.driverName + "' Driver" );
						String dlFiles[] = aFiles.getFolderContent( this.currentDriver.DownloadFolder );
						if ( dlFiles != null && dlFiles.length > 0 ) {
							for( int dlfLoop = 0; dlfLoop < dlFiles.length; dlfLoop ++ ){
								aFiles.DeleteFile( this.currentDriver.DownloadFolder + dlFiles[ dlfLoop ] );
							}
						}
					}
				}
				// *************************************************************************************** Set/Create Upload folder
				if ( this.currentDriver.UploadFolder != null && this.currentDriver.UploadFolder.length() > 0 ) {
					if ( aFiles.getPathExist( this.currentDriver.UploadFolder ) == false ) {
						System.out.println( "[PQ8] Clear default Upload directory for '" + this.currentDriver.driverName + "' Driver" );
						aFiles.MakeDir( this.currentDriver.UploadFolder );
					}
				}				
				// 4 Call the method to start the driver
				System.out.println( "[PQ8] Start the '" + this.currentDriver.driverName + "' Driver instance" );
				pureCore.callMethod( this.currentDriver.driverInstance, this.currentDriver.driverSetupClass, "startDriver", pureDriverDetails.class, "pureDrivers.startDriver()", this.currentDriver );
				System.out.println( "[PQ8] '" + this.currentDriver.driverName + "' Driver startup completed" );
				
			}else {
				// ******************************************************************************************* ERREUR : Requested driver to initialize is illegal
			}
		}
	}



}
