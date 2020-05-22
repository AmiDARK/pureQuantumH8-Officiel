package Tests;

import java.util.List;
import pureQuantumH8.pureConfig;
import pureQuantumH8.pureConfig.pureCapability;
import pureQuantumH8.pureCore;
import pureQuantumH8.pureCore.elementMethod;
import pureQuantumH8.pureDrivers;
import pureQuantumH8.pureDrivers.pureDriverDetails;
import pureQuantumH8.pureElement;

public class ChromeDriverTest{

	public static void main( String[] args ){
		
		List<pureCapability> driverCaps = pureConfig.loadCapabilitiesFromFile( "chromeSetup.json" );		
		
		pureDriverDetails myDriver = pureDrivers.newPureDriver( "chrome", driverCaps, false );
		pureDriverDetails myDriver2 = pureDrivers.newPureDriver( "chrome", driverCaps, false );
		myDriver.startDriver();
		myDriver2.startDriver();

		// You can wait that all drivers are opened and running ...
		// myDriver.waitUntilStarted();
		// myDriver2.waitUntilStarted();
		pureDriverDetails[] DriversToCheckForStartup = { myDriver, myDriver2 };
		pureDrivers.waitUntilDriversAreReady( DriversToCheckForStartup );
		
		// System.out.println( "Printed after both drivers startup completed" );

		pureDrivers.setCurrentDriver( 0 );
		pureDrivers.get( "http://www.amigaimpact.org" );
		pureElement aiForums = new pureElement( "forums", "button", elementMethod.xpath , "//a[@href='/forums/']", 0, null, 0 ); // Linked to driver #0
		aiForums.click();
		pureCore.sleep( 2000 );
		pureDrivers.isPageUpdated();

		pureDrivers.setCurrentDriver( 1 );
		pureDrivers.get( "http://www.amigaworld.net" );
		pureCore.sleep( 2000 );
		pureDrivers.get( "http://www.amigans.net" );
		pureCore.sleep( 2000 );
		pureElement nsForums = new pureElement( "Forums", "button", elementMethod.xpath, "//a[@title='Forums']", 0, null, -1 ); // -1 = uses current driver when action requested
		pureElement nsSearch = new pureElement( "Search", "Text Area", elementMethod.xpath, "//input[@id='term']", 0, null, 1 ); // Linked to driver #1
		pureElement nsSearchR = new pureElement( "Search", "Button", elementMethod.xpath, "//input[@id='submit']", 0, null ); // driverLink not specified = uses current driver
		nsForums.click();
		nsSearch.sendKeys( "AMOS" );
		nsSearchR.click();
		pureCore.sleep( 2000 );
		pureDrivers.isPageUpdated();

		myDriver2.quit();
		pureCore.demoWait( 5000 );
		myDriver.quit();
		
		
	}
	
	
	
}
